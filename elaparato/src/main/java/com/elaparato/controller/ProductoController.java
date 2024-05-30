package com.elaparato.controller;
import com.elaparato.model.Producto;
import com.elaparato.service.IProductoService;
import exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductoController {

    @Autowired
    private IProductoService prodServ;

    //crear un nuevo producto
    @PostMapping("/productos/create")

    public String createProducto(@RequestBody Producto prod) {
        prodServ.saveProducto(prod);
        return "Producto creado correctamente";
    }

    //obtener todos los productos
    @GetMapping("/productos/getall")
    public List<Producto> getProductos () {
        return prodServ.getProductos();
    }

   //Modificar los datos de un producto
    @PutMapping("/productos/edit")
    public String editProducto(@RequestBody Producto prod) {
        prodServ.editProducto(prod);
        return "Producto editado correctamente";
    }

    //ELIMINA UN PRODUCTO POR ID Y AGREGUE UN RESPONSEENTITY Y UNA EXEPCION
    @DeleteMapping("/productos/delete/{id}")
    public ResponseEntity<?> eliminarProducto(@PathVariable int id) throws ResourceNotFoundException {
        ResponseEntity<?> response = null;
        prodServ.deleteProducto(id);
        response = ResponseEntity.status(HttpStatus.OK).body("Producto eliminado correctamente");
        return response;
    }
}

