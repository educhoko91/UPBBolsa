package Registration

import edu.upb.upbBolsa.Acciones
import edu.upb.upbBolsa.Broker

class User {

	transient springSecurityService

    static hasMany = [acciones:Acciones]
	String username
    String email
	String password
    String fullname
    String verify_password
    Broker broker
    double capital
    String mensaje
	boolean enabled
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired

    //campo añadido para las habilitaciones
    int numeroHabilitaciones

    static searchable = [only: ['username', 'fullname']]

	static constraints = {
		username blank: false, unique: true
		password blank: false
        fullname blank: false, unique: true
        verify_password blank: false
        broker nullable: true
        mensaje nullable: true
	}

    static transients = ['verify_password']

	static mapping = {
		password column: '`password`'
	}

	Set<Role> getAuthorities() {
		UserRole.findAllByUser(this).collect { it.role } as Set
	}

	def beforeInsert() {
		encodePassword()
	}

	def beforeUpdate() {
		if (isDirty('password')) {
			encodePassword()
		}
	}

	protected void encodePassword() {
		password = springSecurityService.encodePassword(password)
	}
}
