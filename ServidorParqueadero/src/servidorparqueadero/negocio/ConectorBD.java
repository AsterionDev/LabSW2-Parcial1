/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorparqueadero.negocio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class ConectorBD {

    private Connection cn;
    private ResultSet rs;
    private Statement st=null;
    private final String URL = "jdbc:mariadb://localhost:3306/bdparqueadero";
    private final String USER = "root";
    private final String PASSWORD = "1177";

    public ConectorBD() {

    }
    /**
     * Establece la conexion con la base de datos
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public void conectarse() throws ClassNotFoundException, SQLException {
        //
     Class.forName("org.mariadb.jdbc.Driver");
        cn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/bdparqueadero?user=root&password=1177");
    }

    /**
     * Ejecuta una consulta de tipo select
     * @param sql
     * @throws SQLException 
     */
   public  void crearConsulta(String sql) throws SQLException {
        st = cn.createStatement();
        rs = st.executeQuery(sql);
    }

    /**
     * Ejecuta una consulta de tipo insert, update o delete
     *
     * @param sql
     * @throws SQLException
     */
    public void actualizar(String sql) throws SQLException {
        st = cn.createStatement();
        st.executeUpdate(sql);
    }
    /**
     * Cierra las variables de rs, st y cn que estén abiertas
     * @throws SQLException 
     */
    public void desconectarse() throws SQLException {
        if ( rs != null){
            rs.close();
        }
        //st.close();
        cn.close();
    }
    /**
     * Devuelve todo el conjunto de resultados
     * @return 
     */
    public ResultSet getResultado() {
        return rs;
    }
}
