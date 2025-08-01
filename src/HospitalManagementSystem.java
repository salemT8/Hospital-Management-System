//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class HospitalManagementSystem extends JFrame {
    private DatabaseManager dbManager = new DatabaseManager();
    private JTabbedPane tabbedPane;
    private JTextField patientIdField;
    private JTextField patientFirstNameField;
    private JTextField patientLastNameField;
    private JTextField patientDOBField;
    private JTextField patientAddressField;
    private JTextField patientPhoneField;
    private JTextField patientEmailField;
    private JComboBox<String> patientGenderComboBox;
    private JButton addPatientButton;
    private JButton updatePatientButton;
    private JButton deletePatientButton;
    private JTable patientsTable;
    private DefaultTableModel patientsTableModel;
    private JTextField doctorIdField;
    private JTextField doctorFirstNameField;
    private JTextField doctorLastNameField;
    private JTextField doctorSpecializationField;
    private JTextField doctorPhoneField;
    private JTextField doctorEmailField;
    private JButton addDoctorButton;
    private JButton updateDoctorButton;
    private JButton deleteDoctorButton;
    private JTable doctorsTable;
    private DefaultTableModel doctorsTableModel;
    private JTextField appointmentIdField;
    private JTextField appointmentDateField;
    private JTextField appointmentTimeField;
    private JComboBox<String> appointmentPatientComboBox;
    private JComboBox<String> appointmentDoctorComboBox;
    private JComboBox<String> appointmentStatusComboBox;
    private JButton addAppointmentButton;
    private JButton updateAppointmentButton;
    private JButton deleteAppointmentButton;
    private JTable appointmentsTable;
    private DefaultTableModel appointmentsTableModel;
    private JTextField recordIdField;
    private JTextField recordDateField;
    private JTextField recordDiagnosisField;
    private JTextField recordPrescriptionField;
    private JComboBox<String> recordPatientComboBox;
    private JComboBox<String> recordDoctorComboBox;
    private JButton addRecordButton;
    private JButton updateRecordButton;
    private JButton deleteRecordButton;
    private JTable medicalRecordsTable;
    private DefaultTableModel medicalRecordsTableModel;
    private Map<String, Integer> patientNameToIdMap = new HashMap();
    private Map<Integer, String> patientIdToNameMap = new HashMap();
    private Map<String, Integer> doctorNameToIdMap = new HashMap();
    private Map<Integer, String> doctorIdToNameMap = new HashMap();

    public HospitalManagementSystem() {
        super("Hospital Management System");
        this.setSize(1200, 700);
        this.setDefaultCloseOperation(3);
        this.setLocationRelativeTo((Component)null);
        this.setLayout(new BorderLayout());
        this.tabbedPane = new JTabbedPane();
        this.add(this.tabbedPane, "Center");
        this.setupPatientTab();
        this.setupDoctorTab();
        this.setupAppointmentTab();
        this.setupMedicalRecordTab();
        this.loadPatientsData();
        this.loadDoctorsData();
        this.loadAppointmentsData();
        this.loadMedicalRecordsData();
        this.setVisible(true);
    }

    private void setupPatientTab() {
        JPanel patientPanel = new JPanel(new BorderLayout(10, 10));
        patientPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JPanel inputPanel = new JPanel(new GridLayout(8, 2, 5, 5));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Patient Details"));
        this.patientIdField = new JTextField(10);
        this.patientIdField.setEditable(false);
        this.patientFirstNameField = new JTextField(20);
        this.patientLastNameField = new JTextField(20);
        this.patientDOBField = new JTextField(10);
        this.patientGenderComboBox = new JComboBox(new String[]{"Male", "Female", "Other"});
        this.patientAddressField = new JTextField(30);
        this.patientPhoneField = new JTextField(20);
        this.patientEmailField = new JTextField(20);
        inputPanel.add(new JLabel("Patient ID (Auto/Select):"));
        inputPanel.add(this.patientIdField);
        inputPanel.add(new JLabel("First Name:"));
        inputPanel.add(this.patientFirstNameField);
        inputPanel.add(new JLabel("Last Name:"));
        inputPanel.add(this.patientLastNameField);
        inputPanel.add(new JLabel("Date of Birth (YYYY-MM-DD):"));
        inputPanel.add(this.patientDOBField);
        inputPanel.add(new JLabel("Gender:"));
        inputPanel.add(this.patientGenderComboBox);
        inputPanel.add(new JLabel("Address:"));
        inputPanel.add(this.patientAddressField);
        inputPanel.add(new JLabel("Phone Number:"));
        inputPanel.add(this.patientPhoneField);
        inputPanel.add(new JLabel("Email:"));
        inputPanel.add(this.patientEmailField);
        patientPanel.add(inputPanel, "North");
        JPanel buttonPanel = new JPanel(new FlowLayout(1, 10, 10));
        this.addPatientButton = new JButton("Add Patient");
        this.updatePatientButton = new JButton("Update Patient");
        this.deletePatientButton = new JButton("Delete Patient");
        buttonPanel.add(this.addPatientButton);
        buttonPanel.add(this.updatePatientButton);
        buttonPanel.add(this.deletePatientButton);
        patientPanel.add(buttonPanel, "South");
        String[] patientColumnNames = new String[]{"ID", "First Name", "Last Name", "DOB", "Gender", "Address", "Phone", "Email"};
        this.patientsTableModel = new DefaultTableModel(patientColumnNames, 0);
        this.patientsTable = new JTable(this.patientsTableModel);
        this.patientsTable.setSelectionMode(0);
        this.patientsTable.getTableHeader().setReorderingAllowed(false);
        this.patientsTable.getSelectionModel().addListSelectionListener((e) -> {
            if (!e.getValueIsAdjusting() && this.patientsTable.getSelectedRow() != -1) {
                int selectedRow = this.patientsTable.getSelectedRow();
                this.patientIdField.setText(this.patientsTableModel.getValueAt(selectedRow, 0).toString());
                this.patientFirstNameField.setText(this.patientsTableModel.getValueAt(selectedRow, 1).toString());
                this.patientLastNameField.setText(this.patientsTableModel.getValueAt(selectedRow, 2).toString());
                this.patientDOBField.setText(this.patientsTableModel.getValueAt(selectedRow, 3).toString());
                this.patientGenderComboBox.setSelectedItem(this.patientsTableModel.getValueAt(selectedRow, 4).toString());
                this.patientAddressField.setText(this.patientsTableModel.getValueAt(selectedRow, 5).toString());
                this.patientPhoneField.setText(this.patientsTableModel.getValueAt(selectedRow, 6).toString());
                this.patientEmailField.setText(this.patientsTableModel.getValueAt(selectedRow, 7).toString());
            }

        });
        JScrollPane patientScrollPane = new JScrollPane(this.patientsTable);
        patientPanel.add(patientScrollPane, "Center");
        this.addPatientButton.addActionListener((e) -> this.addPatient());
        this.updatePatientButton.addActionListener((e) -> this.updatePatient());
        this.deletePatientButton.addActionListener((e) -> this.deletePatient());
        this.tabbedPane.addTab("Patient Management", patientPanel);
    }

    private void setupDoctorTab() {
        JPanel doctorPanel = new JPanel(new BorderLayout(10, 10));
        doctorPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JPanel inputPanel = new JPanel(new GridLayout(6, 2, 5, 5));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Doctor Details"));
        this.doctorIdField = new JTextField(10);
        this.doctorIdField.setEditable(false);
        this.doctorFirstNameField = new JTextField(20);
        this.doctorLastNameField = new JTextField(20);
        this.doctorSpecializationField = new JTextField(20);
        this.doctorPhoneField = new JTextField(20);
        this.doctorEmailField = new JTextField(20);
        inputPanel.add(new JLabel("Doctor ID (Auto/Select):"));
        inputPanel.add(this.doctorIdField);
        inputPanel.add(new JLabel("First Name:"));
        inputPanel.add(this.doctorFirstNameField);
        inputPanel.add(new JLabel("Last Name:"));
        inputPanel.add(this.doctorLastNameField);
        inputPanel.add(new JLabel("Specialization:"));
        inputPanel.add(this.doctorSpecializationField);
        inputPanel.add(new JLabel("Phone Number:"));
        inputPanel.add(this.doctorPhoneField);
        inputPanel.add(new JLabel("Email:"));
        inputPanel.add(this.doctorEmailField);
        doctorPanel.add(inputPanel, "North");
        JPanel buttonPanel = new JPanel(new FlowLayout(1, 10, 10));
        this.addDoctorButton = new JButton("Add Doctor");
        this.updateDoctorButton = new JButton("Update Doctor");
        this.deleteDoctorButton = new JButton("Delete Doctor");
        buttonPanel.add(this.addDoctorButton);
        buttonPanel.add(this.updateDoctorButton);
        buttonPanel.add(this.deleteDoctorButton);
        doctorPanel.add(buttonPanel, "South");
        String[] doctorColumnNames = new String[]{"ID", "First Name", "Last Name", "Specialization", "Phone", "Email"};
        this.doctorsTableModel = new DefaultTableModel(doctorColumnNames, 0);
        this.doctorsTable = new JTable(this.doctorsTableModel);
        this.doctorsTable.setSelectionMode(0);
        this.doctorsTable.getTableHeader().setReorderingAllowed(false);
        this.doctorsTable.getSelectionModel().addListSelectionListener((e) -> {
            if (!e.getValueIsAdjusting() && this.doctorsTable.getSelectedRow() != -1) {
                int selectedRow = this.doctorsTable.getSelectedRow();
                this.doctorIdField.setText(this.doctorsTableModel.getValueAt(selectedRow, 0).toString());
                this.doctorFirstNameField.setText(this.doctorsTableModel.getValueAt(selectedRow, 1).toString());
                this.doctorLastNameField.setText(this.doctorsTableModel.getValueAt(selectedRow, 2).toString());
                this.doctorSpecializationField.setText(this.doctorsTableModel.getValueAt(selectedRow, 3).toString());
                this.doctorPhoneField.setText(this.doctorsTableModel.getValueAt(selectedRow, 4).toString());
                this.doctorEmailField.setText(this.doctorsTableModel.getValueAt(selectedRow, 5).toString());
            }

        });
        JScrollPane doctorScrollPane = new JScrollPane(this.doctorsTable);
        doctorPanel.add(doctorScrollPane, "Center");
        this.addDoctorButton.addActionListener((e) -> this.addDoctor());
        this.updateDoctorButton.addActionListener((e) -> this.updateDoctor());
        this.deleteDoctorButton.addActionListener((e) -> this.deleteDoctor());
        this.tabbedPane.addTab("Doctor Management", doctorPanel);
    }

    private void setupAppointmentTab() {
        JPanel appointmentPanel = new JPanel(new BorderLayout(10, 10));
        appointmentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JPanel inputPanel = new JPanel(new GridLayout(6, 2, 5, 5));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Appointment Details"));
        this.appointmentIdField = new JTextField(10);
        this.appointmentIdField.setEditable(false);
        this.appointmentDateField = new JTextField(10);
        this.appointmentTimeField = new JTextField(10);
        this.appointmentPatientComboBox = new JComboBox();
        this.appointmentDoctorComboBox = new JComboBox();
        this.appointmentStatusComboBox = new JComboBox(new String[]{"Scheduled", "Completed", "Cancelled"});
        inputPanel.add(new JLabel("Appointment ID (Auto/Select):"));
        inputPanel.add(this.appointmentIdField);
        inputPanel.add(new JLabel("Patient:"));
        inputPanel.add(this.appointmentPatientComboBox);
        inputPanel.add(new JLabel("Doctor:"));
        inputPanel.add(this.appointmentDoctorComboBox);
        inputPanel.add(new JLabel("Date (YYYY-MM-DD):"));
        inputPanel.add(this.appointmentDateField);
        inputPanel.add(new JLabel("Time (HH:MM:SS):"));
        inputPanel.add(this.appointmentTimeField);
        inputPanel.add(new JLabel("Status:"));
        inputPanel.add(this.appointmentStatusComboBox);
        appointmentPanel.add(inputPanel, "North");
        JPanel buttonPanel = new JPanel(new FlowLayout(1, 10, 10));
        this.addAppointmentButton = new JButton("Add Appointment");
        this.updateAppointmentButton = new JButton("Update Appointment");
        this.deleteAppointmentButton = new JButton("Delete Appointment");
        buttonPanel.add(this.addAppointmentButton);
        buttonPanel.add(this.updateAppointmentButton);
        buttonPanel.add(this.deleteAppointmentButton);
        appointmentPanel.add(buttonPanel, "South");
        String[] appointmentColumnNames = new String[]{"ID", "Patient Name", "Doctor Name", "Date", "Time", "Status"};
        this.appointmentsTableModel = new DefaultTableModel(appointmentColumnNames, 0);
        this.appointmentsTable = new JTable(this.appointmentsTableModel);
        this.appointmentsTable.setSelectionMode(0);
        this.appointmentsTable.getTableHeader().setReorderingAllowed(false);
        this.appointmentsTable.getSelectionModel().addListSelectionListener((e) -> {
            if (!e.getValueIsAdjusting() && this.appointmentsTable.getSelectedRow() != -1) {
                int selectedRow = this.appointmentsTable.getSelectedRow();
                this.appointmentIdField.setText(this.appointmentsTableModel.getValueAt(selectedRow, 0).toString());
                String patientName = this.appointmentsTableModel.getValueAt(selectedRow, 1).toString();
                this.appointmentPatientComboBox.setSelectedItem(patientName);
                String doctorName = this.appointmentsTableModel.getValueAt(selectedRow, 2).toString();
                this.appointmentDoctorComboBox.setSelectedItem(doctorName);
                this.appointmentDateField.setText(this.appointmentsTableModel.getValueAt(selectedRow, 3).toString());
                this.appointmentTimeField.setText(this.appointmentsTableModel.getValueAt(selectedRow, 4).toString());
                this.appointmentStatusComboBox.setSelectedItem(this.appointmentsTableModel.getValueAt(selectedRow, 5).toString());
            }

        });
        JScrollPane appointmentScrollPane = new JScrollPane(this.appointmentsTable);
        appointmentPanel.add(appointmentScrollPane, "Center");
        this.addAppointmentButton.addActionListener((e) -> this.addAppointment());
        this.updateAppointmentButton.addActionListener((e) -> this.updateAppointment());
        this.deleteAppointmentButton.addActionListener((e) -> this.deleteAppointment());
        this.tabbedPane.addTab("Appointment Management", appointmentPanel);
    }

    private void setupMedicalRecordTab() {
        JPanel recordPanel = new JPanel(new BorderLayout(10, 10));
        recordPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JPanel inputPanel = new JPanel(new GridLayout(6, 2, 5, 5));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Medical Record Details"));
        this.recordIdField = new JTextField(10);
        this.recordIdField.setEditable(false);
        this.recordDateField = new JTextField(10);
        this.recordDiagnosisField = new JTextField(50);
        this.recordPrescriptionField = new JTextField(50);
        this.recordPatientComboBox = new JComboBox();
        this.recordDoctorComboBox = new JComboBox();
        inputPanel.add(new JLabel("Record ID (Auto/Select):"));
        inputPanel.add(this.recordIdField);
        inputPanel.add(new JLabel("Patient:"));
        inputPanel.add(this.recordPatientComboBox);
        inputPanel.add(new JLabel("Doctor:"));
        inputPanel.add(this.recordDoctorComboBox);
        inputPanel.add(new JLabel("Record Date (YYYY-MM-DD):"));
        inputPanel.add(this.recordDateField);
        inputPanel.add(new JLabel("Diagnosis:"));
        inputPanel.add(this.recordDiagnosisField);
        inputPanel.add(new JLabel("Prescription:"));
        inputPanel.add(this.recordPrescriptionField);
        recordPanel.add(inputPanel, "North");
        JPanel buttonPanel = new JPanel(new FlowLayout(1, 10, 10));
        this.addRecordButton = new JButton("Add Record");
        this.updateRecordButton = new JButton("Update Record");
        this.deleteRecordButton = new JButton("Delete Record");
        buttonPanel.add(this.addRecordButton);
        buttonPanel.add(this.updateRecordButton);
        buttonPanel.add(this.deleteRecordButton);
        recordPanel.add(buttonPanel, "South");
        String[] recordColumnNames = new String[]{"ID", "Patient Name", "Doctor Name", "Date", "Diagnosis", "Prescription"};
        this.medicalRecordsTableModel = new DefaultTableModel(recordColumnNames, 0);
        this.medicalRecordsTable = new JTable(this.medicalRecordsTableModel);
        this.medicalRecordsTable.setSelectionMode(0);
        this.medicalRecordsTable.getTableHeader().setReorderingAllowed(false);
        this.medicalRecordsTable.getSelectionModel().addListSelectionListener((e) -> {
            if (!e.getValueIsAdjusting() && this.medicalRecordsTable.getSelectedRow() != -1) {
                int selectedRow = this.medicalRecordsTable.getSelectedRow();
                this.recordIdField.setText(this.medicalRecordsTableModel.getValueAt(selectedRow, 0).toString());
                String patientName = this.medicalRecordsTableModel.getValueAt(selectedRow, 1).toString();
                this.recordPatientComboBox.setSelectedItem(patientName);
                String doctorName = this.medicalRecordsTableModel.getValueAt(selectedRow, 2).toString();
                this.recordDoctorComboBox.setSelectedItem(doctorName);
                this.recordDateField.setText(this.medicalRecordsTableModel.getValueAt(selectedRow, 3).toString());
                this.recordDiagnosisField.setText(this.medicalRecordsTableModel.getValueAt(selectedRow, 4).toString());
                this.recordPrescriptionField.setText(this.medicalRecordsTableModel.getValueAt(selectedRow, 5).toString());
            }

        });
        JScrollPane recordScrollPane = new JScrollPane(this.medicalRecordsTable);
        recordPanel.add(recordScrollPane, "Center");
        this.addRecordButton.addActionListener((e) -> this.addMedicalRecord());
        this.updateRecordButton.addActionListener((e) -> this.updateMedicalRecord());
        this.deleteRecordButton.addActionListener((e) -> this.deleteMedicalRecord());
        this.tabbedPane.addTab("Medical Records", recordPanel);
    }

    private void clearPatientFields() {
        this.patientIdField.setText("");
        this.patientFirstNameField.setText("");
        this.patientLastNameField.setText("");
        this.patientDOBField.setText("");
        this.patientGenderComboBox.setSelectedIndex(0);
        this.patientAddressField.setText("");
        this.patientPhoneField.setText("");
        this.patientEmailField.setText("");
        this.patientsTable.clearSelection();
    }

    private void clearDoctorFields() {
        this.doctorIdField.setText("");
        this.doctorFirstNameField.setText("");
        this.doctorLastNameField.setText("");
        this.doctorSpecializationField.setText("");
        this.doctorPhoneField.setText("");
        this.doctorEmailField.setText("");
        this.doctorsTable.clearSelection();
    }

    private void clearAppointmentFields() {
        this.appointmentIdField.setText("");
        this.appointmentPatientComboBox.setSelectedIndex(-1);
        this.appointmentDoctorComboBox.setSelectedIndex(-1);
        this.appointmentDateField.setText("");
        this.appointmentTimeField.setText("");
        this.appointmentStatusComboBox.setSelectedIndex(0);
        this.appointmentsTable.clearSelection();
    }

    private void clearMedicalRecordFields() {
        this.recordIdField.setText("");
        this.recordPatientComboBox.setSelectedIndex(-1);
        this.recordDoctorComboBox.setSelectedIndex(-1);
        this.recordDateField.setText("");
        this.recordDiagnosisField.setText("");
        this.recordPrescriptionField.setText("");
        this.medicalRecordsTable.clearSelection();
    }

    private void loadPatientsData() {
        this.patientsTableModel.setRowCount(0);
        this.patientNameToIdMap.clear();
        this.patientIdToNameMap.clear();
        List<Patient> patients = this.dbManager.getAllPatients();
        Vector<String> patientNames = new Vector();

        for(Patient patient : patients) {
            this.patientsTableModel.addRow(new Object[]{patient.getPatientId(), patient.getFirstName(), patient.getLastName(), patient.getDateOfBirth() != null ? patient.getDateOfBirth().toString() : "", patient.getGender(), patient.getAddress(), patient.getPhoneNumber(), patient.getEmail()});
            String var10000 = patient.getFirstName();
            String fullName = var10000 + " " + patient.getLastName();
            patientNames.add(fullName);
            this.patientNameToIdMap.put(fullName, patient.getPatientId());
            this.patientIdToNameMap.put(patient.getPatientId(), fullName);
        }

        this.appointmentPatientComboBox.setModel(new DefaultComboBoxModel(patientNames));
        this.recordPatientComboBox.setModel(new DefaultComboBoxModel(patientNames));
        if (!patientNames.isEmpty()) {
            this.appointmentPatientComboBox.setSelectedIndex(0);
            this.recordPatientComboBox.setSelectedIndex(0);
        } else {
            this.appointmentPatientComboBox.setSelectedIndex(-1);
            this.recordPatientComboBox.setSelectedIndex(-1);
        }

    }

    private void loadDoctorsData() {
        this.doctorsTableModel.setRowCount(0);
        this.doctorNameToIdMap.clear();
        this.doctorIdToNameMap.clear();
        List<Doctor> doctors = this.dbManager.getAllDoctors();
        Vector<String> doctorNames = new Vector();

        for(Doctor doctor : doctors) {
            this.doctorsTableModel.addRow(new Object[]{doctor.getDoctorId(), doctor.getFirstName(), doctor.getLastName(), doctor.getSpecialization(), doctor.getPhoneNumber(), doctor.getEmail()});
            String var10000 = doctor.getFirstName();
            String fullNameAndSpec = var10000 + " " + doctor.getLastName() + " (" + doctor.getSpecialization() + ")";
            doctorNames.add(fullNameAndSpec);
            this.doctorNameToIdMap.put(fullNameAndSpec, doctor.getDoctorId());
            this.doctorIdToNameMap.put(doctor.getDoctorId(), fullNameAndSpec);
        }

        this.appointmentDoctorComboBox.setModel(new DefaultComboBoxModel(doctorNames));
        this.recordDoctorComboBox.setModel(new DefaultComboBoxModel(doctorNames));
        if (!doctorNames.isEmpty()) {
            this.appointmentDoctorComboBox.setSelectedIndex(0);
            this.recordDoctorComboBox.setSelectedIndex(0);
        } else {
            this.appointmentDoctorComboBox.setSelectedIndex(-1);
            this.recordDoctorComboBox.setSelectedIndex(-1);
        }

    }

    private void loadAppointmentsData() {
        this.appointmentsTableModel.setRowCount(0);

        for(Appointment appointment : this.dbManager.getAllAppointments()) {
            String patientName = (String)this.patientIdToNameMap.getOrDefault(appointment.getPatientId(), "Unknown Patient");
            String doctorName = (String)this.doctorIdToNameMap.getOrDefault(appointment.getDoctorId(), "Unknown Doctor");
            this.appointmentsTableModel.addRow(new Object[]{appointment.getAppointmentId(), patientName, doctorName, appointment.getAppointmentDate().toString(), appointment.getAppointmentTime().toString(), appointment.getStatus()});
        }

    }

    private void loadMedicalRecordsData() {
        this.medicalRecordsTableModel.setRowCount(0);

        for(MedicalRecord record : this.dbManager.getAllMedicalRecords()) {
            String patientName = (String)this.patientIdToNameMap.getOrDefault(record.getPatientId(), "Unknown Patient");
            String doctorName = (String)this.doctorIdToNameMap.getOrDefault(record.getDoctorId(), "Unknown Doctor");
            this.medicalRecordsTableModel.addRow(new Object[]{record.getRecordId(), patientName, doctorName, record.getRecordDate().toString(), record.getDiagnosis(), record.getPrescription()});
        }

    }

    private void addPatient() {
        String firstName = this.patientFirstNameField.getText().trim();
        String lastName = this.patientLastNameField.getText().trim();
        String dobStr = this.patientDOBField.getText().trim();
        String gender = (String)this.patientGenderComboBox.getSelectedItem();
        String address = this.patientAddressField.getText().trim();
        String phone = this.patientPhoneField.getText().trim();
        String email = this.patientEmailField.getText().trim();
        if (!firstName.isEmpty() && !lastName.isEmpty() && !dobStr.isEmpty()) {
            try {
                LocalDate dob = LocalDate.parse(dobStr);
                Patient patient = new Patient(firstName, lastName, dob, gender, address, phone, email);
                if (this.dbManager.addPatient(patient)) {
                    JOptionPane.showMessageDialog(this, "Patient added successfully!", "Success", 1);
                    this.clearPatientFields();
                    this.loadPatientsData();
                    this.loadAppointmentsData();
                    this.loadMedicalRecordsData();
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to add patient. Email might already exist.", "Database Error", 0);
                }
            } catch (DateTimeParseException var10) {
                JOptionPane.showMessageDialog(this, "Invalid Date of Birth format. Please use YYYY-MM-DD.", "Input Error", 0);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "An unexpected error occurred: " + ex.getMessage(), "Error", 0);
                ex.printStackTrace();
            }

        } else {
            JOptionPane.showMessageDialog(this, "First Name, Last Name, and Date of Birth are required.", "Input Error", 0);
        }
    }

    private void updatePatient() {
        String idStr = this.patientIdField.getText().trim();
        if (idStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select a patient from the table to update.", "Selection Error", 2);
        } else {
            String firstName = this.patientFirstNameField.getText().trim();
            String lastName = this.patientLastNameField.getText().trim();
            String dobStr = this.patientDOBField.getText().trim();
            String gender = (String)this.patientGenderComboBox.getSelectedItem();
            String address = this.patientAddressField.getText().trim();
            String phone = this.patientPhoneField.getText().trim();
            String email = this.patientEmailField.getText().trim();
            if (!firstName.isEmpty() && !lastName.isEmpty() && !dobStr.isEmpty()) {
                try {
                    int patientId = Integer.parseInt(idStr);
                    LocalDate dob = LocalDate.parse(dobStr);
                    Patient patient = new Patient(patientId, firstName, lastName, dob, gender, address, phone, email);
                    if (this.dbManager.updatePatient(patient)) {
                        JOptionPane.showMessageDialog(this, "Patient updated successfully!", "Success", 1);
                        this.clearPatientFields();
                        this.loadPatientsData();
                        this.loadAppointmentsData();
                        this.loadMedicalRecordsData();
                    } else {
                        JOptionPane.showMessageDialog(this, "Failed to update patient. Email might already exist or ID not found.", "Database Error", 0);
                    }
                } catch (NumberFormatException var12) {
                    JOptionPane.showMessageDialog(this, "Invalid Patient ID.", "Input Error", 0);
                } catch (DateTimeParseException var13) {
                    JOptionPane.showMessageDialog(this, "Invalid Date of Birth format. Please use YYYY-MM-DD.", "Input Error", 0);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "An unexpected error occurred: " + ex.getMessage(), "Error", 0);
                    ex.printStackTrace();
                }

            } else {
                JOptionPane.showMessageDialog(this, "First Name, Last Name, and Date of Birth are required for update.", "Input Error", 0);
            }
        }
    }

    private void deletePatient() {
        String idStr = this.patientIdField.getText().trim();
        if (idStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select a patient from the table to delete.", "Selection Error", 2);
        } else {
            try {
                int patientId = Integer.parseInt(idStr);
                int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this patient? This will fail if there are associated appointments or medical records.", "Confirm Deletion", 0);
                if (confirm == 0) {
                    if (this.dbManager.deletePatient(patientId)) {
                        JOptionPane.showMessageDialog(this, "Patient deleted successfully!", "Success", 1);
                        this.clearPatientFields();
                        this.loadPatientsData();
                        this.loadAppointmentsData();
                        this.loadMedicalRecordsData();
                    } else {
                        JOptionPane.showMessageDialog(this, "Failed to delete patient. They might be referenced by existing appointments or medical records.", "Database Error", 0);
                    }
                }
            } catch (NumberFormatException var4) {
                JOptionPane.showMessageDialog(this, "Invalid Patient ID.", "Input Error", 0);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "An unexpected error occurred during deletion: " + ex.getMessage(), "Error", 0);
                ex.printStackTrace();
            }

        }
    }

    private void addDoctor() {
        String firstName = this.doctorFirstNameField.getText().trim();
        String lastName = this.doctorLastNameField.getText().trim();
        String specialization = this.doctorSpecializationField.getText().trim();
        String phone = this.doctorPhoneField.getText().trim();
        String email = this.doctorEmailField.getText().trim();
        if (!firstName.isEmpty() && !lastName.isEmpty() && !specialization.isEmpty()) {
            Doctor doctor = new Doctor(firstName, lastName, specialization, phone, email);
            if (this.dbManager.addDoctor(doctor)) {
                JOptionPane.showMessageDialog(this, "Doctor added successfully!", "Success", 1);
                this.clearDoctorFields();
                this.loadDoctorsData();
                this.loadAppointmentsData();
                this.loadMedicalRecordsData();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add doctor. Email might already exist.", "Database Error", 0);
            }

        } else {
            JOptionPane.showMessageDialog(this, "First Name, Last Name, and Specialization are required.", "Input Error", 0);
        }
    }

    private void updateDoctor() {
        String idStr = this.doctorIdField.getText().trim();
        if (idStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select a doctor from the table to update.", "Selection Error", 2);
        } else {
            String firstName = this.doctorFirstNameField.getText().trim();
            String lastName = this.doctorLastNameField.getText().trim();
            String specialization = this.doctorSpecializationField.getText().trim();
            String phone = this.doctorPhoneField.getText().trim();
            String email = this.doctorEmailField.getText().trim();
            if (!firstName.isEmpty() && !lastName.isEmpty() && !specialization.isEmpty()) {
                try {
                    int doctorId = Integer.parseInt(idStr);
                    Doctor doctor = new Doctor(doctorId, firstName, lastName, specialization, phone, email);
                    if (this.dbManager.updateDoctor(doctor)) {
                        JOptionPane.showMessageDialog(this, "Doctor updated successfully!", "Success", 1);
                        this.clearDoctorFields();
                        this.loadDoctorsData();
                        this.loadAppointmentsData();
                        this.loadMedicalRecordsData();
                    } else {
                        JOptionPane.showMessageDialog(this, "Failed to update doctor. Email might already exist or ID not found.", "Database Error", 0);
                    }
                } catch (NumberFormatException var9) {
                    JOptionPane.showMessageDialog(this, "Invalid Doctor ID.", "Input Error", 0);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "An unexpected error occurred: " + ex.getMessage(), "Error", 0);
                    ex.printStackTrace();
                }

            } else {
                JOptionPane.showMessageDialog(this, "First Name, Last Name, and Specialization are required for update.", "Input Error", 0);
            }
        }
    }

    private void deleteDoctor() {
        String idStr = this.doctorIdField.getText().trim();
        if (idStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select a doctor from the table to delete.", "Selection Error", 2);
        } else {
            try {
                int doctorId = Integer.parseInt(idStr);
                int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this doctor? This will fail if there are associated appointments or medical records.", "Confirm Deletion", 0);
                if (confirm == 0) {
                    if (this.dbManager.deleteDoctor(doctorId)) {
                        JOptionPane.showMessageDialog(this, "Doctor deleted successfully!", "Success", 1);
                        this.clearDoctorFields();
                        this.loadDoctorsData();
                        this.loadAppointmentsData();
                        this.loadMedicalRecordsData();
                    } else {
                        JOptionPane.showMessageDialog(this, "Failed to delete doctor. They might be referenced by existing appointments or medical records.", "Database Error", 0);
                    }
                }
            } catch (NumberFormatException var4) {
                JOptionPane.showMessageDialog(this, "Invalid Doctor ID.", "Input Error", 0);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "An unexpected error occurred during deletion: " + ex.getMessage(), "Error", 0);
                ex.printStackTrace();
            }

        }
    }

    private void addAppointment() {
        String selectedPatientName = (String)this.appointmentPatientComboBox.getSelectedItem();
        String selectedDoctorName = (String)this.appointmentDoctorComboBox.getSelectedItem();
        String dateStr = this.appointmentDateField.getText().trim();
        String timeStr = this.appointmentTimeField.getText().trim();
        String status = (String)this.appointmentStatusComboBox.getSelectedItem();
        if (selectedPatientName != null && selectedDoctorName != null && !dateStr.isEmpty() && !timeStr.isEmpty() && status != null) {
            try {
                int patientId = (Integer)this.patientNameToIdMap.getOrDefault(selectedPatientName, -1);
                if (patientId == -1) {
                    JOptionPane.showMessageDialog(this, "Selected patient not found in database.", "Error", 0);
                    return;
                }

                int doctorId = (Integer)this.doctorNameToIdMap.getOrDefault(selectedDoctorName, -1);
                if (doctorId == -1) {
                    JOptionPane.showMessageDialog(this, "Selected doctor not found in database.", "Error", 0);
                    return;
                }

                LocalDate appointmentDate = LocalDate.parse(dateStr);
                LocalTime appointmentTime = LocalTime.parse(timeStr);
                Appointment appointment = new Appointment(patientId, doctorId, appointmentDate, appointmentTime, status);
                if (this.dbManager.addAppointment(appointment)) {
                    JOptionPane.showMessageDialog(this, "Appointment added successfully!", "Success", 1);
                    this.clearAppointmentFields();
                    this.loadAppointmentsData();
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to add appointment.", "Database Error", 0);
                }
            } catch (DateTimeParseException var11) {
                JOptionPane.showMessageDialog(this, "Invalid Date or Time format. Date: YYYY-MM-DD, Time: HH:MM:SS.", "Input Error", 0);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "An unexpected error occurred: " + ex.getMessage(), "Error", 0);
                ex.printStackTrace();
            }

        } else {
            JOptionPane.showMessageDialog(this, "All appointment fields are required.", "Input Error", 0);
        }
    }

    private void updateAppointment() {
        String idStr = this.appointmentIdField.getText().trim();
        if (idStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select an appointment from the table to update.", "Selection Error", 2);
        } else {
            String selectedPatientName = (String)this.appointmentPatientComboBox.getSelectedItem();
            String selectedDoctorName = (String)this.appointmentDoctorComboBox.getSelectedItem();
            String dateStr = this.appointmentDateField.getText().trim();
            String timeStr = this.appointmentTimeField.getText().trim();
            String status = (String)this.appointmentStatusComboBox.getSelectedItem();
            if (selectedPatientName != null && selectedDoctorName != null && !dateStr.isEmpty() && !timeStr.isEmpty() && status != null) {
                try {
                    int appointmentId = Integer.parseInt(idStr);
                    int patientId = (Integer)this.patientNameToIdMap.getOrDefault(selectedPatientName, -1);
                    if (patientId == -1) {
                        JOptionPane.showMessageDialog(this, "Selected patient not found in database.", "Error", 0);
                        return;
                    }

                    int doctorId = (Integer)this.doctorNameToIdMap.getOrDefault(selectedDoctorName, -1);
                    if (doctorId == -1) {
                        JOptionPane.showMessageDialog(this, "Selected doctor not found in database.", "Error", 0);
                        return;
                    }

                    LocalDate appointmentDate = LocalDate.parse(dateStr);
                    LocalTime appointmentTime = LocalTime.parse(timeStr);
                    Appointment appointment = new Appointment(appointmentId, patientId, doctorId, appointmentDate, appointmentTime, status);
                    if (this.dbManager.updateAppointment(appointment)) {
                        JOptionPane.showMessageDialog(this, "Appointment updated successfully!", "Success", 1);
                        this.clearAppointmentFields();
                        this.loadAppointmentsData();
                    } else {
                        JOptionPane.showMessageDialog(this, "Failed to update appointment. Check if ID exists.", "Database Error", 0);
                    }
                } catch (NumberFormatException var13) {
                    JOptionPane.showMessageDialog(this, "Invalid Appointment ID.", "Input Error", 0);
                } catch (DateTimeParseException var14) {
                    JOptionPane.showMessageDialog(this, "Invalid Date or Time format. Date: YYYY-MM-DD, Time: HH:MM:SS.", "Input Error", 0);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "An unexpected error occurred: " + ex.getMessage(), "Error", 0);
                    ex.printStackTrace();
                }

            } else {
                JOptionPane.showMessageDialog(this, "All appointment fields are required for update.", "Input Error", 0);
            }
        }
    }

    private void deleteAppointment() {
        String idStr = this.appointmentIdField.getText().trim();
        if (idStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select an appointment from the table to delete.", "Selection Error", 2);
        } else {
            try {
                int appointmentId = Integer.parseInt(idStr);
                int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this appointment?", "Confirm Deletion", 0);
                if (confirm == 0) {
                    if (this.dbManager.deleteAppointment(appointmentId)) {
                        JOptionPane.showMessageDialog(this, "Appointment deleted successfully!", "Success", 1);
                        this.clearAppointmentFields();
                        this.loadAppointmentsData();
                    } else {
                        JOptionPane.showMessageDialog(this, "Failed to delete appointment. Check if ID exists.", "Database Error", 0);
                    }
                }
            } catch (NumberFormatException var4) {
                JOptionPane.showMessageDialog(this, "Invalid Appointment ID.", "Input Error", 0);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "An unexpected error occurred during deletion: " + ex.getMessage(), "Error", 0);
                ex.printStackTrace();
            }

        }
    }

    private void addMedicalRecord() {
        String selectedPatientName = (String)this.recordPatientComboBox.getSelectedItem();
        String selectedDoctorName = (String)this.recordDoctorComboBox.getSelectedItem();
        String dateStr = this.recordDateField.getText().trim();
        String diagnosis = this.recordDiagnosisField.getText().trim();
        String prescription = this.recordPrescriptionField.getText().trim();
        if (selectedPatientName != null && selectedDoctorName != null && !dateStr.isEmpty() && !diagnosis.isEmpty() && !prescription.isEmpty()) {
            try {
                int patientId = (Integer)this.patientNameToIdMap.getOrDefault(selectedPatientName, -1);
                if (patientId == -1) {
                    JOptionPane.showMessageDialog(this, "Selected patient not found in database.", "Error", 0);
                    return;
                }

                int doctorId = (Integer)this.doctorNameToIdMap.getOrDefault(selectedDoctorName, -1);
                if (doctorId == -1) {
                    JOptionPane.showMessageDialog(this, "Selected doctor not found in database.", "Error", 0);
                    return;
                }

                LocalDate recordDate = LocalDate.parse(dateStr);
                MedicalRecord medicalRecord = new MedicalRecord(patientId, doctorId, recordDate, diagnosis, prescription);
                if (this.dbManager.addMedicalRecord(medicalRecord)) {
                    JOptionPane.showMessageDialog(this, "Medical Record added successfully!", "Success", 1);
                    this.clearMedicalRecordFields();
                    this.loadMedicalRecordsData();
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to add medical record.", "Database Error", 0);
                }
            } catch (DateTimeParseException var10) {
                JOptionPane.showMessageDialog(this, "Invalid Date format. Please use YYYY-MM-DD.", "Input Error", 0);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "An unexpected error occurred: " + ex.getMessage(), "Error", 0);
                ex.printStackTrace();
            }

        } else {
            JOptionPane.showMessageDialog(this, "All medical record fields are required.", "Input Error", 0);
        }
    }

    private void updateMedicalRecord() {
        String idStr = this.recordIdField.getText().trim();
        if (idStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select a medical record from the table to update.", "Selection Error", 2);
        } else {
            String selectedPatientName = (String)this.recordPatientComboBox.getSelectedItem();
            String selectedDoctorName = (String)this.recordDoctorComboBox.getSelectedItem();
            String dateStr = this.recordDateField.getText().trim();
            String diagnosis = this.recordDiagnosisField.getText().trim();
            String prescription = this.recordPrescriptionField.getText().trim();
            if (selectedPatientName != null && selectedDoctorName != null && !dateStr.isEmpty() && !diagnosis.isEmpty() && !prescription.isEmpty()) {
                try {
                    int recordId = Integer.parseInt(idStr);
                    int patientId = (Integer)this.patientNameToIdMap.getOrDefault(selectedPatientName, -1);
                    if (patientId == -1) {
                        JOptionPane.showMessageDialog(this, "Selected patient not found in database.", "Error", 0);
                        return;
                    }

                    int doctorId = (Integer)this.doctorNameToIdMap.getOrDefault(selectedDoctorName, -1);
                    if (doctorId == -1) {
                        JOptionPane.showMessageDialog(this, "Selected doctor not found in database.", "Error", 0);
                        return;
                    }

                    LocalDate recordDate = LocalDate.parse(dateStr);
                    MedicalRecord medicalRecord = new MedicalRecord(recordId, patientId, doctorId, recordDate, diagnosis, prescription);
                    if (this.dbManager.updateMedicalRecord(medicalRecord)) {
                        JOptionPane.showMessageDialog(this, "Medical Record updated successfully!", "Success", 1);
                        this.clearMedicalRecordFields();
                        this.loadMedicalRecordsData();
                    } else {
                        JOptionPane.showMessageDialog(this, "Failed to update medical record. Check if ID exists.", "Database Error", 0);
                    }
                } catch (NumberFormatException var12) {
                    JOptionPane.showMessageDialog(this, "Invalid Medical Record ID.", "Input Error", 0);
                } catch (DateTimeParseException var13) {
                    JOptionPane.showMessageDialog(this, "Invalid Date format. Please use YYYY-MM-DD.", "Input Error", 0);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "An unexpected error occurred: " + ex.getMessage(), "Error", 0);
                    ex.printStackTrace();
                }

            } else {
                JOptionPane.showMessageDialog(this, "All medical record fields are required for update.", "Input Error", 0);
            }
        }
    }

    private void deleteMedicalRecord() {
        String idStr = this.recordIdField.getText().trim();
        if (idStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select a medical record from the table to delete.", "Selection Error", 2);
        } else {
            try {
                int recordId = Integer.parseInt(idStr);
                int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this medical record?", "Confirm Deletion", 0);
                if (confirm == 0) {
                    if (this.dbManager.deleteMedicalRecord(recordId)) {
                        JOptionPane.showMessageDialog(this, "Medical Record deleted successfully!", "Success", 1);
                        this.clearMedicalRecordFields();
                        this.loadMedicalRecordsData();
                    } else {
                        JOptionPane.showMessageDialog(this, "Failed to delete medical record. Check if ID exists.", "Database Error", 0);
                    }
                }
            } catch (NumberFormatException var4) {
                JOptionPane.showMessageDialog(this, "Invalid Medical Record ID.", "Input Error", 0);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "An unexpected error occurred during deletion: " + ex.getMessage(), "Error", 0);
                ex.printStackTrace();
            }

        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new HospitalManagementSystem$1());
    }
}
