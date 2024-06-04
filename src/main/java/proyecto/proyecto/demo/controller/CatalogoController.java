package proyecto.proyecto.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import proyecto.proyecto.demo.entity.CatalogoEntity;
import proyecto.proyecto.demo.service.CatalogoService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/catalogo")
public class CatalogoController {

    @Autowired
    private CatalogoService catalogoService;

    @GetMapping("/getList")
    public List<CatalogoEntity> getAllCatalogos() {
        return catalogoService.getAllCatalogos();
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<CatalogoEntity> getCatalogoById(@PathVariable int id) {
        Optional<CatalogoEntity> catalogo = catalogoService.getCatalogoById(id);
        return catalogo.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/save")
    public CatalogoEntity createCatalogo(@RequestBody CatalogoEntity catalogoEntity) {
        return catalogoService.createCatalogo(catalogoEntity);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CatalogoEntity> updateCatalogo(@PathVariable int id, @RequestBody CatalogoEntity catalogoEntity) {
        Optional<CatalogoEntity> updatedCatalogo = catalogoService.updateCatalogo(id, catalogoEntity);
        return updatedCatalogo.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCatalogo(@PathVariable int id) {
        boolean deleted = catalogoService.deleteCatalogo(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
