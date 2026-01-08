/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package it.unipi.BudgetManager.repositories;

import it.unipi.BudgetManager.beans.Spesa;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author iacopomassei
 */
public interface SpesaRepository extends CrudRepository <Spesa, Integer> {
    
}
