import pck_classes.*;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
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

        System.out.println("doGet");
        Individu individu = new Individu();
        if(req.getParameter("delete") != null){
            try(Connection connection = dataSource.getConnection()) {
                int id = Integer.parseInt(req.getParameter("delete"));
                individu.delete(connection, id);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        try(Connection connection = dataSource.getConnection()){
            ArrayList<Individu> individus = individu.getList(connection);
            req.setAttribute("individus", individus);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/individu.jsp");
            dispatcher.forward(req,resp);
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
            Date dateNaissance = sdf.parse(dateNaissanceStr);
            Date dateActuelle = new Date();




                Individu individu = new Individu(nom, dateNaissance);
                try (Connection connection = dataSource.getConnection()) {
                    if(dateNaissance.compareTo(dateActuelle)<0)
                    {
                        individu.insert(connection);
                    }
                    else
                    {
                        req.setAttribute("erreur", "Vous ne pouvez pas être né dans le futur");
                    }
                    ArrayList<Individu> individus = individu.getList(connection);
                    req.setAttribute("individus", individus);
                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/individu.jsp");
                    dispatcher.forward(req, resp);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

        } catch (ParseException e){
                e.printStackTrace();

        }
    }


}
