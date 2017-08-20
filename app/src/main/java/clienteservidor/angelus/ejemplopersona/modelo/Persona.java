package clienteservidor.angelus.ejemplopersona.modelo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by Angelus on 19/08/2017.
 */

public class Persona implements Parcelable {
    private int id;
    private String nombre;
    private String apellidos;
    private String estadocivil;
    private String fechanac;

    public Persona() {
    }

    public Persona(int id, String nombre, String apellidos, String estadocivil, String fechanac) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.estadocivil = estadocivil;
        this.fechanac = fechanac;
    }

    protected Persona(Parcel in) {
        id = in.readInt();
        nombre = in.readString();
        apellidos = in.readString();
        estadocivil = in.readString();
        fechanac=in.readString();
    }

    public static final Creator<Persona> CREATOR = new Creator<Persona>() {
        @Override
        public Persona createFromParcel(Parcel in) {
            return new Persona(in);
        }

        @Override
        public Persona[] newArray(int size) {
            return new Persona[size];
        }
    };

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

    public String getFechanac() {
        return fechanac;
    }

    public void setFechanac(String fechanac) {
        this.fechanac = fechanac;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(nombre);
        dest.writeString(apellidos);
        dest.writeString(estadocivil);
        dest.writeString(fechanac);
    }
}
