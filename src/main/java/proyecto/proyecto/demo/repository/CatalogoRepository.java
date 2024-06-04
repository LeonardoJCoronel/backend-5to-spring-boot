package proyecto.proyecto.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import proyecto.proyecto.demo.entity.CatalogoEntity;

public interface CatalogoRepository extends JpaRepository<CatalogoEntity, Integer> {
}
