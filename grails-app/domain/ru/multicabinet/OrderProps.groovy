package ru.multicabinet

class OrderProps {
	String key
	String value
	static belongsTo = [order : Order]
    static constraints = {
    }
    static mapping = {
	    value column : 'props_value'
	    key column : 'props_key'
    }

}
