package library;

public abstract class LibraryStaff extends Person {

    private final int staffId;
    private String department;
    protected double monthlySalary;

    private static int staffCount = 0;

    public LibraryStaff(int personId, String name, String phoneNumber,
                        int staffId, String department,
                        double monthlySalary) {

        super(personId, name, phoneNumber);

        if (staffId <= 0 || department == null || department.isBlank()
                || !Double.isFinite(monthlySalary) || monthlySalary < 0) {
            throw new IllegalArgumentException("Invalid staff information");
        }

        this.staffId = staffId;
        this.department = department;
        this.monthlySalary = monthlySalary;
    }

    protected static void registerCreatedStaff() {
        staffCount++;
        registerCreatedPerson();
    }

    public static int getStaffCount() {
        return staffCount;
    }

    public abstract double calculateMonthlyCompensation();

    @Override
    public String getRoleDescription() {
        return "Library Staff";
    }

    public void displayStaffDetails() {
        displayBasicDetails();
        System.out.println("Staff ID: " + staffId);
        System.out.println("Department: " + department);
        System.out.println("Monthly Salary: $" + monthlySalary);
    }
}