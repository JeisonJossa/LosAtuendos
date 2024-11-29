package com.losatuendos.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "detalle_alquiler")
public class DetalleAlquiler {
    @Id
    @Column(name = "id_detalle", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_servicio", nullable = false)
    private com.losatuendos.entity.ServicioAlquiler idServicio;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "referencia_prenda", nullable = false)
    private com.losatuendos.entity.Prenda referenciaPrenda;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public com.losatuendos.entity.ServicioAlquiler getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(com.losatuendos.entity.ServicioAlquiler idServicio) {
        this.idServicio = idServicio;
    }

    public com.losatuendos.entity.Prenda getReferenciaPrenda() {
        return referenciaPrenda;
    }

    public void setReferenciaPrenda(com.losatuendos.entity.Prenda referenciaPrenda) {
        this.referenciaPrenda = referenciaPrenda;
    }

}