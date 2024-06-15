package proyecto.proyecto.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import proyecto.proyecto.demo.entity.PropietarioEntity;
import proyecto.proyecto.demo.service.PropietarioService;

@RestController
@RequestMapping("/propietario")
@CrossOrigin(origins = "http://localhost:4200")
public class PropietarioController {

    @Autowired
    private PropietarioService propietarioService;

    @GetMapping("/getList")
    public ResponseEntity<List<PropietarioEntity>> getList() {
        List<PropietarioEntity> propietarios = propietarioService.getList();
        return new ResponseEntity<List<PropietarioEntity>>(propietarios, HttpStatus.OK);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<PropietarioEntity> getByUsuarioId(@PathVariable("usuarioId") int usuarioId) {
        Optional<PropietarioEntity> propietarios = propietarioService.getByUsuarioId(usuarioId);
        return new ResponseEntity<PropietarioEntity>(propietarios.get(), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<PropietarioEntity> updatePropietario(@RequestBody PropietarioEntity updatedPropietario,
            @PathVariable("id") int id) {
        Optional<PropietarioEntity> propietario = propietarioService.updatePropietario(id, updatedPropietario);
        return propietario.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
