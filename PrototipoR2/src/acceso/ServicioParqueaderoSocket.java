/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acceso;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
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

    private void cerrarFlujos() {
        salidaDecorada.close();
        entradaDecorada.close();
    }

    private void desconectar() {
        try {
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(ServicioParqueaderoSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void conectar(String address, int port) throws IOException {
        socket = new Socket(address, port);
        System.out.println("Conectado");
    }

}
