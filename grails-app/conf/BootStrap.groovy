import Registration.Role
import Registration.User
import Registration.UserRole
import edu.upb.upbBolsa.VariablesSistema

class BootStrap {

    def init = { servletContext ->

        def userRole = Role.findByAuthority('ROLE_USER') ?: new Role(authority: 'ROLE_USER').save(failOnError: true)
        def adminRole = Role.findByAuthority('ROLE_ADMIN') ?: new Role(authority: 'ROLE_ADMIN').save(failOnError: true)
        def cajeroRole = Role.findByAuthority('ROLE_CAJA') ?: new Role(authority: 'ROLE_CAJA').save(failOnError: true)
        def brokeRole = Role.findByAuthority('ROLE_BROK') ?: new Role(authority: 'ROLE_BROK').save(failOnError: true)


        def adminUser = User.findByUsername('admin@hotmail.com') ?: new User( username: 'admin@hotmail.com', password: 'admin', fullname: 'admin@mail.com', enabled: true).save(failOnError: true)
        def userUser = User.findByUsername('user@hotmail.com') ?: new User( username: 'user@hotmail.com', password: 'user', fullname: 'user@mail.com', enabled: true).save(failOnError: true)
        def cajeroUser = User.findByUsername('cajero@hotmail.com') ?: new User( username: 'cajero@hotmail.com', password: 'cajero', fullname: 'cajero@mail.com', enabled: true).save(failOnError: true)
        if (!adminUser.authorities.contains(adminRole)) {
            UserRole.create(adminUser, adminRole)  }

        if (!userUser.authorities.contains(userRole)) {
            UserRole.create(userUser, userRole)  }

        if (!cajeroUser.authorities.contains(cajeroRole)) {
            UserRole.create(cajeroUser, cajeroRole)  }

        if(!VariablesSistema.count()) {

            VariablesSistema.findByNombre('fechaInicio') ?:new VariablesSistema(nombre: 'fechaInicio',value: '01/01/2014').save(failOnError: true);
            VariablesSistema.findByNombre('horaInicio') ?:new VariablesSistema(nombre: 'horaInicio',value: '00:00').save(failOnError: true);
            VariablesSistema.findByNombre('interTiempo') ?:new VariablesSistema(nombre: 'interTiempo',value: '60').save(failOnError: true);
            VariablesSistema.findByNombre('puntoInicial') ?:new VariablesSistema(nombre: 'puntoInicial',value: '0').save(failOnError: true);
            VariablesSistema.findByNombre('capInicio') ?:new VariablesSistema(nombre: 'capInicio',value: '1000').save(failOnError: true);
            VariablesSistema.findByNombre('numHabilit') ?:new VariablesSistema(nombre: 'numHabilit',value: '4').save(failOnError: true);
            VariablesSistema.findByNombre('costoTransfer') ?:new VariablesSistema(nombre: 'costoTransfer',value: '10').save(failOnError: true);
            VariablesSistema.findByNombre('costoHabilitacion') ?:new VariablesSistema(nombre: 'costoHabilitacion',value: '5').save(failOnError: true);
            VariablesSistema.findByNombre('ciclo') ?:new VariablesSistema(nombre: 'ciclo',value: '-2').save(failOnError: true);

        }




    }
    def destroy = {
    }
}
