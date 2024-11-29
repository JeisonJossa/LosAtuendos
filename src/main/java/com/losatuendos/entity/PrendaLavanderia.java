package com.losatuendos.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "prenda_lavanderia")
public class PrendaLavanderia {
    @Id
    @Column(name = "id_lavanderia", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "referencia_prenda", nullable = false)
    private Prenda referenciaPrenda;

    @Column(name = "prioridad", nullable = false)
    private Boolean prioridad = false;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Prenda getReferenciaPrenda() {
        return referenciaPrenda;
    }

    public void setReferenciaPrenda(Prenda referenciaPrenda) {
        this.referenciaPrenda = referenciaPrenda;
    }

    public Boolean getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(Boolean prioridad) {
        this.prioridad = prioridad;
    }

}