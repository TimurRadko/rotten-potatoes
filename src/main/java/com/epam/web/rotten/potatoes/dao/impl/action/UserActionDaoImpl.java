package com.epam.web.rotten.potatoes.dao.impl.action;

import com.epam.web.rotten.potatoes.dao.AbstractDao;
import com.epam.web.rotten.potatoes.dao.extractor.UserActionFieldsExtractor;
import com.epam.web.rotten.potatoes.dao.mapper.UserActionRowMapper;
import com.epam.web.rotten.potatoes.exceptions.DaoException;
import com.epam.web.rotten.potatoes.model.Film;
import com.epam.web.rotten.potatoes.model.UserAction;

import java.sql.Connection;
import java.util.List;

public class UserActionDaoImpl extends AbstractDao<UserAction> implements UserActionDao {
    private static final UserActionRowMapper USER_ACTION_ROW_MAPPER = new UserActionRowMapper();
    private static final UserActionFieldsExtractor USER_ACTION_FIELDS_EXTRACTOR = new UserActionFieldsExtractor();
    private static final String TABLE_NAME = "user_actions";
    private static final String INSERT_REVIEW_AND_RATE = "INSERT INTO user_actions(id, film_rate, review, user_id, film_id) VALUES(?,?,?,?,?)";

    private static final String GET_COMMENTS_BY_FILMS_ID = "SELECT * FROM user_actions WHERE film_id=?";

    public UserActionDaoImpl(Connection connection) {
        super(connection, USER_ACTION_ROW_MAPPER, USER_ACTION_FIELDS_EXTRACTOR, TABLE_NAME, INSERT_REVIEW_AND_RATE);
    }

    @Override
    public List<UserAction> getReviewsByFilmId(int id) throws DaoException {
        return executeQuery(GET_COMMENTS_BY_FILMS_ID, id);
    }
}
