package com.tcsw.ventas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

class DetalleVentaTest {

    @Test
    void crearDetalleVentaValido() {
        Producto producto = new Producto("P-001", "Teclado", new BigDecimal("150.00"), 10);
        DetalleVenta detalle = new DetalleVenta(producto, 2, new BigDecimal("150.00"));

        assertEquals(producto, detalle.getProducto());
        assertEquals(2, detalle.getCantidad());
        assertEquals(new BigDecimal("150.00"), detalle.getPrecioUnitario().obtener());
    }

    @Test
    void calcularSubtotalCorrectamente() {
        Producto producto = new Producto("P-001", "Teclado", new BigDecimal("100.00"), 10);
        DetalleVenta detalle = new DetalleVenta(producto, 3, new BigDecimal("100.00"));

        Moneda subtotal = detalle.obtenerSubtotal();
        assertEquals(new BigDecimal("300.00"), subtotal.obtener());
    }

    @Test
    void rechazarProductoNulo() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new DetalleVenta(null, 2, new BigDecimal("100.00")));
        assertEquals("El producto de la partida no puede ser nulo.", exception.getMessage());
    }

    @Test
    void rechazarCantidadCero() {
        Producto producto = new Producto("P-001", "Teclado", new BigDecimal("100.00"), 10);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new DetalleVenta(producto, 0, new BigDecimal("100.00")));
        assertEquals("La cantidad en la partida debe ser mayor que cero.", exception.getMessage());
    }

    @Test
    void rechazarCantidadNegativa() {
        Producto producto = new Producto("P-001", "Teclado", new BigDecimal("100.00"), 10);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new DetalleVenta(producto, -5, new BigDecimal("100.00")));
        assertEquals("La cantidad en la partida debe ser mayor que cero.", exception.getMessage());
    }

    @Test
    void rechazarPrecioUnitarioNegativo() {
        Producto producto = new Producto("P-001", "Teclado", new BigDecimal("100.00"), 10);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new DetalleVenta(producto, 2, new BigDecimal("-50.00")));
        assertEquals("El precio unitario no puede ser negativo.", exception.getMessage());
    }

    @Test
    void rechazarPrecioUnitarioNulo() {
        Producto producto = new Producto("P-001", "Teclado", new BigDecimal("100.00"), 10);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new DetalleVenta(producto, 2, null));
        assertEquals("El precio unitario no puede ser nulo.", exception.getMessage());
    }

    @Test
    void detalleVentaConCantidadUno() {
        Producto producto = new Producto("P-001", "Teclado", new BigDecimal("150.00"), 10);
        DetalleVenta detalle = new DetalleVenta(producto, 1, new BigDecimal("150.00"));

        Moneda subtotal = detalle.obtenerSubtotal();
        assertEquals(new BigDecimal("150.00"), subtotal.obtener());
    }

    @Test
    void igualdadDeDetallesVenta() {
        Producto producto = new Producto("P-001", "Teclado", new BigDecimal("100.00"), 10);
        DetalleVenta detalle1 = new DetalleVenta(producto, 2, new BigDecimal("100.00"));
        DetalleVenta detalle2 = new DetalleVenta(producto, 2, new BigDecimal("100.00"));

        assertEquals(detalle1, detalle2);
    }
}
