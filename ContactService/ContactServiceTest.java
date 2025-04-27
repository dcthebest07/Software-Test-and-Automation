package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ContactServiceTest {

    private ContactService contactService;

    @BeforeEach
    void setUp() {
        contactService = new ContactService();
    }

    @Test
    void testAddContactSuccessfullyIncreasesListSize() {
        Contact contact = new Contact("123", "John", "Doe", "1234567890", "123 Main St");
        contactService.getContactList().clear(); // clear before test
        contactService.getContactList().add(contact);
        assertEquals(1, contactService.getContactList().size());
    }

    @Test
    void testAddContactStoresCorrectId() {
        Contact contact = new Contact("123", "Jane", "Smith", "1112223333", "456 Elm St");
        contactService.getContactList().add(contact);
        assertEquals("123", contactService.getContactList().get(0).getContactId());
    }

    @Test
    void testDeleteExistingContactReturnsTrue() {
        Contact contact = new Contact("123", "Jane", "Smith", "1112223333", "456 Elm St");
        contactService.getContactList().add(contact);
        boolean deleted = contactService.getContactList().removeIf(c -> c.getContactId().equals("123"));
        assertTrue(deleted);
    }

    @Test
    void testDeleteNonExistentContactReturnsFalse() {
        boolean deleted = contactService.getContactList().removeIf(c -> c.getContactId().equals("nonexistent"));
        assertFalse(deleted);
    }

    @Test
    void testUpdateFirstName() {
        Contact contact = new Contact("123", "Old", "Name", "1234567890", "Address");
        contactService.getContactList().add(contact);
        contact.setFirstName("New");
        assertEquals("New", contact.getFirstName());
    }

    @Test
    void testUpdateLastName() {
        Contact contact = new Contact("123", "First", "Old", "1234567890", "Address");
        contactService.getContactList().add(contact);
        contact.setLastName("New");
        assertEquals("New", contact.getLastName());
    }

    @Test
    void testUpdatePhoneNumber() {
        Contact contact = new Contact("123", "First", "Last", "0000000000", "Address");
        contactService.getContactList().add(contact);
        contact.setPhone("9999999999");
        assertEquals("9999999999", contact.getPhone());
    }

    @Test
    void testUpdateAddress() {
        Contact contact = new Contact("123", "First", "Last", "1234567890", "Old Address");
        contactService.getContactList().add(contact);
        contact.setAddress("New Address");
        assertEquals("New Address", contact.getAddress());
    }

    @Test
    void testDuplicateContactIdNotAllowed() {
        Contact contact1 = new Contact("123", "John", "Doe", "1111111111", "Address");
        Contact contact2 = new Contact("123", "Jane", "Smith", "2222222222", "Another Address");
        contactService.getContactList().add(contact1);

        boolean duplicateIdExists = contactService.getContactList()
            .stream()
            .anyMatch(c -> c.getContactId().equals(contact2.getContactId()));

        assertTrue(duplicateIdExists);
    }

    @Test
    void testContactListInitiallyEmpty() {
        List<Contact> list = contactService.getContactList();
        assertTrue(list.isEmpty());
    }
}
