package com.losatuendos.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "prenda")
public class Prenda {
    @Id
    @Column(name = "referencia", nullable = false, length = 10)
    private String referencia;

    @Lob
    @Column(name = "tipo", nullable = false)
    private String tipo;

    @Column(name = "color", nullable = false, length = 50)
    private String color;

    @Column(name = "marca", nullable = false, length = 50)
    private String marca;

    @Column(name = "talla", nullable = false, length = 10)
    private String talla;

    @Column(name = "valor_alquiler", nullable = false, precision = 10, scale = 2)
    private BigDecimal valorAlquiler;

    @Column(name = "pedreria")
    private Boolean pedreria;

    @Lob
    @Column(name = "largo_corto")
    private String largoCorto;

    @Column(name = "cantidad_piezas")
    private Integer cantidadPiezas;

    @Lob
    @Column(name = "tipo_traje")
    private String tipoTraje;

    @Lob
    @Column(name = "accesorios")
    private String accesorios;

    @Column(name = "nombre_disfraz", length = 50)
    private String nombreDisfraz;

    @Lob
    @Column(name = "estado", nullable = false)
    private String estado;

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getTalla() {
        return talla;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    }

    public BigDecimal getValorAlquiler() {
        return valorAlquiler;
    }

    public void setValorAlquiler(BigDecimal valorAlquiler) {
        this.valorAlquiler = valorAlquiler;
    }

    public Boolean getPedreria() {
        return pedreria;
    }

    public void setPedreria(Boolean pedreria) {
        this.pedreria = pedreria;
    }

    public String getLargoCorto() {
        return largoCorto;
    }

    public void setLargoCorto(String largoCorto) {
        this.largoCorto = largoCorto;
    }

    public Integer getCantidadPiezas() {
        return cantidadPiezas;
    }

    public void setCantidadPiezas(Integer cantidadPiezas) {
        this.cantidadPiezas = cantidadPiezas;
    }

    public String getTipoTraje() {
        return tipoTraje;
    }

    public void setTipoTraje(String tipoTraje) {
        this.tipoTraje = tipoTraje;
    }

    public String getAccesorios() {
        return accesorios;
    }

    public void setAccesorios(String accesorios) {
        this.accesorios = accesorios;
    }

    public String getNombreDisfraz() {
        return nombreDisfraz;
    }

    public void setNombreDisfraz(String nombreDisfraz) {
        this.nombreDisfraz = nombreDisfraz;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

}