package proyecto.proyecto.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class CatalogoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombreCatalogo;
    private String valor;
    private boolean estado;

    public CatalogoEntity(){}

    public CatalogoEntity(String nombreCatalogo, String valor, boolean estado) {
        this.nombreCatalogo = nombreCatalogo;
        this.valor = valor;
        this.estado = estado;
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

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

}
