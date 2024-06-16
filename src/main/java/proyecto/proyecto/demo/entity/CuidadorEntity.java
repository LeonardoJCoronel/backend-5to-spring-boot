package proyecto.proyecto.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "cuidador")
public class CuidadorEntity {

    @Id
    @GeneratedValue
    private int id;
    
    @NotNull
    private Boolean estado;

    @NotNull
    private int rating;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private UsuarioEntity usuario;

    public CuidadorEntity() {
    }

    public CuidadorEntity(int id, Boolean estado, int rating, UsuarioEntity usuario) {
        this.id = id;
        this.estado = estado;
        this.rating = rating;
        this.usuario = usuario;
    }

    public Boolean isEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
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

}
