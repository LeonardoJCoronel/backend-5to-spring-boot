package proyecto.proyecto.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import proyecto.proyecto.demo.entity.MascotaEntity;
import proyecto.proyecto.demo.exceptions.ResourceNotFoundException;
import proyecto.proyecto.demo.service.MascotaService;

import java.util.List;

@RestController
@RequestMapping("/mascota")
@CrossOrigin(origins = "http://localhost:4200")
public class MascotaController {

    @Autowired
    private MascotaService mascotaService;

    @GetMapping("/getList")
    public ResponseEntity<List<MascotaEntity>> getMascotaList() {
        List<MascotaEntity> list = mascotaService.getList();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<MascotaEntity> getById(@PathVariable("id") int id) {
        if (!mascotaService.existById(id)) {
            throw new ResourceNotFoundException("Mascota", "id", id);
        } else {
            MascotaEntity mascota = mascotaService.getById(id).get();
            return new ResponseEntity<>(mascota, HttpStatus.OK);
        }
    }

    @DeleteMapping("/delete/{id}")
    public void deleteMascota(@PathVariable("id") int id) {
        mascotaService.deleteById(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<MascotaEntity> updateMascota(@RequestBody MascotaEntity mascota, @PathVariable("id") int id) {
        if (!mascotaService.existById(id)) {
            throw new ResourceNotFoundException("Mascota", "id", id);
        } else {
            MascotaEntity mascotaActualizada = mascotaService.update(id, mascota);
            return new ResponseEntity<>(mascotaActualizada, HttpStatus.OK);
        }
    }

    @PostMapping("/save")
    public void saveMascota(@RequestBody MascotaEntity mascota) {
        mascotaService.save(mascota);
    }
}
