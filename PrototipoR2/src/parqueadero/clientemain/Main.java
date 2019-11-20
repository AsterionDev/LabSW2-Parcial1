/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parqueadero.clientemain;

import parqueadero.negocio.GestorConductores;
import parqueadero.negocio.GestorVehiculos;
import parqueadero.presentacion.GUIPrincipal;
import parqueadero.presentacion.GUIPrincipalController;

/**
 *
 * @author Asus
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        GestorConductores gestorC=new GestorConductores();
        GestorVehiculos gestorV = new GestorVehiculos();
        
        //PRIMERA VISTA
        GUIPrincipal view1 = new GUIPrincipal();
        gestorC.addView(view1);
        gestorV.addView(view1);
        GUIPrincipalController control = new GUIPrincipalController(gestorC, view1);
        view1.setVisible(true);

      

        // Enlaza el action controller de los botones al controlador y fija el comando de acción
        view1.getBtnConsulta().addActionListener(control);
        view1.getBtnConsulta().setActionCommand("Consultar");
        view1.setVisible(true);
    }
    
}
