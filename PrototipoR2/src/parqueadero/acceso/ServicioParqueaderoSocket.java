/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parqueadero.acceso;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *Conexion con la base de datos
 * @author Asus
 */
public class ServicioParqueaderoSocket implements IParqueadero{
    
    private Socket socket = null;
    private Scanner entradaDecorada;
    private PrintStream salidaDecorada;
    private final String IP_SERVIDOR = "localhost";
    private final int PUERTO = 5000;

    /**
     * Obtiene el registro de un cliente en formato Json
     *
     * @param id identificador del cliente
     * @return json con el registro del cliente
     */
    @Override
    public String consultarVehiculosPorConductor(String id) {
        String respuesta = null;
        try {
            conectar(IP_SERVIDOR, PUERTO);
            respuesta = leerFlujoEntradaSalida("consultarVehiculosPorConductor," + id);
            cerrarFlujos();
            desconectar();

        } catch (IOException ex) {
            Logger.getLogger(ServicioParqueaderoSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;

    }
    /***
     * Obtiene la informacion de un conductor en formato Json
     * @param id identificacion del conductor
     * @return informacion del conduuctor
     */
    @Override
    public String consultarConductor(String id) {
        String respuesta = null;
        try {
            conectar(IP_SERVIDOR, PUERTO);
            respuesta = leerFlujoEntradaSalida("consultarConductor," + id);
            cerrarFlujos();
            desconectar();

        } catch (IOException ex) {
            Logger.getLogger(ServicioParqueaderoSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;

    }
    /***
     * Leer el flujo de entrada salida
     * @param solicitud solicitud que se va a procesar
     * @return respuesta a la solicitud
     * @throws IOException 
     */
    private String leerFlujoEntradaSalida(String solicitud) throws IOException {
        String respuesta = "";
        entradaDecorada = new Scanner(socket.getInputStream());
        salidaDecorada = new PrintStream(socket.getOutputStream());
        salidaDecorada.flush();
        // Usando el protocolo de comunicación
        salidaDecorada.println(solicitud);
        if (entradaDecorada.hasNextLine()) {
            respuesta = entradaDecorada.nextLine();
        }
        return respuesta;
    }
    /***
     * Cerrar el flujo
     */
    private void cerrarFlujos() {
        salidaDecorada.close();
        entradaDecorada.close();
    }
    /***
     * cerrar el socket
     */
    private void desconectar() {
        try {
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(ServicioParqueaderoSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /***
     * abrir el socket y conectar
     * @param address direccion (en este caso: localhost)
     * @param port puerto de conexion
     * @throws IOException 
     */
    public void conectar(String address, int port) throws IOException {
        socket = new Socket(address, port);
        System.out.println("Conectado");
    }

}
