/*package GUI;

import com.google.gson.Gson;
import server.Client_Manager;
import server.Server_Messenger;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;


public class PatientList {
    static GraphicsConfiguration gc; // Class field containing config info
    private JFrame list, new_patient;
    private JPanel mainpanel, mainpanel_2, credentials_panel, list_panel;
    private JButton add_patient, done_button, show_vitals;
    private JLabel p_name, p_lastname, p_email, p_cellnum, dateOfbirth;
    private JTextField name_in, lastname_in, email_in, cellnum_in, dateOfbirth_in;
    private String new_name, new_lastname, new_email, new_cellnum, new_dateOfbirth;
    private JOptionPane error_message, success_message;
    private boolean status;
    private int patient_counter, nOfpatients, start_idx;
    private ArrayList<JCheckBox> patientlist;
    private Border border;
    private JScrollPane scrollPane;
    private Client_Manager manager;

    public PatientList() throws IOException {
        list = new JFrame("List of Patients", gc);
        list.setSize(1000, 700);

        mainpanel = new JPanel(new BorderLayout());
        mainpanel.setVisible(true);

        // Setting up a button that when pressed it prompts the user to a page where
        // the details of a new patients can be typed. The new patient, upon confirmation
        // of the validity of the details inserted is inserted in the database
        add_patient = new JButton("Add Patient");
        add_patient.setVisible(true);
        add_patient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    AddPatient();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        // Creating an array containing many JCheckBox objects each containing the information
        // i.e. first and last name of each patient in the database
        patientlist = new ArrayList<JCheckBox>();

        // Setting up the panel that displays the patients such that it has as many rows as
        // the number of patients in the database
        nOfpatients = getnOfpatients();
        System.out.println(nOfpatients);
        list_panel = new JPanel(new GridLayout(nOfpatients, 1));
        list_panel.setVisible(true);

        // Adding each of the objects (patient) to the panel that will display them.
        createList();

        patient_counter = 0;
        show_vitals = new JButton();
        show_vitals.setVisible(true);

        // Adding ActionListener to the "show_vitals" button so that when pressed it will show
        // as many GUIs as selected by the user.
        show_vitals.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 1; i <= patient_counter; i++) {
                    Main_Frame GUI = new Main_Frame();
                }
            }
        });

        // Constructing main panel
        mainpanel.add(BorderLayout.PAGE_START, add_patient);
        mainpanel.add(BorderLayout.CENTER, list_panel);
        mainpanel.add(BorderLayout.PAGE_END, show_vitals);
        list.add(mainpanel);
        showList();
    }

    public void AddPatient() throws SQLException {
        // Setting up the main frame
        new_patient = new JFrame("Patient Details", gc);
        new_patient.setSize(500, 300);
        new_patient.setVisible(true);
        new_patient.setResizable(false);

        // Setting up main panel
        mainpanel_2 = new JPanel(new BorderLayout());
        mainpanel_2.setVisible(true);

        // Setting up credentials panel
        credentials_panel = new JPanel(new GridLayout(5, 2));
        credentials_panel.setVisible(true);

        // New Patient's First Name
        p_name = new JLabel();
        p_name.setHorizontalAlignment(JTextField.CENTER);
        p_name.setText("Patient's First Name :");
        name_in = new JTextField();
        name_in.setHorizontalAlignment(JTextField.CENTER);

        // New Patient's Last Name
        p_lastname = new JLabel();
        p_lastname.setHorizontalAlignment(JTextField.CENTER);
        p_lastname.setText("Patient's Last Name :");
        lastname_in = new JTextField();
        lastname_in.setHorizontalAlignment(JTextField.CENTER);

        //New Patient's Date of Birth
        dateOfbirth = new JLabel();
        dateOfbirth.setHorizontalAlignment(JTextField.CENTER);
        dateOfbirth.setText("Patient's Date of Birth:");
        dateOfbirth_in = new JTextField();
        dateOfbirth_in.setHorizontalAlignment(JTextField.CENTER);

        // New Patient's Email
        p_email = new JLabel();
        p_email.setHorizontalAlignment(JTextField.CENTER);
        p_email.setText("Patient's Email Address:");
        email_in = new JTextField();
        email_in.setHorizontalAlignment(JTextField.CENTER);

        // New Patient's Cell Number
        p_cellnum = new JLabel();
        p_cellnum.setHorizontalAlignment(JTextField.CENTER);
        p_cellnum.setText("Patient's cellphone number:");
        cellnum_in = new JTextField();
        cellnum_in.setHorizontalAlignment(JTextField.CENTER);
        //--------------Remember to set that the date of birth has to have a specific format that if not respected shoots an error------------------#

        // Done button
        done_button = new JButton("Done");
        done_button.setVisible(true);
        done_button.setHorizontalAlignment(JButton.CENTER);

        credentials_panel.add(p_name);
        credentials_panel.add(name_in);
        credentials_panel.add(p_lastname);
        credentials_panel.add(lastname_in);
        credentials_panel.add(dateOfbirth);
        credentials_panel.add(dateOfbirth_in);
        credentials_panel.add(p_email);
        credentials_panel.add(email_in);
        credentials_panel.add(p_cellnum);
        credentials_panel.add(cellnum_in);

        mainpanel_2.add(BorderLayout.CENTER, credentials_panel);
        mainpanel_2.add(BorderLayout.PAGE_END, done_button);
        new_patient.add(mainpanel_2);

        done_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new_name = name_in.getText();
                new_lastname = lastname_in.getText();
                new_email = email_in.getText();
                new_cellnum = cellnum_in.getText();
                new_dateOfbirth = dateOfbirth_in.getText();

                error_message = new JOptionPane();
                success_message = new JOptionPane();

                Client_Manager manager = null;
                try {
                    manager = new Client_Manager();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                try {
                    manager.send_patient_to_add_patients_db(new_name, new_lastname, new_dateOfbirth, new_email, new_cellnum);
                    success_message.showMessageDialog(new_patient, "Patient Successfully Added", "information", JOptionPane.INFORMATION_MESSAGE);
                    updateList();
                    showList();
                    new_patient.setVisible(false);
                } catch (Exception e) {
                    error_message.showMessageDialog(new_patient, "Unable to add Patient", "Error Message", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public ArrayList<JCheckBox> getPatientsInfo() throws IOException {
        nOfpatients = 0;
        Gson gson = new Gson();
        Server_Messenger messenger = new Server_Messenger();
        Client_Manager manager = new Client_Manager();
        try {
            messenger = manager.get_patients_from_patients_db();
            boolean success = messenger.get_success();
            String output = messenger.get_message();
            System.out.println(output);
            String[][] patients = gson.fromJson(output, String[][].class);
            for (int i = 0; i < patients.length; i++) {
                String p_name = patients[i][0] + " " + patients[i][1];
                patientlist.add(new JCheckBox(p_name));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Unable to retrieve patient information");
        }
        return patientlist;
    }

    public int getnOfpatients() throws IOException {
        int nOfpatients = 0;
        Gson gson = new Gson();
        Server_Messenger messenger = new Server_Messenger();
        Client_Manager manager = new Client_Manager();
        try {
            messenger = manager.get_patients_from_patients_db();
            boolean success = messenger.get_success();
            System.out.println(success);
            String output = messenger.get_message();
            System.out.println(output);
            String[][] patients = gson.fromJson(output, String[][].class);
            nOfpatients = patients.length;

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Unable to retrieve patient number");
        }

        return nOfpatients;
    }

    public void createList() throws IOException {
        status = false;
        patientlist = getPatientsInfo();
        for (int i = 0; i < patientlist.size(); i++) {
            patientlist.get(i).setVisible(true);
            list_panel.add(patientlist.get(i));
        }
        for (int i = 0; i < patientlist.size(); i++) {
            selectPatient(i);
        }
    }

    public void updateList() throws IOException {
        status = true;
        patientlist = getPatientsInfo();
        patientlist.get(patientlist.size() - 1).setVisible(true);
        list_panel.add(patientlist.get(patientlist.size() - 1));
        selectPatient(patientlist.size() - 1);
    }

    public void selectPatient(int select) {
        patientlist.get(select).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
                boolean selected = abstractButton.getModel().isSelected();
                if (selected) {
                    patient_counter++;
                    show_vitals.setText("Show the selected " + patient_counter + " patients vitals");
                } else if (!selected && patient_counter > 0) {
                    patient_counter--;
                    show_vitals.setText("Show the selected " + patient_counter + " patients vitals");
                }
            }
        });
    }

    public void showList() throws IOException {
        list_panel.setVisible(true);
        int rows = getnOfpatients();
        list_panel.setLayout(new GridLayout(rows, 1));
        list.setResizable(false);
        list.setVisible(true);
    }


    public void Test_DB_Connection() {
        Server_Messenger messenger = new Server_Messenger();

        try {
            Client_Manager manager = new Client_Manager();
            System.out.println("Connection Successful");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Unable to Connect");
        }
    }
}*/