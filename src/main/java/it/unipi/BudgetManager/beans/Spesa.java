/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unipi.BudgetManager.beans;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author iacopomassei
 */
@Entity
@Table(name = "spesa", schema = "603217")
public class Spesa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "causale")
    private String causale;
    @Column(name = "data")
    private Date data;
    @Column(name = "costo")
    private float costo;
    @Column(name = "username")
    private String username;

    public Spesa() {
    }

    public Spesa(Integer id, String causale, Date data, float costo, String username) {
        this.id = id;
        this.causale = causale;
        this.data = data;
        this.costo = costo;
        this.username = username;
    }

    public Integer getId() {
        return id;
    }

    public String getCausale() {
        return causale;
    }

    public Date getData() {
        return data;
    }

    public float getCosto() {
        return costo;
    }

    public String getUsername() {
        return username;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCausale(String causale) {
        this.causale = causale;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public void setCosto(float costo) {
        this.costo = costo;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
