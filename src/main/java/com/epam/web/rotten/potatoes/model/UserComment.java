package com.epam.web.rotten.potatoes.model;

public class UserComment implements Entity {
    public static final String TABLE = "user_comments";
    private final Integer id;
    private final String comment;
    private final int filmId;
    private final int userId;


    public UserComment(Integer id, String comment, int filmId, int userId) {
        this.id = id;
        this.comment = comment;
        this.filmId = filmId;
        this.userId = userId;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public String getComment() {
        return comment;
    }

    public int getUserId() {
        return userId;
    }

    public int getFilmId() {
        return filmId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserComment)) {
            return false;
        }

        UserComment that = (UserComment) o;

        if (getUserId() != that.getUserId()) {
            return false;
        }
        if (getFilmId() != that.getFilmId()) {
            return false;
        }
        if (getId() != null ? !getId().equals(that.getId()) : that.getId() != null) {
            return false;
        }
        return getComment() != null ? getComment().equals(that.getComment()) : that.getComment() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getComment() != null ? getComment().hashCode() : 0);
        result = 31 * result + getUserId();
        result = 31 * result + getFilmId();
        return result;
    }

    @Override
    public String toString() {
        return "UserComment{" +
                "id=" + id +
                ", comment='" + comment + '\'' +
                ", userId=" + userId +
                ", filmId=" + filmId +
                '}';
    }
}
