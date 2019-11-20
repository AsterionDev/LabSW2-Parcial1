/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parqueadero.negocio;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

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
        
       
        // Lo consulta
        Conductor result = gestor.consultarConductor("1");
        assertEquals("1", result.getId());
        assertEquals("Juan", result.getNombres());
        assertEquals("David", result.getApellidos());
        assertEquals("M", result.getSexo());
        assertEquals("2019-10-26", result.getFechaNacimiento());
        assertEquals("Estudiante", result.getRol());
        
       
    }


}
