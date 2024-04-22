/*


 * Created by Orkhan
 * 
 * 
 */


 interface DataBaseInterface<T> {
     void add(T obj);
 
     void remove(String name);
 
     T getMember(String name);
 
     boolean isInMap(String obj);
 }