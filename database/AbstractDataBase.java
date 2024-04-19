package database;

public abstract class AbstractDataBase<T> implements DataBaseInterface<T> {

    public default void add(T obj) {

    }
}
