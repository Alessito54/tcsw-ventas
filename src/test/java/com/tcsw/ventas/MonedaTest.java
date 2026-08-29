package com.tcsw.ventas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

class MonedaTest {

    @Test
    void crearMonedaConCantidadValida() {
        Moneda moneda = new Moneda(new BigDecimal("100.50"));
        assertEquals(new BigDecimal("100.50"), moneda.obtener());
    }

    @Test
    void crearMonedaConCeroCantidad() {
        Moneda moneda = new Moneda(BigDecimal.ZERO);
        assertEquals(BigDecimal.ZERO, moneda.obtener());
    }

    @Test
    void rechazarMonedaNula() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Moneda(null));
        assertEquals("La cantidad de dinero no puede ser nula.", exception.getMessage());
    }

    @Test
    void rechazarMonedaNegativa() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Moneda(new BigDecimal("-1.00")));
        assertEquals("La cantidad de dinero no puede ser negativa.", exception.getMessage());
    }

    @Test
    void multiplicarMonedaPorFactorPositivo() {
        Moneda moneda = new Moneda(new BigDecimal("50.00"));
        Moneda resultado = moneda.multiplicar(3);
        assertEquals(new BigDecimal("150.00"), resultado.obtener());
    }

    @Test
    void multiplicarMonedaPorCero() {
        Moneda moneda = new Moneda(new BigDecimal("50.00"));
        Moneda resultado = moneda.multiplicar(0);
        assertEquals(0, resultado.obtener().compareTo(BigDecimal.ZERO));
    }

    @Test
    void rechazarMultiplicarPorFactorNegativo() {
        Moneda moneda = new Moneda(new BigDecimal("50.00"));
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> moneda.multiplicar(-1));
        assertEquals("El factor de multiplicación no puede ser negativo.", exception.getMessage());
    }

    @Test
    void sumarDosMonedas() {
        Moneda moneda1 = new Moneda(new BigDecimal("100.00"));
        Moneda moneda2 = new Moneda(new BigDecimal("50.00"));
        Moneda resultado = moneda1.sumar(moneda2);
        assertEquals(new BigDecimal("150.00"), resultado.obtener());
    }

    @Test
    void rechazarSumarMonedaNula() {
        Moneda moneda = new Moneda(new BigDecimal("100.00"));
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> moneda.sumar(null));
        assertEquals("No se puede sumar una moneda nula.", exception.getMessage());
    }

    @Test
    void monedaIgualdadPorValor() {
        Moneda moneda1 = new Moneda(new BigDecimal("100.00"));
        Moneda moneda2 = new Moneda(new BigDecimal("100.00"));
        assertEquals(moneda1, moneda2);
    }
}
