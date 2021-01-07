package com.epam.web.rotten.potatoes.model;

public class Film implements Entity {
    public static final String TABLE = "films";
    private final Integer id;
    private final String title;
    private final String director;
    private final String poster;
    private final double defaultRate;

    public Film(Integer id, String title, String director, String poster, double defaultRate) {
        this.id = id;
        this.title = title;
        this.director = director;
        this.poster = poster;
        this.defaultRate = defaultRate;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDirector() {
        return director;
    }

    public String getPoster() {
        return poster;
    }

    public double getDefaultRate() {
        return defaultRate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Film)) {
            return false;
        }

        Film film = (Film) o;

        if (Double.compare(film.getDefaultRate(), getDefaultRate()) != 0) {
            return false;
        }
        if (getId() != null ? !getId().equals(film.getId()) : film.getId() != null) {
            return false;
        }
        if (getTitle() != null ? !getTitle().equals(film.getTitle()) : film.getTitle() != null) {
            return false;
        }
        if (getDirector() != null ? !getDirector().equals(film.getDirector()) : film.getDirector() != null) {
            return false;
        }
        return getPoster() != null ? getPoster().equals(film.getPoster()) : film.getPoster() == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getTitle() != null ? getTitle().hashCode() : 0);
        result = 31 * result + (getDirector() != null ? getDirector().hashCode() : 0);
        result = 31 * result + (getPoster() != null ? getPoster().hashCode() : 0);
        temp = Double.doubleToLongBits(getDefaultRate());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Film{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", director='" + director + '\'' +
                ", poster='" + poster + '\'' +
                ", defaultRate=" + defaultRate +
                '}';
    }
}
