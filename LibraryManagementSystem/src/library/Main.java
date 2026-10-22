package library;

public class Main {

    public static void main(String[] args) {

        System.out.println("=== LIBRARY MANAGEMENT SYSTEM ===");

        // 1. 멤버등록
        Member member1 = Member.registerMember(
                101, "Mun", "4161234567", 1001, 26);

        Member member2 = Member.registerMember(
                102, "John", "4169876543", 1002, 30, "Premium");

        System.out.println("\n=== MEMBERS ===");
        member1.displayBasicDetails();
        member2.displayBasicDetails();

        // 2. 스패트 생성
        Librarian librarian = new Librarian(
                201, "Alice", "4161111111",
                501, "General", 3000, 20, 100);

        SeniorLibrarian senior = new SeniorLibrarian(
                202, "Bob", "4162222222",
                502, "Research", 4000, 30, 50,
                "Archives", 500);

        LibraryAssistant assistant = new LibraryAssistant(
                203, "David", "4163333333",
                503, "Circulation", 2500, "Night", 300);

        // 3. 다형성(Polymorphism)
        LibraryStaff[] staffList = {
                librarian, senior, assistant
        };

        System.out.println("\n=== STAFF ===");

        for (LibraryStaff staff : staffList) {
            staff.displayStaffDetails();

            System.out.println("Compensation: $"
                    + staff.calculateMonthlyCompensation());

            System.out.println();
        }

        // 4. 메소드 오버로딩
        System.out.println("=== LATE FEES ===");
        System.out.println(librarian.calculateLateFee(5));
        System.out.println(librarian.calculateLateFee(5, 2.0));

        // 5. 인터페이스
        System.out.println("\n=== RESERVATIONS ===");

        Reservable reservation = librarian;

        reservation.reserveBook("B101");
        reservation.reserveBook("B101");
        reservation.reserveBook("");

        Reportable report = librarian;
        report.generateReport();

        // 6. 스태틱 카운터
        System.out.println("\n=== TOTAL COUNTS ===");
        System.out.println("Total People: " + Person.getPersonCount());
        System.out.println("Total Members: " + Member.getMemberCount());
        System.out.println("Total Staff: " + LibraryStaff.getStaffCount());

        // 7. 유효성 검사
        System.out.println("\n=== VALIDATION TESTS ===");

        try {
            Member.registerMember(
                    103, "Invalid", "4160000000", 1003, -5);
        }
        catch (IllegalArgumentException e) {
            System.out.println("Registration failed: " + e.getMessage());
        }

        try {
            librarian.calculateLateFee(-3);
        }
        catch (IllegalArgumentException e) {
            System.out.println("Late fee failed: " + e.getMessage());
        }

        System.out.println("Final Person Count: "
                + Person.getPersonCount());
    }
}