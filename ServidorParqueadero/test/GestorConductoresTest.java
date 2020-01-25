/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.Test;
import static org.junit.Assert.*;
import servidorparqueadero.negocio.Conductor;
import servidorparqueadero.negocio.GestorConductores;

/**
 *
 * @author Asus
 */
public class GestorConductoresTest {
    
    /**
     * Test of testCRUDConductor method, of class GestorConductores.
     * @throws java.lang.Exception
     */
    @Test
    public void testCRUDConductor() throws Exception {
        
        GestorConductores gestor = new GestorConductores();
        System.out.println("CRUD conductor");
        
        // Lo elimina
        gestor.eliminarConductor("1234");
        
        // Lo agrega
        String id = "1234";
        String nombres = "Pepe Pruebin";
        String apellidos = "Pruebas";
        String sexo = "M";
        String fnacimiento = "2019-10-26";
        String rol = "Estudiante";
        
        gestor.agregarConductor(id, nombres, apellidos, sexo, fnacimiento, rol);
        
        // Lo consulta
        Conductor result = gestor.consultarConductor("1234");
        assertEquals("1234", result.getId());
        assertEquals("Pepe Pruebin", result.getNombres());
        assertEquals("Pruebas", result.getApellidos());
        assertEquals("M", result.getSexo());
        assertEquals("2019-10-26", result.getFechaNacimiento());
        assertEquals("Estudiante", result.getRol());
        
        
        // Lo edita
        nombres = "Don";
        apellidos = "Pruebas";
        rol = "Administrativo";
        gestor.editarConductor(id, nombres, apellidos, sexo, fnacimiento, rol);
        
        // Lo vuelve a consuttar
        
        result = gestor.consultarConductor("1234");
        assertEquals("1234", result.getId());
        assertEquals("Don", result.getNombres());
        assertEquals("Pruebas", result.getApellidos());
        assertEquals("Administrativo", result.getRol());
                
               
        
        //Lo deja eliminando
        gestor.eliminarConductor("1234");
        result = gestor.consultarConductor("123");
        assertEquals(null, result);
                
    }
}
