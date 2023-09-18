package library;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Deletion {
    private static PureGUIForm forms;

    public static void main(String[] args) {
        Libraries libraries = new Libraries(); // Create an instance of Libraries

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                forms = new PureGUIForm(libraries); // Assign the instance to the static variable
                forms.setVisible(true);
            }
        });
    }

    public static PureGUIForm getForms() {
        return forms; // Method to access the instance of GUIForm
    }
}

class PureGUIForm extends JFrame {
    private JTextField bookNameField2;
    private JTextField authorNameField2;
    private Libraries libraries;
    private DefaultTableModel tableModel;
    private JTable table;
    private GUIForm guiForm; // Reference to GUIForm instance

    public PureGUIForm(Libraries libraries) {
        this.libraries = libraries;

        setTitle("Delete Book");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.WHITE); // Set the background color

        JPanel paneltwo = new JPanel();
        paneltwo.setLayout(new GridBagLayout());
        paneltwo.setBackground(Color.WHITE); // Set the background color

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(10, 10, 10, 10);

        JLabel bookNameLabel2 = new JLabel("Book Name:");
        bookNameLabel2.setForeground(Color.BLUE); // Set the label text color
        paneltwo.add(bookNameLabel2, constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.fill = GridBagConstraints.HORIZONTAL;

        bookNameField2 = new JTextField(20);
        paneltwo.add(bookNameField2, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;

        JLabel authorNameLabel2 = new JLabel("Author Name:");
        authorNameLabel2.setForeground(Color.BLUE); // Set the label text color
        paneltwo.add(authorNameLabel2, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;

        authorNameField2 = new JTextField(20);
        paneltwo.add(authorNameField2, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.fill = GridBagConstraints.NONE;

        JButton deleteButton = new JButton("Delete");
        paneltwo.add(deleteButton, constraints);

        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String bookName = bookNameField2.getText();
                String authorName = authorNameField2.getText();

                // Delete the book from the Libraries instance
                boolean deleted = libraries.deleteBook(bookName, authorName);

                if (deleted) {
                    JOptionPane.showMessageDialog(PureGUIForm.this, "Book deleted successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(PureGUIForm.this, "Book not found", "Error", JOptionPane.ERROR_MESSAGE);
                }

                updateBookTables();
                clearFields();

                if (guiForm != null) {
                    guiForm.updateBookTable(); // Notify GUIForm to update the book table
                }
            }
        });

        tableModel = new DefaultTableModel(new Object[]{"Book Title", "Author"}, 0);
        table = new JTable(tableModel);
        table.setBackground(Color.LIGHT_GRAY); // Set the table background color
        table.setSelectionBackground(Color.CYAN); // Set the selected row background color

        JScrollPane scrollPane = new JScrollPane(table);

        add(paneltwo, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    public void setGUIForm(GUIForm guiForm) {
        this.guiForm = guiForm; // Set the reference to GUIForm instance
    }

    private void clearFields() {
        bookNameField2.setText("");
        authorNameField2.setText("");
    }

    public void updateBookTables() {
        tableModel.setRowCount(0); // Clear existing rows in the table

        ArrayList<Book> availableBooks = libraries.getAvailableBooks();
        for (Book book : availableBooks) {
            Object[] row = {book.getTitle(), book.getAuthor()};
            tableModel.addRow(row);
        }
    }
}


