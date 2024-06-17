package proyecto.proyecto.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import proyecto.proyecto.demo.entity.CuidadorEntity;
import proyecto.proyecto.demo.service.CuidadorService;

@RestController
@RequestMapping("/cuidador")
@CrossOrigin(origins = "http://localhost:4200")
@Api(value = "Cuidador Management System", description = "Operations pertaining to Cuidador in Cuidador Management System")
public class CuidadorController {

    @Autowired
    private CuidadorService cuidadorService;

    @ApiOperation(value = "View a list of available cuidadores", response = ResponseEntity.class)
    @GetMapping("/getList")
    public ResponseEntity<List<CuidadorEntity>> getAllCuidadores() {
        List<CuidadorEntity> cuidadores = cuidadorService.findAll();
        return new ResponseEntity<>(cuidadores, HttpStatus.OK);
    }

    @ApiOperation(value = "Get a cuidador by Id")
    @GetMapping("/getById/{id}")
    public ResponseEntity<CuidadorEntity> getById(@PathVariable("id") int id) {
        java.util.Optional<CuidadorEntity> cuidador = cuidadorService.findById(id);
        if (cuidador.isPresent()) {
            return new ResponseEntity<>(cuidador.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(value = "Update a cuidador")
    @PutMapping("/update")
    public ResponseEntity<CuidadorEntity> updateCuidador(@RequestBody CuidadorEntity cuidador) {
        CuidadorEntity updatedCuidador = cuidadorService.update(cuidador);
        return new ResponseEntity<>(updatedCuidador, HttpStatus.OK);
    }

    @ApiOperation(value = "Delete a cuidador")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCuidador(@PathVariable("id") int id) {
        cuidadorService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
