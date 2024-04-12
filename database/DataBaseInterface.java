<<<<<<< HEAD
// package databases;

// interface DataBaseInterface<T> {
// void add(T obj);

// // void remove(T obj);

// // T search(String obj);

// boolean isInMap(String obj);
// }
=======
/*


 * Created by Orkhan
 * 
 * 
 */

package database;

interface DataBaseInterface<T> {
    void add(T obj);

    void remove(String name);

    T getMember(String name);

    boolean isInMap(String obj);
}
>>>>>>> orkhan-branch
