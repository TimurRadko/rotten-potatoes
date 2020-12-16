package com.epam.web.rotten.potatoes.dao.extractor;

import com.epam.web.rotten.potatoes.model.Film;

import java.util.HashMap;
import java.util.Map;

public class FilmFieldsExtractor implements FieldsExtractor<Film> {
    private Map<Integer, Object> fields = new HashMap<>();
    private static final int ID = 1;
    private static final int TITLE = 2;
    private static final int DIRECTOR = 3;
    private static final int POSTER = 4;
    private static final int AVG_RATE = 5;

    @Override
    public Map<Integer, Object> extract(Film film) {
        int id = film.getId();
        fields.put(ID, id);
        String title = film.getTitle();
        fields.put(TITLE, title);
        String director = film.getDirector();
        fields.put(DIRECTOR, director);
        String poster = film.getPoster();
        fields.put(POSTER, poster);
        double avgRate = film.getAvgRate();
        fields.put(AVG_RATE, avgRate);
        return fields;
    }
}
