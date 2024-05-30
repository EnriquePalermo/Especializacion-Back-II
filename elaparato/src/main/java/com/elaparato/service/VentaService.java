package com.elaparato.service;

import com.elaparato.model.Producto;
import com.elaparato.model.Venta;
import com.elaparato.repository.IProductoRepository;
import com.elaparato.repository.IVentaRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VentaService implements IVentaService{

    @Autowired
    private IVentaRepository ventaRepo;
    @Autowired
    private IProductoRepository productoRepository;

    @Autowired
    private EntityManager em;

    @Override
    public List<Venta> getVentas() {
        return ventaRepo.findAll();
    }

    @Transactional
    public Venta saveVenta(Venta nuevaventa) {
        //BUSCA SI LA LISTA ESTA VACIA
        if (!nuevaventa.getListaProductos().isEmpty()) {
        // EXTRAE LOS ID DE LOS PRODUCTOS
            List<Integer> productoIds =nuevaventa.getListaProductos().stream()
                    .map(Producto::getId)
                    .collect(Collectors.toList());

        // RECUPERA LOS PRODUCTOS EXISTENTES POR ID
            List<Producto> productos = productoRepository.findAllById(productoIds);

        // PONE LOS PRODUCTOS EN LA VENTA
            nuevaventa.setListaProductos(productos);

        // AÑADE LA NUEVA VENTA A CADA PRODUCTO POR LA BIDIRECCIONALIDAD
            productos.forEach(producto -> producto.getListaVentas().add(nuevaventa));
        }

        //GUARDA LA VENTA CON LOS PRODUCTOS ASOCIADOS
        return ventaRepo.save(nuevaventa);
    }

    @Transactional
    @Override
    public void deleteVenta(int id) {
        Venta venta = ventaRepo.findById(id).orElse(null);
        if (venta != null) {
            // ELIMINA LOS PRODUCTOS DE LA LISTA
            venta.getListaProductos().forEach(producto -> producto.getListaVentas().remove(venta));
            venta.getListaProductos().clear();

            // GUARDA LOS CAMBIOS EN LA VENTA
            venta.getListaProductos().forEach(productoRepository::save);

            // ELIMINA LA VENTA
            ventaRepo.delete(venta);
        }
    }

    @Override
    public Venta findVenta(int id) {
       return ventaRepo.findById(id).orElse(null);
    }

   @Transactional
    public void editVenta(int id, Venta newData) {
        Venta venta = em.find(Venta.class, id);
        if (venta != null) {
            venta.setFecha(newData.getFecha());
            venta.setListaProductos(newData.getListaProductos());
            em.merge(venta); // UTILIZO MERGE PARA ACTUALIZAR LA ENTIDAD
        }


   }

    }
