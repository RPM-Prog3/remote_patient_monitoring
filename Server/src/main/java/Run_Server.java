import Application_Server_Interface.Data.Patient;
import Application_Server_Interface.Data.Patient_Value;
import Application_Server_Interface.Data.User;
import Application_Server_Interface.Manager.Client_Manager;
import Application_Server_Interface.Messenger.Client_Messenger;
import Server_Core.Database.Manage_Patient_Values_db;
import Server_Core.Database.Manage_Patient_db;
import Server_Core.Database.Manage_User_db;
import Application_Server_Interface.Messenger.Server_Messenger;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.stream.Collectors;

@WebServlet(
        name = "remote_patient_monitoring_server",
        description = "remote_patient_monitoring_server",
        urlPatterns = {
                "/rpm",
                "/rpm/login",
                "/rpm/mypatients",
                "/rpm/summary",
                "/rpm/add_patient",
                "/rpm/add_patient_value",
                "/rpm/add_user",
                "/rpm/request_patients",
                "/rpm/request_users"
                }
        )
public class Run_Server extends HttpServlet {
    Manage_User_db user_db;
    Manage_Patient_db patient_db;
    Manage_Patient_Values_db patient_values_db;
    boolean debug = false;


    public Run_Server() throws IOException, SQLException {
        print_datetime();
        user_db = new Manage_User_db();
        patient_db = new Manage_Patient_db();
        patient_values_db = new Manage_Patient_Values_db();
        if (debug) {
            System.out.println("Starting server");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (debug) {
            System.out.println("---------------------------------");
            print_datetime();
            System.out.println("Do get");
        }

        String uri = req.getRequestURI();

        PrintWriter writer = resp.getWriter();
        switch (uri) {
            case "/Server/rpm": {
                write_file("landing.ejs", writer, false);
                break;
            }
            case "/Server/rpm/login": {
                //writer.println("login page");
                write_file("login.ejs", writer, false);
                String username = req.getParameter("username");
                String password = req.getParameter("password");
                if (username != null || password != null) {
                    if (!username.equals("") || !password.equals("")) {
                        System.out.println(username + " " + password);
                        User login_user = new User(username, password);
                        boolean valid_user = check_valid_user(login_user);
                        System.out.println("valid user: " + valid_user);
                        if (valid_user) {
                            resp.sendRedirect("/Server/rpm/mypatients");
                        } else {

                            resp.sendRedirect("/Server/rpm/login");
//
//                            String str = scan.nextLine();
//                            if (str.equals("#error#")) {
//                                writer.println("<p1><font color=\"red\">Credentials not recognized! Please try again. </font></p1>");
////                                writer.println("<div scope=\"error\">ID</th>");
////                            writer.println (htmlResponse);
//                            }
                        }
                    }
                }
                break;


            }
            case "/Server/rpm/mypatients": {
                write_file("patientlist.ejs", writer, true);
                break;
            }
            case "/Server/rpm/summary": {
                write_file("summary.ejs", writer, false);
                break;
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (debug) {
            System.out.println("---------------------------------");
            print_datetime();
            System.out.println("Do post");
        }

        String reqBody=req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        System.out.println(String.format("reqBody: %s", reqBody));
        resp.setContentType("text/html");

        String server_path = req.getServletPath();

        Server_Messenger messenger = new Server_Messenger();
        messenger.set_success(true);

        Gson gson = new Gson();
        Client_Messenger msg = gson.fromJson(reqBody, Client_Messenger.class);

        if (server_path.equals("/rpm/login")){
            User login_user = msg.get_user_login();
            boolean valid_user = check_valid_user(login_user);
            messenger.set_valid_user(valid_user);
            if (!valid_user) {
                req.setAttribute("error", "Unknown user, please try again.");
                messenger.set_success(false);
            }
        }
        else {
            User login_user = msg.get_user_login();
            boolean valid_user = check_valid_user(login_user);
            messenger.set_valid_user(valid_user);
            if (valid_user) {
                switch (server_path) {
                    case "/rpm/add_patient": {
                        Patient p = gson.fromJson(msg.get_request_message(), Patient.class);
                        p.print_patient_details();
                        try {
                            patient_db.add_patient(p);
                        } catch (SQLException e) {
                            e.printStackTrace();
                            System.out.println("failed to add patient");
                            messenger.set_success(false);
                        }
                        break;
                    }
                    case "/rpm/add_patient_value": {
                        Patient_Value pv = gson.fromJson(msg.get_request_message(), Patient_Value.class);
                        pv.print_values();
                        try {
                            patient_values_db.add_patient_value(pv);
                        } catch (SQLException e) {
                            e.printStackTrace();
                            System.out.println("failed to add patient values");
                            messenger.set_success(false);
                        }
                    }
                    case "/rpm/add_user": {
                        User u = gson.fromJson(msg.get_request_message(), User.class);
                        try {
                            user_db.add_user(u);
                        } catch (SQLException e) {
                            e.printStackTrace();
                            System.out.println("failed to add user");
                            messenger.set_success(false);
                        }
                        break;
                    }
                    case "/rpm/request_users": {
                        User u = gson.fromJson(msg.get_request_message(), User.class);
                        try {
                            String users_json = user_db.get_users();
                            messenger.set_message(users_json);
                        } catch (SQLException e) {
                            e.printStackTrace();
                            System.out.println("failed to get users");
                            messenger.set_success(false);
                        }
                        break;
                    }
                    case "/rpm/request_patients": {
                        User u = gson.fromJson(msg.get_request_message(), User.class);
                        try {
                            String patients_json = patient_db.get_patients();
                            messenger.set_message(patients_json);
                        } catch (SQLException e) {
                            e.printStackTrace();
                            messenger.set_success(false);
                        }
                        break;
                    }
                    default: {
                        req.setAttribute("error", "Unknown page.");
                        messenger.set_success(false);
                    }
                }
            } else {
                System.out.println("Invalid user");
                messenger.set_success(false);
            }
        }
        if (debug) {
            System.out.println(String.format("Success: %b", messenger.get_success()));
        }
        Gson messenger_gson = new Gson();
        String messenger_json_string = messenger_gson.toJson(messenger);
        resp.getWriter().write(messenger_json_string);
    }

    private boolean check_valid_user(User u){
        // Checks to see if this is a valid user
        boolean valid_user = false;
        try {
            valid_user = user_db.find_user(u);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return valid_user;
    }

    private void print_datetime(){
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String strDate = dateFormat.format(date);
        System.out.println("Current time: " + strDate);
    }

    private void write_file(String file_name, PrintWriter writer, boolean add_to_table){
        try {
            String file_path = System.getProperty("user.dir").toString().replace("\\", "/").replace(" ", "%20") + "/web_app/" + file_name;

            FileInputStream input_stream = new FileInputStream(file_path);
            Scanner scan = new Scanner(input_stream);
                while(scan.hasNextLine()){
                if (add_to_table) {
                    String str = scan.nextLine();
                    if (str.equals("#col#")) {
                        writer.println("<th scope=\"col\">ID</th>");
                        writer.println("<th scope=\"col\">First Name</th>");
                        writer.println("<th scope=\"col\">Last Name</th>");
                        writer.println("<th scope=\"col\">Birth of Date</th>");
                        writer.println("<th scope=\"col\">Email</th>");
                        writer.println("<th scope=\"col\">Phone Number</th>");
                    } else if (str.equals("#row#")){
                        String patients_json = patient_db.get_patients();
                        Gson gson = new Gson();
                        String[][] patients = gson.fromJson(patients_json, String[][].class);
                        for (int i = 0; i < patients.length; i++) {
                            writer.println("<tr>");
                            writer.println(String.format("<th scope=\"row\"><a href=\"/Server/rpm/summary\">%s</a></th>", patients[i][0])); // the idx need to be retrieved from the database
                            writer.println(String.format("<td>%s</td>", patients[i][2])); // First name
                            writer.println(String.format("<td>%s</td>", patients[i][1])); // Last Name
                            writer.println(String.format("<td>%s</td>", patients[i][3])); // Birth of Date
                            writer.println(String.format("<td>%s</td>", patients[i][4])); // Email
                            writer.println(String.format("<td>%s</td>", patients[i][5])); // Phone Number
                            writer.println("</tr>");
                        }
                    } else {
                        writer.println(str);
                    }
                } else {
                    writer.println(scan.nextLine());
                }
            }
            scan.close();
        } catch (FileNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}

