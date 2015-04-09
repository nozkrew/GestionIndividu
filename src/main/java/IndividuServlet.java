import pck_classes.*;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Nozkrew on 09/04/2015.
 */

@WebServlet("/individu")
public class IndividuServlet extends HttpServlet {

    @Resource(name = "individuDatabase")
    private DataSource dataSource;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Individu individu = new Individu();

        try(Connection connection = dataSource.getConnection()){
            ArrayList<Individu> individus = individu.getList(connection);
            req.setAttribute("individus", individus);
            req.getRequestDispatcher("/individu.jsp").forward(req, resp);
            System.out.println("doGet");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String nom = req.getParameter("Nom");
        String dateNaissanceStr = req.getParameter("dateNaissance");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date startDate = sdf.parse(dateNaissanceStr);
            Individu individu = new Individu(nom, startDate);
            try (Connection connection = dataSource.getConnection()) {
                individu.insert(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


}
