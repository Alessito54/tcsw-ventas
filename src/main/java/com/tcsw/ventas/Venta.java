package com.tcsw.ventas;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Representa una venta con múltiples detalles/partidas.
 * Mantiene encapsulada la lista de detalles y proporciona operaciones de negocio.
 */
public class Venta {
    private final List<DetalleVenta> detalles;

    public Venta() {
        this.detalles = new ArrayList<>();
    }

    /**
     * Agrega una partida a la venta.
     * No permite partidas nulas.
     */
    public void agregarDetalle(DetalleVenta detalle) {
        if (detalle == null) {
            throw new IllegalArgumentException("No se puede agregar un detalle nulo a la venta.");
        }
        detalles.add(detalle);
    }

    /**
     * Retorna una copia no modificable de los detalles.
     * Esto protege la colección interna de modificaciones externas.
     */
    public List<DetalleVenta> obtenerDetalles() {
        return Collections.unmodifiableList(detalles);
    }

    /**
     * Calcula el total de la venta.
     * Total = suma de los subtotales de todas las partidas.
     */
    public Moneda obtenerTotal() {
        Moneda total = new Moneda(BigDecimal.ZERO);
        for (DetalleVenta detalle : detalles) {
            total = total.sumar(detalle.obtenerSubtotal());
        }
        return total;
    }

    /**
     * Retorna la cantidad de partidas en la venta.
     */
    public int cantidadDetalles() {
        return detalles.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Venta venta = (Venta) o;
        return Objects.equals(detalles, venta.detalles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(detalles);
    }

    @Override
    public String toString() {
        return "Venta{" +
                "detalles=" + detalles +
                ", total=" + obtenerTotal() +
                '}';
    }
}
