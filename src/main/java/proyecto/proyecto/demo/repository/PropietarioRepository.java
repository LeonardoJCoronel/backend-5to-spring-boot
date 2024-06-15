package proyecto.proyecto.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import proyecto.proyecto.demo.entity.PropietarioEntity;

public interface PropietarioRepository extends JpaRepository<PropietarioEntity, Integer>{
    Optional<PropietarioEntity> findByUsuarioId(int usuarioId);
}
