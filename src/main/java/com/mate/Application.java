package com.mate;

import com.mate.lib.Injector;
import com.mate.model.Movie;
import com.mate.service.MovieService;

public class Application {
    private static final Injector injector = Injector.getInstance("com.mate");
    private static final MovieService movieService =
            (MovieService) injector.getInstance(MovieService.class);

    public static void main(String[] args) {
        Movie movie = new Movie();
        movie.setTitle("Duck Stories");
        movieService.add(movie);

        movieService.getAll().forEach(System.out::println);
    }
}
