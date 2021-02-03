package com.mate;

import com.mate.lib.Injector;
import com.mate.model.CinemaHall;
import com.mate.model.Movie;
import com.mate.model.MovieSession;
import com.mate.model.User;
import com.mate.service.AuthenticationService;
import com.mate.service.CinemaHallService;
import com.mate.service.MovieService;
import com.mate.service.MovieSessionService;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Application {
    private static final Injector injector = Injector.getInstance("com.mate");
    private static final MovieService movieService =
            (MovieService) injector.getInstance(MovieService.class);
    private static final MovieSessionService movieSessionService =
            (MovieSessionService) injector.getInstance(MovieSessionService.class);
    private static final CinemaHallService cinemaHallService =
            (CinemaHallService) injector.getInstance(CinemaHallService.class);
    private static final AuthenticationService authenticationService =
            (AuthenticationService) injector.getInstance(AuthenticationService.class);

    public static void main(String[] args) {
        Movie movie = new Movie();
        movie.setTitle("Duck Stories");
        movieService.add(movie);
        movieService.getAll().forEach(System.out::println);

        CinemaHall cinemaHall = new CinemaHall();
        cinemaHall.setCapacity(50);
        cinemaHall.setDescription("Hall for real HTML programmers");
        cinemaHallService.add(cinemaHall);
        cinemaHallService.getAll().forEach(System.out::println);

        MovieSession movieSession = new MovieSession();
        movieSession.setCinemaHall(cinemaHall);
        movieSession.setMovie(movie);
        movieSession.setShowTime(LocalDateTime.now());
        movieSessionService.add(movieSession);
        movieSessionService.findAvailableSessions(movie.getId(), LocalDate.now())
                .forEach(System.out::println);

        User user = authenticationService.register("sample@gmail.com", "123");
        User authenticatedUser = authenticationService.login("sample@gmail.com", "123");
        System.out.println(user);
        System.out.println(authenticatedUser);
    }
}
