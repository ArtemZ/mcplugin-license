package ru.multicabinet

class Server {
	String name
	Integer maxusers = 0 //0 - unlimited
	//Serversgroup group we don't need this
	static hasMany = [props : ServerProps]
	static constraints = {
		name(size:2..64)
	}
	static mapping = {
		props column : 'server_props'
	}
	String toString(){

		name
	}
}
