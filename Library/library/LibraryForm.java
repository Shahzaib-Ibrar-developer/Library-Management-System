
package library;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class LibraryForm {
    private static GUIForm form;

    public static void main(String[] args) {
        Libraries libraries = new Libraries(); // Create an instance of Libraries

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                form = new GUIForm(libraries); // Assign the instance to the static variable
                form.setVisible(true);
            }
        });
    }

    public static GUIForm getForm() {
        return form; // Method to access the instance of GUIForm
    }
}

class GUIForm extends JFrame {
    private JTextField bookNameField;
    private JTextField authorNameField;
    private Libraries libraries;
    private DefaultTableModel tableModel;
    private JTable table;

    public GUIForm(Libraries libraries) {
        this.libraries = libraries;

        setTitle("Add Book");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.WHITE); // Set the background color

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(Color.WHITE); // Set the background color

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(10, 10, 10, 10);

        JLabel bookNameLabel = new JLabel("Book Name:");
        bookNameLabel.setForeground(Color.BLUE); // Set the label text color
        panel.add(bookNameLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.fill = GridBagConstraints.HORIZONTAL;

        bookNameField = new JTextField(20);
        panel.add(bookNameField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;

        JLabel authorNameLabel = new JLabel("Author Name:");
        authorNameLabel.setForeground(Color.BLUE); // Set the label text color
        panel.add(authorNameLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;

        authorNameField = new JTextField(20);
        panel.add(authorNameField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.fill = GridBagConstraints.NONE;

        JButton submitButton = new JButton("Submit");
        panel.add(submitButton, constraints);

        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String bookName = bookNameField.getText();
                String authorName = authorNameField.getText();
                libraries.addBook(bookName, authorName);
                updateBookTable();
                clearFields();
                              

            }
        });

        table = new JTable(tableModel);
        table.setBackground(Color.LIGHT_GRAY); // Set the table background color
        table.setSelectionBackground(Color.CYAN); // Set the selected row background color

        JScrollPane scrollPane = new JScrollPane(table);

        add(panel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void clearFields() {
        bookNameField.setText("");
        authorNameField.setText("");
    }

    public void updateBookTable() {
        JFrame updateFrame = new JFrame("Book Table");
        updateFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        updateFrame.setSize(400, 300);
        updateFrame.setLocationRelativeTo(this);
    
        JPanel updatePanel = new JPanel(new BorderLayout());
        updatePanel.setBackground(Color.WHITE);
    
        DefaultTableModel updateTableModel = new DefaultTableModel(new Object[]{"Book Title", "Author"}, 0);
        JTable updateTable = new JTable(updateTableModel);
        updateTable.setBackground(Color.LIGHT_GRAY);
        updateTable.setSelectionBackground(Color.CYAN);
    
        JScrollPane updateScrollPane = new JScrollPane(updateTable);
        updatePanel.add(updateScrollPane, BorderLayout.CENTER);
    
        ArrayList<Book> availableBooks = libraries.getAvailableBooks();
        for (Book book : availableBooks) {
            Object[] row = {book.getTitle(), book.getAuthor()};
            updateTableModel.addRow(row);
        }
    
        updateFrame.add(updatePanel);
        updateFrame.setVisible(true);
    }
    
}
