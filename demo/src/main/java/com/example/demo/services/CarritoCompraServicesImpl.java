package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Articulo;

@Service
public class CarritoCompraServicesImpl implements CarritoCompraServicesI {
	
	private List<Articulo> cesta = new ArrayList<>();
	
	@Autowired
	private BaseDatosI baseDatos;
	
	@Override
	public void limpiarCesta() {
		cesta.clear();
	}

	@Override
	public void addArticulo(Articulo a) {
		cesta.add(a);		
	}

	@Override
	public Integer getNumArticulo() {
		return cesta.size();
	}

	@Override
	public List<Articulo> getArticulos() {
		return cesta;
	}

	@Override
	public Double totalPrice() {
		Double precioTotal = 0.0;
		
		for(Articulo articulo : cesta) {
			precioTotal += articulo.getPrecio();
		}
		
		return precioTotal;
	}

	@Override
	public Double calculadorDescuento(Double precio, Double porcentaje) {
		return precio-(precio*(porcentaje/100));
	}

	@Override
	public Double aplicarDescuento(Integer id, Double porcentaje) {
		Articulo a = baseDatos.buscarArticulo(id);
		if(Optional.ofNullable(a).isPresent()) {
			return calculadorDescuento(a.getPrecio(),porcentaje);
		} else {
			System.out.println("No se encuentra dicho objeto");
		}
		return 0D;
	}
	
	public Integer insertarArticulo(Articulo a) {
		Integer id = baseDatos.insertarArticulo(a);
		cesta.add(a);
		return id;
	}

	@Override
	public Articulo getArticulo(Integer id) {
		if(id <= cesta.size())
			return cesta.get(id);
		
		return null;
	}


}
