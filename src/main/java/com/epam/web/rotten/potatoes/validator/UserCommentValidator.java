package com.epam.web.rotten.potatoes.validator;

import com.epam.web.rotten.potatoes.model.UserComment;

public class UserCommentValidator implements Validator<UserComment> {
    private static final int MAX_LENGTH = 200;

    @Override
    public boolean isValid(UserComment userComment) {
        if ((userComment.getComment() == null) || (userComment.getComment().isEmpty()) || (userComment.getComment().length() > MAX_LENGTH)) {
            return false;
        }
        return true;
    }
}
