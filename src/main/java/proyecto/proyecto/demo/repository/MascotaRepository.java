package proyecto.proyecto.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import proyecto.proyecto.demo.entity.MascotaEntity;

public interface MascotaRepository extends JpaRepository<MascotaEntity, Integer>{

}
