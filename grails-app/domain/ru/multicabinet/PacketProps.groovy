package ru.multicabinet

class PacketProps {
	String key
	String value
	static belongsTo = [packet : Packet]
    static constraints = {
    }
    static mapping = {
	    value column : 'props_value'
	    key column : 'props_key'
    }

}
