package proyecto.proyecto.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import proyecto.proyecto.demo.entity.UsuarioEntity;
import proyecto.proyecto.demo.exceptions.ResourceNotFoundException;
import proyecto.proyecto.demo.repository.UsuarioRepository;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public List<UsuarioEntity> getList() {
        return usuarioRepository.findAll();
    }

    public Optional<UsuarioEntity> getById(Integer id) {
        return usuarioRepository.findById(id);
    }

    public boolean existById(Integer id) {
        return usuarioRepository.existsById(id);
    }

    public void saveUsuario(UsuarioEntity usuario) {
        usuarioRepository.save(usuario);
    }

    public void deleteUsuario(Integer id) {
        usuarioRepository.deleteById(id);
    }

    public UsuarioEntity updateUsuario(Integer id, UsuarioEntity usuario) {
        Optional<UsuarioEntity> usuarioOptional = usuarioRepository.findById(id);
        if (usuarioOptional.isPresent()) {
            UsuarioEntity usuarioExistente = usuarioOptional.get();

            usuarioExistente.setCorreo(usuario.getCorreo());
            usuarioExistente.setNombre(usuario.getNombre());
            usuarioExistente.setApellido(usuario.getApellido());
            usuarioExistente.setContrasenia(usuario.getContrasenia());
            usuarioExistente.setDirecciones(usuario.getDirecciones());
            return usuarioRepository.save(usuarioExistente);
        } else {
            throw new ResourceNotFoundException("Usuario", "id", id);
        }
    }
}
