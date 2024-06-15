package proyecto.proyecto.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "mascota")
public class MascotaEntity {

    @Id
    @GeneratedValue
    private int id;
    private String nombre;
    private float edad;
    private String alergias;
    private String discapacidades;
    private String tipoSangre;
    private String descripcion;
    private boolean estado;

    @ManyToOne
    @JoinColumn(name = "tipo_mascota_id")
    private CatalogoEntity tipoMascota;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    @JsonBackReference
    private PropietarioEntity propietario;

    public MascotaEntity() {
    }

    public MascotaEntity(int id, String nombre, float edad, String alergias,
            String discapacidades, String tipoSangre, String descripcion, boolean estado) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.alergias = alergias;
        this.discapacidades = discapacidades;
        this.tipoSangre = tipoSangre;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getEdad() {
        return edad;
    }

    public void setEdad(float edad) {
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
        return tipoSangre;
    }

    public void setTipoSangre(String tipoSangre) {
        this.tipoSangre = tipoSangre;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CatalogoEntity getTipoMascota() {
        return tipoMascota;
    }

    public void setTipoMascota(CatalogoEntity tipoMascota) {
        this.tipoMascota = tipoMascota;
    }
}
