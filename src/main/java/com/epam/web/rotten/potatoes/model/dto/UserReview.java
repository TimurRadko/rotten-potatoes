package com.epam.web.rotten.potatoes.model.dto;

import com.epam.web.rotten.potatoes.model.Entity;

public class UserReview extends Entity {
    private final String login;
    private final String review;
    private final double filmRate;

    public UserReview(Integer id, String login, String review, double filmRate) {
        super(id);
        this.login = login;
        this.review = review;
        this.filmRate = filmRate;
    }

    public String getLogin() {
        return login;
    }

    public String getReview() {
        return review;
    }

    public double getFilmRate() {
        return filmRate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserReview)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        UserReview that = (UserReview) o;

        if (Double.compare(that.getFilmRate(), getFilmRate()) != 0) {
            return false;
        }
        if (getLogin() != null ? !getLogin().equals(that.getLogin()) : that.getLogin() != null) {
            return false;
        }
        return getReview() != null ? getReview().equals(that.getReview()) : that.getReview() == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        long temp;
        result = 31 * result + (getLogin() != null ? getLogin().hashCode() : 0);
        result = 31 * result + (getReview() != null ? getReview().hashCode() : 0);
        temp = Double.doubleToLongBits(getFilmRate());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "UserReviewDto{" +
                "login='" + login + '\'' +
                ", review='" + review + '\'' +
                ", filmRate=" + filmRate +
                '}';
    }
}
