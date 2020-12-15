package com.epam.web.rotten.potatoes.dao;

import com.epam.web.rotten.potatoes.dao.extractor.FieldsExtractor;
import com.epam.web.rotten.potatoes.exceptions.DaoException;
import com.epam.web.rotten.potatoes.dao.mapper.RowMapper;
import com.epam.web.rotten.potatoes.model.Entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public abstract class AbstractDao<T extends Entity> implements Dao<T> {
    private final Connection connection;
    private final RowMapper<T> mapper;
    private final FieldsExtractor<T> fieldsExtractor;
    private final String tableName;
    private static final String ID = "id";
    private String query;

    protected AbstractDao(Connection connection, RowMapper<T> mapper, FieldsExtractor<T> fieldsExtractor, String tableName, String query) {
        this.connection = connection;
        this.mapper = mapper;
        this.fieldsExtractor = fieldsExtractor;
        this.tableName = tableName;
        this.query = query;
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

    private PreparedStatement create(String query, Object... params) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(query);
        for (int i = 1; i <= params.length; i++) {
            statement.setObject(i, params[i - 1]);
        }
        return statement;
    }

    @Override
    public Optional<T> getById(Integer id) throws DaoException {
        return executeForSingleResult("SELECT * FROM " + tableName + " WHERE id=?", id);
    }

    @Override
    public List<T> getAll() throws DaoException {
        return executeQuery("SELECT * FROM " + tableName);
    }

    @Override
    public void remove(int id) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        }
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

    @Override
    public int save(T item) throws DaoException {
        Map<Integer, Object> fields = fieldsExtractor.extract(item);
        return execute(query, fields);
    }

    private int execute(String query, Map<Integer, Object> fields) throws DaoException {
        int generatedKey;
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            for (Integer key : fields.keySet()) {
                preparedStatement.setObject(key, fields.get(key));
            }
            generatedKey = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        }
        return generatedKey;
    }
}
