package library;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

class ShowWindow extends JFrame {
    private Libraries library;
    private JPanel mainPanel;
    private JPanel listPanel;

    public ShowWindow() {
        setTitle("Member Features");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        library = new Libraries();
        mainPanel = createMainPanel();
        listPanel = createListPanel();

        JLabel returnLabel = new JLabel("Return Book");
        JLabel listLabel = new JLabel("See Listed Book");
        ImageIcon returnIcon = new ImageIcon("C:\\Users\\ASD\\Desktop\\Java In VS\\Library\\Assets\\Images\\icons8-education-80.png");
        ImageIcon listIcon = new ImageIcon("C:\\Users\\ASD\\Desktop\\Java In VS\\Library\\Assets\\Images\\icons8-library-100.png");

        returnLabel.setIcon(returnIcon);
        listLabel.setIcon(listIcon);

        listLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setContentPane(listPanel);
                revalidate();
                repaint();
            }
        });

        returnLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Members m = new Members();
                m.main(new String[0]);
                setContentPane(mainPanel);
                revalidate();
                repaint();
            }
        });

        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        mainPanel.add(returnLabel, gbc);

        gbc.gridx = 1;
        mainPanel.add(listLabel, gbc);

        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.add(mainPanel);

        setContentPane(centerPanel);
        setVisible(true);
    }

    private JPanel createMainPanel() {
        return new JPanel();
    }

    private JPanel createListPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Book Title");
        tableModel.addColumn("Author");

        ArrayList<Book> availableBooks = library.getAvailableBooks();
        for (Book book : availableBooks) {
            tableModel.addRow(new Object[]{book.getTitle(), book.getAuthor()});
        }

        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setContentPane(mainPanel);
                revalidate();
                repaint();
            }
        });
        panel.add(backButton, BorderLayout.SOUTH);

        return panel;
    }

    @Override
    protected void processWindowEvent(WindowEvent e) {
        if (e.getID() == WindowEvent.WINDOW_CLOSING) {
            int option = JOptionPane.showConfirmDialog(this, "Are you sure you want to exit?", "Confirm Exit", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        }
        super.processWindowEvent(e);
    }

}

public class FrontWindowMembers {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ShowWindow listShow = new ShowWindow();
            }
        });
    }
}
