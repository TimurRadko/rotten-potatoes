package com.epam.web.rotten.potatoes.dao;

import com.epam.web.rotten.potatoes.dao.extractor.FieldsExtractor;
import com.epam.web.rotten.potatoes.exceptions.DaoException;
import com.epam.web.rotten.potatoes.dao.mapper.RowMapper;
import com.epam.web.rotten.potatoes.model.Entity;

import java.sql.*;
import java.util.*;

public abstract class AbstractDao<T extends Entity> implements Dao<T> {
    private final Connection connection;
    private final RowMapper<T> mapper;
    private final FieldsExtractor<T> fieldsExtractor;
    private final String tableName;
    private String saveQuery;
    private String updateQuery;

    private static final String GET_BY_ID = "SELECT * FROM %s WHERE id = ?";
    private static final String GET_ALL = "SELECT * FROM %s";
    private static final String REMOVE_BY_ID = "DELETE FROM %s WHERE id = ?";
    private static final String GET_ROWS_COUNT = "SELECT COUNT(*) FROM %s";

    protected AbstractDao(Connection connection, RowMapper<T> mapper, FieldsExtractor<T> fieldsExtractor,
                          String tableName, String saveQuery, String updateQuery) {
        this.connection = connection;
        this.mapper = mapper;
        this.fieldsExtractor = fieldsExtractor;
        this.tableName = tableName;
        this.saveQuery = saveQuery;
        this.updateQuery = updateQuery;
    }

    protected List<T> executeQuery(String query, Object... params) throws DaoException {
        try (PreparedStatement statement = create(query, params);
             ResultSet resultSet = statement.executeQuery()) {
            List<T> entities = new ArrayList<>();
            while (resultSet.next()) {
                T entity = mapper.map(resultSet);
                entities.add(entity);
            }
            return entities;
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        }
    }

    @Override
    public int countRows() throws SQLException {
        String query = String.format(GET_ROWS_COUNT, tableName);
        PreparedStatement statement = createStatement(query);
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        return resultSet.getInt(1);
    }

    private PreparedStatement createStatement(String query, Object... params) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(query);
        for (int i = 1; i <= params.length; i++) {
            statement.setObject(i, params[i - 1]);
        }
        return statement;
    }

    private PreparedStatement create(String query, Object... params) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(query);
        for (int i = 1; i <= params.length; i++) {
            statement.setObject(i, params[i - 1]);
        }
        return statement;
    }

    @Override
    public Optional<T> findById(Integer id) throws DaoException {
        String query = String.format(GET_BY_ID, tableName);
        return executeForSingleResult(query, id);
    }

    @Override
    public List<T> findAll() throws DaoException {
        String query = String.format(GET_ALL, tableName);
        return executeQuery(query);
    }

    @Override
    public void remove(int id) throws DaoException {
        String query = String.format(REMOVE_BY_ID, tableName);
        Map<Integer, Object> fields = new HashMap<>();
        fields.put(1, id);
        executeUpdate(query, fields);
    }

    @Override
    public Optional<Integer> save(T item) throws DaoException {
        Map<Integer, Object> fields = fieldsExtractor.extract(item);
        Integer id = item.getId();
        String query;
        if (id == null) {
            query = saveQuery;
        } else {
            query = updateQuery;
            fields = changeParameterOrder(fields);
        }
        return executeUpdate(query, fields);
    }

    private Map<Integer, Object> changeParameterOrder(Map<Integer, Object> fields) {
        Map<Integer, Object> newFields = new HashMap<>();
        int size = fields.size();
        Object idField = fields.get(1);
        fields.remove(1);
        newFields.put(size, idField);
        for (Map.Entry<Integer, Object> pair : fields.entrySet()) {
            Integer key = pair.getKey();
            Object object = pair.getValue();
            newFields.put(key - 1, object);
        }
        return newFields;
    }

    protected Optional<T> executeForSingleResult(String query, Object... params) throws DaoException {
        List<T> items = executeQuery(query, params);
        if (items.size() == 1) {
            return Optional.of(items.get(0));
        } else if (items.size() > 1) {
            throw new IllegalArgumentException("More than one result");
        } else {
            return Optional.empty();
        }
    }

    private Optional<Integer> executeUpdate(String query, Map<Integer, Object> fields) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            for (Integer key : fields.keySet()) {
                preparedStatement.setObject(key, fields.get(key));
            }
            preparedStatement.executeUpdate();

            ResultSet result = preparedStatement.getGeneratedKeys();
            if (result.next()) {
                Integer id = result.getInt(1);
                return Optional.of(id);
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        }
    }
}
