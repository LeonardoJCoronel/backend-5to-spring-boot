package proyecto.proyecto.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import proyecto.proyecto.demo.dto.RolEnum;
import proyecto.proyecto.demo.entity.RolEntity;

public interface RolRepository extends JpaRepository<RolEntity, Integer> {
    Optional<RolEntity> findByRolNombre(RolEnum rolNombre);
}
