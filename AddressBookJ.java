import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Contact implements Serializable {
    private String name;
    private String phoneNumber;
    private String emailAddress;

    public Contact(String name, String phoneNumber, String emailAddress) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }

    // Getters and Setters (You can add more as per additional attributes)

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Phone Number: " + phoneNumber + ", Email Address: " + emailAddress;
    }
}

class AddressBook {
    private List<Contact> contacts;
    private static final String FILE_NAME = "contacts.dat"; // File name for storing contact data

    public AddressBook() {
        if (!loadDataFromFile()) {
            contacts = new ArrayList<>();
        }
    }

    public void addContact(Contact contact) {
        contacts.add(contact);
        saveDataToFile();
    }

    public boolean removeContact(String name) {
        Contact contactToRemove = null;
        for (Contact contact : contacts) {
            if (contact.getName().equalsIgnoreCase(name)) {
                contactToRemove = contact;
                break;
            }
        }
        if (contactToRemove != null) {
            contacts.remove(contactToRemove);
            saveDataToFile();
            return true;
        }
        return false;
    }

    public Contact searchContact(String name) {
        for (Contact contact : contacts) {
            if (contact.getName().equalsIgnoreCase(name)) {
                return contact;
            }
        }
        return null;
    }

    public void editContact(String name, String newPhoneNumber, String newEmailAddress) {
        for (Contact contact : contacts) {
            if (contact.getName().equalsIgnoreCase(name)) {
                contact.setPhoneNumber(newPhoneNumber);
                contact.setEmailAddress(newEmailAddress);
                saveDataToFile();
                break;
            }
        }
    }

    public List<Contact> getAllContacts() {
        return contacts;
    }

    public void saveDataToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(contacts);
            System.out.println("Contact data saved to file: " + FILE_NAME);
        } catch (IOException e) {
            System.out.println("Error while saving contact data: " + e.getMessage());
        }
    }

    public boolean loadDataFromFile() {
        File file = new File(FILE_NAME);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
                contacts = (List<Contact>) ois.readObject();
                System.out.println("Contact data loaded from file: " + FILE_NAME);
                return true;
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Error while loading contact data: " + e.getMessage());
            }
        }
        return false;
    }
}

public class AddressBookJ {
    private static Scanner scanner = new Scanner(System.in);
    private static AddressBook addressBook = new AddressBook();

    public static void main(String[] args) {
        boolean exit = false;

        while (!exit) {
            System.out.println("======= Address Book System =======");
            System.out.println("1. Add Contact");
            System.out.println("2. Remove Contact");
            System.out.println("3. Search Contact");
            System.out.println("4. Edit Contact");
            System.out.println("5. Display All Contacts");
            System.out.println("6. Save Data to File");
            System.out.println("7. Exit");
            System.out.print("Enter your choice (1-7): ");

            int choice = validateIntInput(1, 7);

            switch (choice) {
                case 1:
                    addContact();
                    break;
                case 2:
                    removeContact();
                    break;
                case 3:
                    searchContact();
                    break;
                case 4:
                    editContact();
                    break;
                case 5:
                    displayAllContacts();
                    break;
                case 6:
                    addressBook.saveDataToFile();
                    break;
                case 7:
                    addressBook.saveDataToFile(); // Save data before exiting
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addContact() {
        System.out.print("Enter the name of the contact: ");
        String name = validateStringInput();

        System.out.print("Enter the phone number: ");
        String phoneNumber = validatePhoneNumber();

        System.out.print("Enter the email address: ");
        String emailAddress = validateEmailAddress();

        Contact contact = new Contact(name, phoneNumber, emailAddress);
        addressBook.addContact(contact);
        System.out.println("Contact added successfully!");
    }

    private static void removeContact() {
        System.out.print("Enter the name of the contact to remove: ");
        String name = validateStringInput();

        if (addressBook.removeContact(name)) {
            System.out.println("Contact removed successfully!");
        } else {
            System.out.println("Contact not found!");
        }
    }

    private static void searchContact() {
        System.out.print("Enter the name of the contact to search: ");
        String name = validateStringInput();

        Contact contact = addressBook.searchContact(name);
        if (contact != null) {
            System.out.println("Contact found:");
            System.out.println(contact);
        } else {
            System.out.println("Contact not found!");
        }
    }

    private static void editContact() {
        System.out.print("Enter the name of the contact to edit: ");
        String name = validateStringInput();

        Contact contact = addressBook.searchContact(name);
        if (contact != null) {
            System.out.print("Enter the new phone number: ");
            String newPhoneNumber = validatePhoneNumber();

            System.out.print("Enter the new email address: ");
            String newEmailAddress = validateEmailAddress();

            addressBook.editContact(name, newPhoneNumber, newEmailAddress);
            System.out.println("Contact details updated successfully!");
        } else {
            System.out.println("Contact not found!");
        }
    }

    private static void displayAllContacts() {
        List<Contact> contacts = addressBook.getAllContacts();
        if (contacts.isEmpty()) {
            System.out.println("No contacts found.");
        } else {
            System.out.println("======= All Contacts =======");
            for (Contact contact : contacts) {
                System.out.println(contact);
            }
        }
    }

    private static String validateStringInput() {
        String input = scanner.nextLine().trim();
        while (input.isEmpty()) {
            System.out.println("This field cannot be left empty. Please try again: ");
            input = scanner.nextLine().trim();
        }
        return input;
    }

    private static String validatePhoneNumber() {
        String phoneNumber = scanner.nextLine().trim();
        while (!phoneNumber.matches("\\d{10}")) {
            System.out.println("Invalid phone number. Please enter a 10-digit number: ");
            phoneNumber = scanner.nextLine().trim();
        }
        return phoneNumber;
    }

    private static String validateEmailAddress() {
        String emailAddress = scanner.nextLine().trim();
        while (!emailAddress.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
            System.out.println("Invalid email address. Please enter a valid email: ");
            emailAddress = scanner.nextLine().trim();
        }
        return emailAddress;
    }

    private static int validateIntInput(int min, int max) {
        while (true) {
            try {
                int value = Integer.parseInt(scanner.nextLine().trim());
                if (value < min || value > max) {
                    throw new NumberFormatException();
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer between " + min + " and " + max + ": ");
            }
        }
    }
}
