package proyecto.proyecto.demo.controller;

import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import proyecto.proyecto.demo.dto.MensajeDto;
import proyecto.proyecto.demo.dto.RolEnum;
import proyecto.proyecto.demo.entity.RolEntity;
import proyecto.proyecto.demo.entity.UsuarioEntity;
import proyecto.proyecto.demo.exceptions.ResourceNotFoundException;
import proyecto.proyecto.demo.service.RolService;
import proyecto.proyecto.demo.service.UsuarioService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = "http://localhost:4200")
@Api(value = "Usuario Management System", description = "Operations pertaining to Usuario in Usuario Management System")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private RolService rolService;

    @ApiOperation(value = "View a list of available usuarios", response = ResponseEntity.class)
    @GetMapping("/getList")
    public ResponseEntity<List<UsuarioEntity>> getUsuarioList() {
        List<UsuarioEntity> list = usuarioService.getList();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @ApiOperation(value = "Get a usuario by Id")
    @GetMapping("/getById/{id}")
    public ResponseEntity<UsuarioEntity> getById(@PathVariable("id") int id) {
        if (!usuarioService.existById(id)) {
            throw new ResourceNotFoundException("Usuario", "id", id);
        } else {
            UsuarioEntity usuario = usuarioService.getById(id).get();
            return new ResponseEntity<>(usuario, HttpStatus.OK);
        }
    }

    @ApiOperation(value = "Get a usuario by email")
    @GetMapping("/getByEmail/{correo}")
    public ResponseEntity<UsuarioEntity> getByEmail(@PathVariable("correo") String correo) {
        UsuarioEntity usuario = usuarioService.findByEmail(correo).get();
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @ApiOperation(value = "Save a usuario")
    @PostMapping("/save")
    public ResponseEntity<?> saveUsuario(@RequestBody UsuarioEntity nuevoUsuario, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity(new MensajeDto("Campos mal colocados o email invalido"), HttpStatus.BAD_REQUEST);
        }

        Set<RolEntity> roles = new HashSet<>();
        for (RolEntity rol : nuevoUsuario.getRoles()) {
            RolEnum rolEnum = RolEnum.valueOf(rol.getRolNombre().name());
            roles.add(rolService.getByRolNombre(rolEnum)
                    .orElseThrow(() -> new ResourceNotFoundException("Rol", "rolNombre", nuevoUsuario.getId())));
        }
        nuevoUsuario.setRoles(roles);

        usuarioService.saveUsuario(nuevoUsuario);
        return new ResponseEntity(new MensajeDto("Usuario guardado"), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update a usuario")
    @PutMapping("/update/{id}")
    public ResponseEntity<UsuarioEntity> updateUsuario(@RequestBody UsuarioEntity usuario, @PathVariable("id") int id) {
        if (!usuarioService.existById(id)) {
            throw new ResourceNotFoundException("Usuario", "id", id);
        } else {
            UsuarioEntity usuarioActualizado = usuarioService.updateUsuario(id, usuario);
            return new ResponseEntity<>(usuarioActualizado, HttpStatus.OK);
        }
    }

    @ApiOperation(value = "Delete a usuario")
    @DeleteMapping("/delete/{id}")
    public void deleteUsuario(@PathVariable("id") int id) {
        if (!usuarioService.existById(id)) {
            throw new ResourceNotFoundException("Usuario", "id", id);
        } else {
            usuarioService.deleteUsuario(id);
        }
    }
}
