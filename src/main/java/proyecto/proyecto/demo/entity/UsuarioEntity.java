package proyecto.proyecto.demo.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.List;

import jakarta.validation.constraints.*;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuario")
public class UsuarioEntity {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(message = "El nombre debe tener entre 2 y 35 caracteres", min = 2, max = 35)
    private String nombre;

    @NotNull
    @Size(message = "El nombre debe tener entre 2 y 60 caracteres", min = 2, max = 60)
    private String apellido;

    @NotNull
    @Email
    private String correo;
    
    @NotNull
    @Size(message = "La contraseña tiene un limite de 255 caracteres", min = 2, max = 255)
    private String contrasenia;

    @NotNull
    private LocalDateTime fechaRegistro;

    @NotNull
    private Boolean esAceptado;

    @NotNull
    private Boolean estado;

    @NotNull
    @Size(message = "La identificación debe tener entre 2 y 10 caracteres", min = 2, max = 10)
    private String identificacion;

    @NotNull
    @Size(message = "El telefono debe tener entre 2 y 10 caracteres", min = 2, max = 10)
    private String telefono;

    @ManyToMany
    @JoinTable(name = "usuario_rol", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "rol_id"))
    private Set<RolEntity> roles = new HashSet<>();

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<DireccionEntity> direcciones = new ArrayList<>();

    public UsuarioEntity() {}

    public UsuarioEntity(int id, String nombre, String apellido, String correo, String contrasenia,
            LocalDateTime fechaRegistro, Boolean esAceptado, Boolean estado, String identificacion, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.contrasenia = contrasenia;
        this.fechaRegistro = fechaRegistro;
        this.esAceptado = esAceptado;
        this.estado = estado;
        this.identificacion = identificacion;
        this.telefono = telefono;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public Set<RolEntity> getRoles() {
        return roles;
    }

    public void setRoles(Set<RolEntity> roles) {
        this.roles = roles;
    }

    public List<DireccionEntity> getDirecciones() {
        return direcciones;
    }

    public void setDirecciones(List<DireccionEntity> direcciones) {
        this.direcciones = direcciones;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Boolean isEsAceptado() {
        return esAceptado;
    }

    public void setEsAceptado(Boolean esAceptado) {
        this.esAceptado = esAceptado;
    }

    public Boolean isEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
