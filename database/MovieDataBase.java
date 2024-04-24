
import java.util.TreeMap;
import movie.Movie;

public class MovieDataBase implements DataBaseInterface<Movie> {

    private TreeMap<String, Movie> movie_map;

    private static MovieDataBase movieDataBase = new MovieDataBase();

    private MovieDataBase() {

    }

    @Override
    public void add(Movie obj) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'add'");
    }

    @Override
    public void remove(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'remove'");
    }

    @Override
    public Movie getMember(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMember'");
    }

    @Override
    public boolean isInMap(String obj) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isInMap'");
    }
}