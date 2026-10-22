
package library;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class LibraryTests {

    private static int passed = 0;
    private static int failed = 0;

    public static void main(String[] args) {

        System.out.println("=== LIBRARY MANAGEMENT SYSTEM TESTS ===");

        // Test 1: 멤버 등록
        test("Member registration", () -> {
            int before = Member.getMemberCount();

            Member member = Member.registerMember(
                    901, "Test Member", "4161111111", 9001, 25);

            check(member.getName().equals("Test Member"));
            check(Member.getMemberCount() == before + 1);
        });

        // Test 2: 잘못된 나이 확인
        test("Invalid member age", () -> {
            int before = Person.getPersonCount();

            expectException(() -> Member.registerMember(
                    902, "Invalid", "4161111111", 9002, -5));

            check(Person.getPersonCount() == before);
        });

        // 계산 테스트용 직원 생성
        Librarian librarian = new Librarian(
                903, "Alice", "4161111111",
                9003, "General", 3000, 20, 100);

        SeniorLibrarian senior = new SeniorLibrarian(
                904, "Bob", "4162222222",
                9004, "Research", 4000, 30, 50,
                "Archives", 500);

        LibraryAssistant assistant = new LibraryAssistant(
                905, "David", "4163333333",
                9005, "Circulation", 2500, "Night", 300);

        // Test 3: 기본 연체료 계산
        test("Default late fee", () -> {
            check(librarian.calculateLateFee(5) == 5.0);
        });

        // Test 4: 지정 연체료 계산
        test("Custom late fee", () -> {
            check(librarian.calculateLateFee(5, 2.0) == 10.0);
        });

        // Test 5: 잘못된 연체일 확인
        test("Invalid late fee", () -> {
            expectException(() -> librarian.calculateLateFee(-3));
        });

        // Test 6: 사서 월급 계산
        test("Librarian compensation", () -> {
            check(librarian.calculateMonthlyCompensation() == 3200.0);
        });

        // Test 7: 선임 사서 월급 계산
        test("Senior librarian compensation", () -> {
            check(senior.calculateMonthlyCompensation() == 4600.0);
        });

        // Test 8: 보조 직원 월급 계산
        test("Assistant compensation", () -> {
            check(assistant.calculateMonthlyCompensation() == 2800.0);
        });

        // Test 9: 잘못된 선임 수당 확인
        test("Invalid senior librarian allowance", () -> {
            int beforeStaff = LibraryStaff.getStaffCount();
            int beforePeople = Person.getPersonCount();

            expectException(() -> new SeniorLibrarian(
                    906, "Invalid Senior", "4164444444",
                    9006, "Research", 4000, 30, 50,
                    "Archives", -500));

            check(LibraryStaff.getStaffCount() == beforeStaff);
            check(Person.getPersonCount() == beforePeople);
        });

        // Test 10: 잘못된 전문 분야 확인
        test("Invalid senior specialization", () -> {
            int beforeStaff = LibraryStaff.getStaffCount();
            int beforePeople = Person.getPersonCount();

            expectException(() -> new SeniorLibrarian(
                    907, "Invalid Senior", "4165555555",
                    9007, "Research", 4000, 30, 50,
                    "", 500));

            check(LibraryStaff.getStaffCount() == beforeStaff);
            check(Person.getPersonCount() == beforePeople);
        });

        // Test 11: 다형성 확인
        test("Runtime polymorphism", () -> {
            LibraryStaff staff = senior;

            check(staff.calculateMonthlyCompensation() == 4600.0);
            check(staff.getRoleDescription().equals("Senior Librarian"));
        });

        // Test 12: 스태틱 카운터 확인
        test("Static counters", () -> {
            check(Person.getPersonCount() == 4);
            check(Member.getMemberCount() == 1);
            check(LibraryStaff.getStaffCount() == 3);
        });

        // Test 13: 도서 예약 성공
        test("Book reservation success", () -> {
            String result = captureOutput(
                    () -> librarian.reserveBook("B101"));

            check(result.contains("Reservation successful: B101"));
        });

        // Test 14: 중복 예약 확인
        test("Duplicate book reservation", () -> {
            String result = captureOutput(
                    () -> librarian.reserveBook("B101"));

            check(result.contains("Reservation failed: Already reserved"));
        });

        // Test 15: 잘못된 도서 번호 확인
        test("Invalid book reservation", () -> {
            String result = captureOutput(
                    () -> librarian.reserveBook(""));

            check(result.contains("Reservation failed: Invalid book ID"));
        });

        // 테스트 결과 출력
        System.out.println();
        System.out.println("=== TEST RESULTS ===");
        System.out.println("Passed: " + passed);
        System.out.println("Failed: " + failed);
        System.out.println("Total: " + (passed + failed));
    }

    // 각 테스트 실행
    private static void test(String name, Runnable action) {
        try {
            action.run();
            passed++;
            System.out.println("[PASS] " + name);
        } catch (Throwable e) {
            failed++;
            System.out.println("[FAIL] " + name
                    + " - " + e.getMessage());
        }
    }

    // 조건이 맞는지 확인
    private static void check(boolean condition) {
        if (!condition) {
            throw new AssertionError("Expected condition was false");
        }
    }

    // 예외가 발생하는지 확인
    private static void expectException(Runnable action) {
        try {
            action.run();
        } catch (IllegalArgumentException e) {
            return;
        }

        throw new AssertionError("Expected IllegalArgumentException");
    }

    // 콘솔 출력 메시지 확인용
    private static String captureOutput(Runnable action) {
        PrintStream original = System.out;
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        try {
            System.setOut(new PrintStream(output));
            action.run();
        } finally {
            System.setOut(original);
        }

        return output.toString();
    }
}
