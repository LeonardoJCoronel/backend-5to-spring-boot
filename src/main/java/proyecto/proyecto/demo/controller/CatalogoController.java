package proyecto.proyecto.demo.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import proyecto.proyecto.demo.entity.CatalogoEntity;
import proyecto.proyecto.demo.exceptions.ResourceNotFoundException;
import proyecto.proyecto.demo.service.CatalogoService;

@RestController
@RequestMapping("/catalogo")
@CrossOrigin(origins = "http://localhost:4200")
@Api(value = "Catalogo Management System", description = "Operations pertaining to Catalogo in Catalogo Management System")
public class CatalogoController {

    @Autowired
    private CatalogoService catalogoService;

    @ApiOperation(value = "View a list of available catalogos", response = ResponseEntity.class)
    @GetMapping("/getList")
    public ResponseEntity<List<CatalogoEntity>> getCatalogoList(){
        List<CatalogoEntity> list = catalogoService.getList();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @ApiOperation(value = "Get a catalogo by Id")
    @GetMapping("/getById/{id}")
    public ResponseEntity<CatalogoEntity> getById(@PathVariable("id") int id) {
        if (!catalogoService.existById(id)) {
            throw new ResourceNotFoundException("Catalogo", "id", id);
        } else {
            CatalogoEntity catalogo = catalogoService.getById(id).get();
            return new ResponseEntity<>(catalogo, HttpStatus.OK);
        }
    }

    @ApiOperation(value = "Delete a catalogo")
    @DeleteMapping("/delete/{id}")
    public void deleteCatalogo(@PathVariable("id") int id){
        catalogoService.deleteById(id);
    }

    @ApiOperation(value = "Update a catalogo")
    @PutMapping("/update/{id}")
    public ResponseEntity<CatalogoEntity> updateCatalogo(@RequestBody CatalogoEntity catalogo, @PathVariable("id") int id) {
        if (!catalogoService.existById(id)) {
            throw new ResourceNotFoundException("Catalogo", "id", id);
        } else {
            CatalogoEntity catalogoActualizado = catalogoService.update(id, catalogo);
            return new ResponseEntity<>(catalogoActualizado, HttpStatus.OK);
        }
    }

    @ApiOperation(value = "Add a catalogo")
    @PostMapping("/save")
    public void saveCatalogo(@RequestBody CatalogoEntity catalogo){
        catalogoService.save(catalogo);
    }
}
