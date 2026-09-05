package com.tcsw.ventas;

import java.math.BigDecimal;
import java.util.Objects;

public class Producto {
    private String codigo;
    private String nombre;
    private BigDecimal precio;
    private int existencia;

    public Producto(String codigo, String nombre, BigDecimal precio, int existencia) {
        validarCodigo(codigo);
        validarNombre(nombre);
        validarPrecio(precio);
        validarExistencia(existencia);

        this.codigo = codigo.trim();
        this.nombre = nombre.trim();
        this.precio = precio;
        this.existencia = existencia;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        validarCodigo(codigo);
        this.codigo = codigo.trim();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        validarNombre(nombre);
        this.nombre = nombre.trim();
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        validarPrecio(precio);
        this.precio = precio;
    }

    public int getExistencia() {
        return existencia;
    }

    public void setExistencia(int existencia) {
        validarExistencia(existencia);
        this.existencia = existencia;
    }

    private void validarCodigo(String codigo) {
        if (codigo == null || codigo.trim().isEmpty()) {
            throw new IllegalArgumentException("El código del producto no puede ser nulo o vacío.");
        }
    }

    private void validarNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del producto no puede ser nulo o vacío.");
        }
    }

    private void validarPrecio(BigDecimal precio) {
        if (precio == null) {
            throw new IllegalArgumentException("El precio del producto no puede ser nulo.");
        }
        if (precio.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("El precio del producto no puede ser negativo.");
        }
    }

    private void validarExistencia(int existencia) {
        if (existencia < 0) {
            throw new IllegalArgumentException("La existencia del producto no puede ser negativa.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Producto producto = (Producto) o;
        return existencia == producto.existencia && Objects.equals(codigo, producto.codigo)
                && Objects.equals(nombre, producto.nombre) && Objects.equals(precio, producto.precio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo, nombre, precio, existencia);
    }

    @Override
    public String toString() {
        return "Producto[B]{" +
                "codigo='" + codigo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", existencia=" + existencia +
                '}';
    }
}
