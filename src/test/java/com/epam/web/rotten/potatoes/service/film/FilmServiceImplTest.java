package com.epam.web.rotten.potatoes.service.film;

import com.epam.web.rotten.potatoes.dao.helper.DaoHelper;
import com.epam.web.rotten.potatoes.dao.helper.DaoHelperFactory;
import com.epam.web.rotten.potatoes.dao.impl.film.FilmDao;
import com.epam.web.rotten.potatoes.exceptions.DaoException;
import com.epam.web.rotten.potatoes.exceptions.ServiceException;
import com.epam.web.rotten.potatoes.model.Film;
import org.junit.Test;
import org.mockito.Mockito;
import org.testng.Assert;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

public class FilmServiceImplTest {
    private static final int FIRST_FILM_ID_VALUE = 1;
    private static final String TITLE_VALUE = "Valid Title";
    private static final String DIRECTOR_VALUE = "Valid Director";
    private static final String POSTER_VALUE = "Valid Poster";
    private static final int DEFAULT_RATE_VALUE = 10;
    private static final Film FIRST_FILM = new Film(FIRST_FILM_ID_VALUE, TITLE_VALUE, DIRECTOR_VALUE,
            POSTER_VALUE, DEFAULT_RATE_VALUE);

    private static final int SECOND_FILM_ID_VALUE = 2;
    private static final Film SECOND_FILM = new Film(SECOND_FILM_ID_VALUE, TITLE_VALUE, DIRECTOR_VALUE,
            POSTER_VALUE, DEFAULT_RATE_VALUE);

    private static final int THIRD_FILM_ID_VALUE = 3;
    private static final Film THIRD_FILM = new Film(THIRD_FILM_ID_VALUE, TITLE_VALUE, DIRECTOR_VALUE,
            POSTER_VALUE, DEFAULT_RATE_VALUE);

    private static final int FOURTH_FILM_ID_VALUE = 4;
    private static final Film FOURTH_FILM = new Film(FOURTH_FILM_ID_VALUE, TITLE_VALUE, DIRECTOR_VALUE,
            POSTER_VALUE, DEFAULT_RATE_VALUE);

    private static final int FILM_ID_FOR_FINDING = 1;
    private static final int AMOUNT_VALUE = 2;
    private static final int FROM_VALUE = 1;

    private static final List<Film> FULL_FILM_LIST = Arrays.asList(FIRST_FILM, SECOND_FILM, THIRD_FILM, FOURTH_FILM);
    private static final List<Film> EXPECTED_FIRST_PAGE_LIST = Arrays.asList(FIRST_FILM, SECOND_FILM);
    private static final List<Film> EMPTY_FILM_LIST = new ArrayList<>();
    private static final String INVALID_DIRECTOR_VALUE = "Invalid Director";

    @Test
    public void testGetPageShouldReturnCorrectFilmListWhenParametersAreValid() throws DaoException, ServiceException {
        //given
        DaoHelper daoHelper = Mockito.mock(DaoHelper.class);
        DaoHelperFactory daoHelperFactory = Mockito.mock(DaoHelperFactory.class);
        FilmDao filmDao = Mockito.mock(FilmDao.class);

        //when
        when(daoHelperFactory.create()).thenReturn(daoHelper);
        when(daoHelper.createFilmDao()).thenReturn(filmDao);
        when(filmDao.findFilmsPartList(AMOUNT_VALUE, FROM_VALUE)).thenReturn(EXPECTED_FIRST_PAGE_LIST);
        FilmService filmService = new FilmServiceImpl(daoHelperFactory);
        //then
        List<Film> actualFilmList = filmService.getPage(FROM_VALUE, AMOUNT_VALUE);

        Assert.assertEquals(EXPECTED_FIRST_PAGE_LIST, actualFilmList);
    }

    @Test
    public void testGetFilmByIdShouldReturnFilmWhenFilmExists() throws DaoException, ServiceException {
        //given
        DaoHelper daoHelper = Mockito.mock(DaoHelper.class);
        DaoHelperFactory daoHelperFactory = Mockito.mock(DaoHelperFactory.class);
        FilmDao filmDao = Mockito.mock(FilmDao.class);

        //when
        when(daoHelperFactory.create()).thenReturn(daoHelper);
        when(daoHelper.createFilmDao()).thenReturn(filmDao);
        when(filmDao.findById(FILM_ID_FOR_FINDING)).thenReturn(Optional.of(FIRST_FILM));
        FilmService filmService = new FilmServiceImpl(daoHelperFactory);
        //then
        Optional<Film> actualFilm = filmService.getFilmById(FILM_ID_FOR_FINDING);
        Assert.assertEquals(Optional.of(FIRST_FILM), actualFilm);
    }

