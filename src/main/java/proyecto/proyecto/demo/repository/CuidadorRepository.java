package proyecto.proyecto.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import proyecto.proyecto.demo.entity.CuidadorEntity;

public interface CuidadorRepository extends JpaRepository<CuidadorEntity, Integer>{
    Optional<CuidadorEntity> findByUsuarioId(int usuarioId);
}
