package proyecto.proyecto.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import proyecto.proyecto.demo.entity.CuidadorEntity;
import proyecto.proyecto.demo.service.CuidadorService;

@RestController
@RequestMapping("/cuidador")
@CrossOrigin(origins = "http://localhost:4200")
public class CuidadorController {

    @Autowired
    private CuidadorService cuidadorService;

    @GetMapping("/getList")
    public ResponseEntity<List<CuidadorEntity>> getAllCuidadores() {
        List<CuidadorEntity> cuidadores = cuidadorService.findAll();
        return new ResponseEntity<>(cuidadores, HttpStatus.OK);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<CuidadorEntity> getById(@PathVariable("id") int id) {
        Optional<CuidadorEntity> cuidador = cuidadorService.findById(id);
        if (cuidador.isPresent()) {
            return new ResponseEntity<>(cuidador.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<CuidadorEntity> getByUsuarioId(@PathVariable("usuarioId") int usuarioId) {
        Optional<CuidadorEntity> propietarios = cuidadorService.getByUsuarioId(usuarioId);
        return new ResponseEntity<CuidadorEntity>(propietarios.get(), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<CuidadorEntity> updateCuidador(@RequestBody CuidadorEntity cuidador) {
        CuidadorEntity updatedCuidador = cuidadorService.update(cuidador);
        return new ResponseEntity<>(updatedCuidador, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCuidador(@PathVariable("id") int id) {
        cuidadorService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
