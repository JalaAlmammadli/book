package algorithms;

import entities.book.Book;
import java.util.ArrayList;

public class SortTable extends SortMethods{

    public static ArrayList<Book> sort(ArrayList<Book> list, String queue[]){

        for(int i = queue.length - 1; i >= 0; i++){

            String str = queue[i];

            switch(str){
                case "SortTitleAscending":
                    sortTitleAscending(list);
                    break;
                case "SortTitleDescending":
                    sortTitleDescending(list);
                    break;
                case "SortAuthorAscending":
                    sortAuthorAscending(list);
                    break;
                case "SortAuthorDescending":
                    sortAuthorDescending(list);
                    break;
                case "SortRatingAscending":
                    sortRatingAscending(list);
                    break;
                case "SortRatingDescending":
                    sortRatingDescending(list);
                    break;
                case "SortDateAscending":
                    sortDateAscending(list);
                    break;
                case "SortDateDescending":
                    sortDateDescending(list);
                    break;
                default:
                    System.out.println("Wrong command");
            }
        }
        
        return list;
    }
}
