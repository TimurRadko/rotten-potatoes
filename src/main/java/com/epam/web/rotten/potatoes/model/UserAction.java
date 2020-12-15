package com.epam.web.rotten.potatoes.model;

public class UserAction implements Entity {
    private int id;
    private double filmRate;
    private String review;
    private int userId;
    private int filmId;

    public UserAction() {
    }

    public UserAction(int id, double filmRate, String review, int userId, int filmId) {
        this.id = id;
        this.filmRate = filmRate;
        this.review = review;
        this.userId = userId;
        this.filmId = filmId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getFilmRate() {
        return filmRate;
    }

    public void setFilmRate(double filmRate) {
        this.filmRate = filmRate;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getFilmId() {
        return filmId;
    }

    public void setFilmId(int filmId) {
        this.filmId = filmId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserAction)) {
            return false;
        }

        UserAction that = (UserAction) o;

        if (getId() != that.getId()) {
            return false;
        }
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
        int result;
        long temp;
        result = getId();
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
                "id=" + id +
                ", filmRate=" + filmRate +
                ", review='" + review + '\'' +
                ", userId=" + userId +
                ", filmId=" + filmId +
                '}';
    }
}
