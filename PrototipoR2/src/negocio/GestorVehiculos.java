/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import acceso.IParqueadero;
import acceso.ServicioParqueaderoSocket;
import com.google.gson.Gson;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

/**
 *
 * @author Asus
 */
public class GestorVehiculos {
     private final IParqueadero parqueadero;
     
     public GestorVehiculos(){
         parqueadero = new ServicioParqueaderoSocket();
     }
    
    
    public ArrayList<Vehiculo> vehiculosPorConductor(String idConductor) {
        ArrayList<Vehiculo> lista=new ArrayList();
        String json[] = parqueadero.consultarVehiculosPorConductor(idConductor).split(";");
        if (!json[0].equals("NO_ENCONTRADO")) {
            for(String elemento:json){
                Vehiculo veh=new Vehiculo();
                parseToVehiculo(veh,elemento);
                lista.add(veh);
            }
        }
        
        return lista;        
    }
    
    private void parseToVehiculo(Vehiculo veh, String json) {
        Gson gson = new Gson();
        Properties properties = gson.fromJson(json, Properties.class);
        veh.setPlaca(properties.getProperty("placa"));
        veh.setMarca(properties.getProperty("marca"));
        veh.setTipo(properties.getProperty("tipo"));

    }
    
}
