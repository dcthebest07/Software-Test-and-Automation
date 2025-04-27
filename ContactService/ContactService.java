package org.example;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class ContactService {
    private List<Contact> contacts = new ArrayList<>();
    private Scanner scnr = new Scanner(System.in);

    // Add a new contact
    public void addContact() {
        try {
            System.out.print("Enter ID: ");
            String id = scnr.nextLine();
            System.out.print("Enter First Name: ");
            String firstName = scnr.nextLine();
            System.out.print("Enter Last Name: ");
            String lastName = scnr.nextLine();
            System.out.print("Enter Phone Number: ");
            String phone = scnr.nextLine();
            System.out.print("Enter Address: ");
            String address = scnr.nextLine();

            Contact newContact = new Contact(id, firstName, lastName, phone, address);
            contacts.add(newContact);
            System.out.println("✅ Contact added successfully.");
        } catch (IllegalArgumentException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    public List<Contact> getContactList(){
        return contacts;
    }

    public void deleteContact() {
        System.out.print("Enter ID of contact to delete: ");
        String id = scnr.nextLine();

        boolean removed = contacts.removeIf(contact -> contact.getContactId().equals(id));
        if (removed) {
            System.out.println("✅ Contact removed.");
        } else {
            System.out.println("❌ No contact found with ID: " + id);
        }
    }

    public void updateContact() {
        scnr.nextLine(); // Clear scanner buffer
        System.out.print("Enter Contact ID to update: ");
        String idToUpdate = scnr.nextLine();
    
        for (Contact contact : contacts) {
            if (contact.getContactId().equals(idToUpdate)) {
                boolean keepUpdating = true;
    
                while (keepUpdating) {
                    System.out.println("\nWhich field would you like to update?");
                    System.out.println("1 - First Name");
                    System.out.println("2 - Last Name");
                    System.out.println("3 - Phone Number");
                    System.out.println("4 - Address");
                    System.out.println("5 - Done Updating");
                    System.out.print("Choose an option (1-5): ");
    
                    int choice = scnr.hasNextInt() ? scnr.nextInt() : -1;
                    scnr.nextLine(); // Consume newline
    
                    switch (choice) {
                        case 1 -> {
                            System.out.print("Enter new First Name: ");
                            contact.setFirstName(scnr.nextLine());
                            System.out.println("First name updated.");
                        }
                        case 2 -> {
                            System.out.print("Enter new Last Name: ");
                            contact.setLastName(scnr.nextLine());
                            System.out.println("Last name updated.");
                        }
                        case 3 -> {
                            System.out.print("Enter new Phone Number: ");
                            contact.setPhone(scnr.nextLine());
                            System.out.println("Phone number updated.");
                        }
                        case 4 -> {
                            System.out.print("Enter new Address: ");
                            contact.setAddress(scnr.nextLine());
                            System.out.println("Address updated.");
                        }
                        case 5 -> keepUpdating = false;
                        default -> System.out.println("Invalid choice. Try again.");
                    }
                }
                return;
            }
        }
    
        System.out.println("Contact ID not found.");
    }
    





    public void promptUser() {
        int choice = -1;

        while (choice != 4) {
            System.out.println("\nContact Manager Menu:");
            System.out.println("1 - Add Contact");
            System.out.println("2 - Update Contact");
            System.out.println("3 - Delete Contact");
            System.out.println("4 - Return to Main Menu");
            System.out.print("Choose an option (1-4): ");

            if (scnr.hasNextInt()) {
                choice = scnr.nextInt();
            } else {
                scnr.nextLine(); // Clean buffer
                System.out.println("Invalid input. Try again.");
                continue;
            }

            scnr.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> addContact();
                case 2 -> updateContact();
                case 3 -> deleteContact();
                case 4 -> System.out.println("Returning to Main Menu...");
                default -> System.out.println("Invalid selection.");
            }
        }
    }






}
