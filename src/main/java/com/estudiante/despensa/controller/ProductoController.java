package com.estudiante.despensa.controller;

import com.estudiante.despensa.model.Producto;
import com.estudiante.despensa.model.ResumenInventario;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final List<Producto> productos = new ArrayList<>();

    public ProductoController() {
        productos.add(new Producto(1L, "Leche", "Lácteos", 2, 12.50));
        productos.add(new Producto(2L, "Queso", "Lácteos", 5, 18.00));
        productos.add(new Producto(3L, "Arroz", "Granos", 4, 8.50));
        productos.add(new Producto(4L, "Frijol", "Granos", 10, 7.25));
        productos.add(new Producto(5L, "Detergente", "Limpieza", 3, 22.00));
        productos.add(new Producto(6L, "Jugo de naranja", "Bebidas", 1, 9.75));
        validarIdentificadoresUnicos();
    }

    private void validarIdentificadoresUnicos() {
        Set<Long> ids = new HashSet<>();
        for (Producto producto : productos) {
            if (!ids.add(producto.getId())) {
                throw new IllegalStateException("Identificador duplicado: " + producto.getId());
            }
        }
    }

    @GetMapping
    public ResponseEntity<List<Producto>> listarProductos() {
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> buscarPorId(@PathVariable Long id) {
        for (Producto producto : productos) {
            if (producto.getId().equals(id)) {
                return ResponseEntity.ok(producto);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<Producto>> buscarPorCategoria(@PathVariable String categoria) {
        List<Producto> resultado = new ArrayList<>();
        for (Producto producto : productos) {
            if (producto.getCategoria().equalsIgnoreCase(categoria)) {
                resultado.add(producto);
            }
        }
        return ResponseEntity.ok(resultado);
    }

    @GetMapping("/stock-bajo")
    public ResponseEntity<List<Producto>> consultarStockBajo() {
        List<Producto> resultado = new ArrayList<>();
        for (Producto producto : productos) {
            if (producto.getCantidad() <= 3) {
                resultado.add(producto);
            }
        }
        return ResponseEntity.ok(resultado);
    }

    @GetMapping("/mayor-valor")
    public ResponseEntity<Producto> consultarMayorValor() {
        Producto mayor = null;
        for (Producto producto : productos) {
            if (mayor == null || producto.calcularSubtotal() > mayor.calcularSubtotal()) {
                mayor = producto;
            }
        }
        if (mayor == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(mayor);
    }

    @GetMapping("/resumen")
    public ResponseEntity<ResumenInventario> obtenerResumen() {
        int totalUnidades = 0;
        double valorTotal = 0;
        for (Producto producto : productos) {
            totalUnidades += producto.getCantidad();
            valorTotal += producto.calcularSubtotal();
        }
        valorTotal = Math.round(valorTotal * 100.0) / 100.0;
        return ResponseEntity.ok(new ResumenInventario(productos.size(), totalUnidades, valorTotal));
    }
}