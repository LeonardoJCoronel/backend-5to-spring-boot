package proyecto.proyecto.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyecto.proyecto.demo.entity.PropietarioEntity;
import proyecto.proyecto.demo.repository.PropietarioRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PropietarioService {

    @Autowired
    private PropietarioRepository propietarioRepository;

    public Optional<PropietarioEntity> getByUsuarioId(int usuarioId) {
        return propietarioRepository.findByUsuarioId(usuarioId);
    }

    public List<PropietarioEntity> getList() {
        return propietarioRepository.findAll();
    }

    public Optional<PropietarioEntity> updatePropietario(int id, PropietarioEntity updatedPropietario) {
        Optional<PropietarioEntity> propietario = propietarioRepository.findById(id);
        if (propietario.isPresent()) {
            PropietarioEntity existingPropietario = propietario.get();
            existingPropietario.setEstado(updatedPropietario.isEstado());
            existingPropietario.setUsuario(updatedPropietario.getUsuario());

            existingPropietario.getMascotas().clear();
            existingPropietario.getMascotas().addAll(updatedPropietario.getMascotas());
            propietarioRepository.save(existingPropietario);
        }
        return propietario;
    }
}
