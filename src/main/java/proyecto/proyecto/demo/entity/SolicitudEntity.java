package proyecto.proyecto.demo.entity;

import java.sql.Timestamp;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "solicitud_cuidado")
public class SolicitudEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Timestamp fechaRegistro;
    private boolean esAceptado;
    private boolean estado;

    @ManyToOne
    @JoinColumn(name = "propietario_id")
    private PropietarioEntity propietario;

    @ManyToOne
    @JoinColumn(name = "cuidador_id")
    private CuidadorEntity cuidador;

    @ManyToMany
    @JoinTable(name = "detalle_solicitud", joinColumns = @JoinColumn(name = "solicitud_id"), inverseJoinColumns = @JoinColumn(name = "mascota_id"))
    private List<MascotaEntity> mascotas;

    public SolicitudEntity(Timestamp fechaRegistro, boolean esAceptado, boolean estado) {
        this.fechaRegistro = fechaRegistro;
        this.esAceptado = esAceptado;
        this.estado = estado;
    }

    public Timestamp getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Timestamp fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public boolean isEsAceptado() {
        return esAceptado;
    }

    public void setEsAceptado(boolean esAceptado) {
        this.esAceptado = esAceptado;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

}
