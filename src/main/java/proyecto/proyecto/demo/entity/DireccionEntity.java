package proyecto.proyecto.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "direccion")
public class DireccionEntity {

    @Id
    @GeneratedValue
    private int id;
    private String callePrimaria;
    private String calleSecundaria;
    private String numeroCasa;
    private String nombreSector;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    @JsonBackReference
    private UsuarioEntity usuario;

    public DireccionEntity() {
    };

    public DireccionEntity(String callePrimaria, String calleSecundaria, String numeroCasa, String nombreSector) {
        this.callePrimaria = callePrimaria;
        this.calleSecundaria = calleSecundaria;
        this.numeroCasa = numeroCasa;
        this.nombreSector = nombreSector;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCallePrimaria() {
        return callePrimaria;
    }

    public void setCallePrimaria(String callePrimaria) {
        this.callePrimaria = callePrimaria;
    }

    public String getCalleSecundaria() {
        return calleSecundaria;
    }

    public void setCalleSecundaria(String calleSecundaria) {
        this.calleSecundaria = calleSecundaria;
    }

    public String getNumeroCasa() {
        return numeroCasa;
    }

    public void setNumeroCasa(String numeroCasa) {
        this.numeroCasa = numeroCasa;
    }

    public String getNombreSector() {
        return nombreSector;
    }

    public void setNombreSector(String nombreSector) {
        this.nombreSector = nombreSector;
    }

    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }
}
