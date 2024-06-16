package proyecto.proyecto.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;

@Entity
public class CatalogoEntity {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    private String nombreCatalogo;
    @NotNull
    private String valor;
    @NotNull
    private Boolean estado;

    public CatalogoEntity(){}

    public CatalogoEntity(int id, String nombreCatalogo, String valor, boolean estado) {
        this.id = id;
        this.nombreCatalogo = nombreCatalogo;
        this.valor = valor;
        this.estado = estado;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreCatalogo() {
        return nombreCatalogo;
    }

    public void setNombreCatalogo(String nombreCatalogo) {
        this.nombreCatalogo = nombreCatalogo;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

}
