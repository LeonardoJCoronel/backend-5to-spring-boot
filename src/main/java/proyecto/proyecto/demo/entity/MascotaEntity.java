package proyecto.proyecto.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "mascota")
public class MascotaEntity {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(message = "El nombre de la mascota debe tener entre 2 y 15 caracteres", min = 2, max = 15)
    private String nombre;
    
    @NotNull
    private float edad;
    
    @NotNull
    @Size(message = "La descripción de las alertas debe tener entre 2 y 255 caracteres", min = 2, max = 255)
    private String alergias;
    
    @NotNull
    @Size(message = "Las descripción de las discapacidades debe tener entre 2 y 255 caracteres", min = 2, max = 255)
    private String discapacidades;
    
    @NotNull
    private String tipoSangre;
    
    @NotNull
    @Size(message = "La descripción de la mascota debe tener entre 2 y 255 caracteres", min = 2, max = 255)
    private String descripcion;
    
    @NotNull
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
