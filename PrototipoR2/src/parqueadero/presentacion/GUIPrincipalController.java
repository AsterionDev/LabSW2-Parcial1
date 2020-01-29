   /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parqueadero.presentacion;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import mvcf.AActionController;
import mvcf.AModel;
import mvcf.AView;
import parqueadero.negocio.GestorConductores;
import parqueadero.negocio.GestorVehiculos;
import parqueadero.negocio.Vehiculo;

/**
 * Controlador del GUI Principal
 * @author Asus
 */
public class GUIPrincipalController extends AActionController{
    private final GestorVehiculos gestorVehiculos;
    private final GestorConductores gestorConductores;
    private final GUIPrincipal vista;    
    public GUIPrincipalController(AModel myModel, AView myView) {
        super(myModel, myView);
        gestorVehiculos=new GestorVehiculos();
        gestorConductores=new GestorConductores();
        this.vista = (GUIPrincipal) myView;
    }
    
    /**
     * Actualizar la interfaz principal, segun el evento ocurrido
     * @param ae Evento
     */
    @Override
    public void actualizar(ActionEvent ae) {
        switch (ae.getActionCommand()) {
            case "Consultar":
                String id=vista.getId();
                ArrayList<Vehiculo> lista=this.gestorVehiculos.vehiculosPorConductor(id);
                ArrayList<String []> listaRe=new ArrayList<String []> ();
                for(Vehiculo veh:lista){
                    String auxVeh[];
                    auxVeh=new String[3];
                    auxVeh[0] = veh.getPlaca();
                    auxVeh[1] = veh.getMarca();
                    auxVeh[2] = veh.getTipo();
                    listaRe.add(auxVeh);
                }
                vista.llenarTabla(listaRe);
                vista.setRol(gestorConductores.consultarConductor(id).getRol());
                break;
        }
    }
    
}
