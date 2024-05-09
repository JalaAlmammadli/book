[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/4zK3HDh5)

# Code Explanations - Team 50

###### package app-runner
## MainTableGUI:
This class serves as a central point for managing the GUI elements related to the main table or display in the application. It contains a static field databaseLib of type DatabaseLib to interact with the database.

## ReadData:
This class is responsible for reading data from the user and book databases. It has a static method read() which loads data from the main user list and main book list.

## RunApp:
This class extends Thread, suggesting it's meant to be run in a separate thread. It overrides the run() method, which executes the thread's logic. In the run() method, it calls ReadData.read() to read data, and then tries to login using LoginFrame.Login(). It catches any exceptions that occur during login and prints them to the console.

## SaveData:
This class is responsible for saving data to the user and book databases. It has a static method save() which writes data to the main user list and main book list.

###### package app-runner




