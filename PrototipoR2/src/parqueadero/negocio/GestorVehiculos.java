/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parqueadero.negocio;

import parqueadero.acceso.IParqueadero;
import parqueadero.acceso.ServicioParqueaderoSocket;
import com.google.gson.Gson;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import mvcf.AModel;

/**
 * Gestionar Vehiculos
 * @author Asus
 */
public class GestorVehiculos extends AModel{
     private final IParqueadero parqueadero;
     
     public GestorVehiculos(){
         parqueadero = new ServicioParqueaderoSocket();
     }
    
    /***
      * consulta la informacion de los vehiculos del conductor, teniendo en cuenta el id
      * @param idConductor identificacion del conductor
      * @return  retorna una lista de vehiculos
      */
    public ArrayList<Vehiculo> vehiculosPorConductor(String idConductor) {
        ArrayList<Vehiculo> lista=new ArrayList();
        String aux= parqueadero.consultarVehiculosPorConductor(idConductor);
        System.out.println(aux);
        String json[] =aux.split(";");
        if (!json[0].equals("NO_ENCONTRADO")) {
            for(String elemento:json){
                Vehiculo veh=new Vehiculo();
                parseToVehiculo(veh,elemento);
                lista.add(veh);
            }
        }
        
        return lista;        
    }
    /***
    * Obtener la informacion de formato Json y asignarsela a un vehiculo
    * @param veh vehiculo al cual se le asignara la informacion
    * @param json cadena en formato Json de donde se obtendrá la informacion
    */
    private void parseToVehiculo(Vehiculo veh, String json) {
        Gson gson = new Gson();
        Properties properties = gson.fromJson(json, Properties.class);
        veh.setPlaca(properties.getProperty("placa"));
        veh.setMarca(properties.getProperty("marca"));
        veh.setTipo(properties.getProperty("tipo"));

    }
    
}
