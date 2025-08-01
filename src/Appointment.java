//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.time.LocalDate;
import java.time.LocalTime;

public class Appointment {
    private int appointmentId;
    private int patientId;
    private int doctorId;
    private LocalDate appointmentDate;
    private LocalTime appointmentTime;
    private String status;

    public Appointment(int patientId, int doctorId, LocalDate appointmentDate, LocalTime appointmentTime, String status) {
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.status = status;
    }

    public Appointment(int appointmentId, int patientId, int doctorId, LocalDate appointmentDate, LocalTime appointmentTime, String status) {
        this.appointmentId = appointmentId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.status = status;
    }

    public int getAppointmentId() {
        return this.appointmentId;
    }

    public int getPatientId() {
        return this.patientId;
    }

    public int getDoctorId() {
        return this.doctorId;
    }

    public LocalDate getAppointmentDate() {
        return this.appointmentDate;
    }

    public LocalTime getAppointmentTime() {
        return this.appointmentTime;
    }

    public String getStatus() {
        return this.status;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public void setAppointmentTime(LocalTime appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String toString() {
        int var10000 = this.appointmentId;
        return "Appointment{appointmentId=" + var10000 + ", patientId=" + this.patientId + ", doctorId=" + this.doctorId + ", appointmentDate=" + String.valueOf(this.appointmentDate) + ", appointmentTime=" + String.valueOf(this.appointmentTime) + ", status='" + this.status + "'}";
    }
}
