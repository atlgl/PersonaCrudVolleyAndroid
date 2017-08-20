package clienteservidor.angelus.ejemplopersona.modelo;

import java.util.Date;

/**
 * Created by Angelus on 19/08/2017.
 */

public class Persona {
    private int id;
    private String nombre;
    private String apellidos;
    private String estadocivil;
    private Date fechanac;

    public Persona() {
    }

    public Persona(int id, String nombre, String apellidos, String estadocivil, Date fechanac) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.estadocivil = estadocivil;
        this.fechanac = fechanac;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", estadocivil='" + estadocivil + '\'' +
                ", fechanac=" + fechanac +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEstadocivil() {
        return estadocivil;
    }

    public void setEstadocivil(String estadocivil) {
        this.estadocivil = estadocivil;
    }

    public Date getFechanac() {
        return fechanac;
    }

    public void setFechanac(Date fechanac) {
        this.fechanac = fechanac;
    }
}
