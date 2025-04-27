package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TaskServiceTest {

    private TaskService taskService;

    @BeforeEach
    void setUp() {
        taskService = new TaskService();
    }

    @Test
    void testStartListInitiallyEmpty() {
        List<Task> tasks = taskService.startList();
        assertTrue(tasks.isEmpty());
    }

    @Test
    void testAddTaskValid() {
        taskService.startList().add(new Task("001", "Read", "Read a book"));
        List<Task> tasks = taskService.startList();
        assertEquals(1, tasks.size());
        assertEquals("Read", tasks.get(0).getName());
    }

    @Test
    void testUpdateTask() {
        Task task = new Task("002", "Wash", "Wash dishes");
        taskService.startList().add(task);
        task.setName("Clean");
        task.setDescription("Clean the kitchen");

        assertEquals("Clean", task.getName());
        assertEquals("Clean the kitchen", task.getDesc());
    }

    @Test
    void testDeleteTask() {
        Task task = new Task("003", "Code", "Write Java");
        taskService.startList().add(task);
        boolean removed = taskService.startList().removeIf(t -> t.getTaskID().equals("003"));
        assertTrue(removed);
        assertTrue(taskService.startList().isEmpty());
    }

    @Test
    void testTaskIdUniqueness() {
        Task task1 = new Task("004", "Study", "Study algorithms");
        Task task2 = new Task("004", "Review", "Review concepts");
        taskService.startList().add(task1);

        boolean isDuplicate = taskService.startList().stream()
                .anyMatch(t -> t.getTaskID().equals(task2.getTaskID()));

        assertTrue(isDuplicate);
    }
}
