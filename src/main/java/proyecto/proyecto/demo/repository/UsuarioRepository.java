package proyecto.proyecto.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import proyecto.proyecto.demo.entity.UsuarioEntity;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer>{

}
