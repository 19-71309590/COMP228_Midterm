package library;

public abstract class Person {

    private final int personId;
    private String name;
    private String phoneNumber;

    private static int personCount = 0;

    public Person(int personId, String name, String phoneNumber) {
        if (personId <= 0 || name == null || name.isBlank()
                || phoneNumber == null || phoneNumber.isBlank()) {
            throw new IllegalArgumentException("Invalid person details");
        }

        this.personId = personId;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    protected static void registerCreatedPerson() {
        personCount++;
    }

    public static int getPersonCount() {
        return personCount;
    }

    public String getName() {
        return name;
    }

    public void displayBasicDetails() {
        System.out.println("Person ID: " + personId);
        System.out.println("Name: " + name);
        System.out.println("Phone: " + phoneNumber);
    }

    @Override
    public String toString() {
        return personId + " - " + name;
    }

    public abstract String getRoleDescription();
}