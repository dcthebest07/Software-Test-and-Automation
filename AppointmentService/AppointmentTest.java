package org.example;

import org.junit.jupiter.api.Test;
import java.util.Date;
import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

class AppointmentTest {

    private Date futureDate() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 1);
        return cal.getTime();
    }

    private Date pastDate() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        return cal.getTime();
    }

    //  Test 1: Appointment ID
    @Test
    void testValidAppointmentId() {
        Appointment appt = new Appointment("12345", futureDate(), "Check-up");
        assertEquals("12345", appt.getAppointmentId());
    }

    //  Test 2: Appointment Date
    @Test
    void testValidAppointmentDate() {
        Date date = futureDate();
        Appointment appt = new Appointment("12345", date, "Check-up");
        assertEquals(date, appt.getAppointmentDate());
    }

    //  Test 3: Description
    @Test
    void testValidDescription() {
        Appointment appt = new Appointment("12345", futureDate(), "Check-up");
        assertEquals("Check-up", appt.getAppointmentDescription());
    }

    //  Invalid ID: null
    @Test
    void testAppointmentIdCannotBeNull() {
        assertThrows(IllegalArgumentException.class, () -> new Appointment(null, futureDate(), "Test"));
    }

    //  Invalid ID: too long
    @Test
    void testAppointmentIdTooLong() {
        assertThrows(IllegalArgumentException.class, () -> new Appointment("12345678901", futureDate(), "Test"));
    }

    //  Invalid Date: null
    @Test
    void testAppointmentDateCannotBeNull() {
        assertThrows(IllegalArgumentException.class, () -> new Appointment("123", null, "Test"));
    }

    //  Invalid Date: in the past
    @Test
    void testAppointmentDateCannotBeInPast() {
        assertThrows(IllegalArgumentException.class, () -> new Appointment("123", pastDate(), "Test"));
    }

    //  Invalid Description: null
    @Test
    void testAppointmentDescriptionCannotBeNull() {
        assertThrows(IllegalArgumentException.class, () -> new Appointment("123", futureDate(), null));
    }

    //  Invalid Description: too long
    @Test
    void testAppointmentDescriptionTooLong() {
        String longDescription = "This description is way too long and should not be allowed at all by the constructor!";
        assertThrows(IllegalArgumentException.class, () -> new Appointment("123", futureDate(), longDescription));
    }
}

