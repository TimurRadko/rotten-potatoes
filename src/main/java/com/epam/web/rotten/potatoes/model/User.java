package com.epam.web.rotten.potatoes.model;

public class User extends Entity {
    public static final String TABLE = "users";
    private final String login;
    private final String password;
    private final Rights rights;
    private final double rate;
    private final boolean blocked;

    public User(Integer id, String login, String password, Rights rights, double rate, boolean blocked) {
        super(id);
        this.login = login;
        this.password = password;
        this.rights = rights;
        this.rate = rate;
        this.blocked = blocked;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public Rights getRights() {
        return rights;
    }

    public double getRate() {
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
        if (!super.equals(o)) {
            return false;
        }

        User user = (User) o;

        if (Double.compare(user.getRate(), getRate()) != 0) {
            return false;
        }
        if (isBlocked() != user.isBlocked()) {
            return false;
        }
        if (getLogin() != null ? !getLogin().equals(user.getLogin()) : user.getLogin() != null) {
            return false;
        }
        if (getPassword() != null ? !getPassword().equals(user.getPassword()) : user.getPassword() != null) {
            return false;
        }
        return getRights() == user.getRights();
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        long temp;
        result = 31 * result + (getLogin() != null ? getLogin().hashCode() : 0);
        result = 31 * result + (getPassword() != null ? getPassword().hashCode() : 0);
        result = 31 * result + (getRights() != null ? getRights().hashCode() : 0);
        temp = Double.doubleToLongBits(getRate());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (isBlocked() ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", rights=" + rights +
                ", rate=" + rate +
                ", blocked=" + blocked +
                '}';
    }
}
