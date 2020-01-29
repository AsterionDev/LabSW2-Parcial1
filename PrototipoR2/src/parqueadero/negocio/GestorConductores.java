/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parqueadero.negocio;

import parqueadero.acceso.IParqueadero;
import parqueadero.acceso.ServicioParqueaderoSocket;
import com.google.gson.Gson;
import java.util.Properties;
import mvcf.AModel;

/**
 * Gestiona los conductores
 * @author Asus
 */
public class GestorConductores extends AModel {
     private final IParqueadero parqueadero;
     
     public GestorConductores(){
         parqueadero = new ServicioParqueaderoSocket();
     }
    
     /***
      * consulta la informacion del conductor, teniendo en cuenta el id
      * @param idConductor identificacion del conductor
      * @return  retorna un conductor
      */
    public Conductor consultarConductor(String idConductor) {
        Conductor cond=new Conductor();
        String json = parqueadero.consultarConductor(idConductor);
        if (!json.equals("NO_ENCONTRADO")) {
            parseToConductor(cond,json);
        }
        
        return cond;        
    }
    /***
    * Obtener la informacion de formato Json y asignarsela a un conductor
    * @param cond conductor al cual se le asignara la informacion
    * @param json cadena en formato Json de donde se obtendrá la informacion
    */
     private void parseToConductor(Conductor cond, String json) {
        Gson gson = new Gson();
        Properties properties = gson.fromJson(json, Properties.class);
        cond.setId(properties.getProperty("id"));
        cond.setNombres(properties.getProperty("nombres"));
        cond.setApellidos(properties.getProperty("apellidos"));
        cond.setFechaNacimiento(properties.getProperty("fechanacimiento"));
        cond.setSexo(properties.getProperty("sexo"));
        cond.setRol(properties.getProperty("rol"));
        

    }
    
}
