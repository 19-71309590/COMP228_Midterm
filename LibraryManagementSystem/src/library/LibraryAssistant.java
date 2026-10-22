package library;

public class LibraryAssistant extends LibraryStaff {

    private final String shiftType;
    private final double shiftAllowance;

    public LibraryAssistant(int personId, String name, String phoneNumber,
                            int staffId, String department, double monthlySalary,
                            String shiftType, double shiftAllowance) {

        super(personId, name, phoneNumber,
              staffId, department, monthlySalary);

        if (shiftType == null || shiftType.isBlank()
                || !Double.isFinite(shiftAllowance) || shiftAllowance < 0) {
            throw new IllegalArgumentException("Invalid shift information");
        }

        this.shiftType = shiftType;
        this.shiftAllowance = shiftAllowance;

        registerCreatedStaff();
    }

    @Override
    public double calculateMonthlyCompensation() {
        return monthlySalary + shiftAllowance;
    }

    @Override
    public String getRoleDescription() {
        return "Library Assistant";
    }

    @Override
    public void displayStaffDetails() {
        super.displayStaffDetails();
        System.out.println("Role: " + getRoleDescription());
        System.out.println("Shift: " + shiftType);
        System.out.println("Shift Allowance: $" + shiftAllowance);
    }
}