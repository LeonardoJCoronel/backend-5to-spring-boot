package proyecto.proyecto.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import proyecto.proyecto.demo.entity.PropietarioEntity;
import proyecto.proyecto.demo.service.PropietarioService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/propietario")
@CrossOrigin(origins = "http://localhost:4200")
@Api(value = "Propietario Management System", description = "Operations pertaining to Propietario in Propietario Management System")
public class PropietarioController {

    @Autowired
    private PropietarioService propietarioService;

    @ApiOperation(value = "View a list of available propietarios", response = ResponseEntity.class)
    @GetMapping("/getList")
    public ResponseEntity<List<PropietarioEntity>> getList() {
        List<PropietarioEntity> propietarios = propietarioService.getList();
        return new ResponseEntity<>(propietarios, HttpStatus.OK);
    }

    @ApiOperation(value = "Get a propietario by Usuario Id")
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<PropietarioEntity> getByUsuarioId(@PathVariable("usuarioId") int usuarioId) {
        Optional<PropietarioEntity> propietarios = propietarioService.getByUsuarioId(usuarioId);
        return propietarios.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @ApiOperation(value = "Update a propietario")
    @PutMapping("/update/{id}")
    public ResponseEntity<PropietarioEntity> updatePropietario(@RequestBody PropietarioEntity updatedPropietario,
            @PathVariable("id") int id) {
        Optional<PropietarioEntity> propietario = propietarioService.updatePropietario(id, updatedPropietario);
        return propietario.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
