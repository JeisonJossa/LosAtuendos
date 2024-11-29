package com.losatuendos.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "envio_lavanderia")
public class EnvioLavanderia {
    @Id
    @Column(name = "id_envio", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "fecha_envio", nullable = false)
    private LocalDate fechaEnvio;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(LocalDate fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

}