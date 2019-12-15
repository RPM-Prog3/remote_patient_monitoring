import Data.Patient;
import Data.User;
import Database.Manage_User_Db;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
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
                "/rpm/add_patient"
                }
        )
public class Run_Server extends HttpServlet {

    public Run_Server() {
        print_datetime();
        System.out.println("Starting server");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        print_datetime();
        System.out.println("Do get - sends to app");

        String server_path = req.getServletPath();
        String method = req.getMethod();

        String reqBody = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        System.out.println(String.format("Request body: %s",reqBody));

        System.out.println(server_path);
        System.out.println(method);

        PrintWriter writer = resp.getWriter();
        if (server_path.equals("/rpm/login")){
            // Used login backend code as inspiration: https://stackoverflow.com/questions/2349633/doget-and-dopost-in-servlets
            writer.append("Login page");
        }
        else if (server_path.equals("/rpm")){
            // check logged in, if not logged in return error
            writer.append("Main page");
        }
        else if (server_path.equals("/rpm/add_patient")){
            // return page not available
            writer.append("add_patient page");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        print_datetime();
        String reqBody=req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        System.out.println(String.format("reqBody: %s", reqBody));
        resp.setContentType("text/html");
        resp.getWriter().write("Client successfully sent information");

        String server_path = req.getServletPath();

        if (server_path.equals("/rpm/add_patient")) {
            Gson gson = new Gson();
            Patient p = gson.fromJson(reqBody, Patient.class);
            p.print_patient_details();
        } else if (server_path.equals("/rpm/login")){
            Gson gson = new Gson();
            User u = gson.fromJson(reqBody, User.class);
            u.print_username();
            Manage_User_Db user_db = new Manage_User_Db();
            boolean valid_user = user_db.find_user(u);
            if (valid_user){
                System.out.println("valid user");
//                resp.sendRedirect("/rpm");
            } else {
                System.out.println("invalid user");
                req.setAttribute("error", "Unknown user, please try again.");
            }

        }
    }

    private void print_datetime(){
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String strDate = dateFormat.format(date);
        System.out.println("Current time: " + strDate);
    }
}