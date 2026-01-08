/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unipi.BudgetManager.controllers;

import it.unipi.BudgetManager.beans.CredenzialiDB;
import it.unipi.BudgetManager.beans.Gara;
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
public class EntrateTotaliController {

    @GetMapping(path = "/gare")
    public @ResponseBody
    List<Gara> vediGare(@RequestParam String username) {
        List<Gara> risultato = new ArrayList<>();
        try ( Connection co = DriverManager.getConnection(CredenzialiDB.URL, CredenzialiDB.username, CredenzialiDB.password);  Statement st = co.createStatement()) {
            ResultSet rs = st.executeQuery("SELECT * FROM gara WHERE username = '" + username + "'");
            while (rs.next()) {
                Gara g = new Gara();
                g.setN_gara(rs.getInt("n_gara"));
                g.setData(rs.getDate("data"));
                g.setCampionato(rs.getString("campionato"));
                g.setLuogo(rs.getString("luogo"));
                g.setRimborso(rs.getFloat("rimborso"));
                risultato.add(g);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return risultato;
    }

    @GetMapping(path = "/contaGare")
    public @ResponseBody
    int contaGare(@RequestParam String username) {
        int conteggio = 0;
        try ( Connection co = DriverManager.getConnection(CredenzialiDB.URL, CredenzialiDB.username, CredenzialiDB.password);  Statement st = co.createStatement()) {
            ResultSet rs = st.executeQuery("SELECT COUNT(*) AS CONTEGGIO FROM gara WHERE username = '" + username + "'");
            while (rs.next()) {
                conteggio += rs.getInt("CONTEGGIO");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return conteggio;
    }

    @DeleteMapping(path = "/eliminaGara")
    public @ResponseBody
    String eliminaGara(@RequestParam("numeroGara") int numeroGara, @RequestParam("username") String username) {
        try ( Connection co = DriverManager.getConnection(CredenzialiDB.URL, CredenzialiDB.username, CredenzialiDB.password);  PreparedStatement ps = co.prepareStatement("DELETE FROM gara WHERE n_gara = ? AND username = ?");) {
            ps.setInt(1, numeroGara);
            ps.setString(2, username);
            ps.executeUpdate();
        } catch (SQLException ex) {
            return "408"; // eliminazione non andata a buon fine
        }

        return "104";
    }

    @GetMapping(path = "/filtraGare")
    public @ResponseBody
    List<Gara> filtra(@RequestParam("anno") String anno, @RequestParam("mese") String mese, @RequestParam("luogo") String luogo, @RequestParam("campionato") String campionato, @RequestParam("username") String username) {
        
        List<Gara> risultato = new ArrayList<>();
        
        try ( Connection co = DriverManager.getConnection(CredenzialiDB.URL, CredenzialiDB.username, CredenzialiDB.password);  Statement st = co.createStatement();) {
            ResultSet rs;

            StringBuilder queryBuilder = new StringBuilder("SELECT * FROM gara WHERE username = '").append(username).append("'");
            

            // in base ai campi vuoti capisco cosa devo andare a cercare
            if (!"".equals(anno)) {
                queryBuilder.append(" AND YEAR(data) = ").append(Integer.parseInt(anno));
            }

            if (!"".equals(mese)) {
                st.executeUpdate("SET lc_time_names = 'It_IT'");
                queryBuilder.append(" AND MONTHNAME(data) = '").append(mese).append("'");
            }

            if (!"".equals(luogo)) {
                queryBuilder.append(" AND SUBSTRING_INDEX(luogo, ' (', 1) = '").append(luogo).append("'");
            }

            if (!"".equals(campionato)) {
                queryBuilder.append(" AND campionato = '").append(campionato).append("'");
            }

            rs = st.executeQuery(queryBuilder.toString());

            while (rs.next()) {
                Gara g = new Gara();
                g.setN_gara(rs.getInt("n_gara"));
                g.setData(rs.getDate("data"));
                g.setCampionato(rs.getString("campionato"));
                g.setLuogo(rs.getString("luogo"));
                g.setRimborso(rs.getFloat("rimborso"));
                risultato.add(g);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return risultato;
    }
    
    @GetMapping(path = "/contoFiltro")
    public @ResponseBody
    int conteggioFiltro(@RequestParam("anno") String anno, @RequestParam("mese") String mese, @RequestParam("luogo") String luogo, @RequestParam("campionato") String campionato, @RequestParam("username") String username) {
        
        int conteggio = 0;
        
        try ( Connection co = DriverManager.getConnection(CredenzialiDB.URL, CredenzialiDB.username, CredenzialiDB.password);  Statement st = co.createStatement();) {
            ResultSet rs;

            StringBuilder contoBuilder = new StringBuilder("SELECT COUNT(*) AS CONTEGGIO FROM gara WHERE username = '").append(username).append("'");

            // in base ai campi vuoti capisco cosa devo andare a cercare
            if (!"".equals(anno)) {
                contoBuilder.append(" AND YEAR(data) = ").append(Integer.parseInt(anno));
            }

            if (!"".equals(mese)) {
                st.executeUpdate("SET lc_time_names = 'It_IT'");
                contoBuilder.append(" AND MONTHNAME(data) = '").append(mese).append("'");
            }

            if (!"".equals(luogo)) {
                contoBuilder.append(" AND SUBSTRING_INDEX(luogo, ' (', 1) = '").append(luogo).append("'");
            }

            if (!"".equals(campionato)) {
                contoBuilder.append(" AND campionato = '").append(campionato).append("'");
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
