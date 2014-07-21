import Registration.Role
import Registration.User
import Registration.UserRole
import edu.upb.upbBolsa.VariablesSistema

class BootStrap {

    def init = { servletContext ->

        def userRole = Role.findByAuthority('ROLE_USER') ?: new Role(authority: 'ROLE_USER').save(failOnError: true)
        def adminRole = Role.findByAuthority('ROLE_ADMIN') ?: new Role(authority: 'ROLE_ADMIN').save(failOnError: true)

        def adminUser = User.findByUsername('admin') ?: new User( username: 'admin', password: 'admin', email: 'admin@mail.com', enabled: true).save(failOnError: true)
        def userUser = User.findByUsername('user') ?: new User( username: 'user', password: 'user', email: 'user@mail.com', enabled: true).save(failOnError: true)

        if (!adminUser.authorities.contains(adminRole)) {
            UserRole.create(adminUser, adminRole)  }

        if (!userUser.authorities.contains(userRole)) {
            UserRole.create(userUser, userRole)  }

        if(!VariablesSistema.count()) {

            VariablesSistema.findByNombre('fechaInicio') ?:new VariablesSistema(nombre: 'fechaInicio',value: '01/01/2014').save(failOnError: true);
            VariablesSistema.findByNombre('horaInicio') ?:new VariablesSistema(nombre: 'horaInicio',value: '00:00').save(failOnError: true);
            VariablesSistema.findByNombre('interTiempo') ?:new VariablesSistema(nombre: 'interTiempo',value: '60').save(failOnError: true);
            VariablesSistema.findByNombre('puntoInical') ?:new VariablesSistema(nombre: 'puntoInicial',value: '0').save(failOnError: true);
            VariablesSistema.findByNombre('capInicio') ?:new VariablesSistema(nombre: 'capInicio',value: '1000').save(failOnError: true);
            VariablesSistema.findByNombre('numHabilit') ?:new VariablesSistema(nombre: 'numHabilit',value: '4').save(failOnError: true);
            VariablesSistema.findByNombre('costoTransfer') ?:new VariablesSistema(nombre: 'costoTransfer',value: '10').save(failOnError: true);

        }




    }
    def destroy = {
    }
}
