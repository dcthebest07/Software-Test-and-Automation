package org.example;


public class Task {
    private final String taskID;
    private String taskName;
    private String taskDescription;

    public Task(String taskID, String taskName, String taskDescription) {
        if (taskID == null || taskID.length() > 10 || taskID.isEmpty()) {
            throw new IllegalArgumentException("Task ID must be non-null and <= 10 characters.");
        }
        if (taskName == null || taskName.length() > 20 || taskName.isEmpty()) {
            throw new IllegalArgumentException("Task name must be non-null and <= 20 characters.");
        }
        if (taskDescription == null || taskDescription.length() > 50 || taskDescription.isEmpty()) {
            throw new IllegalArgumentException("Task description must be non-null and <= 50 characters.");
        }

        this.taskID = taskID;
        this.taskName = taskName;
        this.taskDescription = taskDescription;
    }


    public String getTaskID() {
        return this.taskID;
    }

    public String getName() {
        return this.taskName;
    }

    public String getDesc() {
        return this.taskDescription;
    }

    public void setName(String name) {
        if (name == null || name.length() > 20 || name.isEmpty()) {
            throw new IllegalArgumentException("Task name must be non-null and <= 20 characters.");
        }
        this.taskName = name;
    }

    public void setDescription(String description) {
        if (description == null || description.length() > 50 || description.isEmpty()) {
            throw new IllegalArgumentException("Task description must be non-null and <= 50 characters.");
        }
        this.taskDescription = description;
    }
}