package ru.multicabinet;
import org.apache.shiro.crypto.hash.Sha256Hash

class ShiroUser implements Comparable{
	String username
	String passwordHash
	String email
	String remarks
	Date dateCreated
	Date lastUpdated
	
	Date lastLogin
	
	
	Boolean updatePassword = false
	Boolean sendNotify = true
	//make users se
	static hasMany = [ roles: ShiroRole, permissions: String, props : UserProps ]
	static transients = ['updatePassword', 'sendNotify']
	transient triggersService
	static constraints = {
		username(unique: true, nullable: false, blank: false)
		lastLogin(nullable:true)
		remarks(nullable:true)
		email(unique : true, email:true, nullable : false)
	}
	static mapping = {
		remarks type : 'text'
	}
	def beforeInsert(){
		passwordHash = new Sha256Hash(passwordHash).toHex();
	}
	def beforeUpdate(){
		
		if(passwordHash && updatePassword){
			passwordHash = new Sha256Hash(passwordHash).toHex()
		}
	}
	String toString(){
		"User #" + id + ": " + username + " - " + email 
	}
	int compareTo(user){
		return id - user.id
	}

}
