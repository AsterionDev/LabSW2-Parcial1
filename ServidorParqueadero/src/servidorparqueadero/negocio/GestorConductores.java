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
     ConectorBD con=new ConectorBD();
    
    
    public Conductor consultarConductor(String idConductor) throws ClassNotFoundException, SQLException  {
        Conductor conductor=null;
        con.conectarse();
       
        con.crearConsulta("SELECT * FROM conductores WHERE idc ="+idConductor);
        if(con.getResultado().next()) {
           conductor=new Conductor();
           conductor.setId(con.getResultado().getString("idc"));
           conductor.setNombres(con.getResultado().getString("nombrec"));
           conductor.setApellidos(con.getResultado().getString("apellidoc"));
           conductor.setSexo(con.getResultado().getString("generoc"));
           conductor.setFechaNacimiento(con.getResultado().getString("fnacimientoc"));
           conductor.setRol(con.getResultado().getString("rolc"));
                   
        }
            
        con.desconectarse();
        return conductor;        
    }

}
