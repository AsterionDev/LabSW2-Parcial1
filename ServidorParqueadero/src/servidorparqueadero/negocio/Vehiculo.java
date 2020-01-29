
package servidorparqueadero.negocio;

public class Vehiculo {

    private String placa;
    private String marca;
    private String tipo;

    /**
     * Constructor parametrizado
     *
     * @param placa Placa del vehiculo
     * @param marca Marca del vehiculo
     * @param tipo Tipo de vehiculo, moto o automovil
     */
    public Vehiculo(String placa, String marca, String tipo) {
        this.placa = placa;
        this.marca = marca;
        this.tipo = tipo;
    }
    /**
     * Constructor de vehiculo
     */
    public Vehiculo() {

    }
    /**
     * Devuelve la placa
     * @return placa
     */
    public String getPlaca() {
        return placa;
    }
    /**
     * Establece la placa
     * @param placa 
     */
    public void setPlaca(String placa) {
        this.placa = placa;
    }
    /**
     * Devuelve la marca
     * @return marca
     */
    public String getMarca() {
        return marca;
    }
    /**
     * Establece la marca
     * @param marca 
     */
    public void setMarca(String marca) {
        this.marca = marca;
    }
    /**
     * Devuelve el tipo
     * @return tipo
     */
    public String getTipo() {
        return tipo;
    }
    /**
     * Establece el tipo
     * @param tipo 
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

}
