package GUI;

import Application_Server_Interface.Data.Patient;
import Application_Server_Interface.Data.Patient_Value;
import Application_Server_Interface.Data.User;
import Application_Server_Interface.Manager.Client_Manager;
import javafx.embed.swing.JFXPanel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoField;

public class Main_Frame {

    static GraphicsConfiguration gc; // Class field containing config info
    private JFrame mainPage;
    private JFXPanel mainPanel;
    private Panel_Controller controller;
    private Client_Manager cm;
    private User login_user;
    private Patient patient;

    public Main_Frame(User login_user, Patient patient) throws IOException {

        this.login_user = login_user;
        this.patient = patient;
        cm = new Client_Manager();

        // Setting up the main frame
        mainPage = new JFrame(patient.get_givenname() + "_" + patient.get_familyname(), gc);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        mainPage.setBounds(0, 0, screenSize.width+10, screenSize.height);
        //System.out.println(screenSize);
        //mainPage.setExtendedState(6);
        Dimension default_dim = mainPage.getSize();
        mainPage.setVisible(true);
        mainPage.setResizable(true);

        mainPage.getContentPane().addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                Component c = (Component) e.getSource();
                if (mainPage.getExtendedState() == 6){
                    mainPage.setSize(default_dim);
                    controller.setPanelControllerSize(default_dim);
                    mainPage.revalidate();
                    mainPage.repaint();

                }
                else {
                    mainPage.setSize(mainPage.getSize());
                    controller.setPanelControllerSize(mainPage.getSize());
                    mainPage.revalidate();
                    mainPage.repaint();
                }
            }
        });

        // Setting up main panel
        controller = new Panel_Controller(mainPage.getSize());
        controller.setPanelControllerSize(mainPage.getSize());
        mainPanel = controller.getMainPanel();

        // Adding main panel to main frame
        mainPage.add(mainPanel);

        // Closing program when when main frame is closed
        mainPage.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        mainPage.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                controller.closeProgram();
            }
        });

        controller.startSimulation();
    }

    public void sendMeanValues(){
        boolean send = true;
        LocalDateTime starting_time = LocalDateTime.now().plusSeconds(20);
        LocalDateTime current_time;
        LocalDateTime time_stamp;
        Duration duration;
        long time_diff_sec;
        int counter_of_recordings = 1;

        while(send){
            current_time = LocalDateTime.now();
            time_stamp = starting_time.plusMinutes(counter_of_recordings);
            duration = Duration.between(starting_time.toLocalTime(), current_time.toLocalTime());
            time_diff_sec = duration.getSeconds();

            if ((int)(time_diff_sec/60) == counter_of_recordings){
                try {
                    cm.send_patient_value_to_pv_db(new Patient_Value(patient.get_patient_id(), time_stamp,
                            getECGMean(), getRespMean(), getTempMean(),
                            getBPMaxMean(), getBPMinMean(),
                            "ECG: " + getECGStatus() + ", BPressure: " + getBPStatus() + ", RespRate: " + getRespStatus() + ", Temp: " + getTempStatus()), login_user);
                }catch (IOException e) {
                    e.printStackTrace();
                    JOptionPane failed_send_means = new JOptionPane();
                    failed_send_means.showMessageDialog(null,"Failed to send values to database","Error Message", JOptionPane.ERROR_MESSAGE);
                }
                counter_of_recordings += 1;
            }
        }
    }

    public int getECGMean(){
        return (int)controller.getMeans()[0];
    }

    public int getBPMaxMean(){
        return (int)controller.getMeans()[1];
    }

    public int getBPMinMean(){
        return (int)controller.getMeans()[2];
    }

    public int getRespMean(){
        return (int)controller.getMeans()[3];
    }

    public double getTempMean(){
        return Math.round(controller.getMeans()[4] * 100.0)/100.0;
    }

    public String getECGStatus(){
        if (controller.getStatus()[0] == 2)
            return "Warning";
        if (controller.getStatus()[0] == 3)
            return "Urgent";
        if (controller.getStatus()[0] == 1)
            return "Stable";
        else
            return null;
    }

    public String  getBPStatus(){
        if (controller.getStatus()[1] == 2)
            return "Warning";
        if (controller.getStatus()[1] == 3)
            return "Urgent";
        if (controller.getStatus()[1] == 1)
            return "Stable";
        else
            return null;
    }

    public String getRespStatus(){
        if (controller.getStatus()[2] == 2)
            return "Warning";
        if (controller.getStatus()[2] == 3)
            return "Urgent";
        if (controller.getStatus()[2] == 1)
            return "Stable";
        else
            return null;
    }

    public String getTempStatus(){
        if (controller.getStatus()[3] == 2)
            return "Warning";
        if (controller.getStatus()[3] == 3)
            return "Urgent";
        if (controller.getStatus()[3] == 1)
            return "Stable";
        else
            return null;
    }



}
