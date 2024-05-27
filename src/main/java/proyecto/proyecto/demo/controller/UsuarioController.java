package proyecto.proyecto.demo.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import proyecto.proyecto.demo.exceptions.ResourceNotFoundException;
import proyecto.proyecto.demo.dto.MensajeDto;
import proyecto.proyecto.demo.dto.RolEnum;
import proyecto.proyecto.demo.entity.RolEntity;
import proyecto.proyecto.demo.entity.UsuarioEntity;
import proyecto.proyecto.demo.service.RolService;
import proyecto.proyecto.demo.service.UsuarioService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = "http://localhost:4200")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private RolService rolService;

    @GetMapping("/getList")
    public ResponseEntity<List<UsuarioEntity>> getUsuarioList() {
        List<UsuarioEntity> list = usuarioService.getList();
        return new ResponseEntity<List<UsuarioEntity>>(list, HttpStatus.OK);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<UsuarioEntity> getById(@PathVariable("id") int id) {
        if (!usuarioService.existById(id)) {
            throw new ResourceNotFoundException("Usuario", "id", id);
        } else {
            UsuarioEntity usuario = usuarioService.getById(id).get();
            return new ResponseEntity<UsuarioEntity>(usuario, HttpStatus.OK);
        }
    }

    @SuppressWarnings({ "rawtypes", "unchecked", "unlikely-arg-type" })
    @PostMapping("/save")
    public ResponseEntity<?> saveUsuario(@RequestBody UsuarioEntity nuevoUsuario, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity(new MensajeDto("Campos mal colocados o email invalido"), HttpStatus.BAD_REQUEST);
        }
        UsuarioEntity usuario = new UsuarioEntity(nuevoUsuario.getId(),nuevoUsuario.getNombre(), nuevoUsuario.getApellido(),
                nuevoUsuario.getCorreo(), nuevoUsuario.getContrasenia(), nuevoUsuario.getFechaRegistro(),
                nuevoUsuario.isEsAceptado(), nuevoUsuario.isEstado());
        Set<RolEntity> roles = new HashSet<>();
        if (nuevoUsuario.getRoles().contains("ROL_CUIDADOR")) {
            roles.add(rolService.getByRolNombre(RolEnum.ROL_CUIDADOR).get());
        }else if (nuevoUsuario.getRoles().contains("ROL_PROPIETARIO")) {
            roles.add(rolService.getByRolNombre(RolEnum.ROL_PROPIETARIO).get());
        }
        if (nuevoUsuario.getRoles().contains("ROL_ADMIN")) {
            roles.add(rolService.getByRolNombre(RolEnum.ROL_ADMIN).get());
        }
        usuario.setRoles(roles);
        usuarioService.saveUsuario(usuario);
        return new ResponseEntity(new MensajeDto("Usuario guardado"), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UsuarioEntity> updateUsuario(@RequestBody UsuarioEntity usuario, @PathVariable("id") int id) {
        if (!usuarioService.existById(id)) {
            throw new ResourceNotFoundException("Usuario", "id", id);
        } else {
            UsuarioEntity usuarioActualizado = usuarioService.updateUsuario(id, usuario);
            return new ResponseEntity<UsuarioEntity>(usuarioActualizado, HttpStatus.OK);
        }
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUsuario(@PathVariable("id") int id) {
        if (!usuarioService.existById(id)) {
            throw new ResourceNotFoundException("Usuario", "id", id);
        } else {
            usuarioService.deleteUsuario(id);
        }
    }
}
