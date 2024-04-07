import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.Arrays;

public class BookDB extends JFrame {
    private JTable table;
    private DefaultTableModel model;
    private JTextField searchField;
    private TableRowSorter<DefaultTableModel> sorter;

    public BookDB() {
        setTitle("Book Database");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        model = new DefaultTableModel() {
            @Override
            public Class<?> getColumnClass(int column) {
                return getValueAt(0, column).getClass();
            }
        };

        model.addColumn("Title");
        model.addColumn("Author");
        model.addColumn("Rating");
        model.addColumn("Reviews");

        table = new JTable(model);
        sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        searchField = new JTextField(20);
        searchField.addActionListener(e -> searchBooks());
        searchPanel.add(new JLabel("Search: "));
        searchPanel.add(searchField);
        add(searchPanel, BorderLayout.NORTH);

        // Add example data
        addBook(new Book("Don Quixote", "Cervantes", 4.22, 9, Arrays.asList("Ferdinandbəy")));
        addBook(new Book("The Gambler", "Dostoevsky", 5.0, 4, Arrays.asList("John", "Tofitaqulu")));
        addBook(new Book("Essays", "Montaigne", 4.5, 5, Arrays.asList("Tofitaqulu", "User123")));

        setVisible(true);
    }

    public void addBook(Book book) {
        model.addRow(new Object[]{book.getTitle(), book.getAuthor(), book.getRating(), book.getReviewCount()});
    }

    private void searchBooks() {
        String query = searchField.getText().toLowerCase();
        
        // Create a RowFilter based on the query
        RowFilter<DefaultTableModel, Object> rf = new RowFilter<DefaultTableModel, Object>() {
            @Override
            public boolean include(Entry<? extends DefaultTableModel, ? extends Object> entry) {
                for (int i = 0; i < entry.getValueCount(); i++) {
                    if (entry.getStringValue(i).toLowerCase().contains(query)) {
                        return true;
                    }
                }
                return false;
            }
        };
    
        sorter.setRowFilter(rf);
    }
    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BookDB());
    }
}
