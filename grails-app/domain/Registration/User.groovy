package Registration

class User {

	transient springSecurityService

	String username
	String password
    String fullname
    String verify_password
	boolean enabled
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired

    static searchable = [only: ['username', 'fullname']]

	static constraints = {
		username blank: false, unique: true, email:true
		password blank: false
        fullname blank: false, unique: true
        verify_password blank: false
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
