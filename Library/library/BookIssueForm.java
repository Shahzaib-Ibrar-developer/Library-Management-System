
package library;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class BookIssueForm extends JFrame {

    private JTextField nameField;
    private JTextField emailField;
    private JTextField dateField;
    private JTextField cityField;
    private JTextField issueIdField;
    private JTextField returnDateField;
    private JTextField bookNameField;
    private Libraries library;
   private static int submissionCount;
   public static int getSubmissionCount() {
    return submissionCount;
}
    public BookIssueForm() {
        submissionCount = 0;

        setTitle("Book Issue Form");
       setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 400);

        library = new Libraries();

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(9, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField();

        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField();

        JLabel dateLabel = new JLabel("Date:");
        dateField = new JTextField();

        JLabel cityLabel = new JLabel("City:");
        cityField = new JTextField();

        JLabel issueIdLabel = new JLabel("Issue ID:");
        issueIdField = new JTextField();
        issueIdField.setEditable(false);

        JLabel returnDateLabel = new JLabel("Return Date:");
        returnDateField = new JTextField();

        JLabel bookNameLabel = new JLabel("Book Name:");
        bookNameField = new JTextField();

        JButton generateButton = new JButton("Generate Issue ID");
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateIssueId();
            }
        });

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submitForm();
            }
        });
   
        // Add the JTextArea to the panel
     
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(dateLabel);
        panel.add(dateField);
        panel.add(cityLabel);
        panel.add(cityField);
        panel.add(bookNameLabel);
        panel.add(bookNameField);
        panel.add(issueIdLabel);
        panel.add(issueIdField);
        panel.add(returnDateLabel);
        panel.add(returnDateField);
        panel.add(generateButton);
        panel.add(submitButton);
       

        add(panel);

        setVisible(true);
    }

    private void generateIssueId() {
        Random rand = new Random();
        int issueId = rand.nextInt(1000) + 1;
        issueIdField.setText(String.valueOf(issueId));
    }

    private void submitForm() {
        String name = nameField.getText();
        String email = emailField.getText();
        String date = dateField.getText();
        String city = cityField.getText();
        String bookName = bookNameField.getText();
        String issueId = issueIdField.getText();
        String returnDate = returnDateField.getText();

        // Search for the book by name in the available books
        ArrayList<Book> availableBooks = library.getAvailableBooks();
        Book selectedBook = null;
        for (Book book : availableBooks) {
            if (book.getTitle().equalsIgnoreCase(bookName)) {
                selectedBook = book;
                break;
            }
        }

        if (selectedBook != null) {
              // Increment the submission count
              submissionCount++;
              
            // Perform actions with the form data and selected book, such as saving to a database
            // Append the information to the output area
            String message = "Name: " + name + "\n"
            + "Email: " + email + "\n"
            + "Date: " + date + "\n"
            + "City: " + city + "\n"
            + "Book: " + selectedBook.getTitle() + " by " + selectedBook.getAuthor() + "\n"
            + "Issue ID: " + issueId + "\n"
            + "Return Date: " + returnDate;

    // Display the message in a dialog
    JOptionPane.showMessageDialog(this, message, "Form Information", JOptionPane.INFORMATION_MESSAGE);
            // Display a message dialog indicating successful submission
            JOptionPane.showMessageDialog(this,"Form submitted successfully!");
            JOptionPane.showMessageDialog(this, "Submission Count: " + submissionCount, "Submission Count", JOptionPane.INFORMATION_MESSAGE);
      
        } else {
            JOptionPane.showMessageDialog(this, "Book not found!");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BookIssueForm());
    }
}

// class Libraries {
//     private ArrayList<Book> availableBooks;

//     public Libraries() {
//         availableBooks = new ArrayList<>();
//         availableBooks.add(new Book("Sample Book 1", "Sample Author 1"));
//         availableBooks.add(new Book("Sample Book 2", "Sample Author 2"));
//         availableBooks.add(new Book("Sample Book 3", "Sample Author 3"));
//         availableBooks.add(new Book("Sample Book 4", "Sample Author 4"));
//         availableBooks.add(new Book("Sample Book 5", "Sample Author 5"));
//     }

//     public ArrayList<Book> getAvailableBooks() {
//         return availableBooks;
//     }
// }

// class Book {
//     private String title;
//     private String author;

//     public Book(String title, String author) {
//         this.title = title;
//         this.author = author;
//     }

//     public String getTitle() {
//         return title;
//     }

//     public String getAuthor() {
//         return author;
//     }
// }
