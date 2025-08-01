//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.time.LocalDate;

public class MedicalRecord {
    private int recordId;
    private int patientId;
    private int doctorId;
    private LocalDate recordDate;
    private String diagnosis;
    private String prescription;

    public MedicalRecord(int patientId, int doctorId, LocalDate recordDate, String diagnosis, String prescription) {
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.recordDate = recordDate;
        this.diagnosis = diagnosis;
        this.prescription = prescription;
    }

    public MedicalRecord(int recordId, int patientId, int doctorId, LocalDate recordDate, String diagnosis, String prescription) {
        this.recordId = recordId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.recordDate = recordDate;
        this.diagnosis = diagnosis;
        this.prescription = prescription;
    }

    public int getRecordId() {
        return this.recordId;
    }

    public int getPatientId() {
        return this.patientId;
    }

    public int getDoctorId() {
        return this.doctorId;
    }

    public LocalDate getRecordDate() {
        return this.recordDate;
    }

    public String getDiagnosis() {
        return this.diagnosis;
    }

    public String getPrescription() {
        return this.prescription;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public void setRecordDate(LocalDate recordDate) {
        this.recordDate = recordDate;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public void setPrescription(String prescription) {
        this.prescription = prescription;
    }

    public String toString() {
        int var10000 = this.recordId;
        return "MedicalRecord{recordId=" + var10000 + ", patientId=" + this.patientId + ", doctorId=" + this.doctorId + ", recordDate=" + String.valueOf(this.recordDate) + ", diagnosis='" + this.diagnosis + "', prescription='" + this.prescription + "'}";
    }
}