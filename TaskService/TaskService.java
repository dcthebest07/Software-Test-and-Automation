package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class TaskService {

    private ContactService contactService = new ContactService();
    private AppointmentService appointmentService = new AppointmentService(this);
    private List<Task> taskList = new ArrayList<>();
    private Scanner scnr = new Scanner(System.in);
    

    public List<Task> startList() {
        return taskList;
    }

    public void addTask() {
        scnr.nextLine(); // Clear buffer

        System.out.print("Enter Task ID: ");
        String id = scnr.nextLine();

        for (Task t : taskList) {
            if (t.getTaskID().equals(id)) {
                System.out.println("Task ID must be unique. This ID already exists.\n");
                return;
            }
        }

        System.out.print("Enter Task Name: ");
        String name = scnr.nextLine();

        System.out.print("Enter Task Description: ");
        String desc = scnr.nextLine();

        try {
            Task newTask = new Task(id, name, desc);
            taskList.add(newTask);
            System.out.println("Task added successfully.\n");
            listTasks();
        } catch (IllegalArgumentException e) {
            System.out.println("Failed to add task: " + e.getMessage() + "\n");
        }
    }

    public void listTasks() {
        if (taskList.isEmpty()) {
            System.out.println("No tasks to show.\n");
            return;
        }

        System.out.println("Current Task List:");
        for (Task t : taskList) {
            System.out.println("ID: " + t.getTaskID() + " | Name: " + t.getName() + " | Desc: " + t.getDesc());
        }
    }

    public void delTask() {
        scnr.nextLine(); // Clean buffer
        System.out.print("Enter Task ID to delete: ");
        String idToDelete = scnr.nextLine();

        boolean removed = taskList.removeIf(t -> t.getTaskID().equals(idToDelete));

        if (removed) {
            System.out.println("Task with ID " + idToDelete + " was deleted successfully.\n");
        } else {
            System.out.println("No task found with ID " + idToDelete + ".\n");
        }
    }

    public void updateTask() {
        scnr.nextLine(); // Clean buffer
        System.out.print("Enter Task ID to update: ");
        String id = scnr.nextLine();

        for (Task task : taskList) {
            if (task.getTaskID().equals(id)) {
                System.out.print("Enter new Task Name: ");
                String newName = scnr.nextLine();

                System.out.print("Enter new Task Description: ");
                String newDesc = scnr.nextLine();

                try {
                    task.setName(newName);
                    task.setDescription(newDesc);
                    System.out.println("Task updated successfully.\n");
                } catch (IllegalArgumentException e) {
                    System.out.println("Update failed: " + e.getMessage() + "\n");
                }
                return;
            }
        }

        System.out.println("Task with ID " + id + " not found.\n"); 
    }

    public void promptUser() {
        int num = -1;
        while (num != 4) {
            System.out.println("Task Manager Options:");
            System.out.println("1 - Add Task");
            System.out.println("2 - Remove Task");
            System.out.println("3 - Update Task");
            System.out.println("4 - Exit");
            System.out.print("Choose an option (1-4): ");

            if (scnr.hasNextInt()) {
                num = scnr.nextInt();
            } else {
                scnr.next(); // discard non-integer input
                System.out.println("Invalid input. Please enter a number 1-4.\n");
                continue;
            }

            switch (num) {
                case 1 -> addTask();
                case 2 -> delTask();
                case 3 -> updateTask();
                case 4 -> System.out.println("Exiting Task Manager...");
                default -> System.out.println("Please enter a valid option (1-4).\n");
            }
        }
    }
    public void promptMainMenu() {
        int choice = -1;

        while (choice != 3) {
            System.out.println("\nMain Menu:");
            System.out.println("1 - Task Manager");
            System.out.println("2 - Contact Manager");
            System.out.println("3 - Appointment Manager");
            System.out.println("4 - Exit");
            System.out.print("Choose an option (1-4): ");

            if (scnr.hasNextInt()) {
                choice = scnr.nextInt();
            } else {
                scnr.nextLine(); // consume invalid input
                System.out.println("Invalid input. Try again.");
                continue;
            }

            switch (choice) {
                case 1 -> this.promptUser();
                case 2 -> contactService.promptUser();
                case 3 -> appointmentService.promptUser();
                case 4 -> {
                    System.out.println("Goodbye!");
                    System.exit(0); 
                }
                
                default -> System.out.println("Please enter a valid option.");
            }
        }
    }

    public static void main(String[] args) {
        TaskService service = new TaskService();

        service.promptMainMenu();
    }
}
