
import org.junit.Test;
import static org.junit.Assert.*;
import servidorparqueadero.negocio.GestorVehiculos;
import servidorparqueadero.negocio.Vehiculo;

/**
 *
 * @author Asus
 */
public class GestorVehiculosTest {
   /**
     * Test of testCRUDConductor method, of class GestorConductores.
     * @throws java.lang.Exception
     */
    @Test
    public void testCRUDConductor() throws Exception {
        
        GestorVehiculos gestor = new GestorVehiculos();
        System.out.println("CRUD vehiculos");
        
        // Lo elimina
        gestor.eliminarVehiculo("KND-000");
        
        // Lo agrega
        String placa = "KND-000";
        String marca = "Renault";
        String tipo = "Automovil";
        
        gestor.agregarVehiculo(placa, marca, tipo);
        
        // Lo consulta
        Vehiculo result = gestor.consultarVehiculo("KND-000");
        assertEquals("KND-000", result.getPlaca());
        assertEquals("Renault", result.getMarca());
        assertEquals("Automovil", result.getTipo());
        
        
        // Lo edita
        marca = "Renault+";
        tipo = "Moto";
        gestor.editarVehiculo(placa, marca, tipo);
        
        // Lo vuelve a consuttar
        
        result = gestor.consultarVehiculo("KND-000");
        assertEquals("KND-000", result.getPlaca());
        assertEquals("Renault+", result.getMarca());
        assertEquals("Moto", result.getTipo());
               
        
        //Lo deja eliminando
        gestor.eliminarVehiculo("KND-000");
        result = gestor.consultarVehiculo("KND-000");
        assertEquals(null, result);
                
    }
}
