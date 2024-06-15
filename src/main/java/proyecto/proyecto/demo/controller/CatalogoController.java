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

import proyecto.proyecto.demo.entity.CatalogoEntity;
import proyecto.proyecto.demo.exceptions.ResourceNotFoundException;
import proyecto.proyecto.demo.service.CatalogoService;

@RestController
@RequestMapping("/catalogo")
@CrossOrigin(origins = "http://localhost:4200")
public class CatalogoController {

    @Autowired
    private CatalogoService catalogoService;

    @GetMapping("/getList")
    public ResponseEntity<List<CatalogoEntity>> getCatalogoList(){
        List<CatalogoEntity> list = catalogoService.getList();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<CatalogoEntity> getById(@PathVariable("id") int id) {
        if (!catalogoService.existById(id)) {
            throw new ResourceNotFoundException("Catalogo", "id", id);
        } else {
            CatalogoEntity catalogo = catalogoService.getById(id).get();
            return new ResponseEntity<>(catalogo, HttpStatus.OK);
        }
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCatalogo(@PathVariable("id") int id){
        catalogoService.deleteById(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CatalogoEntity> updateCatalogo(@RequestBody CatalogoEntity catalogo, @PathVariable("id") int id) {
        if (!catalogoService.existById(id)) {
            throw new ResourceNotFoundException("Catalogo", "id", id);
        } else {
            CatalogoEntity catalogoActualizado = catalogoService.update(id, catalogo);
            return new ResponseEntity<>(catalogoActualizado, HttpStatus.OK);
        }
    }

    @PostMapping("/save")
    public void saveCatalogo(@RequestBody CatalogoEntity catalogo){
        catalogoService.save(catalogo);
    }
}
