/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorparqueadero.main;

import servidorparqueadero.servicio.ParqueaderoServer;

/**
 *
 * @author Asus
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ParqueaderoServer regSer = new ParqueaderoServer();
        regSer.iniciar();
    }
    
}
