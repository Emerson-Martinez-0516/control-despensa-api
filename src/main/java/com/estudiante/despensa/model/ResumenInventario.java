package com.estudiante.despensa.model;

public class ResumenInventario {

    private int cantidadProductos;
    private int totalUnidades;
    private double valorTotal;

    public ResumenInventario(int cantidadProductos, int totalUnidades, double valorTotal) {
        this.cantidadProductos = cantidadProductos;
        this.totalUnidades = totalUnidades;
        this.valorTotal = valorTotal;
    }

    public int getCantidadProductos() {
        return cantidadProductos;
    }

    public int getTotalUnidades() {
        return totalUnidades;
    }

    public double getValorTotal() {
        return valorTotal;
    }
}
