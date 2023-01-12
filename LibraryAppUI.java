import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

class Library {
    // fields to store book information
    private String title;
    private String author;
    private int ISBN;
    private boolean availability;
    // constructor
    public Library(String title, String author, int ISBN, boolean availability) {
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.availability = availability;
    }

    // getters and setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getAuthor() {
        return author;
    }
    
    public void setAuthor(String author) {
        this.author = author;
    }
    
    public int getISBN() {
        return ISBN;
    }
    
    public void setISBN(int ISBN) {
        this.ISBN = ISBN;
    }
    
    public boolean isAvailability() {
        return availability;
    }
    
    public void setAvailability(boolean availability) {
        this.availability = availability;
    }
    
    // method to check out a book
    public void checkOut() {
        if (availability) {
            availability = false;
            JOptionPane.showMessageDialog(null, "Book checked out successfully!");
        } else {
            JOptionPane.showMessageDialog(null, "Sorry, this book is not available.");
        }
    }
    
    // method to return a book
    public void returnBook() {
        if (!availability) {
            availability = true;
            JOptionPane.showMessageDialog(null, "Book returned successfully!");
        } else {
            JOptionPane.showMessageDialog(null, "This book is already available.");
        }
    }
}
public class LibraryAppUI extends JFrame {
    private List<Library> books = new ArrayList<Library>();
    private JFrame loginFrame, mainFrame;
    private JTextField checkOutField, returnField;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTabbedPane tabbedPane;
    private boolean isLoggedIn = false;
    public LibraryAppUI() {
        // adding books to the library
        books.add(new Library("Harry Potter and the Sorcerer's Stone", "J.K. Rowling", 123456789, true));
        books.add(new Library("To Kill a Mockingbird", "Harper Lee", 987654321, true));
        books.add(new Library("The Great Gatsby", "F. Scott Fitzgerald", 111111111, false));
    
        // creating the login frame
        loginFrame = new JFrame("Login");
        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField(10);
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField(10);
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new LoginListener());
        JPanel loginPanel = new JPanel();
        loginPanel.add(usernameLabel);
        loginPanel.add(usernameField);
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordField);
        loginPanel.add(loginButton);
        loginFrame.add(loginPanel);
        loginFrame.setSize(300, 150);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setVisible(true);
            // creating the main frame
    mainFrame = new JFrame("Library Management System");
    tabbedPane = new JTabbedPane();

    // creating UI elements for check out and return
    JLabel checkOutLabel = new JLabel("Enter the number of the book you would like to check out:");
    checkOutField = new JTextField(10);
    JButton checkOutButton = new JButton("Check Out");
    checkOutButton.addActionListener(new CheckOutListener());

    JLabel returnLabel = new JLabel("Enter the number of the book you would like to return:");
    returnField = new JTextField(10);
    JButton returnButton = new JButton("Return");
    returnButton.addActionListener(new ReturnListener());

    JButton generateReportsButton = new JButton("Generate Reports");
    generateReportsButton.addActionListener(new GenerateReportsListener());

    JButton logoutButton = new JButton("Logout");
    logoutButton.addActionListener(new LogoutListener());

    JPanel checkOutPanel = new JPanel();
    checkOutPanel.add(checkOutLabel);
    checkOutPanel.add(checkOutField);
    checkOutPanel.add(checkOutButton);

    JPanel returnPanel = new JPanel();
    returnPanel.add(returnLabel);
    returnPanel.add(returnField);
    returnPanel.add(returnButton);

    JPanel reportPanel = new JPanel();
    reportPanel.add(generateReportsButton);

    JPanel logoutPanel = new JPanel();
    logoutPanel.add(logoutButton);

    tabbedPane.addTab("Check Out/Return", checkOutPanel);
    tabbedPane.addTab("Reports", reportPanel);
    tabbedPane.addTab("Logout", logoutPanel);
        // creating a menu bar for navigating between pages
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Navigation");
        JMenuItem booksMenuItem = new JMenuItem("Books");
        booksMenuItem.addActionListener(new BooksMenuListener());
        JMenuItem usersMenuItem = new JMenuItem("Users");
        usersMenuItem.addActionListener(new UsersMenuListener());
        JMenuItem transactionsMenuItem = new JMenuItem("Transactions");
        transactionsMenuItem.addActionListener(new TransactionsMenuListener());
        menu.add(booksMenuItem);
        menu.add(usersMenuItem);
        menu.add(transactionsMenuItem);
        menuBar.add(menu);
        mainFrame.setJMenuBar(menuBar);
    
        mainFrame.add(tabbedPane, BorderLayout.CENTER);
        mainFrame.setSize(400, 300);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    private class LoginListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String username = usernameField.getText();
            char[] password = passwordField.getPassword();
    
            if (username.equals("admin") && new String(password).equals("password")) {
                isLoggedIn = true;
                loginFrame.setVisible(false);
                mainFrame.setVisible(true);
                JOptionPane.showMessageDialog(null, "Welcome, " + username + "!");
            } else {
                JOptionPane.showMessageDialog(null, "Invalid username or password. Please try again.");
            }
        }
    }
    
    private class CheckOutListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int choice = Integer.parseInt(checkOutField.getText());
            books.get(choice - 1).checkOut();
        }
    }
    
    private class ReturnListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int choice = Integer.parseInt(returnField.getText());
            books.get(choice - 1).returnBook();
        }
    }
    
    private class GenerateReportsListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // code to generate reports
            JOptionPane.showMessageDialog(null, "Generating reports...");
        }
    }
    
    private class LogoutListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            isLoggedIn = false;
            mainFrame.setVisible(false);
            loginFrame.setVisible(true);
            JOptionPane.showMessageDialog(null, "You have been logged out.");
        }
    }
    
    private class BooksMenuListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // code to navigate to the books section
            JOptionPane.showMessageDialog(null, "Navigatingto the books section...");
            tabbedPane.setSelectedIndex(0);
        }
    }
    private class UsersMenuListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // code to navigate to the users section
            JOptionPane.showMessageDialog(null, "Navigating to the users section...");
            // code to navigate to the users section
        }
    }
    
    private class TransactionsMenuListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // code to navigate to the transactions section
            JOptionPane.showMessageDialog(null, "Navigating to the transactions section...");
            // code to navigate to the transactions section
        }
    }
    
    public static void main(String[] args) {
        new LibraryAppUI();
    }
}    
