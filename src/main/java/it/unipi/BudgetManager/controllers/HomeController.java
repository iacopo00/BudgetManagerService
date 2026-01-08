/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unipi.BudgetManager.controllers;

import it.unipi.BudgetManager.beans.CredenzialiDB;
import it.unipi.BudgetManager.beans.Gara;
import it.unipi.BudgetManager.beans.Spesa;
import it.unipi.BudgetManager.repositories.GaraRepository;
import it.unipi.BudgetManager.repositories.SpesaRepository;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author iacopomassei
 */
@Controller
@RequestMapping(path = "/603217")
public class HomeController {

    @Autowired
    private SpesaRepository spesaRepository;
    @Autowired
    private GaraRepository garaRepository;

    @PostMapping(path = "/addSpesa")
    public @ResponseBody
    String addNewSpesa(@RequestBody Spesa spesa) {

        if (spesa.getCosto() < 0) {
            return "407";
        }

        spesaRepository.save(spesa);
        return "103";
    }

    @PostMapping(path = "/addGara")
    public @ResponseBody
    String addNewGara(@RequestBody Gara gara) {

        if (garaRepository.existsByNGaraAndUsername(gara.getN_gara(), gara.getUsername())) {
            return "400"; //400 codifica del fatto che esista già una gara con quell'username e quel numero
        }

        String pattern = "^[A-Za-z\\s]+\\s\\(\\p{Lu}{2}\\)$";
        if (!Pattern.matches(pattern, gara.getLuogo())) {
            return "401"; // 401 codifica del fatto che la stringa non sia nel formato corretto
        }

        garaRepository.save(gara);

        return "100"; // 100 codifica gara salvata con successo
    }

    @GetMapping(path = "/budget")
    public @ResponseBody
    List<Float> trovaBudget(@RequestParam("username") String username, @RequestParam("mese") int mese, @RequestParam("anno") int anno) {
        float tot_e = 0;
        float tot_u = 0;
        try ( Connection co = DriverManager.getConnection(CredenzialiDB.URL, CredenzialiDB.username, CredenzialiDB.password);  Statement st = co.createStatement();  Statement st1 = co.createStatement();) {

            ResultSet rs = st.executeQuery("SELECT SUM(rimborso) AS RimborsiTotali FROM gara WHERE username = '" + username + "' AND MONTH(data) = " + mese + " AND YEAR(data) = '" + anno + "'");
            ResultSet rs1 = st1.executeQuery("SELECT SUM(costo) AS CostiTotali FROM spesa WHERE username = '" + username + "' AND MONTH(data) = " + mese + " AND YEAR(data) = '" + anno + "'");

            if (rs.next() && rs1.next()) {
                tot_e = rs.getFloat("RimborsiTotali");
                tot_u = rs1.getFloat("CostiTotali");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        List<Float> risultato = new ArrayList<>();
        risultato.add(tot_e);
        risultato.add(tot_u);
        
        return risultato;
    }

}
