package proyecto.proyecto.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import proyecto.proyecto.demo.repository.RolRepository;
import proyecto.proyecto.demo.dto.RolEnum;
import proyecto.proyecto.demo.entity.RolEntity;

@Service
@Transactional
public class RolService {

    @Autowired
    RolRepository rolRepository;

    public List<RolEntity> getList() {
        return rolRepository.findAll();
    }

    public Optional<RolEntity> getById(Integer id) {
        return rolRepository.findById(id);
    }

    public void save(RolEntity rol) {
        rolRepository.save(rol);
    }

    public void deleteRol(Integer id) {
        rolRepository.deleteById(id);
    }

    public Optional<RolEntity> getByRolNombre(RolEnum rolNombre){
        return rolRepository.findByRolNombre(rolNombre);
    }

}
