
package parqueadero.negocio;

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

    public Vehiculo() {

    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

}
