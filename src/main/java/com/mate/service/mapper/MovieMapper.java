package com.mate.service.mapper;

import com.mate.dto.request.MovieRequestDto;
import com.mate.dto.response.MovieResponseDto;
import com.mate.model.Movie;
import org.springframework.stereotype.Component;

@Component
public class MovieMapper implements RequestDtoMapper<MovieRequestDto, Movie>,
        ResponseDtoMapper<MovieResponseDto, Movie> {
    @Override
    public Movie mapToObj(MovieRequestDto dto) {
        Movie movie = new Movie();
        movie.setTitle(dto.getMovieTitle());
        movie.setDescription(dto.getMovieDescription());
        return movie;
    }

    @Override
    public MovieResponseDto mapToDto(Movie movie) {
        MovieResponseDto responseDto = new MovieResponseDto();
        responseDto.setMovieId(movie.getId());
        responseDto.setMovieTitle(movie.getTitle());
        responseDto.setMovieDescription(movie.getDescription());
        return responseDto;
    }
}
