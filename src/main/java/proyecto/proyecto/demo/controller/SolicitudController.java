package proyecto.proyecto.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import proyecto.proyecto.demo.entity.SolicitudEntity;
import proyecto.proyecto.demo.exceptions.ResourceNotFoundException;
import proyecto.proyecto.demo.service.SolicitudService;

import java.util.List;

@RestController
@RequestMapping("/solicitud")
@CrossOrigin(origins = "http://localhost:4200")
@Api(value = "Solicitud Management System", description = "Operations pertaining to Solicitud in Solicitud Management System")
public class SolicitudController {

    @Autowired
    private SolicitudService solicitudService;

    @ApiOperation(value = "View a list of available solicitudes", response = ResponseEntity.class)
    @GetMapping("/getList")
    public ResponseEntity<List<SolicitudEntity>> getSolicitudList() {
        List<SolicitudEntity> list = solicitudService.getAllSolicitudes();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @ApiOperation(value = "Get a solicitud by Id")
    @GetMapping("/getById/{id}")
    public ResponseEntity<SolicitudEntity> getById(@PathVariable("id") int id) {
        if (!solicitudService.existById(id)) {
            throw new ResourceNotFoundException("Solicitud", "id", id);
        } else {
            SolicitudEntity solicitud = solicitudService.getSolicitudById(id).get();
            return new ResponseEntity<>(solicitud, HttpStatus.OK);
        }
    }

    @ApiOperation(value = "Update a solicitud")
    @PutMapping("/update/{id}")
    public ResponseEntity<SolicitudEntity> updateSolicitud(@RequestBody SolicitudEntity solicitud, @PathVariable("id") int id) {
        if (!solicitudService.existById(id)) {
            throw new ResourceNotFoundException("Solicitud", "id", id);
        } else {
            SolicitudEntity solicitudActualizada = solicitudService.updateSolicitud(id, solicitud);
            return new ResponseEntity<>(solicitudActualizada, HttpStatus.OK);
        }
    }

    @ApiOperation(value = "Add a solicitud")
    @PostMapping("/save")
    public void saveSolicitud(@RequestBody SolicitudEntity solicitud) {
        solicitudService.createSolicitud(solicitud);
    }

    @ApiOperation(value = "Delete a solicitud")
    @DeleteMapping("/delete/{id}")
    public void deleteSolicitud(@PathVariable("id") int id) {
        solicitudService.deleteSolicitud(id);
    }
}
