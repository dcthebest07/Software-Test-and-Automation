package org.example;

import java.util.Date;

public class Appointment {

// The appointment object shall have a required unique appointment ID string that cannot be longer than 10 characters. The appointment ID shall not be null and shall not be updatable.
// The appointment object shall have a required appointment Date field. The appointment Date field cannot be in the past. The appointment Date field shall not be null.
// Note: Use java.util.Date for the appointmentDate field and use before(new Date()) to check if the date is in the past.
// The appointment object shall have a required description String field that cannot be longer than 50 characters. The description field shall not be null.

private final String appointmentId; // cannot be longer than 10 characters, no update, not null
private Date appointmentDate; // cannot be in the past, not null
private String appointmentDescription; // cannot be longer than 50 characters

public Appointment(String appointmentId, Date appointmentDate, String appointmentDescription) {
    if (appointmentId == null || appointmentId.length() > 10)
        throw new IllegalArgumentException("Appointment ID must not be null or longer than 10 characters.");
    if (appointmentDate == null || appointmentDate.before(new Date()))
        throw new IllegalArgumentException("Appointment date must not be null or in the past.");
    if (appointmentDescription == null || appointmentDescription.length() > 50)
        throw new IllegalArgumentException("Description must not be null or longer than 50 characters.");

    this.appointmentId = appointmentId;
    this.appointmentDate = appointmentDate;
    this.appointmentDescription = appointmentDescription;
}


public String getAppointmentId(){
    return this.appointmentId;
}

public Date getAppointmentDate(){
return this.appointmentDate;
}

public String getAppointmentDescription(){
return this.appointmentDescription;
}


}
