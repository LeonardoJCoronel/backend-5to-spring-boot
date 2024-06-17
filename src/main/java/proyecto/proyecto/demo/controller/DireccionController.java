package proyecto.proyecto.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import proyecto.proyecto.demo.entity.DireccionEntity;
import proyecto.proyecto.demo.exceptions.ResourceNotFoundException;
import proyecto.proyecto.demo.service.DireccionService;

@RestController
@RequestMapping("/direccion")
@CrossOrigin(origins = "http://localhost:4200")
@Api(value = "Direccion Management System", description = "Operations pertaining to Direccion in Direccion Management System")
public class DireccionController {

    @Autowired
    private DireccionService direccionService;

    @ApiOperation(value = "View a list of available direcciones", response = ResponseEntity.class)
    @GetMapping("/getList")
    public ResponseEntity<List<DireccionEntity>> getDireccionList(){
        List<DireccionEntity> list = direccionService.getList();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @ApiOperation(value = "Get a direccion by Id")
    @GetMapping("/getById/{id}")
    public ResponseEntity<DireccionEntity> getById(@PathVariable("id") int id) {
        if (!direccionService.existById(id)) {
            throw new ResourceNotFoundException("Direccion", "id", id);
        } else {
            DireccionEntity direccion = direccionService.getById(id).get();
            return new ResponseEntity<>(direccion, HttpStatus.OK);
        }
    }

    @ApiOperation(value = "Update a direccion")
    @PutMapping("/update/{id}")
    public ResponseEntity<DireccionEntity> updateDireccion(@RequestBody DireccionEntity direccion, @PathVariable("id") int id) {
        if (!direccionService.existById(id)) {
            throw new ResourceNotFoundException("Direccion", "id", id);
        } else {
            DireccionEntity direccionActualizada = direccionService.update(id, direccion);
            return new ResponseEntity<>(direccionActualizada, HttpStatus.OK);
        }
    }

    @ApiOperation(value = "Add a direccion")
    @PostMapping("/save")
    public void saveDireccion(@RequestBody DireccionEntity direccion){
        direccionService.save(direccion);
    }

    @ApiOperation(value = "Delete a direccion")
    @DeleteMapping("/delete/{id}")
    public void deleteDireccion(@PathVariable("id")int id){
        direccionService.deleteById(id);
    }
}
