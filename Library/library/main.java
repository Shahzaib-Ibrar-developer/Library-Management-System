package library;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.HashMap;

public class main {
    private static HashMap<String, String> userData;
    private static int Counting_Register_users; 
    public static int getCounting_Register_users() {
        return Counting_Register_users;
    }
  
    
    
    public static void main(String[] args) {
        Counting_Register_users= 0;
        JFrame frame = new JFrame("Library Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(350, 400);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("LOGIN");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel emailLabel = new JLabel("Email");
        JTextField emailField = new JTextField(15);

        JLabel passwordLabel = new JLabel("Password   ");
        JPasswordField passwordField = new JPasswordField(15);

        JButton loginButton = new JButton("Login");
        loginButton.setBackground(new Color(59, 89, 182));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        JButton RegisterButton = new JButton("Register");
        RegisterButton.setBackground(new Color(59, 89, 182));
        RegisterButton.setForeground(Color.WHITE);
        RegisterButton.setFocusPainted(false);
        RegisterButton.setFont(new Font("Arial", Font.BOLD, 14));
        // Perform logic with the email and password
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();
                char[] passwordChars = passwordField.getPassword();
                String password = new String(passwordChars);
                
                // This portion is for admin
                if (email.equals("admin") && password.equals("password")) {
                    // Admin credentials are valid
                    JOptionPane.showMessageDialog(frame, "Admin Login Successful");



                    
                    BookManagementSystem bookManagementSystem = new BookManagementSystem();
                    bookManagementSystem.main(args);
                    frame.dispose();
               
                
                   // book b = new book();
                    // return;
                }
           
                // Check if the email and password match the stored user data
              if (userData.containsKey(email) && userData.get(email).equals(password)) {
                    // Login successful

                    FrontWindowMembers Window = new FrontWindowMembers();
                    FrontWindowMembers.main(args);
                    JOptionPane.showMessageDialog(frame, "Login Successful!");
      //last time
                    frame.dispose();
                    
                } 
                // else  if (!(email.equals("admin") && password.equals("password"))) {
                //     JOptionPane.showMessageDialog(frame, "Invalid email or password, Re-Enter  your Credentials", "Login Failed", JOptionPane.ERROR_MESSAGE);

                    
                   
                //    // book b = new book();
                //     // return;
                // }
                
                
                else {

                    // Invalid credentials
                    JOptionPane.showMessageDialog(frame, "Invalid email or password", "Login Failed", JOptionPane.ERROR_MESSAGE);
                }
                
                // Clear the password field
                Arrays.fill(passwordChars, ' ');
                passwordField.setText("");

            }
        });
 
    
    
    

        // member register portion 
        RegisterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Counting_Register_users++;
                // Open the registration window
                frame.dispose();
                JFrame registerFrame = new JFrame("Registration");
                JPanel registerPanel = new JPanel(new GridLayout(8, 2, 10, 10));
                registerPanel.setBackground(Color.WHITE);
                registerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
                JLabel registerLabel = new JLabel("REGISTER");
                registerLabel.setFont(new Font("Arial", Font.BOLD, 24));
                registerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
                JLabel nameLabel = new JLabel("Name");
                JTextField nameField = new JTextField(15);
        
                JLabel emailLabel2 = new JLabel("Email");
                JTextField emailField2 = new JTextField(15);
        
                JLabel cityLabel = new JLabel("City");
                JTextField cityField = new JTextField(15);
        
                JLabel cnicLabel = new JLabel("CNIC");
                JTextField cnicField = new JTextField(15);
        
                JLabel passwordLabel2 = new JLabel("Password");
                JTextField passwordField2 = new JTextField(15);
        
                JLabel designationLabel = new JLabel("Designation");
                JTextField designationField = new JTextField(15);
        
                JButton signUpButton = new JButton("Sign Up");
                signUpButton.setBackground(new Color(59, 89, 182));
                signUpButton.setForeground(Color.WHITE);
                signUpButton.setFocusPainted(false);
                signUpButton.setFont(new Font("Arial", Font.BOLD, 14));
        
                JButton signInButton = new JButton("Sign In");
                signInButton.setBackground(new Color(59, 89, 182));
                signInButton.setForeground(Color.WHITE);
                signInButton.setFocusPainted(false);
                signInButton.setFont(new Font("Arial", Font.BOLD, 14));
            
               signUpButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        userData = new HashMap<>();

                        String email = emailField2.getText();
                        String password = passwordField2.getText();
        
                        if (email.contains("@") && email.contains(".com")) {
                            // Check password requirements
                            boolean hasSymbol = false;
                            boolean hasCapitalLetter = false;
                            boolean hasNumber = false;
                        
                            for (char c : password.toCharArray()) {
                                if (Character.isDigit(c)) {
                                    hasNumber = true;
                                } else if (Character.isUpperCase(c)) {
                                    hasCapitalLetter = true;
                                } else if (!Character.isLetterOrDigit(c)) {
                                    hasSymbol = true;
                                }
                            }
                        
                            if (hasSymbol && hasCapitalLetter && hasNumber) {
                                // Store the user data in the HashMap
                                userData.put(email, password);
                                JOptionPane.showMessageDialog(frame, "Registration Successful!");
                            } else {
                                // Invalid password format
                                JOptionPane.showMessageDialog(frame, "Invalid password format", "Registration Failed", JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            // Invalid email format
                            JOptionPane.showMessageDialog(frame, "Invalid email format", "Registration Failed", JOptionPane.ERROR_MESSAGE);
                        }
                        
        
                        // Clear the fields after registration
                        nameField.setText("");
                        emailField.setText("");
                        cityField.setText("");
                        cnicField.setText("");
                        passwordField.setText("");
                        designationField.setText("");
        
                        // Show a message or perform any other action upon successful registration
                       // JOptionPane.showMessageDialog(registerFrame, "Registration Successful!");
                    }
                });

               
                signInButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        registerFrame.dispose(); 
                         // Close the registration window
                        frame.setVisible(true); // Show the login window
                    }
                });
        
                registerPanel.add(registerLabel);
                registerPanel.add(new JLabel()); // Empty label for spacing
                registerPanel.add(nameLabel);
                registerPanel.add(nameField);
                registerPanel.add(emailLabel2);
                registerPanel.add(emailField2);
                registerPanel.add(cityLabel);
                registerPanel.add(cityField);
                registerPanel.add(cnicLabel);
                registerPanel.add(cnicField);
                registerPanel.add(passwordLabel2);
                registerPanel.add(passwordField2);
                registerPanel.add(designationLabel);
                registerPanel.add(designationField);
                registerPanel.add(signUpButton);
                registerPanel.add(signInButton);
        
                registerFrame.getContentPane().add(registerPanel);
                registerFrame.pack();
                registerFrame.setLocationRelativeTo(null);
                registerFrame.setVisible(true);
            }
        });
        
        
        
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(5, 0, 5, 0);

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        panel.add(titleLabel, constraints);

        constraints.gridy = 1;
        constraints.gridwidth = 1;
        panel.add(emailLabel, constraints);

        constraints.gridx = 1;
        panel.add(emailField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(passwordLabel, constraints);

        constraints.gridx = 1;
        panel.add(passwordField, constraints);

        constraints.gridy = 3;
        constraints.gridwidth = 2;
        panel.add(loginButton, constraints);

        
        constraints.gridy = 4;
        panel.add(RegisterButton, constraints);

        frame.getContentPane().add(panel);
        frame.setVisible(true);
        frame.pack();
    }
}
