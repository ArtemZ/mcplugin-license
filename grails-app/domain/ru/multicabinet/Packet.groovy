package ru.multicabinet

/* Packet is a template for orders */
class Packet {
	String name
	String description
	String paytype = "recurring"
	Integer stock = -1 //-1 -unlimited, 0 - out of stock
	Boolean orderable = true
	/*static hasOne = [plugin : Plugin]*/
	static hasMany = [
		props : PacketProps, //packet properties declared by server module
	]
    static constraints = {
	    name(size:2..64)
	    description(nullable : true)
	    paytype(inList : ['free', 'recurring', 'onetime'])
    }
    static mapping = {
	    props column : 'packet_props'
	    description type : 'text'
    }
    String toString(){ 
	    name
    }
}
