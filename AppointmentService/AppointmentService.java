package org.example;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
// The appointment service shall be able to add appointments with a unique appointment ID.
// The appointment service shall be able to delete appointments     per appointment ID.

public class AppointmentService {
    private Scanner scnr = new Scanner(System.in);
    private List<Appointment> appointmentList = new ArrayList<>();
    private TaskService taskService;
    
    public AppointmentService(TaskService taskService) {
        this.taskService = taskService;
    }

    public void promptUser(){
        int num = -1;
        while (num != 4){
            System.out.println("Appointment Service Options:");
            System.out.println("1 - Add Appointment");
            System.out.println("2 - Remove Appointment");
            System.out.println("3 - List Appointments");
            System.out.println("4 - Exit");
            System.out.print("Choose an option (1-4): ");
    
            if (scnr.hasNextInt()) {
                num = scnr.nextInt();
            } else {
                scnr.nextLine(); // discard non-integer input
                System.out.println("Invalid input. Please enter a number 1-3.\n");
                continue; // skip to next loop iteration
            }
    
            switch (num) {
                case 1 -> addAppointment();
                case 2 -> delAppointment();
                case 3 -> listAppointments();
                case 4 -> {
                    System.out.println("Returning to Main Menu...");
                    taskService.promptMainMenu();
                    return;
                }
                default -> System.out.println("Please enter a valid option (1-4).\n");
            }
        }
    }
    

    public List<Appointment> listAppointments() {
        if (appointmentList.isEmpty()) {
            System.out.println("No appointments found.\n");
        
        }
    
        System.out.println("All Appointments:");
        for (Appointment appt : appointmentList) {
            System.out.println("ID: " + appt.getAppointmentId());
            System.out.println("Date: " + appt.getAppointmentDate());
            System.out.println("Description: " + appt.getAppointmentDescription());
            System.out.println("-----------------------");
        }
        return appointmentList;
    }

public void delAppointment() {
    scnr.nextLine(); // Clean buffer
    System.out.print("Enter Appointment ID to delete: ");
    String idToDelete = scnr.nextLine();

    boolean removed = appointmentList.removeIf(t -> t.getAppointmentId().equals(idToDelete));

    if (removed) {
        System.out.println("Appointment with ID " + idToDelete + " was deleted successfully.\n");
    } else {
        System.out.println("No appointment found with ID " + idToDelete + ".\n");
    }
}



public void addAppointment() {
    String appointmentId;
    Date appointmentDate;
    String appointmentDescription;
    String stringToDate;
    Date date = null;

    scnr.nextLine();

    System.out.println("Enter Appointment ID: ");
    appointmentId = scnr.nextLine();
    System.out.println("Enter Appointment Date in format yyyy-MM-dd");
    stringToDate = scnr.nextLine();


    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date = formatter.parse(stringToDate);
        } catch (ParseException e) {
            System.out.println("Error parsing date: " + e.getMessage());
        }
        appointmentDate = date;
    System.out.println("Enter an Appointment Description: ");
    appointmentDescription = scnr.nextLine();
Appointment appointment = new Appointment(appointmentId, appointmentDate, appointmentDescription);
appointmentList.add(appointment);
listAppointments();
}

public void addAppointment(Appointment appt) {
    for (Appointment existingAppt : appointmentList) {
        if (existingAppt.getAppointmentId().equals(appt.getAppointmentId())) {
            throw new IllegalArgumentException("Appointment ID already exists: " + appt.getAppointmentId());
        }
    }
    appointmentList.add(appt);
}


public void deleteAppointment(String str) {
    boolean removed = appointmentList.removeIf(appt -> appt.getAppointmentId().equals(str));
    
    if (!removed) {
        throw new IllegalArgumentException("Appointment not found");
    }
}


public Appointment getAppointmentById(String id) {
    for (Appointment appt : appointmentList) {
        if (appt.getAppointmentId().equals(id)) {
            return appt;
        }
    }
    throw new IllegalArgumentException("Appointment not found");
}



}
