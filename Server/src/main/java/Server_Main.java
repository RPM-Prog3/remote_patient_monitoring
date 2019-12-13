import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.stream.Collectors;

@WebServlet(urlPatterns={""},loadOnStartup = 1)
public class Server_Main extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        print_datetime();
        System.out.println("Do get - sends to app");

        String server_path = req.getServletPath();
        String method = req.getMethod();

        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");

        PrintWriter writer = resp.getWriter();
        writer.append("Hello get");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        print_datetime();
        System.out.println("Do post - sent to server");

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