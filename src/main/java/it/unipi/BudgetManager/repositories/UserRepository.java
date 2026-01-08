/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package it.unipi.BudgetManager.repositories;

import it.unipi.BudgetManager.beans.User;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author iacopomassei
 */
public interface UserRepository extends CrudRepository <User, Integer>{
    User findByUsername(String username);
    boolean existsByUsername(String username);
}
