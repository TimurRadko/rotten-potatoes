package com.epam.web.rotten.potatoes.dao.impl.action;

import com.epam.web.rotten.potatoes.dao.AbstractDao;
import com.epam.web.rotten.potatoes.dao.extractor.UserActionFieldsExtractor;
import com.epam.web.rotten.potatoes.dao.mapper.UserActionRowMapper;
import com.epam.web.rotten.potatoes.exceptions.DaoException;
import com.epam.web.rotten.potatoes.model.UserAction;

import java.sql.Connection;
import java.util.List;

public class UserActionDaoImpl extends AbstractDao<UserAction> implements UserActionDao {
    private static final UserActionRowMapper USER_ACTION_ROW_MAPPER = new UserActionRowMapper();
    private static final UserActionFieldsExtractor USER_ACTION_FIELDS_EXTRACTOR = new UserActionFieldsExtractor();
    private static final String TABLE_NAME = "user_actions";
    private static final String SAVE_USER_ACTION = "INSERT INTO user_actions(id, film_rate, review, user_id, film_id) VALUES(?,?,?,?,?)";

    private static final String GET_REVIEWS_BY_FILMS_ID = "SELECT * FROM user_actions WHERE film_id=?";
    private static final String GET_REVIEWS_BY_USER_ID = "SELECT * FROM user_actions WHERE user_id=?";
    private static final String UPDATE_USER_ACTION = "";

    public UserActionDaoImpl(Connection connection) {
        super(connection, USER_ACTION_ROW_MAPPER, USER_ACTION_FIELDS_EXTRACTOR,
                TABLE_NAME, SAVE_USER_ACTION, UPDATE_USER_ACTION);
    }

    @Override
    public List<UserAction> getReviewsByFilmId(Integer filmId) throws DaoException {
        return executeQuery(GET_REVIEWS_BY_FILMS_ID, filmId);
    }

    @Override
    public List<UserAction> getReviewsByUserId(Integer userId) throws DaoException {
        return executeQuery(GET_REVIEWS_BY_USER_ID, userId);
    }
}
