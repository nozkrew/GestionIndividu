package pck_classes;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Nozkrew on 09/04/2015.
 */
public class Individu {
    private int id;
    private String nom;
    private Date dateDeNaissance;

    public Individu(String p_nom, Date p_dateDeNaissance) {
        this.nom = p_nom;
        this.dateDeNaissance = p_dateDeNaissance;
    }

    public Individu()
    {

    }
    public void insert(Connection p_connection)
    {
        String request = "INSERT INTO individu (nom, dateNaissance) values (?, ?)";
        try(java.sql.PreparedStatement pstmt = p_connection.prepareStatement(request))
        {
            pstmt.setString(1, this.nom);
            java.sql.Date sqlDate = new java.sql.Date(this.dateDeNaissance.getTime());
            pstmt.setDate(2, sqlDate);

            pstmt.executeUpdate();
            System.out.println("Indiviu ajouté");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(Connection p_connection, int p_id)
    {
        String request = "DELETE FROM individu WHERE id = ?";
        try(java.sql.PreparedStatement pstmt = p_connection.prepareStatement(request))
        {
            pstmt.setInt(1, p_id);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Individu> getList(Connection p_connection) {
        try (java.sql.Statement stmt = p_connection.createStatement()) {
            String request = "SELECT * FROM individu";
            ArrayList<Individu> individus = new ArrayList<Individu>();

            try (java.sql.ResultSet resultSet = stmt.executeQuery(request)) {
                while(resultSet.next()) {
                    individus.add(new Individu(resultSet.getString("nom"), resultSet.getDate("dateNaissance")));
                }
                return individus;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Date getDateDeNaissance() {
        return dateDeNaissance;
    }

    public void setDateDeNaissance(Date dateDeNaissance) {
        this.dateDeNaissance = dateDeNaissance;
    }
}
