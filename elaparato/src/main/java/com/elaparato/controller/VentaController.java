package com.elaparato.controller;

import com.elaparato.model.Venta;
import com.elaparato.repository.IProductoRepository;
import com.elaparato.service.IVentaService;
import exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VentaController {


    @Autowired
    private IVentaService ventServ;

    private IProductoRepository productoRepository;

    //crear una nueva venta
    @PostMapping("/ventas/create")
    public Venta createVentao(@RequestBody Venta nuevaVenta) {

        return ventServ.saveVenta(nuevaVenta);
    }

    //obtener todas las ventas
    @GetMapping("/ventas/getall")
    public List<Venta> getVentas () {
        return ventServ.getVentas();
    }

    @GetMapping("/ventas/{id}")
    public Venta getVentaById(@PathVariable Integer id) {
        Venta venta = ventServ.findVenta(id);
        return venta;
    }

    //Modificar los datos de una venta
    @PutMapping("/ventas/edit/{id}")
    public Venta editVenta(@PathVariable Integer id, @RequestBody Venta vent) {
        ventServ.editVenta(id, vent);
        return vent;
    }

    //ELIMINA UNA VENTA POR ID Y AGREGUE UN RESPONSEENTITY Y UNA EXEPCION
    @DeleteMapping("/ventas/delete/{id}")
    public ResponseEntity<?> eliminarVenta(@PathVariable int id) throws ResourceNotFoundException {
        ResponseEntity<?> response = null;
        ventServ.deleteVenta(id);
        response = ResponseEntity.status(HttpStatus.OK).body("Venta eliminada correctamente");
        return response;
    }


}
