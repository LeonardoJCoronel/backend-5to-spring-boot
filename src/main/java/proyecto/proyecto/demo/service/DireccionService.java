package proyecto.proyecto.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import proyecto.proyecto.demo.entity.DireccionEntity;
import proyecto.proyecto.demo.exceptions.ResourceNotFoundException;
import proyecto.proyecto.demo.repository.DireccionRepository;

@Service
@Transactional
public class DireccionService {

    @Autowired
    DireccionRepository direccionRepository;

    public List<DireccionEntity> getList() {
        return direccionRepository.findAll();
    }

    public Optional<DireccionEntity> getById(Integer id) {
        return direccionRepository.findById(id);
    }

    public void save(DireccionEntity rol) {
        direccionRepository.save(rol);
    }

    public void deleteById(Integer id) {
        direccionRepository.deleteById(id);
    }

    public boolean existById(Integer id){
        return direccionRepository.existsById(id);
    }

        public DireccionEntity update(Integer id, DireccionEntity direccion){
        Optional<DireccionEntity> direccionOptional = direccionRepository.findById(id);
        if (direccionOptional.isPresent()) {
            DireccionEntity direccionExistente = direccionOptional.get();

            direccionExistente.setCallePrimaria(direccion.getCallePrimaria());
            direccionExistente.setCalleSecundaria(direccion.getCalleSecundaria());
            direccionExistente.setNumeroCasa(direccion.getNumeroCasa());
            direccionExistente.setNombreSector(direccion.getNombreSector());

            return direccionRepository.save(direccionExistente);
        }else{
            throw new ResourceNotFoundException("Usuario", "id", id);
        }
    }

}
