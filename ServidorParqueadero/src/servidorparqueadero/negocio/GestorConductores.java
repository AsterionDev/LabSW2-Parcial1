/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorparqueadero.negocio;

import java.sql.SQLException;

/**
 *
 * @author Asus
 */
public class GestorConductores {
     ConectorBD conector=new ConectorBD();
    
    
    public Conductor consultarConductor(String idConductor) throws ClassNotFoundException, SQLException  {
        Conductor conductor=null;
        conector.conectarse();
       
        conector.crearConsulta("SELECT * FROM conductores WHERE idc ="+idConductor);
        if(conector.getResultado().next()) {
           conductor=new Conductor();
           conductor.setId(conector.getResultado().getString("idc"));
           conductor.setNombres(conector.getResultado().getString("nombrec"));
           conductor.setApellidos(conector.getResultado().getString("apellidoc"));
           conductor.setSexo(conector.getResultado().getString("generoc"));
           conductor.setFechaNacimiento(conector.getResultado().getString("fnacimientoc"));
           conductor.setRol(conector.getResultado().getString("rolc"));
                   
        }
            
        conector.desconectarse();
        return conductor;        
    }
    
    
    public void agregarConductor(String id, String nombres, String apellidos, String genero, String fnacimiento, String rol) throws ClassNotFoundException, SQLException {
        conector.conectarse();
        conector.actualizar("INSERT INTO conductores (idc, nombrec, apellidoc, generoc, fnacimientoc, rolc)"
                + " VALUES ("
                + "" + id + ","
                + "'" + nombres + "',"
                + "'" + apellidos + "',"
                + "'" + genero + "',"
                + "'" + fnacimiento + "',"
                + "'" + rol + "'"
                + ")");
        conector.desconectarse();
    }
    
    public void editarConductor(String id, String nombres, String apellidos, String genero, String fnacimiento, String rol) throws ClassNotFoundException, SQLException {
        conector.conectarse();
        conector.actualizar("UPDATE conductores SET "
                + "nombrec = '" + nombres + "',"
                + "apellidoc ='" + apellidos + "',"
                + "generoc ='" + genero + "',"
                + "fnacimientoc = '" + fnacimiento + "',"
                + "rolc ='" + rol + "'"
                + " WHERE idc ='" + id
                + "'");
        conector.desconectarse();

    }
    
    public void eliminarConductor(String id) throws ClassNotFoundException, SQLException {
        conector.conectarse();
        conector.actualizar("DELETE FROM conductores  "
                + " WHERE idc ='" + id
                + "'");
        conector.desconectarse();
    }

}