    @Test
    public void testGetFilmByIdShouldReturnEmptyOptionalWhenFilmNotExists() throws DaoException, ServiceException {
        //given
        DaoHelper daoHelper = Mockito.mock(DaoHelper.class);
        DaoHelperFactory daoHelperFactory = Mockito.mock(DaoHelperFactory.class);
        FilmDao filmDao = Mockito.mock(FilmDao.class);

        //when
        when(daoHelperFactory.create()).thenReturn(daoHelper);
        when(daoHelper.createFilmDao()).thenReturn(filmDao);
        when(filmDao.findById(FILM_ID_FOR_FINDING)).thenReturn(Optional.empty());
        FilmService filmService = new FilmServiceImpl(daoHelperFactory);

        //then
        Optional<Film> actualFilm = filmService.getFilmById(FILM_ID_FOR_FINDING);
        Assert.assertEquals(Optional.empty(), actualFilm);
    }

    @Test
    public void testGetFilmsByDirectorShouldReturnFilmListWhenDbContainedFilmsThisDirector() throws DaoException, ServiceException {
        //given
        DaoHelper daoHelper = Mockito.mock(DaoHelper.class);
        DaoHelperFactory daoHelperFactory = Mockito.mock(DaoHelperFactory.class);
        FilmDao filmDao = Mockito.mock(FilmDao.class);

        //when
        when(daoHelperFactory.create()).thenReturn(daoHelper);
        when(daoHelper.createFilmDao()).thenReturn(filmDao);
        when(filmDao.findFilmByDirector(DIRECTOR_VALUE)).thenReturn(FULL_FILM_LIST);
        FilmService filmService = new FilmServiceImpl(daoHelperFactory);

        //then
        List<Film> actualFilmList = filmService.getFilmByDirector(DIRECTOR_VALUE);
        Assert.assertEquals(FULL_FILM_LIST, actualFilmList);
    }

    @Test
    public void testGetFilmsByDirectorShouldReturnEmptyFilmListWhenDbNotContainedFilmsTisDirector() throws DaoException, ServiceException {
        //given
        DaoHelper daoHelper = Mockito.mock(DaoHelper.class);
        DaoHelperFactory daoHelperFactory = Mockito.mock(DaoHelperFactory.class);
        FilmDao filmDao = Mockito.mock(FilmDao.class);

        //when
        when(daoHelperFactory.create()).thenReturn(daoHelper);
        when(daoHelper.createFilmDao()).thenReturn(filmDao);
        when(filmDao.findFilmByDirector(INVALID_DIRECTOR_VALUE)).thenReturn(EMPTY_FILM_LIST);
        FilmService filmService = new FilmServiceImpl(daoHelperFactory);

        //then
        List<Film> actualFilmList = filmService.getFilmByDirector(INVALID_DIRECTOR_VALUE);
        Assert.assertEquals(EMPTY_FILM_LIST, actualFilmList);
    }

    @Test
    public void testSaveShouldReturnIdWhenFilmIsSaved() throws DaoException, ServiceException {
        //given
        DaoHelper daoHelper = Mockito.mock(DaoHelper.class);
        DaoHelperFactory daoHelperFactory = Mockito.mock(DaoHelperFactory.class);
        FilmDao filmDao = Mockito.mock(FilmDao.class);

        //when
        when(daoHelperFactory.create()).thenReturn(daoHelper);
        when(daoHelper.createFilmDao()).thenReturn(filmDao);
        when(filmDao.save(FIRST_FILM)).thenReturn(Optional.of(FIRST_FILM_ID_VALUE));
        FilmService filmService = new FilmServiceImpl(daoHelperFactory);

        //then
        Optional<Integer> actualFilmId = filmService.save(FIRST_FILM);
        Assert.assertEquals(Optional.of(FIRST_FILM_ID_VALUE), actualFilmId);
    }

    @Test
    public void testGetCountRowsShouldReturnCorrectNumberOfRows() throws SQLException, ServiceException {
        //given
        DaoHelper daoHelper = Mockito.mock(DaoHelper.class);
        DaoHelperFactory daoHelperFactory = Mockito.mock(DaoHelperFactory.class);
        FilmDao filmDao = Mockito.mock(FilmDao.class);

        //when
        when(daoHelperFactory.create()).thenReturn(daoHelper);
        when(daoHelper.createFilmDao()).thenReturn(filmDao);
        when(filmDao.countRows()).thenReturn(4);
        FilmService filmService = new FilmServiceImpl(daoHelperFactory);

        //then
        int actualCount = filmService.getCountRows(AMOUNT_VALUE);
        Assert.assertEquals(2, actualCount);
    }
}