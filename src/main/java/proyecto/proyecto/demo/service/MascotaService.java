package proyecto.proyecto.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyecto.proyecto.demo.entity.MascotaEntity;
import proyecto.proyecto.demo.entity.CatalogoEntity;
import proyecto.proyecto.demo.exceptions.ResourceNotFoundException;
import proyecto.proyecto.demo.repository.MascotaRepository;
import proyecto.proyecto.demo.repository.CatalogoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MascotaService {

    @Autowired
    private MascotaRepository mascotaRepository;

    @Autowired
    private CatalogoRepository catalogoRepository;

    public List<MascotaEntity> getList() {
        return mascotaRepository.findAll();
    }

    public Optional<MascotaEntity> getById(int id) {
        return mascotaRepository.findById(id);
    }

    public MascotaEntity save(MascotaEntity mascota) {
        if (mascota.getTipoMascota() != null) {
            Optional<CatalogoEntity> tipoMascotaOptional = catalogoRepository.findById(mascota.getTipoMascota().getId());
            if (!tipoMascotaOptional.isPresent()) {
                throw new ResourceNotFoundException("Catalogo", "id", mascota.getTipoMascota().getId());
            }
        }
        return mascotaRepository.save(mascota);
    }

    public MascotaEntity update(int id, MascotaEntity mascota) {
        return mascotaRepository.findById(id).map(existingMascota -> {
            existingMascota.setNombre(mascota.getNombre());
            existingMascota.setEdad(mascota.getEdad());
            existingMascota.setAlergias(mascota.getAlergias());
            existingMascota.setDiscapacidades(mascota.getDiscapacidades());
            existingMascota.setTipoSangre(mascota.getTipoSangre());
            existingMascota.setDescripcion(mascota.getDescripcion());
            existingMascota.setEstado(mascota.isEstado());
            existingMascota.setPropietario(mascota.getPropietario());
            
            if (mascota.getTipoMascota() != null) {
                Optional<CatalogoEntity> tipoMascotaOptional = catalogoRepository.findById(mascota.getTipoMascota().getId());
                if (!tipoMascotaOptional.isPresent()) {
                    throw new ResourceNotFoundException("Catalogo", "id", mascota.getTipoMascota().getId());
                }
                existingMascota.setTipoMascota(mascota.getTipoMascota());
            }

            return mascotaRepository.save(existingMascota);
        }).orElseThrow(() -> new ResourceNotFoundException("Mascota", "id", id));
    }

    public void deleteById(int id) {
        if (mascotaRepository.existsById(id)) {
            mascotaRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Mascota", "id", id);
        }
    }

    public boolean existById(int id) {
        return mascotaRepository.existsById(id);
    }
}
