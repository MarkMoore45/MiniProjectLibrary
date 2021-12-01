package LibrarySYS;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Loan {

    private int LoanID;
    private int MemberID;
    private int BookID;
    private LocalDate loanedDate = LocalDate.now();
    //https://beginnersbook.com/2017/11/java-8-adding-days-to-the-localdate/#:~:text=Java%20LocalDate%20Example%201%3A%20Adding%20Days%20to%20the,the%20specified%20number%20of%20days%20to%20the%20LocalDate.
    private LocalDate dueDate = loanedDate.plusDays(7);
    private LocalDate returnedDate = LocalDate.now();
    //https://www.tutorialspoint.com/javatime/javatime_localdate_format.htm
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");
    private static int count = 1;

    public Loan(int memberID, int bookID, LocalDate loanedDate, LocalDate dueDate, LocalDate returnedDate) {
        setLoanID();
        setMemberID(memberID);
        setBookID(bookID);
        setLoanedDate(loanedDate);
        setDueDate(dueDate);
        setReturnedDate(returnedDate);
    }

    public int getLoanID() {
        return LoanID;
    }

    public void setLoanID() {
        this.LoanID = count++;
    }

    public int getMemberID() {
        return MemberID;
    }

    public void setMemberID(int memberID) {
        MemberID = memberID;
    }

    public int getBookID() {
        return BookID;
    }

    public void setBookID(int bookID) {
        BookID = bookID;
    }

    public LocalDate getLoanedDate() {
        return loanedDate;
    }

    public void setLoanedDate(LocalDate loanedDate) {
        this.loanedDate = loanedDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDate getReturnedDate() {
        return returnedDate;
    }

    public void setReturnedDate(LocalDate returnedDate) {
        this.returnedDate = returnedDate;
    }

    @Override
    public String toString() {
        return
                "\nLoan ID: " + getLoanID() +
                "\nMember ID: " + getMemberID() +
                "\nBook ID: " + getBookID() +
                "\nLoaned Date: " + getLoanedDate() +
                "\nDue Date: " + getDueDate() +
                "\nReturned Date: " + getReturnedDate() ;
    }
}
