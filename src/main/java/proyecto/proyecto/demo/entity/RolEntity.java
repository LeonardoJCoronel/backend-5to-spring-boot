package proyecto.proyecto.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import proyecto.proyecto.demo.dto.RolEnum;

@Entity
@Table(name = "rol")
public class RolEntity {

    @Id
    @GeneratedValue
    private int id;

    @Enumerated(EnumType.STRING)
    private RolEnum rolNombre;

    public RolEntity() {
        
    }

    public RolEntity(RolEnum rolNombre) {
        this.rolNombre = rolNombre;
    }

    public RolEntity(String rolNombre) {
        this.rolNombre = RolEnum.valueOf(rolNombre);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RolEnum getRolNombre() {
        return rolNombre;
    }

    public void setRolNombre(RolEnum rolNombre) {
        this.rolNombre = rolNombre;
    } 
}
