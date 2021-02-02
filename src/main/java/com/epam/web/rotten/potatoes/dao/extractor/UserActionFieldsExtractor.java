package com.epam.web.rotten.potatoes.dao.extractor;

import com.epam.web.rotten.potatoes.model.UserAction;

import java.util.HashMap;
import java.util.Map;

public class UserActionFieldsExtractor implements FieldsExtractor<UserAction> {
    private Map<Integer, Object> fields = new HashMap<>();
    private static final Integer ID = 1;
    private static final int FILM_RATE = 2;
    private static final int REVIEW = 3;
    private static final int USER_ID = 4;
    private static final int FILM_ID = 5;

    @Override
    public Map<Integer, Object> extract(UserAction userAction) {
        Integer id = userAction.getId();
        fields.put(ID, id);
        double filmRate = userAction.getFilmRate();
        fields.put(FILM_RATE, filmRate);
        String review = userAction.getReview();
        fields.put(REVIEW, review);
        int userId = userAction.getUserId();
        fields.put(USER_ID, userId);
        int filmId = userAction.getFilmId();
        fields.put(FILM_ID, filmId);
        return fields;
    }
}