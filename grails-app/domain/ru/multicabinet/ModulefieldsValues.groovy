package ru.multicabinet

class ModulefieldsValues {
	String name
	String value
	static belongsTo = [order : Order]
    static constraints = {
	    order(nullable : true)
    }
}
