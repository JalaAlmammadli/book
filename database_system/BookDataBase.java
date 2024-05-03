package database_system;

import database_system.exceptions.IllegalMemberException;
import entities.book.Book;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class BookDataBase extends AbstractDataBase<Book>{

    public static BookDataBase MainBookList = new BookDataBase();

    public BookDataBase() {
        super();
    }

    public void loadData() {

        try (BufferedReader br = new BufferedReader(new FileReader("./data/book_list.csv"))) {

            String line;
            while ((line = br.readLine()) != null) {
                String data[] = line.split(";");
                super.list.add(Book.readBook(data[0], data[1]));
                super.nameList.add(data[0]);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void writeData() {

        new File("./data/book_list.csv").delete();

        try {
            File newFile = new File("./data/book_list.csv");
            newFile.createNewFile();

            BufferedWriter bw = new BufferedWriter(new FileWriter("./data/book_list.csv"));

            for (int i = 0; i < super.nameList.size(); i++) {
                Book b = super.list.get(i);
                bw.write(b.getTitle() + ";" + b.getAuthor());
                bw.newLine();
            }

            bw.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void add(Book book) throws IllegalMemberException {

        // if (contains(book.getTitle())) {
        // throw new IllegalMemberException("User with username " + book.getTitle() + "
        // already exists");
        // }

        super.add(book);
        super.nameList.add(book.getTitle());
    }

    public String[] returnData(int index){

        Book book = MainBookList.getMemberByIndex(index);
        String data[] = {book.getTitle(), book.getAuthor()};
        return data;
    }
}
