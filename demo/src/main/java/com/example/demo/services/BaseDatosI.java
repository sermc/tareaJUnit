package com.example.demo.services;

import com.example.demo.model.Articulo;

public interface BaseDatosI {
	public void iniciar();
	public Integer insertarArticulo(Articulo a);
	public Articulo buscarArticulo(Integer id);
}
