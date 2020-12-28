package com.epam.web.rotten.potatoes.model.dto;

import com.epam.web.rotten.potatoes.model.Entity;

public class UserCommentDto extends Entity {
    private final String login;
    private final String comment;

    public UserCommentDto(Integer id, String login, String comment) {
        super(id);
        this.login = login;
        this.comment = comment;
    }

    public String getLogin() {
        return login;
    }

    public String getComment() {
        return comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserCommentDto)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        UserCommentDto that = (UserCommentDto) o;

        if (getLogin() != null ? !getLogin().equals(that.getLogin()) : that.getLogin() != null) return false;
        return getComment() != null ? getComment().equals(that.getComment()) : that.getComment() == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getLogin() != null ? getLogin().hashCode() : 0);
        result = 31 * result + (getComment() != null ? getComment().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserComment{" +
                "login='" + login + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}
