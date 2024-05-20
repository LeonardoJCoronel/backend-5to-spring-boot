package proyecto.proyecto.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import proyecto.proyecto.demo.entity.RolEntity;
import proyecto.proyecto.demo.service.RolService;

@RestController
@RequestMapping("/rol")
@CrossOrigin(origins = "http://localhost:4200")
public class RolController {

    @Autowired
    private RolService rolService;

    @GetMapping("/getList")
    public ResponseEntity<List<RolEntity>> getRolList() {
        List<RolEntity> list = rolService.getList();
        return new ResponseEntity<List<RolEntity>>(list, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteRol(@PathVariable("id")int id){
        rolService.deleteRol(id);
    }
}
