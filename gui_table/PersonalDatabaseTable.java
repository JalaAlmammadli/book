package gui_table;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class PersonalDatabaseTable extends JFrame {

    private JTable table;
    private DefaultTableModel model;

    public PersonalDatabaseTable() {
        setTitle("Personal Database");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        // Sample data for the personal database
        ArrayList<PersonalDatabase> entries = new ArrayList<>();
        entries.add(new PersonalDatabase("Task 1", "John Doe", "Not started", 0.0, LocalDate.now(), null));

        // Create table model with columns
        String[] columnNames = {"Task", "Assigned To", "Status", "Time Spent", "Start Date", "End Date"};
        model = new DefaultTableModel(columnNames, 0);

        // Populate the table model with personal database entries
        for (PersonalDatabase entry : entries) {
            Object[] rowData = {
                    entry.getTask(),
                    entry.getAssignedTo(),
                    entry.getStatus(),
                    entry.getTimeSpent(),
                    entry.getStartDate(),
                    entry.getEndDate()
            };
            model.addRow(rowData);
        }

        // Create JTable with the model
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new PersonalDatabaseTable();
        });
    }
}

class PersonalDatabase {
    private String task;
    private String assignedTo;
    private String status;
    private Double timeSpent;
    private LocalDate startDate;
    private LocalDate endDate;

    public PersonalDatabase(String task, String assignedTo, String status, Double timeSpent, LocalDate startDate, LocalDate endDate) {
        this.task = task;
        this.assignedTo = assignedTo;
        this.status = status;
        this.timeSpent = timeSpent;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getTask() {
        return task;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public String getStatus() {
        return status;
    }

    public Double getTimeSpent() {
        return timeSpent;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }
}

