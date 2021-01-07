package com.epam.web.rotten.potatoes.model;

public class User implements Entity {
    public static final String TABLE = "users";
    private final Integer id;
    private final String login;
    private final String rights;
    private final Integer rate;
    private final boolean blocked;

    public User(Integer id, String login, String rights, Integer rate, boolean blocked) {
        this.id = id;
        this.login = login;
        this.rights = rights;
        this.rate = rate;
        this.blocked = blocked;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getRights() {
        return rights;
    }

    public Integer getRate() {
        return rate;
    }

    public boolean isBlocked() {
        return blocked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }

        User user = (User) o;

        if (isBlocked() != user.isBlocked()) {
            return false;
        }
        if (getId() != null ? !getId().equals(user.getId()) : user.getId() != null) {
            return false;
        }
        if (getLogin() != null ? !getLogin().equals(user.getLogin()) : user.getLogin() != null) {
            return false;
        }
        if (getRights() != null ? !getRights().equals(user.getRights()) : user.getRights() != null) {
            return false;
        }
        return getRate() != null ? getRate().equals(user.getRate()) : user.getRate() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getLogin() != null ? getLogin().hashCode() : 0);
        result = 31 * result + (getRights() != null ? getRights().hashCode() : 0);
        result = 31 * result + (getRate() != null ? getRate().hashCode() : 0);
        result = 31 * result + (isBlocked() ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", rights='" + rights + '\'' +
                ", rate=" + rate +
                ", blocked=" + blocked +
                '}';
    }
}