package proyecto.proyecto.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyecto.proyecto.demo.entity.CatalogoEntity;
import proyecto.proyecto.demo.repository.CatalogoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CatalogoService {

    @Autowired
    private CatalogoRepository catalogoRepository;

    public List<CatalogoEntity> getAllCatalogos() {
        return catalogoRepository.findAll();
    }

    public Optional<CatalogoEntity> getCatalogoById(int id) {
        return catalogoRepository.findById(id);
    }

    public CatalogoEntity createCatalogo(CatalogoEntity catalogoEntity) {
        return catalogoRepository.save(catalogoEntity);
    }

    public Optional<CatalogoEntity> updateCatalogo(int id, CatalogoEntity catalogoEntity) {
        return catalogoRepository.findById(id).map(existingCatalogo -> {
            existingCatalogo.setNombreCatalogo(catalogoEntity.getNombreCatalogo());
            existingCatalogo.setValor(catalogoEntity.getValor());
            existingCatalogo.setEstado(catalogoEntity.isEstado());
            return catalogoRepository.save(existingCatalogo);
        });
    }

    public boolean deleteCatalogo(int id) {
        if (catalogoRepository.existsById(id)) {
            catalogoRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
