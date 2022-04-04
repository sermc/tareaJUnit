package com.example.demo.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.demo.model.Articulo;

@Service
public class BaseDatosImpl implements BaseDatosI {
	
	private Map<Integer,Articulo> baseDatos;
	
	@Override
	public void iniciar() {
		baseDatos = new HashMap<>();
		
		baseDatos.put(1,new Articulo("Pantalones",10D));
		baseDatos.put(2,new Articulo("Calcetines",20D));
		baseDatos.put(3,new Articulo("Gorra",14D));
		baseDatos.put(4,new Articulo("Camiseta",4D));
		baseDatos.put(5,new Articulo("Bañador",24D));
	}

	@Override
	public Integer insertarArticulo(Articulo a) {
		baseDatos.put(baseDatos.size()+1,a);
		return baseDatos.size();
	}

	@Override
	public Articulo buscarArticulo(Integer id) {
		return baseDatos.get(id);
	}

}
