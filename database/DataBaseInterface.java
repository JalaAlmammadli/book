/*


 * Created by Orkhan
 * 
 * 
 */

package database;

import java.util.TreeMap;

interface DataBaseInterface<T> {

    void add(T obj);

    void remove(String name);

    T getMember(String name);

    boolean isInMap(String obj);
}