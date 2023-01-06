package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    MovieService movieService;


    @PostMapping("/add-movie")
    public ResponseEntity<String> addMovie(@RequestBody Movie movie){
        movieService.addMovie(movie);
        return new ResponseEntity<>("New movie added successfully",HttpStatus.CREATED);
    }

    @PostMapping("/add-director")
    public ResponseEntity<String> addDirector(@RequestBody Director director){
        movieService.addDirector(director);
        return new ResponseEntity<>("New director added successfully",HttpStatus.CREATED);
    }

    @PutMapping("/add-movie-director-pair")
    public ResponseEntity<String> addMovieDirectorPair(@RequestParam("movie") String movie, @RequestParam("director") String director){
        movieService.addMovieDirectorPair(movie,director);
        return new ResponseEntity<>("New movie-directer pair added successfully",HttpStatus.CREATED);
    }

    @GetMapping("/get-director-name-by-movie-name")
    public ResponseEntity<String> getDirectorNameByMovieName(@RequestParam("name") String name){
        String result = movieService.getDirectorNameByMovieName(name);
        //System.out.println("hi");
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @GetMapping("/get-movie-by-name/{name}")
    public ResponseEntity<Movie> getMovieByName(@PathVariable("name") String name){
        Movie result = movieService.getMovieByName(name);
        if(result == null) {
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(result,HttpStatus.OK);
        }
    }


    @GetMapping("get-director-by-name/{name}")
    public ResponseEntity<Director> getDirectorByName(@PathVariable("name") String name){
        Director result = movieService.getDirectorByName(name);
        if(result==null){
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(result,HttpStatus.OK);
        }
    }

    @GetMapping("get-movies-by-director-name/{director}")
    public ResponseEntity<List<String>> getMoviesByDirectorName(@PathVariable("name") String name){
        return new ResponseEntity<>(movieService.getMoviesByDirectorName(name),HttpStatus.CREATED);
    }

    @GetMapping("get-all-movies")
    public ResponseEntity<List<String>> findAllMovies(){
        return new ResponseEntity<>(movieService.findAllMovies(),HttpStatus.OK);
    }

    @DeleteMapping("/delete-director-by-name")
    public ResponseEntity<String> deleteDirectorByName(@RequestParam String director){
        movieService.deleteDirector(director);
        return new ResponseEntity<>(director + " removed successfully", HttpStatus.CREATED);
    }
    @DeleteMapping("/delete-all-directors")
    public ResponseEntity<String> deleteAllDirectors(){
        movieService.deleteAllDirectors();
        return new ResponseEntity<>("All directors deleted successfully", HttpStatus.CREATED);
    }
}
