//return book code java
package library;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Members{
    private static GUI forming;

    public static void main(String[] args) {
        Libraries libraries = new Libraries(); // Create an instance of Libraries

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                forming = new GUI(libraries); // Assign the instance to the static variable
                forming.setVisible(true);
            }
        });
    }

    public static GUI getForming() {
        return forming; // Method to access the instance of GUIForm
    }
}
//inheritance
class GUI extends JFrame {
    private JTextField bookNameField888;
    private JTextField authorNameField888;
    private Libraries libraries;
    private DefaultTableModel tableModel888;
    private JTable table888;
//constructor
    public GUI(Libraries libraries) {
        this.libraries = libraries;

        setTitle("Return Book");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.WHITE); // Set the background color

        JPanel panel888 = new JPanel();
        panel888.setLayout(new GridBagLayout());
        panel888.setBackground(Color.WHITE); // Set the background color

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(10, 10, 10, 10);

        JLabel bookNameLabel888 = new JLabel("Book Name:");
        bookNameLabel888.setForeground(Color.BLUE); // Set the label text color
        panel888.add(bookNameLabel888, constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.fill = GridBagConstraints.HORIZONTAL;

        bookNameField888 = new JTextField(20);
        panel888.add(bookNameField888, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;

        JLabel authorNameLabel888 = new JLabel("Author Name:");
        authorNameLabel888.setForeground(Color.BLUE); // Set the label text color
        panel888.add(authorNameLabel888, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;

        authorNameField888 = new JTextField(20);
        panel888.add(authorNameField888, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.fill = GridBagConstraints.NONE;

        JButton submitButton888 = new JButton("Submit");
        panel888.add(submitButton888, constraints);

        // submitButton888.addActionListener(new ActionListener() {
        //     public void actionPerformed(ActionEvent e) {
        //         String bookName = bookNameField888.getText();
        //         String authorName = authorNameField888.getText();
        //         libraries.addBook(bookName, authorName);
        //         updateBookTable888();
        //         clearFields();
                              

        //     }
        // });
        submitButton888.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String bookName = bookNameField888.getText();
                String authorName = authorNameField888.getText();
                
                boolean isBookReturned = libraries.returnBook(bookName, authorName);
        
                if (isBookReturned) {
                    JOptionPane.showMessageDialog(GUI.this, "Book returned successfully!");
                    updateBookTable888();
                    clearFields();
                } else {
                    JOptionPane.showMessageDialog(GUI.this, "Book not found or mismatch in title/author!");
                }
            }
        });
        
        table888 = new JTable(tableModel888);
        table888.setBackground(Color.LIGHT_GRAY); // Set the table background color
        table888.setSelectionBackground(Color.CYAN); // Set the selected row background color

        JScrollPane scrollPane888 = new JScrollPane(table888);

        add(panel888, BorderLayout.NORTH);
        add(scrollPane888, BorderLayout.CENTER);
    }

    private void clearFields() {
        bookNameField888.setText("");
        authorNameField888.setText("");
    }

    public void updateBookTable888() {
        JFrame updateFrame888 = new JFrame("Book Table");
        updateFrame888.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        updateFrame888.setSize(400, 300);
        updateFrame888.setLocationRelativeTo(this);
    
        JPanel updatePanel888 = new JPanel(new BorderLayout());
        updatePanel888.setBackground(Color.WHITE);
    
        DefaultTableModel updateTableModel888 = new DefaultTableModel(new Object[]{"Book Title", "Author"}, 0);
        JTable updateTable888 = new JTable(updateTableModel888);
        updateTable888.setBackground(Color.LIGHT_GRAY);
        updateTable888.setSelectionBackground(Color.CYAN);
    
        JScrollPane updateScrollPane888 = new JScrollPane(updateTable888);
        updatePanel888.add(updateScrollPane888, BorderLayout.CENTER);
    
        ArrayList<Book> availableBooks = libraries.getAvailableBooks();
        for (Book book : availableBooks) {
            Object[] row = {book.getTitle(), book.getAuthor()};
            updateTableModel888.addRow(row);
        }
    
        updateFrame888.add(updatePanel888);
        updateFrame888.setVisible(true);
    }
    
}
