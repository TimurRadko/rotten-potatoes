package com.epam.web.rotten.potatoes.model;

public class Film implements Entity {
    public static final String TABLE = "films";
    private int id;
    private String title;
    private String director;
    private String poster;
    private double avgRate;

    public Film() {
    }

    public Film(int id, String title, String director, String poster, double avgRate) {
        this.id = id;
        this.title = title;
        this.director = director;
        this.poster = poster;
        this.avgRate = avgRate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public double getAvgRate() {
        return avgRate;
    }

    public void setAvgRate(double avgRate) {
        this.avgRate = avgRate;
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

        if (getId() != film.getId()) {
            return false;
        }
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
        int result;
        long temp;
        result = getId();
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
                "id=" + id +
                ", title='" + title + '\'' +
                ", director='" + director + '\'' +
                ", poster='" + poster + '\'' +
                ", avgRate=" + avgRate +
                '}';
    }
}
