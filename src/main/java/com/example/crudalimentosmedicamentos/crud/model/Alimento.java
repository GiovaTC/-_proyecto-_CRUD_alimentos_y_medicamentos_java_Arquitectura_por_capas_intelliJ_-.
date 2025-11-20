package com.example.crudalimentosmedicamentos.crud.model;

import java.time.LocalDate;

public class Alimento {
    private Long id;
    private String nombre;
    private String categoria;
    private LocalDate fechaVencimiento;
    private Integer stock;
    private String observaciones;

    // getters y setters
    public Long getId() { return id;}
    public void setId(Long id) { this.id = id;}
    public String getNombre() { return nombre;}
    public void setNombre(String nombre) { this.nombre = nombre;}
    public String getCategoria() { return categoria;}
    public void setCategoria(String categoria) { this.categoria = categoria;}
    public LocalDate getFechaVencimiento() { return fechaVencimiento;}
    public void setFechaVencimiento(LocalDate fechaVencimiento) { this.fechaVencimiento = fechaVencimiento;}
    public Integer getStock() { return stock;}
    public void setStock(Integer stock) { this.stock = stock;}
    public String getObservaciones() { return observaciones;}
    public void setObservaciones(String observaciones) { this.observaciones = observaciones;}
}
