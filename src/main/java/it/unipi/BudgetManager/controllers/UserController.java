/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unipi.BudgetManager.controllers;

import it.unipi.BudgetManager.beans.User;
import it.unipi.BudgetManager.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author iacopomassei
 */
@Controller
@RequestMapping(path = "/603217")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    /**
     *
     * @param user
     * @return
     */
    @PostMapping(path = "/login")
    public @ResponseBody
    String getAllGare(@RequestBody User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            User u = userRepository.findByUsername(user.getUsername());
            if (u.getPassword().equals(user.getPassword())) {
                return "102";
            } else {
                return "406";
            }
        }
        return "406";
    }

    @PostMapping(path = "/signUp")
    public @ResponseBody
    String addNewSpesa(@RequestBody User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            return "405"; //405 è stato deciso come codifica del fatto che esista già un utente con quell'username
        }

        userRepository.save(user);

        return "101"; //101 è stato deciso come codifica del fatto che la registrazione sia andata bene
    }
}
