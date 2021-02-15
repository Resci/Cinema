package com.mate.controller;

import com.mate.dto.MovieSessionRequestDto;
import com.mate.dto.MovieSessionResponseDto;
import com.mate.model.MovieSession;
import com.mate.service.MovieSessionService;
import com.mate.util.mapper.MovieSessionMapper;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie-sessions")
public class MovieSessionController {
    public static final String DATE_PATTERN = "dd.MM.yyyy";
    private final MovieSessionService movieSessionService;
    private final MovieSessionMapper movieSessionMapper;

    public MovieSessionController(MovieSessionService movieSessionService,
                                  MovieSessionMapper movieSessionMapper) {
        this.movieSessionService = movieSessionService;
        this.movieSessionMapper = movieSessionMapper;
    }

    @PostMapping
    public MovieSessionResponseDto add(@RequestBody MovieSessionRequestDto requestDto) {
        MovieSession movieSession;
        movieSession = movieSessionService.add(movieSessionMapper.mapToObj(requestDto));
        return movieSessionMapper.mapToDto(movieSession);
    }

    @GetMapping("/available")
    public List<MovieSessionResponseDto> getAll(@RequestParam Long movieId,
                                                @RequestParam
                                                @DateTimeFormat(pattern = DATE_PATTERN)
                                                        LocalDate date) {
        return movieSessionService.findAvailableSessions(movieId, date)
                .stream()
                .map(movieSessionMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    public MovieSessionResponseDto update(@PathVariable Long id,
                                          @RequestBody MovieSessionRequestDto requestDto) {
        MovieSession movieSession = movieSessionMapper.mapToObj(requestDto);
        movieSession.setId(id);
        movieSessionService.update(movieSession);
        return movieSessionMapper.mapToDto(movieSession);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        try {
            movieSessionService.delete(id);
        } catch (Exception e) {
            return e.getMessage();
        }
        return "Movie session with id " + id + " deleted successful";
    }
}
