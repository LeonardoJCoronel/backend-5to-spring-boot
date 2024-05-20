package proyecto.proyecto.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import proyecto.proyecto.demo.entity.DireccionEntity;
import proyecto.proyecto.demo.exceptions.ResourceNotFoundException;
import proyecto.proyecto.demo.service.DireccionService;

@RestController
@RequestMapping("/direccion")
@CrossOrigin(origins = "http://localhost:4200")
public class DireccionController {

    @Autowired
    private DireccionService direccionService;

    @GetMapping("/getList")
    public ResponseEntity<List<DireccionEntity>> getDireccionList(){
        List<DireccionEntity> list = direccionService.getList();
        return new ResponseEntity<List<DireccionEntity>>(list, HttpStatus.OK);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<DireccionEntity> getById(@PathVariable("id") int id) {
        if (!direccionService.existById(id)) {
            throw new ResourceNotFoundException("Direccion", "id", id);
        } else {
            DireccionEntity direccion = direccionService.getById(id).get();
            return new ResponseEntity<DireccionEntity>(direccion, HttpStatus.OK);
        }
    }

    @DeleteMapping("/delete/{id}")
    public void deleteDireccion(@PathVariable("id")int id){
        direccionService.deleteById(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<DireccionEntity> updateDireccion(@RequestBody DireccionEntity direccion, @PathVariable("id") int id) {
        if (!direccionService.existById(id)) {
            throw new ResourceNotFoundException("Direccion", "id", id);
        } else {
            DireccionEntity direccionActualizada = direccionService.update(id, direccion);
            return new ResponseEntity<DireccionEntity>(direccionActualizada, HttpStatus.OK);
        }
    }

    @PostMapping("/save")
    public void saveDireccion(@RequestBody DireccionEntity direccion){
        direccionService.save(direccion);
    }
}
