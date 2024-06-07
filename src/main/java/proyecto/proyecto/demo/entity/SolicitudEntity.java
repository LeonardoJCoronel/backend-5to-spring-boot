package proyecto.proyecto.demo.entity;

import java.sql.Timestamp;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
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
    @GeneratedValue
    private int id;
    private Timestamp fechaRegistro;
    private boolean esAceptado;
    private boolean estado;
    private float paga;

    @ManyToOne
    @JoinColumn(name = "propietario_id")
    private PropietarioEntity propietario;

    @ManyToOne
    @JoinColumn(name = "cuidador_id")
    private CuidadorEntity cuidador;

    @ManyToOne
    @JoinColumn(name = "catagoria_id")
    private CatalogoEntity catalogo;

    @ManyToMany
    @JoinTable(name = "detalle_solicitud", joinColumns = @JoinColumn(name = "solicitud_id"), inverseJoinColumns = @JoinColumn(name = "mascota_id"))
    private List<MascotaEntity> mascotas;

    public SolicitudEntity(Timestamp fechaRegistro, boolean esAceptado, boolean estado, float paga) {
        this.fechaRegistro = fechaRegistro;
        this.esAceptado = esAceptado;
        this.estado = estado;
        this.paga = paga;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getPaga() {
        return paga;
    }

    public void setPaga(float paga) {
        this.paga = paga;
    }

    public PropietarioEntity getPropietario() {
        return propietario;
    }

    public void setPropietario(PropietarioEntity propietario) {
        this.propietario = propietario;
    }

    public CuidadorEntity getCuidador() {
        return cuidador;
    }

    public void setCuidador(CuidadorEntity cuidador) {
        this.cuidador = cuidador;
    }

    public List<MascotaEntity> getMascotas() {
        return mascotas;
    }

    public void setMascotas(List<MascotaEntity> mascotas) {
        this.mascotas = mascotas;
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

    public CatalogoEntity getCatalogo() {
        return catalogo;
    }

    public void setCatalogo(CatalogoEntity catalogo) {
        this.catalogo = catalogo;
    }

}
