package com.epam.web.rotten.potatoes.model;

public class UserAction extends Entity {
    public static final String TABLE = "user_actions";
    private final double filmRate;
    private final String review;
    private final int userId;
    private final int filmId;

    public UserAction(Integer id, double filmRate, String review, int userId, int filmId) {
        super(id);
        this.filmRate = filmRate;
        this.review = review;
        this.userId = userId;
        this.filmId = filmId;
    }

    public double getFilmRate() {
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
        if (!super.equals(o)) {
            return false;
        }

        UserAction that = (UserAction) o;

        if (Double.compare(that.getFilmRate(), getFilmRate()) != 0) {
            return false;
        }
        if (getUserId() != that.getUserId()) {
            return false;
        }
        if (getFilmId() != that.getFilmId()) {
            return false;
        }
        return getReview() != null ? getReview().equals(that.getReview()) : that.getReview() == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        long temp;
        temp = Double.doubleToLongBits(getFilmRate());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (getReview() != null ? getReview().hashCode() : 0);
        result = 31 * result + getUserId();
        result = 31 * result + getFilmId();
        return result;
    }

    @Override
    public String toString() {
        return "UserAction{" +
                "filmRate=" + filmRate +
                ", review='" + review + '\'' +
                ", userId=" + userId +
                ", filmId=" + filmId +
                '}';
    }
}
