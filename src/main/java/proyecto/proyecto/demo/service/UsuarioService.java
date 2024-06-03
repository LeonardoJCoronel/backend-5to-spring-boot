package proyecto.proyecto.demo.service;

import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

import proyecto.proyecto.demo.entity.UsuarioEntity;
import proyecto.proyecto.demo.dto.RolEnum;
import proyecto.proyecto.demo.entity.CuidadorEntity;
import proyecto.proyecto.demo.entity.PropietarioEntity;
import proyecto.proyecto.demo.entity.RolEntity;
import proyecto.proyecto.demo.exceptions.ResourceNotFoundException;
import proyecto.proyecto.demo.repository.CuidadorRepository;
import proyecto.proyecto.demo.repository.PropietarioRepository;
import proyecto.proyecto.demo.repository.UsuarioRepository;

@Service
@Transactional
public class UsuarioService implements UserDetailsService {

    private final BCryptPasswordEncoder passwordEncoder;

    public UsuarioService() {
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    private CuidadorRepository cuidadorRepository;

    @Autowired
    private PropietarioRepository propietarioRepository;

    public List<UsuarioEntity> getList() {
        return (List<UsuarioEntity>) usuarioRepository.findAll();
    }

    public Optional<UsuarioEntity> getById(int id) {
        return usuarioRepository.findById(id);
    }

    public boolean existById(int id) {
        return usuarioRepository.existsById(id);
    }

    public Optional<?> findByEmail(String email) {
        return usuarioRepository.findByCorreo(email);
    }

    @SuppressWarnings("unlikely-arg-type")
    public void saveUsuario(UsuarioEntity usuario) {

        if (usuarioRepository.existsByCorreo(usuario.getCorreo())) {
            throw new HttpClientErrorException(HttpStatus.ALREADY_REPORTED);
        } else {
            String encodedPassword = passwordEncoder.encode(usuario.getContrasenia());
            usuario.setContrasenia(encodedPassword);
            UsuarioEntity guardarUsuario = usuarioRepository.save(usuario);
            if (guardarUsuario.getRoles().contains(RolEnum.ROL_CUIDADOR)) {
                CuidadorEntity cuidador = new CuidadorEntity(true, 0);
                cuidador.setUsuario(guardarUsuario);
                cuidadorRepository.save(cuidador);
            } else if (guardarUsuario.getRoles().contains(RolEnum.ROL_PROPIETARIO)) {
                PropietarioEntity propietario = new PropietarioEntity(true);
                propietario.setUsuario(guardarUsuario);
                propietarioRepository.save(propietario);
            }

        }
    }

    public void deleteUsuario(int id) {
        usuarioRepository.deleteById(id);
    }

    public UsuarioEntity updateUsuario(int id, UsuarioEntity usuario) {
        Optional<UsuarioEntity> usuarioOptional = usuarioRepository.findById(id);
        if (usuarioOptional.isPresent()) {
            UsuarioEntity usuarioExistente = usuarioOptional.get();

            usuarioExistente.setCorreo(usuario.getCorreo());
            usuarioExistente.setNombre(usuario.getNombre());
            usuarioExistente.setApellido(usuario.getApellido());
            usuarioExistente.setContrasenia(usuario.getContrasenia());
            usuarioExistente.setDirecciones(usuario.getDirecciones());
            usuarioExistente.setEsAceptado(usuario.isEsAceptado());
            usuarioExistente.setEstado(usuario.isEstado());
            usuarioExistente.setIdentificacion(usuario.getIdentificacion());
            usuarioExistente.setTelefono(usuario.getTelefono());
            return usuarioRepository.save(usuarioExistente);
        } else {
            throw new ResourceNotFoundException("Usuario", "id", id);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
        UsuarioEntity user = usuarioRepository.findByCorreo(correo)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + correo));

        Set<GrantedAuthority> authorities = new HashSet<>();
        for (RolEntity rol : user.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(rol.getRolNombre().name()));
        }

        return new User(user.getCorreo(), user.getContrasenia(), authorities);
    }

}
