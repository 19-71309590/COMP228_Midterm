package library;

public class Member extends Person {

    private final int memberNumber;
    private int age;
    private String membershipType;
    private double outstandingFees;

    private static int memberCount = 0;

    private Member(int personId, String name, String phoneNumber,
                   int memberNumber, int age, String membershipType) {

        super(personId, name, phoneNumber);

        if (memberNumber <= 0 || age < 1 || age > 120
                || membershipType == null || membershipType.isBlank()) {
            throw new IllegalArgumentException("Invalid member information");
        }

        this.memberNumber = memberNumber;
        this.age = age;
        this.membershipType = membershipType;
        this.outstandingFees = 0;

        memberCount++;
        registerCreatedPerson();
    }

    public static Member registerMember(int personId, String name,
            String phoneNumber, int memberNumber, int age) {

        return new Member(personId, name, phoneNumber,
                          memberNumber, age, "Standard");
    }

    public static Member registerMember(int personId, String name,
            String phoneNumber, int memberNumber, int age,
            String membershipType) {

        return new Member(personId, name, phoneNumber,
                          memberNumber, age, membershipType);
    }

    public static int getMemberCount() {
        return memberCount;
    }

    public void addFee(double amount) {
        if (!Double.isFinite(amount) || amount < 0) {
            throw new IllegalArgumentException("Invalid fee");
        }
        outstandingFees += amount;
    }

    @Override
    public String getRoleDescription() {
        return "Library Member";
    }

    @Override
    public void displayBasicDetails() {
        super.displayBasicDetails();
        System.out.println("Member Number: " + memberNumber);
        System.out.println("Age: " + age);
        System.out.println("Membership: " + membershipType);
        System.out.println("Outstanding Fees: $" + outstandingFees);
    }
}