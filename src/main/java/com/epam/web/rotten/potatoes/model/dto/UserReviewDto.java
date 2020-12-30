package com.epam.web.rotten.potatoes.model.dto;

import com.epam.web.rotten.potatoes.model.Entity;

public class UserReviewDto implements Entity {
    private final Integer id;
    private final String login;
    private final String review;
    private final double filmRate;

    public UserReviewDto(Integer id, String login, String review, double filmRate) {
        this.id = id;
        this.login = login;
        this.review = review;
        this.filmRate = filmRate;
    }

    @Override
    public Integer getId() {
        return id;
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
        if (!(o instanceof UserReviewDto)) {
            return false;
        }

        UserReviewDto that = (UserReviewDto) o;

        if (Double.compare(that.getFilmRate(), getFilmRate()) != 0) {
            return false;
        }
        if (getId() != null ? !getId().equals(that.getId()) : that.getId() != null) {
            return false;
        }
        if (getLogin() != null ? !getLogin().equals(that.getLogin()) : that.getLogin() != null) {
            return false;
        }
        return getReview() != null ? getReview().equals(that.getReview()) : that.getReview() == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getLogin() != null ? getLogin().hashCode() : 0);
        result = 31 * result + (getReview() != null ? getReview().hashCode() : 0);
        temp = Double.doubleToLongBits(getFilmRate());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "UserReviewDto{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", review='" + review + '\'' +
                ", filmRate=" + filmRate +
                '}';
    }
}
