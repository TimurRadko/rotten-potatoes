package com.epam.web.rotten.potatoes.model;

public class Film extends Entity {
    public static final String TABLE = "films";
    private final String title;
    private final String director;
    private final String poster;
    private final double avgRate;

    public Film(Integer id, String title, String director, String poster, double avgRate) {
        super(id);
        this.title = title;
        this.director = director;
        this.poster = poster;
        this.avgRate = avgRate;
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

    public double getAvgRate() {
        return avgRate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Film)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        Film film = (Film) o;

        if (Double.compare(film.getAvgRate(), getAvgRate()) != 0) {
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
        int result = super.hashCode();
        long temp;
        result = 31 * result + (getTitle() != null ? getTitle().hashCode() : 0);
        result = 31 * result + (getDirector() != null ? getDirector().hashCode() : 0);
        result = 31 * result + (getPoster() != null ? getPoster().hashCode() : 0);
        temp = Double.doubleToLongBits(getAvgRate());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Film{" +
                "title='" + title + '\'' +
                ", director='" + director + '\'' +
                ", poster='" + poster + '\'' +
                ", avgRate=" + avgRate +
                '}';
    }
}
