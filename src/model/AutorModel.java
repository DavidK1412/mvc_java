package model;

public class AutorModel {
    private int aut_id;
    private String aut_nombre;
    private String aut_apellido;
    private String nacionalidad = null;

    public AutorModel(int aut_id, String aut_nombre, String aut_apellido) {
        this.aut_id = aut_id;
        this.aut_nombre = aut_nombre;
        this.aut_apellido = aut_apellido;
    }

    public int getAut_id() {
        return aut_id;
    }

    public void setAut_id(int aut_id) {
        this.aut_id = aut_id;
    }

    public String getAut_nombre() {
        return aut_nombre;
    }

    public void setAut_nombre(String aut_nombre) {
        this.aut_nombre = aut_nombre;
    }

    public String getAut_apellido() {
        return aut_apellido;
    }

    public void setAut_apellido(String aut_apellido) {
        this.aut_apellido = aut_apellido;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }
}
