package ru.multicabinet

class ServerProps {
	String key
	String value
	static belongsTo = [server : Server]
	static constraints = {
		//server(nullable : true)
	}
	String toString(){
		key + value
	}
	static mapping = {
		value column : 'props_value'
		key column : 'props_key'
	}

}
