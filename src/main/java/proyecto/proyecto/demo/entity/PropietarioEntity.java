package proyecto.proyecto.demo.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "propietario")
public class PropietarioEntity {

    @Id
    @GeneratedValue
    private int id;
    private Boolean estado;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private UsuarioEntity usuario;

    @OneToMany(mappedBy = "propietario", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<MascotaEntity> mascotas = new ArrayList<>();

    public PropietarioEntity() {
    }

    public PropietarioEntity(int id, Boolean estado, UsuarioEntity usuario) {
        this.id = id;
        this.estado = estado;
        this.usuario = usuario;
    }

    public PropietarioEntity(Boolean estado) {
        this.estado = estado;
    }

    public Boolean isEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<MascotaEntity> getMascotas() {
        return mascotas;
    }

    public void setMascotas(List<MascotaEntity> mascotas) {
        this.mascotas = mascotas;
    }

}
