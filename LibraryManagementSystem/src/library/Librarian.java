
package library;

import java.util.HashSet;
import java.util.Set;

public class Librarian extends LibraryStaff
        implements Reportable, Reservable {

    private final int dailyIssueLimit;
    private int booksProcessed;
    private Set<String> reservedBooks = new HashSet<>();

    public Librarian(int personId, String name, String phoneNumber,
                     int staffId, String department, double monthlySalary,
                     int dailyIssueLimit, int booksProcessed) {

        this(personId, name, phoneNumber, staffId, department,
                monthlySalary, dailyIssueLimit, booksProcessed, true);
    }

    protected Librarian(int personId, String name, String phoneNumber,
                        int staffId, String department, double monthlySalary,
                        int dailyIssueLimit, int booksProcessed,
                        boolean registerNow) {

        super(personId, name, phoneNumber,
                staffId, department, monthlySalary);

        if (dailyIssueLimit < 0 || booksProcessed < 0) {
            throw new IllegalArgumentException("Invalid book counts");
        }

        this.dailyIssueLimit = dailyIssueLimit;
        this.booksProcessed = booksProcessed;

        if (registerNow) {
            registerCreatedStaff();
        }
    }

    @Override
    public double calculateMonthlyCompensation() {
        return monthlySalary + booksProcessed * 2.0;
    }

    public double calculateLateFee(int daysOverdue) {
        return calculateLateFee(daysOverdue, 1.0);
    }

    public double calculateLateFee(int daysOverdue, double dailyRate) {
        if (daysOverdue < 0 || !Double.isFinite(dailyRate)
                || dailyRate < 0) {
            throw new IllegalArgumentException("Invalid late fee input");
        }

        return daysOverdue * dailyRate;
    }

    @Override
    public void reserveBook(String bookId) {
        if (bookId == null || bookId.isBlank()) {
            System.out.println("Reservation failed: Invalid book ID");
        } else if (reservedBooks.add(bookId)) {
            System.out.println("Reservation successful: " + bookId);
        } else {
            System.out.println("Reservation failed: Already reserved");
        }
    }

    @Override
    public void generateReport() {
        System.out.println("Librarian Report");
        System.out.println("Books Processed: " + booksProcessed);
        System.out.println("Daily Issue Limit: " + dailyIssueLimit);
        System.out.println("Reserved Books: " + reservedBooks.size());
    }

    @Override
    public String getRoleDescription() {
        return "Librarian";
    }

    @Override
    public void displayStaffDetails() {
        super.displayStaffDetails();
        System.out.println("Role: " + getRoleDescription());
        System.out.println("Books Processed: " + booksProcessed);
        System.out.println("Daily Issue Limit: " + dailyIssueLimit);
    }
}
