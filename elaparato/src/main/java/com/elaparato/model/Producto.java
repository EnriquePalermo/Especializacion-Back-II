package com.elaparato.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Producto {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY) // ESPECIFICO LA GENERACION DE ID EN LA BASE DE DATOS Y USO IDENTITY POR FACILIDAD(AUTOINCREMENTAL)
    private int id;
    private String nombre;
    private String descripcion;
    private Double precio;// CAMBIO PRECIO A DOUBLE PARA QUE NO GENERE ERROR EN LA BASE DE DATOS
    private int cantidad;

    @ManyToMany(cascade = CascadeType.ALL)
    @JsonIgnore //importante agregar para evitar errores de formato en la response
    @JoinTable(
            name = "producto_lista_ventas",
            joinColumns = @JoinColumn(name = "lista_productos_id"),
            inverseJoinColumns = @JoinColumn(name = "lista_ventas_id_venta")
    )
    private List<Venta> listaVentas;

    public Producto() {
    }

    public Producto(int id, String nombre, String descripcion, Double precio, int cantidad, List<Venta> listaVentas) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.cantidad = cantidad;
        this.listaVentas = listaVentas;
    }


}
