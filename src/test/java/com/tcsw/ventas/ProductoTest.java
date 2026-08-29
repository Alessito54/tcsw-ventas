package com.tcsw.ventas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

class ProductoTest {

    @Test
    void crearProductoConDatosValidos() {
        Producto producto = new Producto("P-001", "Teclado", new BigDecimal("150.00"), 25);

        assertNotNull(producto);
        assertEquals("P-001", producto.getCodigo());
        assertEquals("Teclado", producto.getNombre());
        assertEquals(new BigDecimal("150.00"), producto.getPrecio());
        assertEquals(25, producto.getExistencia());
    }

    @Test
    void rechazarCodigoNulo() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Producto(null, "Teclado", new BigDecimal("150.00"), 25));

        assertEquals("El código del producto no puede ser nulo o vacío.", exception.getMessage());
    }

    @Test
    void rechazarCodigoVacio() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Producto("   ", "Teclado", new BigDecimal("150.00"), 25));

        assertEquals("El código del producto no puede ser nulo o vacío.", exception.getMessage());
    }

    @Test
    void rechazarNombreNulo() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Producto("P-001", null, new BigDecimal("150.00"), 25));

        assertEquals("El nombre del producto no puede ser nulo o vacío.", exception.getMessage());
    }

    @Test
    void rechazarNombreVacio() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Producto("P-001", "   ", new BigDecimal("150.00"), 25));

        assertEquals("El nombre del producto no puede ser nulo o vacío.", exception.getMessage());
    }

    @Test
    void rechazarPrecioNegativo() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Producto("P-001", "Teclado", new BigDecimal("-1.00"), 25));

        assertEquals("El precio del producto no puede ser negativo.", exception.getMessage());
    }

    @Test
    void rechazarExistenciaNegativa() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Producto("P-001", "Teclado", new BigDecimal("150.00"), -1));

        assertEquals("La existencia del producto no puede ser negativa.", exception.getMessage());
    }
}
