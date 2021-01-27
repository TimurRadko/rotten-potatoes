package com.epam.web.rotten.potatoes.model;

public class UserAction implements Entity {
    public static final String TABLE = "user_actions";
    private final Integer id;
    private final int filmRate;
    private final String review;
    private final int userId;
    private final int filmId;

    public UserAction(Integer id, int filmRate, String review, int userId, int filmId) {
        this.id = id;
        this.filmRate = filmRate;
        this.review = review;
        this.userId = userId;
        this.filmId = filmId;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public int getFilmRate() {
        return filmRate;
    }

    public String getReview() {
        return review;
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
        if (!(o instanceof UserAction)) {
            return false;
        }

        UserAction action = (UserAction) o;

        if (getFilmRate() != action.getFilmRate()) {
            return false;
        }
        if (getUserId() != action.getUserId()) {
            return false;
        }
        if (getFilmId() != action.getFilmId()) {
            return false;
        }
        if (getId() != null ? !getId().equals(action.getId()) : action.getId() != null) {
            return false;
        }
        return getReview() != null ? getReview().equals(action.getReview()) : action.getReview() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + getFilmRate();
        result = 31 * result + (getReview() != null ? getReview().hashCode() : 0);
        result = 31 * result + getUserId();
        result = 31 * result + getFilmId();
        return result;
    }

    @Override
    public String toString() {
        return "UserAction{" +
                "id=" + id +
                ", filmRate=" + filmRate +
                ", review='" + review + '\'' +
                ", userId=" + userId +
                ", filmId=" + filmId +
                '}';
    }
}