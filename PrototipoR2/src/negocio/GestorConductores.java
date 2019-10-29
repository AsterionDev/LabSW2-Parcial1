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
public class GestorConductores {
     private final IParqueadero parqueadero;
     
     public GestorConductores(){
         parqueadero = new ServicioParqueaderoSocket();
     }
    
    
    public Conductor consultarConductor(String idConductor) {
        Conductor cond=new Conductor();
        String json = parqueadero.consultarConductor(idConductor);
        if (!json.equals("NO_ENCONTRADO")) {
            parseToConductor(cond,json);
        }
        
        return cond;        
    }
   
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
