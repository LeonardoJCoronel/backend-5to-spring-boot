package proyecto.proyecto.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import proyecto.proyecto.demo.entity.UsuarioEntity;
import proyecto.proyecto.demo.dto.RolEnum;
import proyecto.proyecto.demo.entity.CuidadorEntity;
import proyecto.proyecto.demo.entity.PropietarioEntity;
import proyecto.proyecto.demo.exceptions.ResourceNotFoundException;
import proyecto.proyecto.demo.repository.CuidadorRepository;
import proyecto.proyecto.demo.repository.PropietarioRepository;
import proyecto.proyecto.demo.repository.UsuarioRepository;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    private CuidadorRepository cuidadorRepository;

    @Autowired
    private PropietarioRepository propietarioRepository;

    public List<UsuarioEntity> getList() {
        return usuarioRepository.findAll();
    }

    public Optional<UsuarioEntity> getById(Integer id) {
        return usuarioRepository.findById(id);
    }

    public boolean existById(Integer id) {
        return usuarioRepository.existsById(id);
    }

    @SuppressWarnings("unlikely-arg-type")
    public void saveUsuario(UsuarioEntity usuario) {
        UsuarioEntity guardarUsuario = usuarioRepository.save(usuario);
        if (guardarUsuario.getRoles().contains(RolEnum.ROL_CUIDADOR)) {
            CuidadorEntity cuidador = new CuidadorEntity(true, 0);
            cuidador.setUsuario(guardarUsuario);
            cuidadorRepository.save(cuidador);
        }else if(guardarUsuario.getRoles().contains(RolEnum.ROL_PROPIETARIO)){
            PropietarioEntity propietario = new PropietarioEntity(true);
            propietario.setUsuario(guardarUsuario);
            propietarioRepository.save(propietario);
        }
        
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
