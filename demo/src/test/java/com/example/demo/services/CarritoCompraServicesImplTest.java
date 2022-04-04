package com.example.demo.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.example.demo.model.Articulo;
@RunWith(MockitoJUnitRunner.class)
public class CarritoCompraServicesImplTest {
	
	@InjectMocks
	private CarritoCompraServicesImpl carritoService = new CarritoCompraServicesImpl();
	
	@Mock
	private BaseDatosI baseDatos;
	
	@Test
	public void testLimpiarCesta() {
		carritoService.addArticulo(new Articulo("Camiseta",15.99));
		
		assertFalse(carritoService.getArticulos().isEmpty());
		
		carritoService.limpiarCesta();
		
		assertTrue(carritoService.getArticulos().isEmpty());
	}

	@Test
	public void testAddArticulo() {
		carritoService.limpiarCesta();
		
		assertTrue(carritoService.getArticulos().isEmpty());
		
		carritoService.addArticulo(new Articulo("Camiseta",15.99));
		
		assertFalse(carritoService.getArticulos().isEmpty());
	}

	@Test
	public void testGetNumArticulo() {
		carritoService.limpiarCesta();
		
		assertTrue(carritoService.getArticulos().isEmpty());
		
		carritoService.addArticulo(new Articulo("Camiseta",15.99));
		carritoService.addArticulo(new Articulo("Pantalones",25.99));
		carritoService.addArticulo(new Articulo("Calcetines",5.99));
		
		assertEquals((Integer)3,carritoService.getNumArticulo());
	}

	@Test
	public void testGetArticulos() {
		carritoService.limpiarCesta();
		
		assertTrue(carritoService.getArticulos().isEmpty());
		
		carritoService.addArticulo(new Articulo("Camiseta",15.99));
		carritoService.addArticulo(new Articulo("Pantalones",25.99));
		carritoService.addArticulo(new Articulo("Calcetines",5.99));
		
		assertEquals(3,carritoService.getArticulos().size());
	}

	@Test
	public void testTotalPrice() {
		carritoService.addArticulo(new Articulo("Camiseta",15.0));
		carritoService.addArticulo(new Articulo("Pantalones",25.0));
		carritoService.addArticulo(new Articulo("Calcetines",5.0));
		
		assertEquals((Double)45.0,carritoService.totalPrice());
	}

	@Test
	public void testCalculadorDescuento() {
		assertEquals((Double)90.0,carritoService.calculadorDescuento(100.0, 10.0));
	}
	
	@Test
	public void testAplicarDescuento() {
		Articulo articulo = new Articulo("Camiseta",20.00);
		when(baseDatos.buscarArticulo(any(Integer.class))).thenReturn(articulo);
		Double res = carritoService.aplicarDescuento(1, 10D);
		assertEquals(Double.valueOf(18D),res);
	}
	
	@Test
	public void testInsertar() {
		//Se insertar articulo
		Articulo a = new Articulo("Camiseta",22D);
		Integer id = carritoService.insertarArticulo(a);
		
		//Se comprueba que el id es el esperado
		assertEquals(Integer.valueOf(0),id);
		
		//Se comprueba que son los mismos objetos
		Articulo prueba = carritoService.getArticulo(id);
		assertSame(a,prueba);
		
		//Se comprueba que se llamada al menos una vez
		verify(baseDatos,times(1)).insertarArticulo(a);
	}
}
