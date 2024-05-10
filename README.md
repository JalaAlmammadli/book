[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/4zK3HDh5)

# Code Explanations - Team 50

###### package app-runner
### MainTableGUI:
This class serves as a central point for managing the GUI elements related to the main table or display in the application. It contains a static field databaseLib of type DatabaseLib to interact with the database.

### ReadData:
This class is responsible for reading data from the user and book databases. It has a static method read() which loads data from the main user list and main book list.

### RunApp:
This class extends Thread, suggesting it's meant to be run in a separate thread. It overrides the run() method, which executes the thread's logic. In the run() method, it calls ReadData.read() to read data, and then tries to login using LoginFrame.Login(). It catches any exceptions that occur during login and prints them to the console.

### SaveData:
This class is responsible for saving data to the user and book databases. It has a static method save() which writes data to the main user list and main book list.

###### package database_system

### AbstractDataBase<T>:
This abstract class serves as a blueprint for specific database implementations. It defines a generic type T to represent the type of objects stored in the database. 
The class consists of: 
+ an ArrayList to store objects of type T;
+ another ArrayList to store names or identifiers associated with each object;
+ a constructor which initializes the ArrayLists;
+ add() method to add an object to the database (throws IllegalMemberException defined in the exceptions package of the package database_system);
+ remove() method to remove an object from the database;
+ contains() method checks if a specific name or identifier exists in the database;
+ getMember() method to retrieve a member from the database based on its index;
+ size() method returns the size of the database.

### BookDataBase:
This class extends AbstractDataBase<Book> and specializes in managing book-related data.
Detailed logic of the class:
+ It extends the AbstractDataBase<Book> class, inheriting its methods and properties. This means it inherits functionalities like adding, removing, and accessing books from the database;
+ It declares a static instance of BookDataBase named MainBookList. This allows for a single instance of the BookDataBase to be shared across the application;
+ It defines a default constructor that initializes the superclass AbstractDataBase using super();
+ loadData() method reads data from the file book_list.csv and populates the database. It reads each line from the file, splits it into title and author, creates Book objects using the readBook method, and adds them to the database;
+ writeData() method writes the current state of the database to the file (book_list.csv). It first deletes the existing file, then creates a new one and writes the title and author of each book in the database to it;
+ add() method adds a Book object to the database. It invokes the add method of the superclass AbstractDataBase, then adds the book's title to the nameList.
+ returnData() method has two overloaded implementations: the first version retrieves data of a book by its index (returnData(int index)), the second by passing a Book object (returnData(Book book)). Both return a String array containing the title and author of the book;
+ contains() method checks if a book with the given title and author exists in the database. It iterates through the list of books and compares the title and author with the given parameters;
+ getMember() method retrieves a Book object from the database by its title and author. If the book is found, it returns the Book object, otherwise, it throws an IllegalMemberException;
+ deleteBook() method removes a book from the database based on its title and author. It iterates through the list of books, finds the matching book, removes it from the list, and then deletes associated files.
+ deleteBookFiles() method deletes associated files of the book;
+ editBook() method edits the parameters of a book in the database. It finds the book based on its original title and author, updates its title and author with the new values, and then writes the changes to the file.





