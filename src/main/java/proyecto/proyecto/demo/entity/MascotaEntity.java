package proyecto.proyecto.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "mascota")
public class MascotaEntity {

    @Id
    @GeneratedValue
    private int id;
    private boolean esPerro;
    private boolean esGato;
    private String nombre;
    private int edad;
    private String alergias;
    private String discapacidades;
    private String TipoSangre;
    private String descripcion;
    private boolean estado;

    @ManyToOne
    private PropietarioEntity propietario;

    public MascotaEntity(boolean esPerro, boolean esGato, String nombre, int edad, String alergias,
            String discapacidades, String tipoSangre, String descripcion, boolean estado) {
        this.esPerro = esPerro;
        this.esGato = esGato;
        this.nombre = nombre;
        this.edad = edad;
        this.alergias = alergias;
        this.discapacidades = discapacidades;
        TipoSangre = tipoSangre;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    public boolean isEsPerro() {
        return esPerro;
    }

    public void setEsPerro(boolean esPerro) {
        this.esPerro = esPerro;
    }

    public boolean isEsGato() {
        return esGato;
    }

    public void setEsGato(boolean esGato) {
        this.esGato = esGato;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getAlergias() {
        return alergias;
    }

    public void setAlergias(String alergias) {
        this.alergias = alergias;
    }

    public String getDiscapacidades() {
        return discapacidades;
    }

    public void setDiscapacidades(String discapacidades) {
        this.discapacidades = discapacidades;
    }

    public String getTipoSangre() {
        return TipoSangre;
    }

    public void setTipoSangre(String tipoSangre) {
        TipoSangre = tipoSangre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public PropietarioEntity getPropietario() {
        return propietario;
    }

    public void setPropietario(PropietarioEntity propietario) {
        this.propietario = propietario;
    }
}
