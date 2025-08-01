//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager implements IPatientDAO, IDoctorDAO, IAppointmentDAO, IMedicalRecordDAO {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/hospital_management_db";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "123";
    private Connection connection;

    public DatabaseManager() {
        try {
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/hospital_management_db", "postgres", "123");
            System.out.println("Connected to the PostgreSQL database successfully!");
            this.createTables();
        } catch (ClassNotFoundException e) {
            System.err.println("PostgreSQL JDBC Driver not found. Make sure it's in your classpath.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Failed to connect to the database or create tables.");
            e.printStackTrace();
        }

    }

    public Connection getConnection() throws SQLException {
        if (this.connection == null || this.connection.isClosed()) {
            this.connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/hospital_management_db", "postgres", "123");
        }

        return this.connection;
    }

    private void createTables() {
        String createPatientsTableSQL = "CREATE TABLE IF NOT EXISTS patients (patient_id SERIAL PRIMARY KEY,first_name VARCHAR(100) NOT NULL,last_name VARCHAR(100) NOT NULL,date_of_birth DATE,gender VARCHAR(10),address VARCHAR(255),phone_number VARCHAR(20),email VARCHAR(100) UNIQUE);";
        String createDoctorsTableSQL = "CREATE TABLE IF NOT EXISTS doctors (doctor_id SERIAL PRIMARY KEY,first_name VARCHAR(100) NOT NULL,last_name VARCHAR(100) NOT NULL,specialization VARCHAR(100),phone_number VARCHAR(20),email VARCHAR(100) UNIQUE);";
        String createAppointmentsTableSQL = "CREATE TABLE IF NOT EXISTS appointments (appointment_id SERIAL PRIMARY KEY,patient_id INT NOT NULL,doctor_id INT NOT NULL,appointment_date DATE NOT NULL,appointment_time TIME NOT NULL,status VARCHAR(50) NOT NULL,FOREIGN KEY (patient_id) REFERENCES patients(patient_id) ON DELETE RESTRICT,FOREIGN KEY (doctor_id) REFERENCES doctors(doctor_id) ON DELETE RESTRICT);";
        String createMedicalRecordsTableSQL = "CREATE TABLE IF NOT EXISTS medical_records (record_id SERIAL PRIMARY KEY,patient_id INT NOT NULL,doctor_id INT NOT NULL,record_date DATE NOT NULL,diagnosis TEXT,prescription TEXT,FOREIGN KEY (patient_id) REFERENCES patients(patient_id) ON DELETE RESTRICT,FOREIGN KEY (doctor_id) REFERENCES doctors(doctor_id) ON DELETE RESTRICT);";

        try (Statement stmt = this.connection.createStatement()) {
            stmt.execute(createPatientsTableSQL);
            stmt.execute(createDoctorsTableSQL);
            stmt.execute(createAppointmentsTableSQL);
            stmt.execute(createMedicalRecordsTableSQL);
            System.out.println("All tables (patients, doctors, appointments, medical_records) checked/created successfully.");
        } catch (SQLException e) {
            System.err.println("Error creating tables: " + e.getMessage());
            e.printStackTrace();
        }

    }

    public boolean addPatient(Patient patient) {
        String insertSQL = "INSERT INTO patients (first_name, last_name, date_of_birth, gender, address, phone_number, email) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            boolean var5;
            try (PreparedStatement pstmt = this.connection.prepareStatement(insertSQL)) {
                pstmt.setString(1, patient.getFirstName());
                pstmt.setString(2, patient.getLastName());
                pstmt.setDate(3, Date.valueOf(patient.getDateOfBirth()));
                pstmt.setString(4, patient.getGender());
                pstmt.setString(5, patient.getAddress());
                pstmt.setString(6, patient.getPhoneNumber());
                pstmt.setString(7, patient.getEmail());
                int rowsAffected = pstmt.executeUpdate();
                var5 = rowsAffected > 0;
            }

            return var5;
        } catch (SQLException e) {
            System.err.println("Error adding patient: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public Patient getPatientById(int patientId) {
        String selectSQL = "SELECT patient_id, first_name, last_name, date_of_birth, gender, address, phone_number, email FROM patients WHERE patient_id = ?";

        try (PreparedStatement pstmt = this.connection.prepareStatement(selectSQL)) {
            pstmt.setInt(1, patientId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Patient(rs.getInt("patient_id"), rs.getString("first_name"), rs.getString("last_name"), rs.getDate("date_of_birth") != null ? rs.getDate("date_of_birth").toLocalDate() : null, rs.getString("gender"), rs.getString("address"), rs.getString("phone_number"), rs.getString("email"));
            }
        } catch (SQLException e) {
            System.err.println("Error getting patient by ID: " + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    public List<Patient> getAllPatients() {
        List<Patient> patients = new ArrayList();
        String selectSQL = "SELECT patient_id, first_name, last_name, date_of_birth, gender, address, phone_number, email FROM patients ORDER BY patient_id";

        try (
                Statement stmt = this.connection.createStatement();
                ResultSet rs = stmt.executeQuery(selectSQL);
        ) {
            while(rs.next()) {
                patients.add(new Patient(rs.getInt("patient_id"), rs.getString("first_name"), rs.getString("last_name"), rs.getDate("date_of_birth") != null ? rs.getDate("date_of_birth").toLocalDate() : null, rs.getString("gender"), rs.getString("address"), rs.getString("phone_number"), rs.getString("email")));
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving patients: " + e.getMessage());
            e.printStackTrace();
        }

        return patients;
    }

    public boolean updatePatient(Patient patient) {
        String updateSQL = "UPDATE patients SET first_name = ?, last_name = ?, date_of_birth = ?, gender = ?, address = ?, phone_number = ?, email = ? WHERE patient_id = ?";

        try {
            boolean var5;
            try (PreparedStatement pstmt = this.connection.prepareStatement(updateSQL)) {
                pstmt.setString(1, patient.getFirstName());
                pstmt.setString(2, patient.getLastName());
                pstmt.setDate(3, Date.valueOf(patient.getDateOfBirth()));
                pstmt.setString(4, patient.getGender());
                pstmt.setString(5, patient.getAddress());
                pstmt.setString(6, patient.getPhoneNumber());
                pstmt.setString(7, patient.getEmail());
                pstmt.setInt(8, patient.getPatientId());
                int rowsAffected = pstmt.executeUpdate();
                var5 = rowsAffected > 0;
            }

            return var5;
        } catch (SQLException e) {
            System.err.println("Error updating patient: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean deletePatient(int patientId) {
        String deleteSQL = "DELETE FROM patients WHERE patient_id = ?";

        try {
            boolean var5;
            try (PreparedStatement pstmt = this.connection.prepareStatement(deleteSQL)) {
                pstmt.setInt(1, patientId);
                int rowsAffected = pstmt.executeUpdate();
                var5 = rowsAffected > 0;
            }

            return var5;
        } catch (SQLException e) {
            System.err.println("Error deleting patient: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public Patient getPatientByName(String firstName, String lastName) {
        String selectSQL = "SELECT patient_id, first_name, last_name, date_of_birth, gender, address, phone_number, email FROM patients WHERE first_name = ? AND last_name = ?";

        try (PreparedStatement pstmt = this.connection.prepareStatement(selectSQL)) {
            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Patient(rs.getInt("patient_id"), rs.getString("first_name"), rs.getString("last_name"), rs.getDate("date_of_birth") != null ? rs.getDate("date_of_birth").toLocalDate() : null, rs.getString("gender"), rs.getString("address"), rs.getString("phone_number"), rs.getString("email"));
            }
        } catch (SQLException e) {
            System.err.println("Error getting patient by name: " + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    public boolean addDoctor(Doctor doctor) {
        String insertSQL = "INSERT INTO doctors (first_name, last_name, specialization, phone_number, email) VALUES (?, ?, ?, ?, ?)";

        try {
            boolean var5;
            try (PreparedStatement pstmt = this.connection.prepareStatement(insertSQL)) {
                pstmt.setString(1, doctor.getFirstName());
                pstmt.setString(2, doctor.getLastName());
                pstmt.setString(3, doctor.getSpecialization());
                pstmt.setString(4, doctor.getPhoneNumber());
                pstmt.setString(5, doctor.getEmail());
                int rowsAffected = pstmt.executeUpdate();
                var5 = rowsAffected > 0;
            }

            return var5;
        } catch (SQLException e) {
            System.err.println("Error adding doctor: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public Doctor getDoctorById(int doctorId) {
        String selectSQL = "SELECT doctor_id, first_name, last_name, specialization, phone_number, email FROM doctors WHERE doctor_id = ?";

        try (PreparedStatement pstmt = this.connection.prepareStatement(selectSQL)) {
            pstmt.setInt(1, doctorId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Doctor(rs.getInt("doctor_id"), rs.getString("first_name"), rs.getString("last_name"), rs.getString("specialization"), rs.getString("phone_number"), rs.getString("email"));
            }
        } catch (SQLException e) {
            System.err.println("Error getting doctor by ID: " + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    public List<Doctor> getAllDoctors() {
        List<Doctor> doctors = new ArrayList();
        String selectSQL = "SELECT doctor_id, first_name, last_name, specialization, phone_number, email FROM doctors ORDER BY doctor_id";

        try (
                Statement stmt = this.connection.createStatement();
                ResultSet rs = stmt.executeQuery(selectSQL);
        ) {
            while(rs.next()) {
                doctors.add(new Doctor(rs.getInt("doctor_id"), rs.getString("first_name"), rs.getString("last_name"), rs.getString("specialization"), rs.getString("phone_number"), rs.getString("email")));
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving doctors: " + e.getMessage());
            e.printStackTrace();
        }

        return doctors;
    }

    public boolean updateDoctor(Doctor doctor) {
        String updateSQL = "UPDATE doctors SET first_name = ?, last_name = ?, specialization = ?, phone_number = ?, email = ? WHERE doctor_id = ?";

        try {
            boolean var5;
            try (PreparedStatement pstmt = this.connection.prepareStatement(updateSQL)) {
                pstmt.setString(1, doctor.getFirstName());
                pstmt.setString(2, doctor.getLastName());
                pstmt.setString(3, doctor.getSpecialization());
                pstmt.setString(4, doctor.getPhoneNumber());
                pstmt.setString(5, doctor.getEmail());
                pstmt.setInt(6, doctor.getDoctorId());
                int rowsAffected = pstmt.executeUpdate();
                var5 = rowsAffected > 0;
            }

            return var5;
        } catch (SQLException e) {
            System.err.println("Error updating doctor: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteDoctor(int doctorId) {
        String deleteSQL = "DELETE FROM doctors WHERE doctor_id = ?";

        try {
            boolean var5;
            try (PreparedStatement pstmt = this.connection.prepareStatement(deleteSQL)) {
                pstmt.setInt(1, doctorId);
                int rowsAffected = pstmt.executeUpdate();
                var5 = rowsAffected > 0;
            }

            return var5;
        } catch (SQLException e) {
            System.err.println("Error deleting doctor: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public Doctor getDoctorByName(String firstName, String lastName) {
        String selectSQL = "SELECT doctor_id, first_name, last_name, specialization, phone_number, email FROM doctors WHERE first_name = ? AND last_name = ?";

        try (PreparedStatement pstmt = this.connection.prepareStatement(selectSQL)) {
            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Doctor(rs.getInt("doctor_id"), rs.getString("first_name"), rs.getString("last_name"), rs.getString("specialization"), rs.getString("phone_number"), rs.getString("email"));
            }
        } catch (SQLException e) {
            System.err.println("Error getting doctor by name: " + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    public boolean addAppointment(Appointment appointment) {
        String insertSQL = "INSERT INTO appointments (patient_id, doctor_id, appointment_date, appointment_time, status) VALUES (?, ?, ?, ?, ?)";

        try {
            boolean var5;
            try (PreparedStatement pstmt = this.connection.prepareStatement(insertSQL)) {
                pstmt.setInt(1, appointment.getPatientId());
                pstmt.setInt(2, appointment.getDoctorId());
                pstmt.setDate(3, Date.valueOf(appointment.getAppointmentDate()));
                pstmt.setTime(4, Time.valueOf(appointment.getAppointmentTime()));
                pstmt.setString(5, appointment.getStatus());
                int rowsAffected = pstmt.executeUpdate();
                var5 = rowsAffected > 0;
            }

            return var5;
        } catch (SQLException e) {
            System.err.println("Error adding appointment: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public Appointment getAppointmentById(int appointmentId) {
        String selectSQL = "SELECT appointment_id, patient_id, doctor_id, appointment_date, appointment_time, status FROM appointments WHERE appointment_id = ?";

        try (PreparedStatement pstmt = this.connection.prepareStatement(selectSQL)) {
            pstmt.setInt(1, appointmentId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Appointment(rs.getInt("appointment_id"), rs.getInt("patient_id"), rs.getInt("doctor_id"), rs.getDate("appointment_date").toLocalDate(), rs.getTime("appointment_time").toLocalTime(), rs.getString("status"));
            }
        } catch (SQLException e) {
            System.err.println("Error getting appointment by ID: " + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    public List<Appointment> getAllAppointments() {
        List<Appointment> appointments = new ArrayList();
        String selectSQL = "SELECT appointment_id, patient_id, doctor_id, appointment_date, appointment_time, status FROM appointments ORDER BY appointment_date DESC, appointment_time ASC";

        try (
                Statement stmt = this.connection.createStatement();
                ResultSet rs = stmt.executeQuery(selectSQL);
        ) {
            while(rs.next()) {
                appointments.add(new Appointment(rs.getInt("appointment_id"), rs.getInt("patient_id"), rs.getInt("doctor_id"), rs.getDate("appointment_date").toLocalDate(), rs.getTime("appointment_time").toLocalTime(), rs.getString("status")));
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving appointments: " + e.getMessage());
            e.printStackTrace();
        }

        return appointments;
    }

    public boolean updateAppointment(Appointment appointment) {
        String updateSQL = "UPDATE appointments SET patient_id = ?, doctor_id = ?, appointment_date = ?, appointment_time = ?, status = ? WHERE appointment_id = ?";

        try {
            boolean var5;
            try (PreparedStatement pstmt = this.connection.prepareStatement(updateSQL)) {
                pstmt.setInt(1, appointment.getPatientId());
                pstmt.setInt(2, appointment.getDoctorId());
                pstmt.setDate(3, Date.valueOf(appointment.getAppointmentDate()));
                pstmt.setTime(4, Time.valueOf(appointment.getAppointmentTime()));
                pstmt.setString(5, appointment.getStatus());
                pstmt.setInt(6, appointment.getAppointmentId());
                int rowsAffected = pstmt.executeUpdate();
                var5 = rowsAffected > 0;
            }

            return var5;
        } catch (SQLException e) {
            System.err.println("Error updating appointment: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteAppointment(int appointmentId) {
        String deleteSQL = "DELETE FROM appointments WHERE appointment_id = ?";

        try {
            boolean var5;
            try (PreparedStatement pstmt = this.connection.prepareStatement(deleteSQL)) {
                pstmt.setInt(1, appointmentId);
                int rowsAffected = pstmt.executeUpdate();
                var5 = rowsAffected > 0;
            }

            return var5;
        } catch (SQLException e) {
            System.err.println("Error deleting appointment: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public List<Appointment> getAppointmentsByPatient(int patientId) {
        List<Appointment> appointments = new ArrayList();
        String selectSQL = "SELECT appointment_id, patient_id, doctor_id, appointment_date, appointment_time, status FROM appointments WHERE patient_id = ? ORDER BY appointment_date DESC, appointment_time ASC";

        try (PreparedStatement pstmt = this.connection.prepareStatement(selectSQL)) {
            pstmt.setInt(1, patientId);
            ResultSet rs = pstmt.executeQuery();

            while(rs.next()) {
                appointments.add(new Appointment(rs.getInt("appointment_id"), rs.getInt("patient_id"), rs.getInt("doctor_id"), rs.getDate("appointment_date").toLocalDate(), rs.getTime("appointment_time").toLocalTime(), rs.getString("status")));
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving appointments by patient: " + e.getMessage());
            e.printStackTrace();
        }

        return appointments;
    }

    public List<Appointment> getAppointmentsByDoctor(int doctorId) {
        List<Appointment> appointments = new ArrayList();
        String selectSQL = "SELECT appointment_id, patient_id, doctor_id, appointment_date, appointment_time, status FROM appointments WHERE doctor_id = ? ORDER BY appointment_date DESC, appointment_time ASC";

        try (PreparedStatement pstmt = this.connection.prepareStatement(selectSQL)) {
            pstmt.setInt(1, doctorId);
            ResultSet rs = pstmt.executeQuery();

            while(rs.next()) {
                appointments.add(new Appointment(rs.getInt("appointment_id"), rs.getInt("patient_id"), rs.getInt("doctor_id"), rs.getDate("appointment_date").toLocalDate(), rs.getTime("appointment_time").toLocalTime(), rs.getString("status")));
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving appointments by doctor: " + e.getMessage());
            e.printStackTrace();
        }

        return appointments;
    }

    public boolean addMedicalRecord(MedicalRecord medicalRecord) {
        String insertSQL = "INSERT INTO medical_records (patient_id, doctor_id, record_date, diagnosis, prescription) VALUES (?, ?, ?, ?, ?)";

        try {
            boolean var5;
            try (PreparedStatement pstmt = this.connection.prepareStatement(insertSQL)) {
                pstmt.setInt(1, medicalRecord.getPatientId());
                pstmt.setInt(2, medicalRecord.getDoctorId());
                pstmt.setDate(3, Date.valueOf(medicalRecord.getRecordDate()));
                pstmt.setString(4, medicalRecord.getDiagnosis());
                pstmt.setString(5, medicalRecord.getPrescription());
                int rowsAffected = pstmt.executeUpdate();
                var5 = rowsAffected > 0;
            }

            return var5;
        } catch (SQLException e) {
            System.err.println("Error adding medical record: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public MedicalRecord getMedicalRecordById(int recordId) {
        String selectSQL = "SELECT record_id, patient_id, doctor_id, record_date, diagnosis, prescription FROM medical_records WHERE record_id = ?";

        try (PreparedStatement pstmt = this.connection.prepareStatement(selectSQL)) {
            pstmt.setInt(1, recordId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new MedicalRecord(rs.getInt("record_id"), rs.getInt("patient_id"), rs.getInt("doctor_id"), rs.getDate("record_date").toLocalDate(), rs.getString("diagnosis"), rs.getString("prescription"));
            }
        } catch (SQLException e) {
            System.err.println("Error getting medical record by ID: " + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    public List<MedicalRecord> getAllMedicalRecords() {
        List<MedicalRecord> records = new ArrayList();
        String selectSQL = "SELECT record_id, patient_id, doctor_id, record_date, diagnosis, prescription FROM medical_records ORDER BY record_date DESC, record_id DESC";

        try (
                Statement stmt = this.connection.createStatement();
                ResultSet rs = stmt.executeQuery(selectSQL);
        ) {
            while(rs.next()) {
                records.add(new MedicalRecord(rs.getInt("record_id"), rs.getInt("patient_id"), rs.getInt("doctor_id"), rs.getDate("record_date").toLocalDate(), rs.getString("diagnosis"), rs.getString("prescription")));
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving medical records: " + e.getMessage());
            e.printStackTrace();
        }

        return records;
    }

    public boolean updateMedicalRecord(MedicalRecord medicalRecord) {
        String updateSQL = "UPDATE medical_records SET patient_id = ?, doctor_id = ?, record_date = ?, diagnosis = ?, prescription = ? WHERE record_id = ?";

        try {
            boolean var5;
            try (PreparedStatement pstmt = this.connection.prepareStatement(updateSQL)) {
                pstmt.setInt(1, medicalRecord.getPatientId());
                pstmt.setInt(2, medicalRecord.getDoctorId());
                pstmt.setDate(3, Date.valueOf(medicalRecord.getRecordDate()));
                pstmt.setString(4, medicalRecord.getDiagnosis());
                pstmt.setString(5, medicalRecord.getPrescription());
                pstmt.setInt(6, medicalRecord.getRecordId());
                int rowsAffected = pstmt.executeUpdate();
                var5 = rowsAffected > 0;
            }

            return var5;
        } catch (SQLException e) {
            System.err.println("Error updating medical record: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteMedicalRecord(int recordId) {
        String deleteSQL = "DELETE FROM medical_records WHERE record_id = ?";

        try {
            boolean var5;
            try (PreparedStatement pstmt = this.connection.prepareStatement(deleteSQL)) {
                pstmt.setInt(1, recordId);
                int rowsAffected = pstmt.executeUpdate();
                var5 = rowsAffected > 0;
            }

            return var5;
        } catch (SQLException e) {
            System.err.println("Error deleting medical record: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public List<MedicalRecord> getMedicalRecordsByPatient(int patientId) {
        List<MedicalRecord> records = new ArrayList();
        String selectSQL = "SELECT record_id, patient_id, doctor_id, record_date, diagnosis, prescription FROM medical_records WHERE patient_id = ? ORDER BY record_date DESC, record_id DESC";

        try (PreparedStatement pstmt = this.connection.prepareStatement(selectSQL)) {
            pstmt.setInt(1, patientId);
            ResultSet rs = pstmt.executeQuery();

            while(rs.next()) {
                records.add(new MedicalRecord(rs.getInt("record_id"), rs.getInt("patient_id"), rs.getInt("doctor_id"), rs.getDate("record_date").toLocalDate(), rs.getString("diagnosis"), rs.getString("prescription")));
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving medical records by patient: " + e.getMessage());
            e.printStackTrace();
        }

        return records;
    }

    public List<MedicalRecord> getMedicalRecordsByDoctor(int doctorId) {
        List<MedicalRecord> records = new ArrayList();
        String selectSQL = "SELECT record_id, patient_id, doctor_id, record_date, diagnosis, prescription FROM medical_records WHERE doctor_id = ? ORDER BY record_date DESC, record_id DESC";

        try (PreparedStatement pstmt = this.connection.prepareStatement(selectSQL)) {
            pstmt.setInt(1, doctorId);
            ResultSet rs = pstmt.executeQuery();

            while(rs.next()) {
                records.add(new MedicalRecord(rs.getInt("record_id"), rs.getInt("patient_id"), rs.getInt("doctor_id"), rs.getDate("record_date").toLocalDate(), rs.getString("diagnosis"), rs.getString("prescription")));
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving medical records by doctor: " + e.getMessage());
            e.printStackTrace();
        }

        return records;
    }

    public void closeConnection() {
        try {
            if (this.connection != null && !this.connection.isClosed()) {
                this.connection.close();
                System.out.println("Database connection closed.");
            }
        } catch (SQLException e) {
            System.err.println("Error closing database connection: " + e.getMessage());
            e.printStackTrace();
        }

    }
}
