package com.example.demo.services;

import java.util.List;

import com.example.demo.model.Articulo;

public interface CarritoCompraServicesI {
	public void limpiarCesta();
	public void addArticulo(Articulo a);
	public Integer getNumArticulo();
	public List<Articulo> getArticulos();
	public Double totalPrice();
	public Double calculadorDescuento(Double precio, Double porcentaje);
	public Double aplicarDescuento(Integer id, Double porcentaje);
	public Integer insertarArticulo(Articulo a);
	public Articulo getArticulo(Integer id);
}
