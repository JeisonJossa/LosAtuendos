package com.losatuendos.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "detalle_envio_lavanderia")
public class DetalleEnvioLavanderia {
    @Id
    @Column(name = "id_detalle_envio", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_envio", nullable = false)
    private com.losatuendos.entity.EnvioLavanderia idEnvio;

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

    public com.losatuendos.entity.EnvioLavanderia getIdEnvio() {
        return idEnvio;
    }

    public void setIdEnvio(com.losatuendos.entity.EnvioLavanderia idEnvio) {
        this.idEnvio = idEnvio;
    }

    public com.losatuendos.entity.Prenda getReferenciaPrenda() {
        return referenciaPrenda;
    }

    public void setReferenciaPrenda(com.losatuendos.entity.Prenda referenciaPrenda) {
        this.referenciaPrenda = referenciaPrenda;
    }

}