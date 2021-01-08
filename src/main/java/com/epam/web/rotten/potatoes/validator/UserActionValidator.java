package com.epam.web.rotten.potatoes.validator;

import com.epam.web.rotten.potatoes.model.UserAction;

public class UserActionValidator implements Validator<UserAction> {
    private static final int MAX_LENGTH = 1000;

    @Override
    public boolean isValid(UserAction userAction) {
        if ((userAction.getFilmRate() < 0) || (userAction.getFilmRate() > 10)) {
            return false;
        }
        if ((userAction.getReview() == null) || (userAction.getReview().isEmpty()) || (userAction.getReview().length() > MAX_LENGTH)) {
            return false;
        }
        return true;
    }
}
