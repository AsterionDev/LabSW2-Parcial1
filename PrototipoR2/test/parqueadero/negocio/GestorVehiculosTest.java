/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parqueadero.negocio;

import java.util.ArrayList;
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
public class GestorVehiculosTest {
    
     /**
     * Test of testCRUDVehiculo method, of class GestorVehiculos.
     * @throws java.lang.Exception
     */
    @Test
    public void testCRUDVehiculo() throws Exception {
        
        GestorVehiculos gestor = new GestorVehiculos();
        System.out.println("CRUD conductor");
        
       
        // Lo consulta
        ArrayList<Vehiculo> result = gestor.vehiculosPorConductor("2");
        assertEquals(1, result.size());
        assertEquals("KND-000", result.get(0).getPlaca());
        assertEquals("mar", result.get(0).getMarca());
        assertEquals("Moto", result.get(0).getTipo());
       
    }
}
