import Data.Patient;
import Data.User;
import Database.Manage_Patient_db;
import Database.Manage_User_db;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.stream.Collectors;

@WebServlet(
        name = "remote_patient_monitoring_server",
        description = "remote_patient_monitoring_server",
        urlPatterns = {
                "/rpm",
                "/rpm/login",
                "/rpm/add_patient",
                "/rpm/request_patients"
                }
        )
public class Run_Server extends HttpServlet {
    Manage_User_db user_db;
    Manage_Patient_db patient_db;
    boolean debug = true;


    public Run_Server() throws IOException, SQLException {
        print_datetime();
        user_db = new Manage_User_db();
        patient_db = new Manage_Patient_db();
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

//        String server_path = req.getServletPath();
//        String method = req.getMethod();
//
//        String reqBody = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
//        System.out.println(String.format("Request body: %s",reqBody));
//
//        System.out.println(server_path);
//        System.out.println(method);

        PrintWriter writer = resp.getWriter();
        writer.append("Get needs to be implemented still");

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
        boolean success = true;

        if (server_path.equals("/rpm/add_patient")) {
            Gson gson = new Gson();
            Patient p = gson.fromJson(reqBody, Patient.class);
            p.print_patient_details();
            try {
                patient_db.add_patient(p);
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("failed to add patient");
                success = false;
            }
        } else if (server_path.equals("/rpm/request_patients")) {
            Gson gson = new Gson();
            User u = gson.fromJson(reqBody, User.class);
            if (check_valid_user(u)){
                try {
                    ResultSet rs = patient_db.get_patients_resultSet();
                    patient_db.print_rs(rs);
                } catch (SQLException e) {
                    success = false;
                    e.printStackTrace();
                }
            } else {
                success = false;
                req.setAttribute("error", "Unknown user, please try again.");
            }

        } else if (server_path.equals("/rpm/login")){
            // Used login backend code as inspiration: https://stackoverflow.com/questions/2349633/doget-and-dopost-in-servlets
            Gson gson = new Gson();
            User u = gson.fromJson(reqBody, User.class);

            boolean valid_user  = check_valid_user(u);
            if (!valid_user) {
                success = false;
                req.setAttribute("error", "Unknown user, please try again.");
            }
        }
        if (debug) {
            System.out.println(String.format("Success: %b", success));
        }
        if (success) {
            resp.getWriter().write("Client successfully sent information");
        } else {
            resp.getWriter().write("Error occurred.");
        }
    }

    private boolean check_valid_user(User u){
        boolean valid_user = false;
        try {
            valid_user = user_db.find_user(u);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (valid_user){
            System.out.println("valid user");
        } else {
            System.out.println("invalid user");
        }
        return valid_user;
    }

    private void print_datetime(){
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String strDate = dateFormat.format(date);
        System.out.println("Current time: " + strDate);
    }
}