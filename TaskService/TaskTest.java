package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {

    // ✅ ID Tests
    @Test
    public void testValidTaskId() {
        Task task = new Task("12345", "Title", "Description");
        assertEquals("12345", task.getTaskID());
    }

    @Test
    public void testNullTaskIdThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> new Task(null, "Title", "Description"));
    }

    @Test
    public void testTaskIdTooLongThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> new Task("12345678901", "Title", "Description"));
    }

    // ✅ Title Tests
    @Test
    public void testValidTitle() {
        Task task = new Task("123", "Valid Title", "Description");
        assertEquals("Valid Title", task.getName());
    }

    @Test
    public void testNullTitleThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> new Task("123", null, "Description"));
    }

    @Test
    public void testTitleTooLongThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> new Task("123", "This title is way too long", "Description"));
    }

    // ✅ Description Tests
    @Test
    public void testValidDescription() {
        Task task = new Task("123", "Title", "This is a valid description.");
        assertEquals("This is a valid description.", task.getDesc());
    }

    @Test
    public void testNullDescriptionThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> new Task("123", "Title", null));
    }

    @Test
    public void testDescriptionTooLongThrowsException() {
        String longDesc = "This description is way too long and will definitely exceed fifty characters.";
        assertThrows(IllegalArgumentException.class, () -> new Task("123", "Title", longDesc));
    }
}
