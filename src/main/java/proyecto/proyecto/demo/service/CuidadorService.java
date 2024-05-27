package proyecto.proyecto.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import proyecto.proyecto.demo.entity.CuidadorEntity;
import proyecto.proyecto.demo.repository.CuidadorRepository;

@Service
@Transactional
public class CuidadorService {

    @Autowired
    CuidadorRepository cuidadorRepository;

    public List<CuidadorEntity> findAll() {
        return cuidadorRepository.findAll();
    }

    public Optional<CuidadorEntity> findById(int id) {
        return cuidadorRepository.findById(id);
    }

    public CuidadorEntity update(CuidadorEntity cuidador) {
        return cuidadorRepository.save(cuidador);
    }

    public void deleteById(int id) {
        cuidadorRepository.deleteById(id);
    }

}
