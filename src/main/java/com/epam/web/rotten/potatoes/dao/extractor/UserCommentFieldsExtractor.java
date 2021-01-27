package com.epam.web.rotten.potatoes.dao.extractor;

import com.epam.web.rotten.potatoes.model.UserComment;

import java.util.HashMap;
import java.util.Map;

public class UserCommentFieldsExtractor implements FieldsExtractor<UserComment> {
    private Map<Integer, Object> fields = new HashMap<>();
    private static final Integer ID = 1;
    private static final int COMMENT = 2;
    private static final int FILM_ID = 3;
    private static final int USER_ID = 4;

    @Override
    public Map<Integer, Object> extract(UserComment userComment) {
        Integer id = userComment.getId();
        fields.put(ID, id);
        String comment = userComment.getComment();
        fields.put(COMMENT, comment);
        int filmId = userComment.getFilmId();
        fields.put(FILM_ID, filmId);
        int userId = userComment.getUserId();
        fields.put(USER_ID, userId);
        return fields;
    }
}
