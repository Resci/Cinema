package com.mate.service;

import com.mate.model.Movie;
import java.util.List;

public interface MovieService {
    Movie add(Movie movie);
    
    List<Movie> getAll();
}
