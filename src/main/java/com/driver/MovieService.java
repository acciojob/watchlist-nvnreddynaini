package com.driver;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    public void addMovie(Movie movie){
        movieRepository.addMovieFromDb(movie);
    }

    public void addDirector(Director director){
        movieRepository.addDirectorFromDb(director);
    }

    public void addMovieDirectorPair(String movie, String director){
        movieRepository.addMovieDirectorPairFromDb(movie,director);
    }

    public String getDirectorNameByMovieName(String movieName){
        return movieRepository.getDirectorNameByMovieNameFromDb(movieName);
    }

    public Movie getMovieByName(String movieName){
        return movieRepository.getMovieByNameFromDb(movieName);
    }

    public Director getDirectorByName(String directorName){
        return movieRepository.getDirectorByNameFromDb(directorName);
    }

    public List<String> getMoviesByDirectorName(String directorName){
        return movieRepository.getMoviesByDirectorNameFromDb(directorName);
    }

    public List<String> findAllMovies(){
        return movieRepository.findAllMoviesFromDb();
    }

    public void deleteDirector(String director){
        movieRepository.deleteDirectorFromDb(director);
    }

    public void deleteAllDirectors(){
        movieRepository.deleteAllDirectorFromDb();
    }
}
