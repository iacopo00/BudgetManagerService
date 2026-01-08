/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unipi.BudgetManager.beans;


import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

/**
 *
 * @author iacopomassei
 */
@Entity
@Table(name = "gara", schema = "603217")
@IdClass(GaraKey.class)

public class Gara {

    @Id
    @Column(name = "n_gara")
    private Integer n_gara;

    @Column(name = "data")
    private Date data;
    @Column(name = "campionato")
    private String campionato;
    @Column(name = "luogo")
    private String luogo;
    @Column(name = "rimborso")
    private float rimborso;
    @Id
    @Column(name = "username")
    private String username;

    public Gara() {
    }

    public Gara(Integer n_gara, Date data, String campionato, String luogo, float rimborso, String username) {
        this.n_gara = n_gara;
        this.data = data;
        this.campionato = campionato;
        this.luogo = luogo;
        this.rimborso = rimborso;
        this.username = username;
    }

    public Integer getN_gara() {
        return n_gara;
    }

    public Date getData() {
        return data;
    }

    public String getCampionato() {
        return campionato;
    }

    public String getLuogo() {
        return luogo;
    }

    public float getRimborso() {
        return rimborso;
    }

    public String getUsername() {
        return username;
    }

    public void setN_gara(Integer n_gara) {
        this.n_gara = n_gara;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public void setCampionato(String campionato) {
        this.campionato = campionato;
    }

    public void setLuogo(String luogo) {
        this.luogo = luogo;
    }

    public void setRimborso(float rimborso) {
        this.rimborso = rimborso;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
