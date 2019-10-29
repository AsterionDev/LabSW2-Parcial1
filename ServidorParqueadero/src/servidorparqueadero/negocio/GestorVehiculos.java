/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorparqueadero.negocio;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Asus
 */
public class GestorVehiculos {
    ConectorBD con=new ConectorBD();
    
    
    public ArrayList<Vehiculo> vehiculosPorConductor(String idConductor) throws ClassNotFoundException, SQLException{
        ArrayList<Vehiculo> lista=new ArrayList();
        con.conectarse();
        
        con.crearConsulta("SELECT placav,marcav,tipov FROM (conductor_vehiculo c inner JOIN vehiculos v ON c.placaVehiculo=v.placaV) WHERE idconductor ="+idConductor);
        while(con.getResultado().next()) {
            lista.add(new Vehiculo(con.getResultado().getString("placav"),con.getResultado().getString("marcav"),con.getResultado().getString("tipov")));
        }
            
        con.desconectarse();
        return lista;        
    }
    
}
