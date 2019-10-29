package servidorparqueadero.servicio;

import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import servidorparqueadero.negocio.Conductor;
import servidorparqueadero.negocio.GestorConductores;
import servidorparqueadero.negocio.Vehiculo;
import servidorparqueadero.negocio.GestorVehiculos;

public class ParqueaderoServer implements Runnable {

    private final GestorVehiculos gestorV;
    private final GestorConductores gestorC;

    private static ServerSocket ssock;
    private static Socket socket;

    private Scanner entradaDecorada;
    private PrintStream salidaDecorada;
    private static final int PUERTO = 5000;

    /**
     * Constructor
     */
    public ParqueaderoServer() {
        gestorV = new GestorVehiculos();
        gestorC= new GestorConductores();
    }
    /**
     * Logica completa del servidor
     */
    public void iniciar() {
        abrirPuerto();

        while (true) {
            esperarAlCliente();
            lanzarHilo();
        }
    }

    /**
     * Lanza el hilo
     */
    private static void lanzarHilo() {
        new Thread(new ParqueaderoServer()).start();
    }

    private static void abrirPuerto() {
        try {
            ssock = new ServerSocket(PUERTO);
            System.out.println("Escuchando por el puerto " + PUERTO);
        } catch (IOException ex) {
            Logger.getLogger(ParqueaderoServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Espera que el cliente se conecta y le devuelve un socket
     */
    private static void esperarAlCliente() {
        try {
            socket = ssock.accept();
            System.out.println("Cliente conectado");
        } catch (IOException ex) {
            Logger.getLogger(ParqueaderoServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Cuerpo del hilo
     */
    @Override
    public void run() {
        try {
            crearFlujos();
            leerFlujos();
            cerrarFlujos();

        } catch (IOException e) {
            System.out.println(e);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ParqueaderoServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ParqueaderoServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Crea los flujos con el socket
     *
     * @throws IOException
     */
    private void crearFlujos() throws IOException {
        salidaDecorada = new PrintStream(socket.getOutputStream());
        entradaDecorada = new Scanner(socket.getInputStream());
    }

    /**
     * Lee los flujos del socket
     */
    private void leerFlujos() throws ClassNotFoundException, SQLException {
        if (entradaDecorada.hasNextLine()) {
            // Extrae el flujo que envía el cliente
            String peticion = entradaDecorada.nextLine();
            decodificarPeticion(peticion);

        } else {
            salidaDecorada.flush();
            salidaDecorada.println("NO_ENCONTRADO");
        }
    }

    /**
     * Decodifica la petición, extrayeno la acción y los parámetros
     *
     * @param peticion petición completa al estilo
     * "consultarVehiculosPorConductor,100"
     */
    private void decodificarPeticion(String peticion) throws ClassNotFoundException, SQLException {
        StringTokenizer tokens = new StringTokenizer(peticion, ",");
        String parametros[] = new String[10];

        int i = 0;
        while (tokens.hasMoreTokens()) {
            parametros[i++] = tokens.nextToken();
        }
        String accion = parametros[0];
        procesarAccion(accion, parametros);

    }

    /**
     * Segun el protocolo decide qué accion invocar
     *
     * @param accion acción a procesar
     * @param parametros parámetros de la acción
     */
    private void procesarAccion(String accion, String parametros[]) throws ClassNotFoundException, SQLException {
        switch (accion) {
            case "consultarVehiculosPorConductor":
                String id = parametros[1];
                ArrayList<Vehiculo> lista=gestorV.vehiculosPorConductor(id);
                if (lista.isEmpty()) {
                    salidaDecorada.println("NO_ENCONTRADO");
                } else {
                    salidaDecorada.println(parseToJSON(lista));
                }
                break;
            case "consultarConductor":
                String idConductor = parametros[1];
                Conductor cond=gestorC.consultarConductor(idConductor);
                if (cond==null) {
                    salidaDecorada.println("NO_ENCONTRADO");
                } else {
                    salidaDecorada.println(parseToJSON(cond));
                }
                break;
        }
    }

    /**
     * Cierra los flujos de entrada y salida
     *
     * @throws IOException
     */
    private void cerrarFlujos() throws IOException {
        salidaDecorada.close();
        entradaDecorada.close();
        socket.close();
    }

    /**
     * Convierte el objeto Vehiculo a json
     *
     * @param veh Objeto vehiculo
     * @return cadena json
     */
    private String parseToJSON(Vehiculo veh) {
        JsonObject jsonobj = new JsonObject();
        jsonobj.addProperty("placa", veh.getPlaca());
        jsonobj.addProperty("marca", veh.getMarca());
        jsonobj.addProperty("tipo", veh.getTipo());
        return jsonobj.toString();
    }
    
    private String parseToJSON(Conductor cond) {
        JsonObject jsonobj = new JsonObject();
        jsonobj.addProperty("id", cond.getId());
        jsonobj.addProperty("nombres", cond.getNombres());
        jsonobj.addProperty("apellidos", cond.getApellidos());
        jsonobj.addProperty("fechanacimiento", cond.getFechaNacimiento());
        jsonobj.addProperty("sexo", cond.getSexo());
        jsonobj.addProperty("rol", cond.getRol());
        return jsonobj.toString();
    }
    
    private String parseToJSON(ArrayList<Vehiculo> lista){
        String resultado="";
        for(Vehiculo v : lista){
            resultado+= parseToJSON(v)+";";
        }
        return resultado;
    }
}
