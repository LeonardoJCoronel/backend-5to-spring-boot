package proyecto.proyecto.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import proyecto.proyecto.demo.entity.RolEntity;
import proyecto.proyecto.demo.service.RolService;

import java.util.List;

@RestController
@RequestMapping("/rol")
@CrossOrigin(origins = "http://localhost:4200")
@Api(value = "Rol Management System", description = "Operations pertaining to Rol in Rol Management System")
public class RolController {

    @Autowired
    private RolService rolService;

    @ApiOperation(value = "View a list of available roles", response = ResponseEntity.class)
    @GetMapping("/getList")
    public ResponseEntity<List<RolEntity>> getRolList() {
        List<RolEntity> list = rolService.getList();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @ApiOperation(value = "Delete a role")
    @DeleteMapping("/delete/{id}")
    public void deleteRol(@PathVariable("id")int id){
        rolService.deleteRol(id);
    }
}
