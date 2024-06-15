package proyecto.proyecto.demo.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
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
    @Column(name = "fecha_registro", columnDefinition = "timestamp(6) without time zone")
    private LocalDateTime fechaRegistro;
    private boolean esAceptado;
    private boolean estado;
    private float paga;
    private LocalDate fechaServicio;

    @ManyToOne
    @JoinColumn(name = "propietario_id")
    private PropietarioEntity propietario;

    @ManyToOne
    @JoinColumn(name = "cuidador_id")
    private CuidadorEntity cuidador;

    @ManyToOne
    @JoinColumn(name = "direccion_id")
    private DireccionEntity direccion;

    @ManyToOne
    @JoinColumn(name = "catagoria_id")
    private CatalogoEntity servicio;

    @ManyToMany
    @JoinTable(name = "detalle_solicitud", joinColumns = @JoinColumn(name = "solicitud_id"), inverseJoinColumns = @JoinColumn(name = "mascota_id"))
    private List<MascotaEntity> mascotas;

    public SolicitudEntity() {
    }

    public SolicitudEntity(int id, LocalDateTime fechaRegistro, boolean esAceptado, boolean estado, float paga,
            LocalDate fechaServicio) {
        this.id = id;
        this.fechaRegistro = fechaRegistro;
        this.esAceptado = esAceptado;
        this.estado = estado;
        this.paga = paga;
        this.fechaServicio = fechaServicio;
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

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
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

    public CatalogoEntity getServicio() {
        return servicio;
    }

    public void setServicio(CatalogoEntity catalogo) {
        this.servicio = catalogo;
    }

    public LocalDate getFechaServicio() {
        return fechaServicio;
    }

    public void setFechaServicio(LocalDate fechaServicio) {
        this.fechaServicio = fechaServicio;
    }

    public DireccionEntity getDireccion() {
        return direccion;
    }

    public void setDireccion(DireccionEntity direccion) {
        this.direccion = direccion;
    }

}
