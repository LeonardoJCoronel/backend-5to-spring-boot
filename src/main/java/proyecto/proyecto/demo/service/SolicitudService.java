package proyecto.proyecto.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyecto.proyecto.demo.entity.SolicitudEntity;
import proyecto.proyecto.demo.exceptions.ResourceNotFoundException;
import proyecto.proyecto.demo.repository.SolicitudRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SolicitudService {

    @Autowired
    private SolicitudRepository solicitudRepository;

    public List<SolicitudEntity> getAllSolicitudes() {
        return solicitudRepository.findAll();
    }

    public Optional<SolicitudEntity> getSolicitudById(int id) {
        return solicitudRepository.findById(id);
    }

    public SolicitudEntity createSolicitud(SolicitudEntity solicitud) {
        return solicitudRepository.save(solicitud);
    }

    public SolicitudEntity updateSolicitud(int id, SolicitudEntity solicitud) {
        return solicitudRepository.findById(id).map(existingSolicitud -> {
            existingSolicitud.setFechaRegistro(solicitud.getFechaRegistro());
            existingSolicitud.setEsAceptado(solicitud.isEsAceptado());
            existingSolicitud.setEstado(solicitud.isEstado());
            existingSolicitud.setPaga(solicitud.getPaga());
            existingSolicitud.setPropietario(solicitud.getPropietario());
            existingSolicitud.setCuidador(solicitud.getCuidador());
            existingSolicitud.setMascotas(solicitud.getMascotas());
            existingSolicitud.setCatalogo(solicitud.getCatalogo());
            return solicitudRepository.save(existingSolicitud);
        }).orElseThrow(() -> new ResourceNotFoundException("Solicitud", "id", id));
    }

    public void deleteSolicitud(int id) {
        if (solicitudRepository.existsById(id)) {
            solicitudRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Solicitud", "id", id);
        }
    }

    public boolean existById(int id) {
        return solicitudRepository.existsById(id);
    }
}
