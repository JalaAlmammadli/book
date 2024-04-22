package database;

import java.util.TreeMap;
import movie.Movie;

public class MovieDataBase implements DataBaseInterface<Movie> {

    private TreeMap<String, Movie> movie_map;

    private static MovieDataBase movieDataBase = new MovieDataBase();

    private MovieDataBase() {

    }
}