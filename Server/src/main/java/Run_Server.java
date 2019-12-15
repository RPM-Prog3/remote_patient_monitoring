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

        System.out.println(server_path);
        System.out.println(method);
        System.out.println(reqBody);

        if (server_path.equals("/rpm/add_patient")){
            String familyname = req.getParameter("familyname");
            String givenname = req.getParameter("givenname");
            String dofbirth = req.getParameter("dofbirth");
            String email = req.getParameter("email");
            String phonenumber = req.getParameter("phonenumber");

            System.out.println(String.format("%s, %s, %s, %s, %s", familyname, givenname, dofbirth, email, phonenumber));
            
        }

        PrintWriter writer = resp.getWriter();
        writer.append("Hello get");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        print_datetime();
        System.out.println("Do post - sent to server");


        String orderNumber = req.getParameter("testString");
        System.out.println(orderNumber);
        resp.getWriter().print(orderNumber);

//        String reqBody= req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
//        resp.setContentType("text/html");
//        resp.getWriter().write("Thank you client!");

        String user = req.getParameter("user");

        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");

        PrintWriter writer = resp.getWriter();
        writer.append("Hello post");

    }

    private void print_datetime(){
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String strDate = dateFormat.format(date);
        System.out.println("Current time: " + strDate);
    }
}