package LibrarySYS;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

class LibrarySystem extends JFrame implements ActionListener {

    JMenu bookMenu;
    JMenu memberMenu;
    JMenu loanMenu;
    JButton addMemberButton;
    JButton addBookButton;
    JButton borrowBookButton;
    JPanel shortcutPanel;
    TitledBorder titledBorder;


    public LibrarySystem() {


        super("Library System");

        setLayout(new GridBagLayout());

        try {
            setIconImage(new ImageIcon(getClass().getResource("book_logo.png")).getImage());
        }
        catch(Exception ex) {

            JOptionPane.showMessageDialog(null,"Invalid Logo Image File in Main Screen");
        }


        createBookMenu();
        createMemberMenu();
        createLoanMenu();

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        menuBar.setBackground(Color.gray);
        menuBar.add(bookMenu);
        menuBar.add(memberMenu);
        menuBar.add((loanMenu));


        setSize(500,400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        shortcutPanel = new JPanel();

        titledBorder = new TitledBorder("Shortcut Buttons");
        titledBorder.setTitleColor(Color.RED);
        shortcutPanel.setPreferredSize(new Dimension(330, 60));
        shortcutPanel.setBorder(titledBorder);

        addBookButton = new JButton("Add Book");
        addBookButton.addActionListener(this);
        shortcutPanel.add(addBookButton);

        addMemberButton = new JButton("Add Member");
        addMemberButton.addActionListener(this);
        shortcutPanel.add(addMemberButton);

        borrowBookButton = new JButton("Borrow Book");
        borrowBookButton.addActionListener(this);
        shortcutPanel.add(borrowBookButton);

        add(shortcutPanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    ArrayList<Book> books = new ArrayList<>();
    private Book book;

    ArrayList<Member> members = new ArrayList<>();
    private Member member;

    public static void main(String[] args) {
        new LibrarySystem();
    }

    private void createMemberMenu( ) {
        JMenuItem    item;
        memberMenu = new JMenu("Member");

        item = new JMenuItem("Add Member");
        item.addActionListener(this);
        memberMenu.add(item);

        item = new JMenuItem("Update Member");
        item.addActionListener(this);
        memberMenu.add(item);

        item = new JMenuItem("Delete Member");
        item.addActionListener(this);
        memberMenu.add(item);

        item = new JMenuItem("View Member Profile");
        item.addActionListener(this);
        memberMenu.add(item);
    }

    private void createBookMenu() {
        JMenuItem    item;
        bookMenu = new JMenu("Book");

        item = new JMenuItem("Add Book");
        item.addActionListener(this);
        bookMenu.add(item);

        item = new JMenuItem("Update Book");
        item.addActionListener(this);
        bookMenu.add(item);

        item = new JMenuItem("Delete Book");
        item.addActionListener(this);
        bookMenu.add(item);

        item = new JMenuItem("View Book");
        item.addActionListener(this);
        bookMenu.add(item);
    }

    private void createLoanMenu( ) {
        JMenuItem    item;

        loanMenu = new JMenu("Loan");

        item = new JMenuItem("Borrow Book");
        item.addActionListener(this);
        loanMenu.add(item);

        item = new JMenuItem("Return Book");
        item.addActionListener(this);
        loanMenu.add(item);

        item = new JMenuItem("View Loan");
        item.addActionListener(this);
        loanMenu.add(item);

        item = new JMenuItem("Pay Fines");
        item.addActionListener(this);
        loanMenu.add(item);
    }

    public void addBook(){
        String ISBN;
        String title;
        String author;
        String genre;
        int pages;
        final String [] genreList = {"Non-Fiction","Fiction","Sci-Fi","Horror","Romance","Historic","Sports & Leisure","Fantasy"};

        //validate
        ISBN = JOptionPane.showInputDialog("Enter Books's ISBN");

        title = JOptionPane.showInputDialog("Enter Books's Title");

        author = JOptionPane.showInputDialog("Enter Books's Author");

        genre = (String) JOptionPane.showInputDialog(null,"Book","Book",JOptionPane.QUESTION_MESSAGE,null,genreList,genreList[0]);

        pages = Integer.parseInt(JOptionPane.showInputDialog("Enter the number of pages in the book"));

        book = new Book(ISBN,title,author,genre,pages,'A');
        books.add(book);
        JOptionPane.showMessageDialog(null,"Book '" + title + "' added to the system");
    }

    public void viewBooks() {
        JComboBox bookCombo = new JComboBox();
        JTextArea output = new JTextArea();

        output.setText("Book Details:\n");

        if(books.size() < 1) {
            JOptionPane.showMessageDialog(null,"No books have been  added to the system yet.","Warning",JOptionPane.WARNING_MESSAGE);
        }
        else {
            Iterator<Book> iterator = books.iterator();

            while(iterator.hasNext()) {
                bookCombo.addItem(iterator.next().getTitle() +  "\n");
            }

            JOptionPane.showMessageDialog(null,bookCombo,"Select Book to view details",JOptionPane.PLAIN_MESSAGE);

            int selected = bookCombo.getSelectedIndex();
            output.append(books.get(selected).toString());

            JOptionPane.showMessageDialog(null,output,"Book Details",JOptionPane.PLAIN_MESSAGE);
        }
    }

    public void deleteBook() {

        JComboBox bookList = new JComboBox();

        if(books.size() < 1) {
            JOptionPane.showMessageDialog(null,"No books have been  added to the system yet.","Warning",JOptionPane.WARNING_MESSAGE);
        }
        else {
            for (Book book : books) {
                bookList.addItem(book.getTitle());
            }

            JOptionPane.showMessageDialog(null, "Select Book to be removed", "Remove Book", JOptionPane.INFORMATION_MESSAGE);

            JOptionPane.showMessageDialog(null, bookList, "Remove Book", JOptionPane.INFORMATION_MESSAGE);

            int selected = bookList.getSelectedIndex();

            books.remove(selected);

            JOptionPane.showMessageDialog(null, "Book Removed", "Removed", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void updateBook(){
        final String [] genreList = {"Non-Fiction","Fiction","Sci-Fi","Horror","Romance","Historic","Sports & Leisure","Fantasy"};

        JComboBox bookCombo = new JComboBox();
        JTextArea output = new JTextArea();


        if (books.size() < 1) {
            JOptionPane.showMessageDialog(null, "No books have been  added to the system yet.", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            Iterator<Book> iterator = books.iterator();

            while (iterator.hasNext()) {
                bookCombo.addItem(iterator.next().getTitle() + "\n");
            }

            JOptionPane.showMessageDialog(null, bookCombo, "Select Book to update details", JOptionPane.PLAIN_MESSAGE);

            int selected = bookCombo.getSelectedIndex();
            output.append(books.get(selected).toString());

            book.setISBN(JOptionPane.showInputDialog("Enter Books's ISBN"));
            book.setTitle(JOptionPane.showInputDialog("Enter Books's Title"));
            book.setAuthor(JOptionPane.showInputDialog("Enter Books's Author"));
            book.setGenre((String) JOptionPane.showInputDialog(null,"Book","Book",JOptionPane.QUESTION_MESSAGE,null,genreList,genreList[0]));
            book.setPages(Integer.parseInt(JOptionPane.showInputDialog("Enter the number of pages in the book")));

            JOptionPane.showMessageDialog(null,"Updated Books details");

        }
    }

    public void addMember(){
        String forename;
        String surname;
        String password;
        String email;
        String address;
        int phone;

        //validate
        forename = JOptionPane.showInputDialog("Enter Member's Forename");
        surname = JOptionPane.showInputDialog("Enter Member's Surname");
        email = JOptionPane.showInputDialog("Enter Member's E-mail");
        password = JOptionPane.showInputDialog("Enter Member's Password");
        address = JOptionPane.showInputDialog("Enter Member's Address");
        phone = Integer.parseInt(JOptionPane.showInputDialog("Enter Member's Phone Number"));

        member = new Member(forename,surname,email,password,address,phone);
        members.add(member);
        JOptionPane.showMessageDialog(null,"Member " + forename + " " + surname + " added to the system");
    }

    public void viewMembers() {
        JComboBox memberCombo = new JComboBox();
        JTextArea output = new JTextArea();

        output.setText("Member Details:\n");

        if(members.size() < 1) {
            JOptionPane.showMessageDialog(null,"No members have been  added to the system yet.","Warning",JOptionPane.WARNING_MESSAGE);
        }
        else {
            Iterator<Member> iterator = members.iterator();

            while(iterator.hasNext()) {
                memberCombo.addItem(iterator.next().getForename() +  "\n");
            }

            JOptionPane.showMessageDialog(null,memberCombo,"Select Member to view details",JOptionPane.PLAIN_MESSAGE);

            int selected = memberCombo.getSelectedIndex();
            output.append(members.get(selected).toString());

            JOptionPane.showMessageDialog(null,output,"Member Details",JOptionPane.PLAIN_MESSAGE);
        }
    }

    public void deleteMember() {

        if(members.size() < 1) {
            JOptionPane.showMessageDialog(null,"No members have been  added to the system yet.","Warning",JOptionPane.WARNING_MESSAGE);
        }
        else {
            JComboBox memberList = new JComboBox();

            for (Member member : members) {
                memberList.addItem(member.getForename());
            }

            JOptionPane.showMessageDialog(null, "Select Member to be removed", "Remove Member", JOptionPane.INFORMATION_MESSAGE);

            JOptionPane.showMessageDialog(null, memberList, "Remove Member", JOptionPane.INFORMATION_MESSAGE);

            int selected = memberList.getSelectedIndex();

            members.remove(selected);

            JOptionPane.showMessageDialog(null, "Member Removed", "Removed", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void updateMember() {

        JComboBox memberCombo = new JComboBox();
        JTextArea output = new JTextArea();


        if (members.size() < 1) {
            JOptionPane.showMessageDialog(null, "No members have been  added to the system yet.", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            Iterator<Member> iterator = members.iterator();

            while (iterator.hasNext()) {
                memberCombo.addItem(iterator.next().getForename() + "\n");
            }

            JOptionPane.showMessageDialog(null, memberCombo, "Select Member to update details", JOptionPane.PLAIN_MESSAGE);

            int selected = memberCombo.getSelectedIndex();
            output.append(members.get(selected).toString());

            member.setForename(JOptionPane.showInputDialog("Enter Member's Forename"));
            member.setSurname(JOptionPane.showInputDialog("Enter Member's Surname"));
            member.setEmail(JOptionPane.showInputDialog("Enter Member's E-mail"));
            member.setPassword(JOptionPane.showInputDialog("Enter Member's Password"));
            member.setAddress(JOptionPane.showInputDialog("Enter Member's Address"));
            member.setPhone(Integer.parseInt(JOptionPane.showInputDialog("Enter Member's Phone Number")));


            JOptionPane.showMessageDialog(null,"Updated Members details");
        }
    }

    public void borrowBook() {
        JComboBox bookCombo = new JComboBox();
        JTextArea output = new JTextArea();

        output.setText("Book Details:\n");

        if (books.size() < 1) {
            JOptionPane.showMessageDialog(null, "No books have been  added to the system yet.", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            Iterator<Book> iterator = books.iterator();

            while (iterator.hasNext()) {
                bookCombo.addItem(iterator.next().getTitle() + "\n");
            }

            JOptionPane.showMessageDialog(null, bookCombo, "Select a book to borrow", JOptionPane.PLAIN_MESSAGE);

            int selected = bookCombo.getSelectedIndex();
            output.append(books.get(selected).toString());
        }
    }

    public void returnBook(){

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String menuName = e.getActionCommand();


        if(menuName == "Add Member" || e.getSource() == addMemberButton) {
            addMember();
        } else if(menuName.equals("View Member Profile")) {
            viewMembers();
        } else if(menuName.equals("Delete Member")) {
            deleteMember();
        } else if(menuName.equals("Update Member")) {
            updateMember();
        }

        if(menuName == "Add Book" || e.getSource() == addBookButton) {
            addBook();
        } else if(menuName.equals("View Book")) {
            viewBooks();
        } else if(menuName.equals("Delete Book")) {
            deleteBook();
        }else if(menuName.equals("Update Book")) {
            updateBook();
        }

        if(menuName == "Borrow Book" || e.getSource() == borrowBookButton) {
            borrowBook();
        } else if(menuName.equals("Return Book")) {
            returnBook();
        }else if(menuName.equals("View Loan")) {
            returnBook();
        }else if(menuName.equals("Pay Fine")) {
            returnBook();
        }


    }
}
