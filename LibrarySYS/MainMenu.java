package LibrarySYS;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.time.LocalDate;
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
        createFileMenu();

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

        open();
    }

    public void save() throws IOException {
        ObjectOutputStream memberOS = new ObjectOutputStream(new FileOutputStream("members.dat"));
        memberOS.writeObject(members);
        memberOS.close();

        ObjectOutputStream bookOS = new ObjectOutputStream(new FileOutputStream("books.dat"));
        bookOS.writeObject(books);
        bookOS.close();

        ObjectOutputStream loanOS = new ObjectOutputStream(new FileOutputStream("loans.dat"));
        loanOS.writeObject(loans);
        loanOS.close();

    }


    public void open() {
        try {

            File memberFile = new File("members.dat");
            File bookFile = new File("books.dat");
            File loanFile = new File("loans.dat");

            if(memberFile.exists()) { 

                
                ObjectInputStream is = new ObjectInputStream(new FileInputStream(memberFile));
                members = (ArrayList<Member>) is.readObject();
                is.close();



                JOptionPane.showMessageDialog(null, memberFile.getName() + " file loaded into the system", "Open", JOptionPane.INFORMATION_MESSAGE);
            }
            else{
                memberFile.createNewFile();
                JOptionPane.showMessageDialog(null, "File just created!!", "Created " + memberFile.getName() + " file", JOptionPane.INFORMATION_MESSAGE);
            }

            if(bookFile.exists()) {


                ObjectInputStream is = new ObjectInputStream(new FileInputStream(bookFile));
                books = (ArrayList<Book>) is.readObject();
                is.close();



                JOptionPane.showMessageDialog(null, bookFile.getName() + " file loaded into the system", "Open", JOptionPane.INFORMATION_MESSAGE);
            }
            else{
                bookFile.createNewFile();
                JOptionPane.showMessageDialog(null, "File just created!!", "Created " + bookFile.getName() + " file", JOptionPane.INFORMATION_MESSAGE);
            }

            if(loanFile.exists()) {


                ObjectInputStream is = new ObjectInputStream(new FileInputStream(loanFile));
                loans = (ArrayList<Loan>) is.readObject();
                is.close();


                JOptionPane.showMessageDialog(null, loanFile.getName() + " file loaded into the system", "Open", JOptionPane.INFORMATION_MESSAGE);
            }
            else{
                loanFile.createNewFile();
                JOptionPane.showMessageDialog(null, "File just created!!", "Created " + loanFile.getName() + " file", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        catch(ClassNotFoundException cce) {
            JOptionPane.showMessageDialog(null,"Class of object deserialized not a match for anything used in this application","Error",JOptionPane.ERROR_MESSAGE);
            cce.printStackTrace();
        } catch (FileNotFoundException fileNotFoundException) {
            JOptionPane.showMessageDialog(null,"File not found","Error",JOptionPane.ERROR_MESSAGE);
            fileNotFoundException.printStackTrace();
        }
        catch (IOException ioe) {
            JOptionPane.showMessageDialog(null,"Problem reading from the file","Error",JOptionPane.ERROR_MESSAGE);
            ioe.printStackTrace();
        }
    }

    public void createFileMenu() {

        addWindowListener(new WindowAdapter()  {
            @Override
            public void windowClosing(WindowEvent e) {
                int option = JOptionPane.showConfirmDialog(null,"Are you sure you want to exit?","Confirmation",JOptionPane.YES_NO_OPTION);

                if(option == JOptionPane.YES_OPTION) {
                    try {
                        save();
                        JOptionPane.showMessageDialog(null,"Data saved successfully","Saved",JOptionPane.INFORMATION_MESSAGE);
                    } catch (IOException e1) {
                        JOptionPane.showMessageDialog(null,"Not able to save the file");
                        e1.printStackTrace();
                    }

                    System.exit(0);
                }
            }
        });

    }

    ArrayList<Book> books = new ArrayList<>();
    private Book book;

    ArrayList<Member> members = new ArrayList<>();
    private Member member;

    ArrayList<Loan> loans = new ArrayList<>();
    private Loan loan;

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
    }

    public void addBook(){
        String ISBN;
        String title;
        String author;
        String genre;
        int pages;
        final String [] genreList = {"Non-Fiction","Fiction","Sci-Fi","Horror","Romance","Historic","Sports & Leisure","Fantasy"};

        ISBN = JOptionPane.showInputDialog("Enter Books's ISBN");


                if(ISBN.length()==10)
                {
                    int j;
                    for(j=0; j<=8; j++)
                        if(!Character.isDigit(ISBN.charAt(j)))
                            break;

                    if(j==9)
                        if(Character.isDigit(ISBN.charAt(9)) || ISBN.charAt(9)=='X' || ISBN.charAt(9)=='x')
                        {
                            int sum=0;
                            int lastCharAsInt;

                            for(j=0;j<=8;j++)
                                sum+=Character.getNumericValue(ISBN.charAt(j))*(10-j);

                            if(Character.isDigit(ISBN.charAt(9)))
                                lastCharAsInt = Character.getNumericValue(ISBN.charAt(9));
                            else
                                lastCharAsInt = 10;

                            if(11-sum%11 != lastCharAsInt)
                            {
                                ISBN = JOptionPane.showInputDialog("Invalid! ISBN fails the golden rule. Please re-enter");
                            }
                        }
                        else
                            ISBN = JOptionPane.showInputDialog("Invalid! Last character must be a digit, an 'X' or an 'x'. Please re-enter");
                    else
                        ISBN = JOptionPane.showInputDialog("Invalid! First 9 characters must be digits. Please re-enter");
                }
                else
                    ISBN = JOptionPane.showInputDialog("Invalid! ISBN must have exactly 10 characters. Please re-enter");



        title = JOptionPane.showInputDialog("Enter Books's Title");
        if(title.length()>40){
            title = JOptionPane.showInputDialog("Title is too long. Please enter a different title");
        }
        else if(title == null || title.equals("") || title.equals(" ")){
            title = JOptionPane.showInputDialog("Title must not be empty");
        }

        author = JOptionPane.showInputDialog("Enter Books's Author");
        if(author.length()>40){
            author = JOptionPane.showInputDialog("Author is too long. Please enter a different author");
        }
        else if(author == null || author.equals("") || author.equals(" ")){
            author = JOptionPane.showInputDialog("Author must not be empty");
        }

        genre = (String) JOptionPane.showInputDialog(null,"Book Genre","Book",JOptionPane.QUESTION_MESSAGE,null,genreList,genreList[0]);
        if(genre == null){
            genre = (String) JOptionPane.showInputDialog(null,"Genre must be selected","Book",JOptionPane.ERROR_MESSAGE,null,genreList,genreList[0]);
        }

        pages = Integer.parseInt(JOptionPane.showInputDialog("Enter the number of pages in the book"));
        if(pages <= 0 || pages > 9999){
            pages = Integer.parseInt(JOptionPane.showInputDialog("Pages must be greater than 0 and less than 10,000"));
        }

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

            for (Book value : books) {
                bookCombo.addItem(value.getTitle() + "\n");
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
        String ISBN = "1";
        String title = "1";
        String author = "1";
        int pages = 1;
        String genre = "1";
        final String [] genreList = {"Non-Fiction","Fiction","Sci-Fi","Horror","Romance","Historic","Sports & Leisure","Fantasy"};

        JComboBox bookCombo = new JComboBox();
        JTextArea output = new JTextArea();


        if (books.size() < 1) {
            JOptionPane.showMessageDialog(null, "No books have been  added to the system yet.", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {

            for (Book value : books) {
                bookCombo.addItem(value.getTitle() + "\n");
            }

            JOptionPane.showMessageDialog(null, bookCombo, "Select Book to update details", JOptionPane.PLAIN_MESSAGE);

            int selected = bookCombo.getSelectedIndex();
            output.append(books.get(selected).toString());

            book.setISBN(JOptionPane.showInputDialog("Enter Books's ISBN"));
            if(ISBN.length()==10)
            {
                int j;
                for(j=0; j<=8; j++)
                    if(!Character.isDigit(ISBN.charAt(j)))
                        break;

                if(j==9)
                    if(Character.isDigit(ISBN.charAt(9)) || ISBN.charAt(9)=='X' || ISBN.charAt(9)=='x')
                    {
                        int sum=0;
                        int lastCharAsInt;

                        for(j=0;j<=8;j++)
                            sum+=Character.getNumericValue(ISBN.charAt(j))*(10-j);

                        if(Character.isDigit(ISBN.charAt(9)))
                            lastCharAsInt = Character.getNumericValue(ISBN.charAt(9));
                        else
                            lastCharAsInt = 10;

                        if(11-sum%11 != lastCharAsInt)
                        {
                            ISBN = JOptionPane.showInputDialog("Invalid! ISBN fails the golden rule. Please re-enter");
                        }
                    }
                    else
                        ISBN = JOptionPane.showInputDialog("Invalid! Last character must be a digit, an 'X' or an 'x'. Please re-enter");
                else
                    ISBN = JOptionPane.showInputDialog("Invalid! First 9 characters must be digits. Please re-enter");
            }
            else
                ISBN = JOptionPane.showInputDialog("Invalid! ISBN must have exactly 10 characters. Please re-enter");


            book.setTitle(JOptionPane.showInputDialog("Enter Books's Title"));
            if(title.length()>40){
                title = JOptionPane.showInputDialog("Title is too long. Please enter a different title");
            }
            else if(title == null || title.equals("") || title.equals(" ")){
                title = JOptionPane.showInputDialog("Title must not be empty");
            }
            book.setAuthor(JOptionPane.showInputDialog("Enter Books's Author"));
            if(author.length()>40){
                author = JOptionPane.showInputDialog("Author is too long. Please enter a different author");
            }
            else if(author == null || author.equals("") || author.equals(" ")){
                title = JOptionPane.showInputDialog("Author must not be empty");
            }
            book.setGenre((String) JOptionPane.showInputDialog(null,"Book","Book",JOptionPane.QUESTION_MESSAGE,null,genreList,genreList[0]));
            if(genre == null){
                genre = (String) JOptionPane.showInputDialog(null,"Genre must be selected","Book",JOptionPane.ERROR_MESSAGE,null,genreList,genreList[0]);
            }

            book.setPages(Integer.parseInt(JOptionPane.showInputDialog("Enter the number of pages in the book")));
            if(pages <= 0 || pages > 9999){
                pages = Integer.parseInt(JOptionPane.showInputDialog("Pages must be greater than 0 and less than 10,000"));
            }

            JOptionPane.showMessageDialog(null,"Updated Books details");

        }
    }

    public void addMember(){
        String forename;
        String surname;
        String password;
        String email;
        String dateOfBirth;
        String address;
        String phone;

        //validate
        forename = JOptionPane.showInputDialog("Enter Member's Forename");
        if(forename.length()>30){
            forename = JOptionPane.showInputDialog("Forename is too long. Please enter a different forename");
        }
        else if(forename == null || forename.equals("") || forename.equals(" ")){
            forename = JOptionPane.showInputDialog("Forename must not be empty");
        }

        surname = JOptionPane.showInputDialog("Enter Member's Surname");
        if(surname.length()>30){
            surname = JOptionPane.showInputDialog("Surname is too long. Please enter a different forename");
        }
        else if(surname == null || surname.equals("") || surname.equals(" ")){
            surname = JOptionPane.showInputDialog("Surname must not be empty");
        }

        email = JOptionPane.showInputDialog("Enter Member's E-mail");
        int locationOfAtSymbol,j;
        char ch;
        String domainName,recipient;
        boolean valid = false;

        while(!valid)
        {
            if(email.length()>=7 && email.length()<=322)
            {
                locationOfAtSymbol = email.indexOf('@');

                if(locationOfAtSymbol!=-1)
                    if(email.endsWith(".com") || email.endsWith(".org") || email.endsWith(".net") || email.endsWith(".ie"))
                    {
                        recipient = email.substring(0,locationOfAtSymbol);

                        if(recipient.length()>=1 && recipient.length()<=64)
                        {
                            for(j=0;j<recipient.length();j++)
                            {
                                ch = recipient.charAt(j);

                                if(!Character.isDigit(ch) && !Character.isLetter(ch) && ch!='-' && ch!='.' && ch!='_')
                                    break;
                            }

                            if(j==recipient.length())
                            {
                                if(email.endsWith("e"))
                                    domainName = email.substring(locationOfAtSymbol+1,email.length()-3);
                                else
                                    domainName = email.substring(locationOfAtSymbol+1,email.length()-4);

                                if(domainName.length()>=2 && domainName.length()<=253)
                                {
                                    for(j=0;j<domainName.length();j++)
                                    {
                                        ch = domainName.charAt(j);

                                        if(!Character.isDigit(ch) && !Character.isLetter(ch) && ch!='-' && ch!='.')
                                            break;
                                    }

                                    if(j==domainName.length())
                                        valid = true;
                                    else
                                        email = JOptionPane.showInputDialog("Invalid! Domain name must only contain letters, digits, dashes and dots");
                                }
                                else
                                    email = JOptionPane.showInputDialog("Invalid! Domain name must contain between 2 and 253 characters inclusive");
                            }
                            else
                                email = JOptionPane.showInputDialog("Invalid! Recipient name must only contain letters, digits, dashes, dots and underscores");
                        }
                        else
                            email = JOptionPane.showInputDialog("Invalid! Recipient name must contain between 1 and 64 characters inclusive");
                    }
                    else
                        email = JOptionPane.showInputDialog("Invalid! Email value must end with .com   .org   .net or .ie");
                else
                    email = JOptionPane.showInputDialog("Invalid! Email value must contain an @ symbol");
            }
            else
                email = JOptionPane.showInputDialog("Invalid! Email value must have between 7 and 322 characters inclusive");
        }

        dateOfBirth = JOptionPane.showInputDialog("Please enter the date of birth in the form dd-mm-yy");
        {
            if(dateOfBirth.length()==8)
                if(Character.isDigit(dateOfBirth.charAt(0)) && Character.isDigit(dateOfBirth.charAt(1)))
                {
                    int dayPart = Integer.parseInt(dateOfBirth.substring(0,2));

                    if(dayPart>=1 && dayPart<=31)
                        if(dateOfBirth.charAt(2)=='-')
                            if(Character.isDigit(dateOfBirth.charAt(3)) && Character.isDigit(dateOfBirth.charAt(4)))
                            {
                                int monthPart = Integer.parseInt(dateOfBirth.substring(3, 5));

                                if(monthPart>=1 && monthPart<=12)
                                    if(dateOfBirth.charAt(5)=='-')
                                        if(Character.isDigit(dateOfBirth.charAt(6)) && Character.isDigit(dateOfBirth.charAt(7)))
                                            if((monthPart == 1 || monthPart == 3 || monthPart == 5 || monthPart == 7 || monthPart == 8 || monthPart == 10 ||
                                                    monthPart == 12) && dayPart<=31 || (monthPart == 4 || monthPart == 6 || monthPart == 9 || monthPart == 11) &&
                                                    dayPart<=30 || monthPart == 2 && dayPart<=28) {
                                            }
                                            else
                                                dateOfBirth = JOptionPane.showInputDialog("Invalid! Too many days for this month value - Please re-enter");
                                        else
                                            dateOfBirth = JOptionPane.showInputDialog("Invalid! Year part must both be digits - Please re-enter");
                                    else
                                        dateOfBirth = JOptionPane.showInputDialog("Invalid! Sixth character must be a dash - Please re-enter");
                                else
                                    dateOfBirth = JOptionPane.showInputDialog("Invalid! Month value must be <= 12 - Please re-enter");
                            }
                            else
                                dateOfBirth = JOptionPane.showInputDialog("Invalid! Month part must both be digits - Please re-enter");
                        else
                            dateOfBirth = JOptionPane.showInputDialog("Invalid! Third character must be a dash - Please re-enter");
                    else
                        dateOfBirth = JOptionPane.showInputDialog("Invalid! Must have <= 31 days in any month - Please re-enter");
                }
                else
                    dateOfBirth = JOptionPane.showInputDialog("Invalid! Day part must both be digits - Please re-enter");
            else
                dateOfBirth = JOptionPane.showInputDialog("Invalid! Date of birth must have exactly 8 characters - Please re-enter");
        }

        password = JOptionPane.showInputDialog("Enter Member's Password");
         if(password == null || password.equals("") || password.equals(" ")){
            member.setSurname(JOptionPane.showInputDialog("Password must not be empty"));
        }
        else if(password.length()>20 || password.length()<=5){
            member.setPassword(JOptionPane.showInputDialog("Password must be between 6 and 20 characters. Please enter a different password"));
        }

        address = JOptionPane.showInputDialog("Enter Member's Address");
        if(address.length()>30){
            member.setAddress(JOptionPane.showInputDialog("Address is too long. Please enter a different address"));
        }
        else if(address == null || address.equals("") || address.equals(" ")){
            member.setSurname(JOptionPane.showInputDialog("Address must not be empty"));
        }

        phone = JOptionPane.showInputDialog("Enter Member's Phone Number");
        valid = false;

        while(!valid)
        {
            if(phone.length()==12)
            {
                if(phone.startsWith("(0"))
                {
                    if(phone.charAt(2)>='1' && phone.charAt(3)<='9')
                    {
                        if(phone.charAt(3)>='1' && phone.charAt(3)<='9')
                        {
                            if(phone.charAt(4)==')')
                            {
                                for(j=5;j<12;j++)
                                    if(!Character.isDigit(phone.charAt(j)))
                                        break;

                                if(j==12)
                                    valid=true;
                                else
                                    phone = JOptionPane.showInputDialog("Invalid! The last 7 characters must all be digits. Please re-enter");
                            }
                            else
                                phone = JOptionPane.showInputDialog("Invalid! 5th character must be ). Please re-enter");
                        }
                        else
                            phone = JOptionPane.showInputDialog("Invalid! 4th character must be in the range 1 to 9. Please re-enter");
                    }
                    else
                        phone = JOptionPane.showInputDialog("Invalid! 3rd character must be in the range 1 to 9. Please re-enter");
                }
                else
                    phone = JOptionPane.showInputDialog("Invalid! Must begin with (0. Please re-enter");
            }
            else
                phone = JOptionPane.showInputDialog("Invalid! Must have exactly 12 characters. Please re-enter");
        }

        member = new Member(forename,surname,email,dateOfBirth,password,address,phone);
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
        String forename = "0";
        String surname = "0";
        String password = "0";
        String email = "0";
        String dateOfBirth;
        String address = "0";
        String phone = "0";

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
            if(forename.length()>30){
                member.setForename(JOptionPane.showInputDialog("Forename is too long. Please enter a different forename"));
            }
            else if(forename == null || forename.equals("") || forename.equals(" ")){
                member.setForename(JOptionPane.showInputDialog("Forename must not be empty"));
            }

            member.setSurname(JOptionPane.showInputDialog("Enter Member's Surname"));
            if(surname.length()>30){
                member.setSurname(JOptionPane.showInputDialog("Surname is too long. Please enter a different forename"));
            }
            else if(surname == null || surname.equals("") || surname.equals(" ")){
                member.setSurname(JOptionPane.showInputDialog("Surname must not be empty"));
            }

            member.setEmail(JOptionPane.showInputDialog("Enter Member's E-mail"));
            int locationOfAtSymbol,j;
            char ch;
            String domainName,recipient;
            boolean valid = false;

            while(!valid)
            {
                if(email.length()>=7 && email.length()<=322)
                {
                    locationOfAtSymbol = email.indexOf('@');

                    if(locationOfAtSymbol!=-1)
                        if(email.endsWith(".com") || email.endsWith(".org") || email.endsWith(".net") || email.endsWith(".ie"))
                        {
                            recipient = email.substring(0,locationOfAtSymbol);

                            if(recipient.length()>=1 && recipient.length()<=64)
                            {
                                for(j=0;j<recipient.length();j++)
                                {
                                    ch = recipient.charAt(j);

                                    if(!Character.isDigit(ch) && !Character.isLetter(ch) && ch!='-' && ch!='.' && ch!='_')
                                        break;
                                }

                                if(j==recipient.length())
                                {
                                    if(email.endsWith("e"))
                                        domainName = email.substring(locationOfAtSymbol+1,email.length()-3);
                                    else
                                        domainName = email.substring(locationOfAtSymbol+1,email.length()-4);

                                    if(domainName.length()>=2 && domainName.length()<=253)
                                    {
                                        for(j=0;j<domainName.length();j++)
                                        {
                                            ch = domainName.charAt(j);

                                            if(!Character.isDigit(ch) && !Character.isLetter(ch) && ch!='-' && ch!='.')
                                                break;
                                        }

                                        if(j==domainName.length())
                                            valid = true;
                                        else
                                            email = JOptionPane.showInputDialog("Invalid! Domain name must only contain letters, digits, dashes and dots");
                                    }
                                    else
                                        email = JOptionPane.showInputDialog("Invalid! Domain name must contain between 2 and 253 characters inclusive");
                                }
                                else
                                    email = JOptionPane.showInputDialog("Invalid! Recipient name must only contain letters, digits, dashes, dots and underscores");
                            }
                            else
                                email = JOptionPane.showInputDialog("Invalid! Recipient name must contain between 1 and 64 characters inclusive");
                        }
                        else
                            email = JOptionPane.showInputDialog("Invalid! Email value must end with .com   .org   .net or .ie");
                    else
                        email = JOptionPane.showInputDialog("Invalid! Email value must contain an @ symbol");
                }
                else
                    email = JOptionPane.showInputDialog("Invalid! Email value must have between 7 and 322 characters inclusive");
            }

            member.setDateOfBirth(JOptionPane.showInputDialog("Enter Members Date of Birth"));
            dateOfBirth = JOptionPane.showInputDialog("Please enter the date of birth in the form dd-mm-yy");
            {
                if(dateOfBirth.length()==8)
                    if(Character.isDigit(dateOfBirth.charAt(0)) && Character.isDigit(dateOfBirth.charAt(1)))
                    {
                        int dayPart = Integer.parseInt(dateOfBirth.substring(0,2));

                        if(dayPart>=1 && dayPart<=31)
                            if(dateOfBirth.charAt(2)=='-')
                                if(Character.isDigit(dateOfBirth.charAt(3)) && Character.isDigit(dateOfBirth.charAt(4)))
                                {
                                    int monthPart = Integer.parseInt(dateOfBirth.substring(3, 5));

                                    if(monthPart>=1 && monthPart<=12)
                                        if(dateOfBirth.charAt(5)=='-')
                                            if(Character.isDigit(dateOfBirth.charAt(6)) && Character.isDigit(dateOfBirth.charAt(7)))
                                                if((monthPart == 1 || monthPart == 3 || monthPart == 5 || monthPart == 7 || monthPart == 8 || monthPart == 10 ||
                                                        monthPart == 12) && dayPart<=31 || (monthPart == 4 || monthPart == 6 || monthPart == 9 || monthPart == 11) &&
                                                        dayPart<=30 || monthPart == 2 && dayPart<=28) {
                                                }
                                                else
                                                    dateOfBirth = JOptionPane.showInputDialog("Invalid! Too many days for this month value - Please re-enter");
                                            else
                                                dateOfBirth = JOptionPane.showInputDialog("Invalid! Year part must both be digits - Please re-enter");
                                        else
                                            dateOfBirth = JOptionPane.showInputDialog("Invalid! Sixth character must be a dash - Please re-enter");
                                    else
                                        dateOfBirth = JOptionPane.showInputDialog("Invalid! Month value must be <= 12 - Please re-enter");
                                }
                                else
                                    dateOfBirth = JOptionPane.showInputDialog("Invalid! Month part must both be digits - Please re-enter");
                            else
                                dateOfBirth = JOptionPane.showInputDialog("Invalid! Third character must be a dash - Please re-enter");
                        else
                            dateOfBirth = JOptionPane.showInputDialog("Invalid! Must have <= 31 days in any month - Please re-enter");
                    }
                    else
                        dateOfBirth = JOptionPane.showInputDialog("Invalid! Day part must both be digits - Please re-enter");
                else
                    dateOfBirth = JOptionPane.showInputDialog("Invalid! Date of birth must have exactly 8 characters - Please re-enter");
            }

            member.setPassword(JOptionPane.showInputDialog("Enter Member's Password"));
            if(password.length()>20 || password.length()<=5){
                member.setPassword(JOptionPane.showInputDialog("Password must be between 6 and 20 characters. Please enter a different password"));
            }
            else if(password == null || password.equals("") || password.equals(" ")){
                member.setSurname(JOptionPane.showInputDialog("Password must not be empty"));
            }

            member.setAddress(JOptionPane.showInputDialog("Enter Member's Address"));
            if(address.length()>30){
                member.setAddress(JOptionPane.showInputDialog("Address is too long. Please enter a different address"));
            }
            else if(address == null || address.equals("") || address.equals(" ")){
                member.setSurname(JOptionPane.showInputDialog("Address must not be empty"));
            }

            member.setPhone(JOptionPane.showInputDialog("Enter Member's Phone Number"));
            valid = false;

            while(!valid)
            {
                if(phone.length()==12)
                {
                    if(phone.startsWith("(0"))
                    {
                        if(phone.charAt(2)>='1' && phone.charAt(3)<='9')
                        {
                            if(phone.charAt(3)>='1' && phone.charAt(3)<='9')
                            {
                                if(phone.charAt(4)==')')
                                {
                                    for(j=5;j<12;j++)
                                        if(!Character.isDigit(phone.charAt(j)))
                                            break;

                                    if(j==12)
                                        valid=true;
                                    else
                                        phone = JOptionPane.showInputDialog("Invalid! The last 7 characters must all be digits. Please re-enter");
                                }
                                else
                                    phone = JOptionPane.showInputDialog("Invalid! 5th character must be ). Please re-enter");
                            }
                            else
                                phone = JOptionPane.showInputDialog("Invalid! 4th character must be in the range 1 to 9. Please re-enter");
                        }
                        else
                            phone = JOptionPane.showInputDialog("Invalid! 3rd character must be in the range 1 to 9. Please re-enter");
                    }
                    else
                        phone = JOptionPane.showInputDialog("Invalid! Must begin with (0. Please re-enter");
                }
                else
                    phone = JOptionPane.showInputDialog("Invalid! Must have exactly 12 characters. Please re-enter");
            }

            JOptionPane.showMessageDialog(null,"Updated Members details");
        }
    }

    public void borrowBook() {
         int MemberID;
         int BookID;

        //use LocalDate and add days
        /*******************************************************************
         *Title: Java 8 – Adding Days to the LocalDate
         *Author: Chaitanya Singh
         *Site owner/sponsor: stackoverflow.com
         *Date: Nov 15 2017
         *Code Version: Nov 15 2017
         *Availability: https://beginnersbook.com/2017/11/java-8-adding-days-to-the-localdate/#:~:text=Java%20LocalDate%20Example%201%3A%20Adding%20Days%20to%20the,the%20specified%20number%20of%20days%20to%20the%20LocalDate
         *Modified: Code refactored
         *******************************************************************/

         LocalDate loanedDate = LocalDate.now();
         LocalDate dueDate = loanedDate.plusDays(7);

        JComboBox memberCombo = new JComboBox();
        JTextArea output = new JTextArea();
        JComboBox bookCombo = new JComboBox();
        JTextArea outputBook = new JTextArea();


        if(members.size() < 1) {
            JOptionPane.showMessageDialog(null,"No members have been  added to the system yet.","Warning",JOptionPane.WARNING_MESSAGE);
        }
        else {

            for (Member item : members) {
                memberCombo.addItem(item.getSurname() + "\n");
            }

            JOptionPane.showMessageDialog(null, memberCombo, "Select Member to loan a book", JOptionPane.PLAIN_MESSAGE);

            int selected = memberCombo.getSelectedIndex();
            output.append(members.get(selected).toString());

            MemberID = member.getMemberID();

            if (books.size() < 1) {
                JOptionPane.showMessageDialog(null, "No books have been  added to the system yet.", "Warning", JOptionPane.WARNING_MESSAGE);
            } else {
                if (book.getStatus()=='A') {

                    for (Book value : books) {
                        bookCombo.addItem(value.getTitle() + "\n");
                    }
                }

                JOptionPane.showMessageDialog(null, bookCombo, "Select a book to loan", JOptionPane.PLAIN_MESSAGE);

                int selectedBook = bookCombo.getSelectedIndex();
                outputBook.append(books.get(selectedBook).toString());

                BookID = book.getBookID();
                book.setStatus('U');

                loan = new Loan(MemberID, BookID, loanedDate, dueDate, null);
                loans.add(loan);

                JOptionPane.showMessageDialog(null, "Loan '" + loan.getLoanID() + "' added to the system");
            }
        }
    }


    public void returnBook(){
        JComboBox returnCombo = new JComboBox();
        JTextArea output = new JTextArea();

        if (loans.size() < 1) {
            JOptionPane.showMessageDialog(null, "No Loans have been  added to the system yet.", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {

            for (Loan value : loans) {
                returnCombo.addItem(value.getLoanID() + "\n");
            }

            JOptionPane.showMessageDialog(null, returnCombo, "Select Loan to return its Book", JOptionPane.PLAIN_MESSAGE);

            int selected = returnCombo.getSelectedIndex();
            output.append(loans.get(selected).toString());

            loan.setReturnedDate(LocalDate.now());

            book.setStatus('A');

            JOptionPane.showMessageDialog(null,"Returned Book");
        }

    }

    public void viewLoan(){
        JComboBox loanCombo = new JComboBox();
        JTextArea output = new JTextArea();

        output.setText("Loan Details:\n");

        if(loans.size() < 1) {
            JOptionPane.showMessageDialog(null,"No loans been  added to the system yet.","Warning",JOptionPane.WARNING_MESSAGE);
        }
        else {

            for (Loan value : loans) {
                loanCombo.addItem(value.getLoanID() + "\n");
            }

            JOptionPane.showMessageDialog(null, loanCombo, "Select Loan to view details", JOptionPane.PLAIN_MESSAGE);

            int selected = loanCombo.getSelectedIndex();
            output.append(loans.get(selected).toString());

            JOptionPane.showMessageDialog(null, output, "Loan Details", JOptionPane.PLAIN_MESSAGE);
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String menuName = e.getActionCommand();

        if(menuName.equals("Add Member") || e.getSource() == addMemberButton) {
            addMember();
        } else if(menuName.equals("View Member Profile")) {
            viewMembers();
        } else if(menuName.equals("Delete Member")) {
            deleteMember();
        } else if(menuName.equals("Update Member")) {
            updateMember();
        }

        if(menuName.equals("Add Book") || e.getSource() == addBookButton) {
            addBook();
        } else if(menuName.equals("View Book")) {
            viewBooks();
        } else if(menuName.equals("Delete Book")) {
            deleteBook();
        }else if(menuName.equals("Update Book")) {
            updateBook();
        }

        if(menuName.equals("Borrow Book") || e.getSource() == borrowBookButton) {
            borrowBook();
        } else if(menuName.equals("Return Book")) {
            returnBook();
        }else if(menuName.equals("View Loan")) {
            viewLoan();
        }


    }
}
