package proyecto.proyecto.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import proyecto.proyecto.demo.entity.SolicitudEntity;

public interface SolicitudRepository extends JpaRepository<SolicitudEntity, Integer> {

}
