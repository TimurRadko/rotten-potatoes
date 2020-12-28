package com.epam.web.rotten.potatoes.model;

public class UserComment extends Entity {
    public static final String TABLE = "user_comments";
    private final String comment;
    private final int userId;
    private final int filmId;

    public UserComment(Integer id, String comment, int userId, int filmId) {
        super(id);
        this.comment = comment;
        this.userId = userId;
        this.filmId = filmId;
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
        if (!super.equals(o)) {
            return false;
        }

        UserComment that = (UserComment) o;

        if (getUserId() != that.getUserId()) {
            return false;
        }
        if (getFilmId() != that.getFilmId()) {
            return false;
        }
        return getComment() != null ? getComment().equals(that.getComment()) : that.getComment() == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getComment() != null ? getComment().hashCode() : 0);
        result = 31 * result + getUserId();
        result = 31 * result + getFilmId();
        return result;
    }

    @Override
    public String toString() {
        return "UserComment{" +
                "comment='" + comment + '\'' +
                ", userId=" + userId +
                ", filmId=" + filmId +
                '}';
    }
}
