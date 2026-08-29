package com.tcsw.ventas;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Objeto de valor que representa una cantidad de dinero.
 * Proporciona encapsulamiento y validación para operaciones monetarias.
 */
public class Moneda {
    private final BigDecimal cantidad;

    public Moneda(BigDecimal cantidad) {
        validarCantidad(cantidad);
        this.cantidad = cantidad;
    }

    private void validarCantidad(BigDecimal cantidad) {
        if (cantidad == null) {
            throw new IllegalArgumentException("La cantidad de dinero no puede ser nula.");
        }
        if (cantidad.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("La cantidad de dinero no puede ser negativa.");
        }
    }

    public BigDecimal obtener() {
        return cantidad;
    }

    /**
     * Multiplica esta moneda por un factor.
     * Útil para calcular subtotales (precio * cantidad).
     */
    public Moneda multiplicar(int factor) {
        if (factor < 0) {
            throw new IllegalArgumentException("El factor de multiplicación no puede ser negativo.");
        }
        return new Moneda(cantidad.multiply(BigDecimal.valueOf(factor)));
    }

    /**
     * Suma esta moneda con otra.
     */
    public Moneda sumar(Moneda otra) {
        if (otra == null) {
            throw new IllegalArgumentException("No se puede sumar una moneda nula.");
        }
        return new Moneda(this.cantidad.add(otra.cantidad));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Moneda moneda = (Moneda) o;
        return Objects.equals(cantidad, moneda.cantidad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cantidad);
    }

    @Override
    public String toString() {
        return "Moneda{" +
                "cantidad=" + cantidad +
                '}';
    }
}
