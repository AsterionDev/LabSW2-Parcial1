
package servidorparqueadero.negocio;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Asus
 */
public class GestorVehiculos {
    ConectorBD conector=new ConectorBD();
    
    /**
     * Consulta los vehiculos asociados a un conductor
     * @param idConductor Identificacion del conductor
     * @return Arreglo de vehiculos
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public ArrayList<Vehiculo> vehiculosPorConductor(String idConductor) throws ClassNotFoundException, SQLException{
        ArrayList<Vehiculo> lista=new ArrayList();
        conector.conectarse();
        
        conector.crearConsulta("SELECT placav,marcav,tipov FROM (conductor_vehiculo c inner JOIN vehiculos v ON c.placaVehiculo=v.placaV) WHERE idconductor ="+idConductor);
        while(conector.getResultado().next()) {
            lista.add(new Vehiculo(conector.getResultado().getString("placav"),conector.getResultado().getString("marcav"),conector.getResultado().getString("tipov")));
        }
            
        conector.desconectarse();
        return lista;        
    }
    /**
     * Consulta un vehiculo
     * @param placa Placa a consultar
     * @return Objeto vehiculo
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public Vehiculo consultarVehiculo(String placa) throws ClassNotFoundException, SQLException  {
        Vehiculo veh=null;
        conector.conectarse();
       
        conector.crearConsulta("SELECT * FROM vehiculos WHERE placav ='"+placa+"'");
        if(conector.getResultado().next()) {
           veh=new Vehiculo();
           veh.setPlaca(conector.getResultado().getString("placaV"));
           veh.setMarca(conector.getResultado().getString("marcaV"));
           veh.setTipo(conector.getResultado().getString("tipoV"));
                   
        }
            
        conector.desconectarse();
        return veh;        
    }
    
    /**
     * Agrega un vehiculo a la base de datos
     * @param placa Placa
     * @param marca Marca
     * @param tipo Tipo. Puede ser automovil o moto
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public void agregarVehiculo(String placa, String marca, String tipo) throws ClassNotFoundException, SQLException {
        conector.conectarse();
        conector.actualizar("INSERT INTO vehiculos (placaV, marcaV, tipoV)"
                + " VALUES ("
                + "'" + placa + "',"
                + "'" + marca + "',"
                + "'" + tipo + "'"
                + ")");
        conector.desconectarse();
    }
    /**
     * Modifica un vehiculo
     * @param placa placa
     * @param marca marca
     * @param tipo tipo
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public void editarVehiculo(String placa, String marca, String tipo) throws ClassNotFoundException, SQLException {
        conector.conectarse();
        conector.actualizar("UPDATE vehiculos SET "
                + "marcav ='" + marca + "',"
                + "tipov ='" + tipo + "'"
                + " WHERE placav ='" + placa
                + "'");
        conector.desconectarse();

    }
    /**
     * Elimina el vehiculo identificado por placa
     * @param placa Placa
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public void eliminarVehiculo(String placa) throws ClassNotFoundException, SQLException {
        conector.conectarse();
        conector.actualizar("DELETE FROM vehiculos  "
                + " WHERE placav ='" + placa
                + "'");
        conector.desconectarse();
    }
    
}
