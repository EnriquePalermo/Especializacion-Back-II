package com.elaparato.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter @Setter
@Entity
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ESPECIFICO LA GENERACION DE ID EN LA BASE DE DATOS Y USO IDENTITY POR FACILIDAD(AUTOINCREMENTAL)
    private int id_venta;
    private Date fecha;


 @ManyToMany (mappedBy = "listaVentas")
 private List<Producto> listaProductos;


}
