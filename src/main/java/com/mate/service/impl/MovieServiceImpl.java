package com.mate.service.impl;

import com.mate.dao.MovieDao;
import com.mate.lib.Inject;
import com.mate.lib.Service;
import com.mate.model.Movie;
import com.mate.service.MovieService;
import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {
    @Inject
    private MovieDao movieDao;

    @Override
    public Movie add(Movie movie) {
        return movieDao.add(movie);
    }

    @Override
    public List<Movie> getAll() {
        return movieDao.getAll();
    }
}
