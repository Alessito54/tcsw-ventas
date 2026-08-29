package com.tcsw.ventas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

class VentaTest {

    @Test
    void crearVentaValida() {
        Venta venta = new Venta();
        assertEquals(0, venta.cantidadDetalles());
    }

    @Test
    void agregarDetalleAVenta() {
        Venta venta = new Venta();
        Producto producto = new Producto("P-001", "Teclado", new BigDecimal("100.00"), 10);
        DetalleVenta detalle = new DetalleVenta(producto, 2, new BigDecimal("100.00"));

        venta.agregarDetalle(detalle);

        assertEquals(1, venta.cantidadDetalles());
        assertEquals(detalle, venta.obtenerDetalles().get(0));
    }

    @Test
    void calcularTotalVentaConUnDetalle() {
        Venta venta = new Venta();
        Producto producto = new Producto("P-001", "Teclado", new BigDecimal("100.00"), 10);
        DetalleVenta detalle = new DetalleVenta(producto, 2, new BigDecimal("100.00"));

        venta.agregarDetalle(detalle);

        Moneda total = venta.obtenerTotal();
        assertEquals(new BigDecimal("200.00"), total.obtener());
    }

    @Test
    void calcularTotalVentaConVariosDetalles() {
        Venta venta = new Venta();

        Producto producto1 = new Producto("P-001", "Teclado", new BigDecimal("100.00"), 10);
        DetalleVenta detalle1 = new DetalleVenta(producto1, 2, new BigDecimal("100.00"));

        Producto producto2 = new Producto("P-002", "Ratón", new BigDecimal("50.00"), 20);
        DetalleVenta detalle2 = new DetalleVenta(producto2, 3, new BigDecimal("50.00"));

        venta.agregarDetalle(detalle1);
        venta.agregarDetalle(detalle2);

        Moneda total = venta.obtenerTotal();
        // 100 * 2 + 50 * 3 = 200 + 150 = 350
        assertEquals(new BigDecimal("350.00"), total.obtener());
    }

    @Test
    void ventaSinDetallesRetornaTotalCero() {
        Venta venta = new Venta();
        Moneda total = venta.obtenerTotal();
        assertEquals(BigDecimal.ZERO, total.obtener());
    }

    @Test
    void rechazarAgregarDetalleNulo() {
        Venta venta = new Venta();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> venta.agregarDetalle(null));
        assertEquals("No se puede agregar un detalle nulo a la venta.", exception.getMessage());
    }

    @Test
    void obtenerDetallesRetornaListaNoModificable() {
        Venta venta = new Venta();
        Producto producto = new Producto("P-001", "Teclado", new BigDecimal("100.00"), 10);
        DetalleVenta detalle = new DetalleVenta(producto, 2, new BigDecimal("100.00"));

        venta.agregarDetalle(detalle);

        var detalles = venta.obtenerDetalles();
        assertThrows(UnsupportedOperationException.class, () -> detalles.add(null));
    }

    @Test
    void calcularTotalVentaConMultiplesProductosDiferentes() {
        Venta venta = new Venta();

        Producto p1 = new Producto("P-001", "Producto A", new BigDecimal("50.00"), 5);
        Producto p2 = new Producto("P-002", "Producto B", new BigDecimal("75.00"), 3);
        Producto p3 = new Producto("P-003", "Producto C", new BigDecimal("25.00"), 10);

        venta.agregarDetalle(new DetalleVenta(p1, 1, new BigDecimal("50.00")));
        venta.agregarDetalle(new DetalleVenta(p2, 2, new BigDecimal("75.00")));
        venta.agregarDetalle(new DetalleVenta(p3, 4, new BigDecimal("25.00")));

        Moneda total = venta.obtenerTotal();
        // 50 * 1 + 75 * 2 + 25 * 4 = 50 + 150 + 100 = 300
        assertEquals(new BigDecimal("300.00"), total.obtener());
    }

    @Test
    void igualdadDeVentas() {
        Venta venta1 = new Venta();
        Venta venta2 = new Venta();

        Producto producto = new Producto("P-001", "Teclado", new BigDecimal("100.00"), 10);
        DetalleVenta detalle = new DetalleVenta(producto, 2, new BigDecimal("100.00"));

        venta1.agregarDetalle(detalle);
        venta2.agregarDetalle(detalle);

        assertEquals(venta1, venta2);
    }
}
