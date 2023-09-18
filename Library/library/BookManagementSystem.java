
package library;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class BookManagementSystem {
  
    public static void main(String[] args) {
        
        SwingUtilities.invokeLater(() -> {
            // Create the JFrame
            JFrame frame = new JFrame("Admin Panel");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//last time
            frame.setSize(1500, 800);
            frame.setLocationRelativeTo(null);

            // Create the main JPanel
            JPanel mainPanel = new JPanel();
            mainPanel.setLayout(new BorderLayout());
            mainPanel.setBackground(Color.WHITE);

            // Create the image icon
            ImageIcon icon = new ImageIcon("C:\\Users\\ASD\\Desktop\\Java In VS\\Library\\icons8-book-100.png");
            JLabel imageLabel = new JLabel(icon);
            imageLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            imageLabel.setVerticalAlignment(SwingConstants.TOP);
            mainPanel.add(imageLabel, BorderLayout.WEST);

            // Create the text label
            JLabel textLabel = new JLabel("<html>ONLINE LIBRARY<br>MANAGEMENT SYSTEM</html>");
            textLabel.setFont(new Font("Arial", Font.BOLD, 24));
            textLabel.setForeground(Color.BLACK);
            textLabel.setVerticalAlignment(SwingConstants.TOP);
            textLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 10));
            mainPanel.add(textLabel, BorderLayout.CENTER);

            // Add the main panel to the frame
            frame.getContentPane().add(mainPanel, BorderLayout.NORTH);


            JPanel panel = new JPanel(new GridBagLayout());
            panel.setBackground(Color.WHITE);

            // Create the image icons and labels
            ImageIcon icon1 = new ImageIcon("C:\\Users\\ASD\\Desktop\\Java In VS\\Library\\Assets\\Images\\icons8-library-100.png");
            JLabel imageLabel1 = new JLabel(icon1);
            JLabel label1 = new JLabel("ADD BOOK");
            label1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    Libraries libraries = new Libraries(); // Create an instance of Libraries

                    GUIForm guiForm = new GUIForm(libraries); // Create an instance of GUIForm and pass Libraries
                    
                    guiForm.setVisible(true);
                    guiForm.setSize(500, 500);
                   guiForm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                }

            });

            ImageIcon icon2 = new ImageIcon("C:\\Users\\ASD\\Desktop\\Java In VS\\Library\\Assets\\Images\\icons8-delete-100.png");
            JLabel imageLabel2 = new JLabel(icon2);
            JLabel label2 = new JLabel("DELETE BOOK");
            label2.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    Deletion.main(new String[0]); // Invoke the main method to initialize the GUI
                  
                    PureGUIForm forms = Deletion.getForms();
    

                    // Access and use the forms instance and its methods
                    forms.updateBookTables();
                    forms.setVisible(true);
                }
            });


          


            ImageIcon icon3 = new ImageIcon("C:\\Users\\ASD\\Desktop\\Java In VS\\Library\\Assets\\Images\\icons8-education-100.png");
            JLabel imageLabel3 = new JLabel(icon3);
            JLabel label3 = new JLabel("BOOKS LISTED");

            // Create the libraries instance
            Libraries library = new Libraries();

            // Create the JTable model
            DefaultTableModel tableModel = new DefaultTableModel();
            tableModel.addColumn("Book Title");
            tableModel.addColumn("Author");

            // Populate the JTable model with data from the libraries class
            for (Book booking : library.getAvailableBooks()) {
                tableModel.addRow(new Object[]{booking.getTitle(), booking.getAuthor()});
            }

            // Create the JTable using the tableModel
            JTable table = new JTable(tableModel);

            // Action listener of label3
            label3.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    // Code to display the available books in the JTable
                    JFrame frame = new JFrame("Available Books");
                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    frame.getContentPane().add(new JScrollPane(table));
                    frame.pack();
                    frame.setVisible(true);
                }
            });
            

            ImageIcon icon4 = new ImageIcon("C:\\Users\\ASD\\Desktop\\Java In VS\\Library\\Assets\\Images\\icons8-mail-by-timer-100.png");
            JLabel imageLabel4 = new JLabel(icon4);
            JLabel label4 = new JLabel("TIMES BOOK ISSUED");
            label4.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                  
                   int submissionCount = BookIssueForm.getSubmissionCount();
                   JOptionPane.showMessageDialog( label4, "Total Value of Issued Books : " + submissionCount );
                    
                }
            });
            ImageIcon icon5 = new ImageIcon("C:\\Users\\ASD\\Desktop\\Java In VS\\Library\\Assets\\Images\\icons8-librarian-100.png");
            JLabel imageLabel5 = new JLabel(icon5);
            JLabel label5 = new JLabel("ISSUE BOOK");
            label5.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                 
                        BookIssueForm bookIssueForm = new BookIssueForm();
                     
                       
                      

                }
            });
           

            ImageIcon icon6 = new ImageIcon("C:\\Users\\ASD\\Desktop\\Java In VS\\Library\\Assets\\Images\\icons8-users-100.png");
            JLabel imageLabel6 = new JLabel(icon6);
            JLabel label6 = new JLabel("REGISTER USERS");
            label6.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                 
             int Counting_Register_users = main.getCounting_Register_users();
             JOptionPane.showMessageDialog(label6,"Total Register Users : "+Counting_Register_users);                     
                       
                      

                }
            });
            // Create constraints for the image labels
            GridBagConstraints imageConstraints = new GridBagConstraints();
            imageConstraints.gridx = GridBagConstraints.RELATIVE;
            imageConstraints.gridy = GridBagConstraints.RELATIVE;
            imageConstraints.anchor = GridBagConstraints.NORTHWEST;
            imageConstraints.insets = new Insets(10, 10, 10, 10);

            // Create constraints for the text labels
            GridBagConstraints textConstraints = new GridBagConstraints();
            textConstraints.gridx = GridBagConstraints.RELATIVE;
            textConstraints.gridy = GridBagConstraints.RELATIVE;
            textConstraints.anchor = GridBagConstraints.WEST;
            textConstraints.insets = new Insets(10, 0, 10, 10);

            // Add the image labels and text labels to the panel
            panel.add(imageLabel1, imageConstraints);
            panel.add(label1, textConstraints);
            panel.add(imageLabel2, imageConstraints);
            panel.add(label2, textConstraints);
            panel.add(imageLabel3, imageConstraints);
            panel.add(label3, textConstraints);
            panel.add(imageLabel4, imageConstraints);
            panel.add(label4, textConstraints);
            panel.add(imageLabel5, imageConstraints);
            panel.add(label5, textConstraints);
            panel.add(imageLabel6, imageConstraints);
            panel.add(label6, textConstraints);

            // Add the panel to the frame
            frame.getContentPane().add(panel, BorderLayout.CENTER);

            // Display the frame
            frame.setVisible(true);
            

        });
    }
}

