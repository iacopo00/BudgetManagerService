/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package it.unipi.BudgetManager.repositories;

import it.unipi.BudgetManager.beans.Gara;
import it.unipi.BudgetManager.beans.GaraKey;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author iacopomassei
 */
public interface GaraRepository extends CrudRepository<Gara, GaraKey> {

    @Query("SELECT CASE WHEN COUNT(g) > 0 THEN true ELSE false END FROM Gara g WHERE g.n_gara = :n_gara AND g.username = :username")
    boolean existsByNGaraAndUsername(@Param("n_gara") Integer n_gara, @Param("username") String username);
}
