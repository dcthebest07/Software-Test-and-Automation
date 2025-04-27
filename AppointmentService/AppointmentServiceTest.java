package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class AppointmentServiceTest {

    private AppointmentService service;

    @BeforeEach
    void setUp() {
        service = new AppointmentService(null);
    }

    // Utility: Create a valid future date
    private Date getFutureDate() {
        return new Date(System.currentTimeMillis() + 100000); // +~1 minute
    }

    //  Test: Add valid appointment
    @Test
    void testAddAppointmentSuccessfully() {
        Appointment appt = new Appointment("A123", getFutureDate(), "Doctor Visit");
        service.addAppointment(appt); 
        assertEquals(1, service.listAppointments().size()); // expose getter for testing
    }

    // Test: Add appointment with duplicate ID throws exception
    @Test
    void testAddDuplicateAppointmentThrowsException() {
        Appointment appt1 = new Appointment("DUPL1", getFutureDate(), "Meeting");
        Appointment appt2 = new Appointment("DUPL1", getFutureDate(), "Second Meeting");
        service.addAppointment(appt1);
        assertThrows(IllegalArgumentException.class, () -> service.addAppointment(appt2));
    }

    //  Test: Delete existing appointment
    @Test
    void testDeleteAppointmentSuccessfully() {
        Appointment appt = new Appointment("DELME", getFutureDate(), "Delete This");
        service.addAppointment(appt);
        service.deleteAppointment("DELME");
        assertEquals(0, service.listAppointments().size());
    }

    //  Test: Delete nonexistent appointment
    @Test
    void testDeleteNonExistentAppointmentThrowsException() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> service.deleteAppointment("XYZ"));
        assertEquals("Appointment not found", e.getMessage());
    }

    // Test: Appointment ID must be unique
    @Test
    void testCannotAddDuplicateId() {
        Appointment appt = new Appointment("A1", getFutureDate(), "First Appt");
        service.addAppointment(appt);
        Appointment duplicate = new Appointment("A1", getFutureDate(), "Dup Appt");
        assertThrows(IllegalArgumentException.class, () -> service.addAppointment(duplicate));
    }

    // Test: Get appointment by ID
    @Test
    void testGetAppointmentById() {
        Appointment appt = new Appointment("GET1", getFutureDate(), "Lookup Test");
        service.addAppointment(appt);
        Appointment found = service.getAppointmentById("GET1");
        assertEquals("GET1", found.getAppointmentId());
    }

    //  Test: Null ID not allowed
    @Test
    void testNullIdNotAllowed() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Appointment(null, getFutureDate(), "Null ID");
        });
    }

    //  Test: Long ID not allowed
    @Test
    void testIdTooLong() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Appointment("TOO_LONG_ID_123", getFutureDate(), "Too long");
        });
    }

    //  Test: Null date not allowed
    @Test
    void testNullDateNotAllowed() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Appointment("X1", null, "Missing date");
        });
    }

    //  Test: Past date not allowed
    @Test
    void testPastDateNotAllowed() {
        Date pastDate = new Date(System.currentTimeMillis() - 100000); // 1 min ago
        assertThrows(IllegalArgumentException.class, () -> {
            new Appointment("P1", pastDate, "In the past");
        });
    }

    //  Test: Null description not allowed
    @Test
    void testNullDescriptionNotAllowed() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Appointment("Y1", getFutureDate(), null);
        });
    }

    //  Test: Description too long not allowed
    @Test
    void testDescriptionTooLong() {
        String longDescription = "x".repeat(51);
        assertThrows(IllegalArgumentException.class, () -> {
            new Appointment("Z1", getFutureDate(), longDescription);
        });
    }
}
