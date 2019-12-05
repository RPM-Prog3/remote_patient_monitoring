package GUI;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
    private int patient_counter, nOfpatients;
    private ArrayList<JCheckBox> patientlist;
    private Border border;

    public PatientList(){

        list = new JFrame("List of Patients", gc);
        list.setSize(1000,700);
        list.setVisible(true);
        list.setResizable(false);

        mainpanel = new JPanel(new BorderLayout());
        mainpanel.setVisible(true);

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

        patientlist = new ArrayList<JCheckBox>();
        nOfpatients = 0;
        Connection conn = connect_DB();
        try {
            Statement s = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = s.executeQuery("SELECT * FROM patient_info");
            rs = s.executeQuery("SELECT COUNT(*) FROM patient_info");
            // get the number of rows from the result set
            rs.next();
            nOfpatients = rs.getInt(1);
            System.out.println(nOfpatients);
            rs = s.executeQuery("SELECT givenname, familyname FROM patient_info WHERE id >= 1");

            while(rs.next()){
                System.out.println(rs.getString("givenname")+" "+ rs.getString("familyname"));
                patientlist.add(new JCheckBox(rs.getString("givenname")+" "+ rs.getString("familyname")));
            }
            rs.close();
            s.close();
            conn.close();
        }
        catch (Exception e){
        }

        list_panel = new JPanel(new GridLayout(nOfpatients,1));
        list_panel.setVisible(true);

        for (int i = 0; i < patientlist.size(); i++) {
            patientlist.get(i).setVisible(true);
            list_panel.add(patientlist.get(i));
        }

        patient_counter = 0;
        show_vitals = new JButton();
        show_vitals.setVisible(true);

        for (int i = 0; i < patientlist.size(); i++) {
            patientlist.get(i).addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    patient_counter++;
                    show_vitals.setText("Show the selected " + patient_counter + " patients vitals");
                }

                @Override
                public void mousePressed(MouseEvent e) {
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                }

                @Override
                public void mouseExited(MouseEvent e) {
                }
            });
        }

        show_vitals.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 1; i<= patient_counter; i++){
                    Main_Frame GUI = new Main_Frame();
                }
            }
        });

        mainpanel.add(BorderLayout.PAGE_START, add_patient);
        mainpanel.add(BorderLayout.CENTER, list_panel);
        mainpanel.add(BorderLayout.PAGE_END, show_vitals);
        list.add(mainpanel);
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
        credentials_panel = new JPanel(new GridLayout(5,2));
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

        mainpanel_2.add(BorderLayout.CENTER,credentials_panel);
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

                System.out.println("step");
                error_message = new JOptionPane();
                success_message = new JOptionPane();

                // Connecting the Database:
                Connection conn = connect_DB();

                System.out.println(conn);
                System.out.println(new_name);

                try {
                    Statement s = conn.createStatement();
                    System.out.println("step 2");
                    String sqlStr = String.format("insert into patient_info (familyname, givenname, dofbirth, email, phonenumber) values('%s', '%s', '%s', '%s', '%s');", new_name, new_lastname, new_dateOfbirth, new_email, new_cellnum);
                    s.executeUpdate (sqlStr);
                    System.out.println("EXECUTED");
                    success_message.showMessageDialog(new_patient, "Patient Successfully Added", "information", JOptionPane.INFORMATION_MESSAGE);
                    status = true;
                    getStatus();
                    s.close();
                    conn.close();
                    new_patient.setVisible(false);
                }
                catch (Exception e){
                    System.out.println(e);
                    error_message.showMessageDialog(new_patient,"Unable to add Patient","Error Message", JOptionPane.ERROR_MESSAGE);
                    status = false;
                    getStatus();
                }
            }
        });
    }

    public boolean getStatus(){
        return status;
    }

    public Connection connect_DB(){
        String dbUrl = "jdbc:postgresql://localhost:5432/postgres";
        try {
            // Registers the driver
            Class.forName("org.postgresql.Driver");
        } catch (Exception e) {

        }
        Connection conn= null;
        try {
            conn = DriverManager.getConnection(dbUrl, "postgres", "admin");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
