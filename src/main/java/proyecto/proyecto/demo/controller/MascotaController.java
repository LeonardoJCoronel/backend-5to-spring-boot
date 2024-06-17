package proyecto.proyecto.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import proyecto.proyecto.demo.entity.MascotaEntity;
import proyecto.proyecto.demo.exceptions.ResourceNotFoundException;
import proyecto.proyecto.demo.service.MascotaService;

import java.util.List;

@RestController
@RequestMapping("/mascota")
@CrossOrigin(origins = "http://localhost:4200")
@Api(value = "Mascota Management System", description = "Operations pertaining to Mascota in Mascota Management System")
public class MascotaController {

    @Autowired
    private MascotaService mascotaService;

    @ApiOperation(value = "View a list of available mascotas", response = ResponseEntity.class)
    @GetMapping("/getList")
    public ResponseEntity<List<MascotaEntity>> getMascotaList() {
        List<MascotaEntity> list = mascotaService.getList();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @ApiOperation(value = "Get a mascota by Id")
    @GetMapping("/getById/{id}")
    public ResponseEntity<MascotaEntity> getById(@PathVariable("id") int id) {
        if (!mascotaService.existById(id)) {
            throw new ResourceNotFoundException("Mascota", "id", id);
        } else {
            MascotaEntity mascota = mascotaService.getById(id).get();
            return new ResponseEntity<>(mascota, HttpStatus.OK);
        }
    }

    @ApiOperation(value = "Update a mascota")
    @PutMapping("/update/{id}")
    public ResponseEntity<MascotaEntity> updateMascota(@RequestBody MascotaEntity mascota, @PathVariable("id") int id) {
        if (!mascotaService.existById(id)) {
            throw new ResourceNotFoundException("Mascota", "id", id);
        } else {
            MascotaEntity mascotaActualizada = mascotaService.update(id, mascota);
            return new ResponseEntity<>(mascotaActualizada, HttpStatus.OK);
        }
    }

    @ApiOperation(value = "Add a mascota")
    @PostMapping("/save")
    public void saveMascota(@RequestBody MascotaEntity mascota) {
        mascotaService.save(mascota);
    }

    @ApiOperation(value = "Delete a mascota")
    @DeleteMapping("/delete/{id}")
    public void deleteMascota(@PathVariable("id") int id) {
        mascotaService.deleteById(id);
    }
}
