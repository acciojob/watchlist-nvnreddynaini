package com.driver;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MovieRepository {

    HashMap<String,Movie> MovieDb = new HashMap<>();
    HashMap<String,Director> DirectorDb = new HashMap<>();
    HashMap<String,List<String>> MovieDirectorDb = new HashMap<>();

    public void addMovieFromDb(Movie movie){
        String movieName = movie.getName();
        MovieDb.put(movieName,movie);
    }

    public void addDirectorFromDb(Director director){
        String directorName = director.getName();
        DirectorDb.put(directorName,director);
    }

    public void addMovieDirectorPairFromDb(String movie,String director){
        if(MovieDb.containsKey(movie) && DirectorDb.containsKey(director)){
            List<String> MoviesOfDirector = new ArrayList<>();
            if(MovieDirectorDb.containsKey(director))
                MoviesOfDirector = MovieDirectorDb.get(director);
            MoviesOfDirector.add(movie);
            MovieDirectorDb.put(director,MoviesOfDirector);
        }
    }

    public Movie getMovieByNameFromDb(String movie){
        if(MovieDb.containsKey(movie)){
            return MovieDb.get(movie);
        }else{
            return null;
        }
    }

    public Director getDirectorByNameFromDb(String director){
        if(DirectorDb.containsKey(director)){
            return DirectorDb.get(director);
        }else{
            return null;
        }
    }

    public List<String> getMoviesByDirectorNameFromDb(String director){
        List<String> moviesList = new ArrayList<>();
        if(MovieDirectorDb.containsKey(director))
            moviesList = MovieDirectorDb.get(director);
        return moviesList;
    }

    public List<String> findAllMoviesFromDb(){
        return new ArrayList<>(MovieDb.keySet());
    }

    public void deleteDirectorFromDb(String director){

        List<String> movies = new ArrayList<>();
        if(MovieDirectorDb.containsKey(director)){
            movies = MovieDirectorDb.get(director);

            for(String movie: movies){
                if(MovieDb.containsKey(movie)){
                    MovieDb.remove(movie);
                }
            }

            MovieDirectorDb.remove(director);
        }

        if(DirectorDb.containsKey(director)){
            DirectorDb.remove(director);
        }
    }

    public void deleteAllDirectorFromDb(){

        HashSet<String> moviesSet = new HashSet<String>();

        //Deleting the director's map
        DirectorDb = new HashMap<>();

        for(String director: MovieDirectorDb.keySet()){

            for(String movie: MovieDirectorDb.get(director)){
                moviesSet.add(movie);
            }
        }

        for(String movie: moviesSet){
            if(MovieDb.containsKey(movie)){
                MovieDb.remove(movie);
            }
        }
        MovieDirectorDb = new HashMap<>();
    }
}
