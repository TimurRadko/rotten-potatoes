package com.epam.web.rotten.potatoes.dao.impl.comment;

import com.epam.web.rotten.potatoes.dao.AbstractDao;
import com.epam.web.rotten.potatoes.dao.extractor.UserCommentFieldsExtractor;
import com.epam.web.rotten.potatoes.dao.mapper.UserCommentRowMapper;
import com.epam.web.rotten.potatoes.exceptions.DaoException;
import com.epam.web.rotten.potatoes.model.UserComment;

import java.sql.Connection;
import java.util.List;

public class UserCommentDaoImpl extends AbstractDao<UserComment> implements UserCommentDao {
    private static final UserCommentRowMapper USER_COMMENT_ROW_MAPPER = new UserCommentRowMapper();
    private static final UserCommentFieldsExtractor USER_COMMENT_FIELDS_EXTRACTOR = new UserCommentFieldsExtractor();
    private static final String TABLE_NAME = "user_comments";
    private static final String SAVE_USER_COMMENT = "INSERT INTO user_comments(id, comment, film_id, user_id) VALUES(?,?,?,?)";
    private static final String UPDATE_USER_COMMENT = "";
    private static final String GET_COMMENTS_BY_FILM_ID = "SELECT * FROM user_comments WHERE film_id=?";
    private static final String GET_COMMENTS_BY_USER_ID = "SELECT * FROM user_comments WHERE user_id=?";

    public UserCommentDaoImpl(Connection connection) {
        super(connection, USER_COMMENT_ROW_MAPPER, USER_COMMENT_FIELDS_EXTRACTOR,
                TABLE_NAME, SAVE_USER_COMMENT, UPDATE_USER_COMMENT);
    }

    @Override
    public List<UserComment> findCommentsByFilmId(Integer filmId) throws DaoException {
        return executeQuery(GET_COMMENTS_BY_FILM_ID, filmId);
    }

    @Override
    public List<UserComment> findCommentsByUserId(Integer userId) throws DaoException {
        return executeQuery(GET_COMMENTS_BY_USER_ID, userId);
    }
}