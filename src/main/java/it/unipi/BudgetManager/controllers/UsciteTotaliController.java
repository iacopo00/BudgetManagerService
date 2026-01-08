/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unipi.BudgetManager.controllers;

import it.unipi.BudgetManager.beans.CredenzialiDB;
import it.unipi.BudgetManager.beans.Spesa;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author iacopomassei
 */
@Controller
@RequestMapping(path = "/603217")
public class UsciteTotaliController {

    @GetMapping(path = "/spese")
    public @ResponseBody
    List<Spesa> vediSpese(@RequestParam String username) {
        List<Spesa> risultato = new ArrayList<>();
        try ( Connection co = DriverManager.getConnection(CredenzialiDB.URL, CredenzialiDB.username, CredenzialiDB.password);  Statement st = co.createStatement();  Statement st1 = co.createStatement();) {
            ResultSet rs = st.executeQuery("SELECT * FROM spesa WHERE username = '" + username + "'");
            while (rs.next()) {
                risultato.add(new Spesa(rs.getInt("id"), rs.getString("causale"), rs.getDate("data"), rs.getFloat("costo"), rs.getString("username")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return risultato;
    }

    @GetMapping(path = "/contaSpese")
    public @ResponseBody
    int contaSpese(@RequestParam String username) {
        int conteggio = 0;
        try ( Connection co = DriverManager.getConnection(CredenzialiDB.URL, CredenzialiDB.username, CredenzialiDB.password);  Statement st = co.createStatement()) {
            ResultSet rs = st.executeQuery("SELECT COUNT(*) AS CONTEGGIO FROM spesa WHERE username = '" + username + "'");
            while (rs.next()) {
                conteggio += rs.getInt("CONTEGGIO");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return conteggio;
    }

    @DeleteMapping(path = "/eliminaSpesa")
    public @ResponseBody
    String eliminaSpesa(@RequestParam("id") int id) {
        try ( Connection co = DriverManager.getConnection(CredenzialiDB.URL, CredenzialiDB.username, CredenzialiDB.password);  PreparedStatement ps = co.prepareStatement("DELETE FROM spesa WHERE id = ?");) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            return "408"; // eliminazione non andata a buon fine
        }

        return "104";
    }

    @GetMapping(path = "/filtraSpese")
    public @ResponseBody
    List<Spesa> filtra(@RequestParam("anno") String anno, @RequestParam("mese") String mese, @RequestParam("username") String username) {

        List<Spesa> risultato = new ArrayList<>();

        try ( Connection co = DriverManager.getConnection(CredenzialiDB.URL, CredenzialiDB.username, CredenzialiDB.password);  Statement st = co.createStatement();) {
            ResultSet rs;

            StringBuilder queryBuilder = new StringBuilder("SELECT * FROM spesa WHERE username = '").append(username).append("'");

            // in base ai campi vuoti capisco cosa devo andare a cercare
            if (!"".equals(anno)) {
                queryBuilder.append(" AND YEAR(data) = ").append(Integer.parseInt(anno));
            }

            if (!"".equals(mese)) {
                st.executeUpdate("SET lc_time_names = 'It_IT'");
                queryBuilder.append(" AND MONTHNAME(data) = '").append(mese).append("'");
            }

            rs = st.executeQuery(queryBuilder.toString());

            while (rs.next()) {
                risultato.add(new Spesa(rs.getInt("id"), rs.getString("causale"), rs.getDate("data"), rs.getFloat("costo"), rs.getString("username")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return risultato;
    }

    @GetMapping(path = "/contoFiltroSpesa")
    public @ResponseBody
    int conteggioFiltroSpesa(@RequestParam("anno") String anno, @RequestParam("mese") String mese, @RequestParam("username") String username) {

        int conteggio = 0;

        try ( Connection co = DriverManager.getConnection(CredenzialiDB.URL, CredenzialiDB.username, CredenzialiDB.password);  Statement st = co.createStatement();) {
            ResultSet rs;

            StringBuilder contoBuilder = new StringBuilder("SELECT COUNT(*) AS CONTEGGIO FROM spesa WHERE username = '").append(username).append("'");

            // in base ai campi vuoti capisco cosa devo andare a cercare
            if (!"".equals(anno)) {
                contoBuilder.append(" AND YEAR(data) = ").append(Integer.parseInt(anno));
            }

            if (!"".equals(mese)) {
                st.executeUpdate("SET lc_time_names = 'It_IT'");
                contoBuilder.append(" AND MONTHNAME(data) = '").append(mese).append("'");
            }

            rs = st.executeQuery(contoBuilder.toString());

            if (rs.next()) {
                conteggio += rs.getInt("CONTEGGIO");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conteggio;
    }
}
