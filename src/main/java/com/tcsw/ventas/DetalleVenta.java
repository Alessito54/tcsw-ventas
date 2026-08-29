package com.tcsw.ventas;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Representa una partida o línea de venta.
 * Encapsula un producto, cantidad y precio unitario.
 * El subtotal se calcula automáticamente.
 */
public class DetalleVenta {
    private final Producto producto;
    private final int cantidad;
    private final Moneda precioUnitario;

    public DetalleVenta(Producto producto, int cantidad, BigDecimal precioUnitario) {
        validarProducto(producto);
        validarCantidad(cantidad);
        validarPrecioUnitario(precioUnitario);

        this.producto = producto;
        this.cantidad = cantidad;
        this.precioUnitario = new Moneda(precioUnitario);
    }

    private void validarProducto(Producto producto) {
        if (producto == null) {
            throw new IllegalArgumentException("El producto de la partida no puede ser nulo.");
        }
    }

    private void validarCantidad(int cantidad) {
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad en la partida debe ser mayor que cero.");
        }
    }

    private void validarPrecioUnitario(BigDecimal precioUnitario) {
        if (precioUnitario == null) {
            throw new IllegalArgumentException("El precio unitario no puede ser nulo.");
        }
        if (precioUnitario.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("El precio unitario no puede ser negativo.");
        }
    }

    public Producto getProducto() {
        return producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public Moneda getPrecioUnitario() {
        return precioUnitario;
    }

    /**
     * Calcula el subtotal de esta partida.
     * Subtotal = precio unitario * cantidad
     */
    public Moneda obtenerSubtotal() {
        return precioUnitario.multiplicar(cantidad);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DetalleVenta that = (DetalleVenta) o;
        return cantidad == that.cantidad &&
                Objects.equals(producto, that.producto) &&
                Objects.equals(precioUnitario, that.precioUnitario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(producto, cantidad, precioUnitario);
    }

    @Override
    public String toString() {
        return "DetalleVenta{" +
                "producto=" + producto +
                ", cantidad=" + cantidad +
                ", precioUnitario=" + precioUnitario +
                ", subtotal=" + obtenerSubtotal() +
                '}';
    }
}
