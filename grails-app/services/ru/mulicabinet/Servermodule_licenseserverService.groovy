package ru.mulicabinet

import net.netdedicated.license.BasicCredentials
import net.netdedicated.license.LicenseApi
import ru.netdedicated.GrailsUtils


class Servermodule_licenseserverService {
	static transactional = false
	Map info = [
			name : "licenseserver",
			desc : "Multicabinet License Server",
			version : 0.1
	]
	Map accountAccessFields = [
			licenseid : [ type : "password", desc : "License ID", required : true]
	]
	Map serverAccessFields = [
			licenseserverUrl : [type : "text", desc : "License Server URL", required : true],
			licenseserverLogin : [ type: "text", desc: "License Server Login", required: true],
			licenseserverPassword : [ type: "password", desc: "License Server Password", required: true]

	]
	Map accountDataFields = [
			licenseName : [ type: "text", desc: "License Name", required: true]
	]
	Map customUserFields = [
			licenseIp : [ type: "text", desc: "License IP", required: true]
	]
	String create(Map serverAccessData, Map accountAccessData, Map accountData, Map options) throws Exception {
		LicenseApi api = new LicenseApi(
				new BasicCredentials(serverAccessData.get("licenseserverLogin"), serverAccessData.get("licenseserverPassword")),
				serverAccessData.get("licenseserverUrl")
		)
		//find order
		def order = GrailsUtils.getDomainClass("OrderProps").findByKeyAndValue("licenseid", accountAccessData.get("licenseid"))?.order
		if(!order) throw new Exception("No OrderProps found for licenseid: " + accountAccessData.get("licenseid"));

		//find license ip
		def field = GrailsUtils.getDomainClass("ModulefieldsValues").findByNameAndOrder("licenseIp", order)
		if(!field) throw new Exception("Unable to find ModulefieldsValues for licenseIp and order #" + order.id);

		//create actual license
		api.create(accountData.get("licenseName"), field.value)
		return field.value
	}
	Boolean suspend(String serviceId, Map serverAccessData, Map accountAccessData){
		terminate(serviceId, serverAccessData, accountAccessData)
	}
	Boolean terminate(String serviceId, Map serverAccessData, Map accountAccessData) throws Exception{
		LicenseApi api = new LicenseApi(
				new BasicCredentials(serverAccessData.get("licenseserverLogin"), serverAccessData.get("licenseserverPassword")),
				serverAccessData.get("licenseserverUrl")
		)
		api.delete(serviceId)
		return true
	}
	/*
		Updating license type, not ip
	 */
	Boolean update(String serviceId, Map serverAccessData, Map accountAccessData, Map accountData){
		LicenseApi api = new LicenseApi(
				new BasicCredentials(serverAccessData.get("licenseserverLogin"), serverAccessData.get("licenseserverPassword")),
				serverAccessData.get("licenseserverUrl")
		)
		api.update(serviceId, serviceId, accountData.get("licenseName"))
		return true
	}
	Boolean unsuspend(String serviceId, Map serverAccessData, Map accountAccessData){
		LicenseApi api = new LicenseApi(
				new BasicCredentials(serverAccessData.get("licenseserverLogin"), serverAccessData.get("licenseserverPassword")),
				serverAccessData.get("licenseserverUrl")
		)
		def order = GrailsUtils.getDomainClass("Order").findByServiceId(serviceId);
		if(!order) throw new Exception("Unable to find order with service id: " + order.serviceId)
		def props = GrailsUtils.getDomainClass("PacketProps").findByPacketAndKey(order.packet, "licenseName");
		if (!props) throw new Exception("Unable to find PacketProps for packet #" + order.packet.id)
		api.create(props.value, serviceId)
		return true
	}
}
