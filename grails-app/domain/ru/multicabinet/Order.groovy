package ru.multicabinet

class Order {
	static belongsTo = [user : ShiroUser]
	static hasMany = [props: OrderProps, modulefields : ModulefieldsValues]
	OrderStatus status = OrderStatus.PENDING
	String serviceId
	Date due
	Map packet = [ name : 'test']
	static constraints = {
		due nullable :true
	}
	static mapping = {
	    table 'Orders'

	}
}
