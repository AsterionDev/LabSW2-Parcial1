/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parqueadero.negocio;

public class Conductor {
    
    private String id;
    private String nombres;
    private String apellidos;
    private String fechaNacimiento;
    private String sexo;
    private String rol;

 
    /**
     * Constructor parametrizado
     *
     * @param id identificacion de la persona
     * @param nombres nombres
     * @param apellidos Apellidos
     * @param sexo Sexo de la persona
     * @param fechaNacimiento fecha de nacimiento
     * @param rol Rol de la persona
     */
    public Conductor(String id, String nombres, String apellidos, String sexo, String fechaNacimiento,String rol) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.rol = rol;
        this.fechaNacimiento = fechaNacimiento;
        this.sexo = sexo;
    }
    /**
     * Constructor del conductor
     * 
     */
    public Conductor() {

    }
    /**
     * Devuelve la identificacion
     * @return identificacion
     */
    public String getId() {
        return id;
    }
    /**
     * Establece la identificacion 
     * @param id 
     */
    public void setId(String id) {
        this.id = id;
    }
    
    /**
     * Devuelve el nombre
     * @return nombre
     */
    public String getNombres() {
        return nombres;
    }
    /**
     * Establece el nombre
     * @param nombres 
     */
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }
    /**
     * Devuelve el apellido
     * @return Apellido
     */
    public String getApellidos() {
        return apellidos;
    }
    /**
     * Establece el apellido
     * @param apellidos 
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    /**
     * Devuelve la fecha de nacimiento
     * @return Fecha de nacimiento
     */
    public String getFechaNacimiento() {
        return fechaNacimiento;
    }
    /**
     * Establece la fecha de nacimiento
     * @param fechaNacimiento 
     */
    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
    /**
     * Devuelve el rol
     * @return Rol
     */
    public String getRol() {
        return rol;
    }
    /**
     * Establece el rol
     * @param rol 
     */
    public void setRol(String rol) {
        this.rol = rol;
    }
    /**
     * Devuelve el sexo
     * @return Sexo
     */
    public String getSexo() {
        return sexo;
    }
    /**
     * Establece el sexo
     * @param sexo 
     */
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

}
