package proyecto.proyecto.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import proyecto.proyecto.demo.entity.CatalogoEntity;
import proyecto.proyecto.demo.exceptions.ResourceNotFoundException;
import proyecto.proyecto.demo.repository.CatalogoRepository;

@Service
@Transactional
public class CatalogoService {

    @Autowired
    private CatalogoRepository catalogoRepository;

    public List<CatalogoEntity> getList() {
        return catalogoRepository.findAll();
    }

    public Optional<CatalogoEntity> getById(Integer id) {
        return catalogoRepository.findById(id);
    }

    public void save(CatalogoEntity catalogo) {
        catalogoRepository.save(catalogo);
    }

    public void deleteById(Integer id) {
        catalogoRepository.deleteById(id);
    }

    public boolean existById(Integer id){
        return catalogoRepository.existsById(id);
    }

    public CatalogoEntity update(Integer id, CatalogoEntity catalogoDetails){
        Optional<CatalogoEntity> catalogoOptional = catalogoRepository.findById(id);
        if (catalogoOptional.isPresent()) {
            CatalogoEntity catalogoExistente = catalogoOptional.get();

            catalogoExistente.setNombreCatalogo(catalogoDetails.getNombreCatalogo());
            catalogoExistente.setValor(catalogoDetails.getValor());
            catalogoExistente.setEstado(catalogoDetails.getEstado());

            return catalogoRepository.save(catalogoExistente);
        } else {
            throw new ResourceNotFoundException("Catalogo", "id", id);
        }
    }
}
