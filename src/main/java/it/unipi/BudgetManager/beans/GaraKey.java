/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unipi.BudgetManager.beans;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author iacopomassei
 */
public class GaraKey implements Serializable {

    private Integer n_gara;
    private String username;

    public GaraKey() {
    }

    public GaraKey(Integer n_gara, String username) {
        this.n_gara = n_gara;
        this.username = username;
    }

    /**
     *
     * @param n_gara
     */
    public void setN_gara(Integer n_gara) {
        this.n_gara = n_gara;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getN_gara() {
        return n_gara;
    }

    public String getUsername() {
        return username;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GaraKey garaKey = (GaraKey) o;
        return Objects.equals(n_gara, garaKey.n_gara) &&
                Objects.equals(username, garaKey.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(n_gara, username);
    }
    

}


