package proyecto.proyecto.demo.util;


import proyecto.proyecto.demo.entity.RolEntity;
import proyecto.proyecto.demo.dto.RolEnum;
import proyecto.proyecto.demo.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


/**
 * MUY IMPORTANTE: ESTA CLASE SÓLO SE EJECUTARÁ UNA VEZ PARA CREAR LOS ROLES.
 * UNA VEZ CREADOS SE DEBERÁ ELIMINAR O BIEN COMENTAR EL CÓDIGO
 *
 */

@SuppressWarnings("unused")
@Component
public class CreateRoles implements CommandLineRunner {

    @Autowired
    RolService rolService;

    @Override
    public void run(String... args) throws Exception {
        // RolEntity rolCuidador = new RolEntity(RolEnum.ROL_CUIDADOR);
        // RolEntity rolPropietario = new RolEntity(RolEnum.ROL_PROPIETARIO);
        // RolEntity rolAdmin = new RolEntity(RolEnum.ROL_ADMIN);
        // rolService.save(rolCuidador);
        // rolService.save(rolPropietario);
        // rolService.save(rolAdmin);
    }
}