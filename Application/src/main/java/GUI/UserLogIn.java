package GUI;

import Application_Server_Interface.Data.User;
import Application_Server_Interface.Manager.Client_Manager;
import Application_Server_Interface.Messenger.Server_Messenger;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.*;

public class UserLogIn {
    private JFrame WelcomePage;
    private JPanel LogInPanel;
    private JPanel EnterTextPanel;
    private JButton LogInButton;
    private JLabel user_name_label, password_label, response_message;
    private JTextField WelcomeText, user_name_input;
    private JTextField password_input;
    private JOptionPane error_message;
    private PatientList listOfPatients;

    public UserLogIn() {
        WelcomePage = new JFrame();

        // Username Label
        user_name_label = new JLabel();
        user_name_label.setHorizontalAlignment(JTextField.CENTER);
        user_name_label.setText("User Name :");
        user_name_input = new JTextField();
        user_name_input.setHorizontalAlignment(JTextField.CENTER);

        // Password Label
        password_label = new JLabel();
        password_label.setHorizontalAlignment(JTextField.CENTER);
        password_label.setText("Password :");
        password_input = new JTextField();
        password_input.setHorizontalAlignment(JTextField.CENTER);

        // Setting components to be visible:
        user_name_label.setVisible(true);
        user_name_input.setVisible(true);
        password_label.setVisible(true);
        password_input.setVisible(true);

        // Setting the panel where credentials are entered
        EnterTextPanel = new JPanel(new GridLayout(2, 2));
        EnterTextPanel.add(user_name_label);
        EnterTextPanel.add(user_name_input);
        EnterTextPanel.add(password_label);
        EnterTextPanel.add(password_input);
        EnterTextPanel.setVisible(true);
        LogInPanel = new JPanel();
        LogInButton = new JButton("Log-in");

        // Setting the features of thw Welcome text
        WelcomeText = new JTextField("Welcome to RPM");
        WelcomeText.setHorizontalAlignment(JTextField.CENTER);
        Font font1 = new Font("SansSerif", Font.BOLD, 35);
        WelcomeText.setFont(font1);
        WelcomeText.setEditable(false);
        LogInPanel.add(LogInButton);

        // Setting up the frame
        WelcomePage.add(BorderLayout.PAGE_START, WelcomeText);
        WelcomePage.add(BorderLayout.CENTER, EnterTextPanel);
        WelcomePage.add(BorderLayout.PAGE_END, LogInPanel);
        WelcomePage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        WelcomePage.setTitle("Welcome to RPM");
        WelcomePage.setSize(500, 300);
        WelcomePage.setVisible(true);
        WelcomePage.setResizable(false);
        WelcomePage.setLocationRelativeTo(null);

        LogInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String userName = user_name_input.getText();
                String password = password_input.getText();
                error_message = new JOptionPane();
                boolean valid_user = false;
                User login_user;
                try {
                    Client_Manager cm = new Client_Manager();
                    login_user = new User(userName.trim(), password.trim());
                    Server_Messenger login_msg = cm.send_user_to_login(login_user);
                    valid_user = login_msg.get_valid_user();
                    System.out.println(valid_user);
                    System.out.println(login_msg.get_success());
                    if (valid_user) {
                        // Here we want to open the list of patients along with their details
                        try {
                            listOfPatients = new PatientList(login_user);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        WelcomePage.setVisible(false);
                    }
                    else {
                        error_message.showMessageDialog(WelcomePage,"Invalid username or password","Error Message", JOptionPane.ERROR_MESSAGE);
                        user_name_input.setText("");
                        password_input.setText("");
                    }
                } catch (IOException e){
                    e.printStackTrace();
                    valid_user = false;
                }
            }
        });
    }
}

