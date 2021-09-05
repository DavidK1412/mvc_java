package model;

public class LibroModel {
    private int id_fk;
    private String titulo;
    private String autor;
    private int autor_id_fk;
    private int lib_anio;

    public LibroModel(int id_fk, String titulo, String autor, int autor_id_fk, int lib_anio) {
        this.id_fk = id_fk;
        this.titulo = titulo;
        this.autor = autor;
        this.autor_id_fk = autor_id_fk;
        this.lib_anio = lib_anio;
    }

    public LibroModel(){};

    public int getId_fk() {
        return id_fk;
    }

    public void setId_fk(int id_fk) {
        this.id_fk = id_fk;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getAutor_id_fk() {
        return autor_id_fk;
    }

    public void setAutor_id_fk(int autor_id_fk) {
        this.autor_id_fk = autor_id_fk;
    }

    public int getLib_anio() {
        return lib_anio;
    }

    public void setLib_anio(int lib_anio) {
        this.lib_anio = lib_anio;
    }
}
