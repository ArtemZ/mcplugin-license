package ru.mulicabinet

import net.netdedicated.license.BasicCredentials
import net.netdedicated.license.LicenseApi
import ru.multicabinet.module.server.ServerModuleData
import ru.netdedicated.GrailsUtils


class Servermodule_licenseserverService {
	static transactional = false
	Map info = [
			name : "licenseserver",
			desc : "Multicabinet License Server",
			version : 0.1
	]
	Map accountAccessFields = [
			password : [ type : "password", desc : "License ID", required : true, force : true]
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
	String create(ServerModuleData data) throws Exception {
		LicenseApi api = new LicenseApi(
				new BasicCredentials(data.getServerAccessValue("licenseserverLogin"), data.getServerAccessValue("licenseserverPassword")),
				data.getServerAccessValue("licenseserverUrl")
		)
		/*//find order
		def order = GrailsUtils.getDomainClass("OrderProps").findByKeyAndValue("password", accountAccessData.get("password"))?.order
		if(!order) throw new Exception("No OrderProps found for licenseid: " + accountAccessData.get("password"));

		//find license ip
		def field = GrailsUtils.getDomainClass("ModulefieldsValues").findByNameAndOrder("licenseIp", order)
		if(!field) throw new Exception("Unable to find ModulefieldsValues for licenseIp and order #" + order.id);*/

		//create actual license
		api.create(data.getAccountField("licenseName"), data.getCustomField("licenseIp"))
		return data.getCustomField("licenseIp")
	}
	Boolean suspend(String serviceId, ServerModuleData data) throws Exception{
		terminate(serviceId, data)
	}
	Boolean terminate(String serviceId, ServerModuleData data) throws Exception{
		LicenseApi api = new LicenseApi(
				new BasicCredentials(data.getServerAccessValue("licenseserverLogin"), data.getServerAccessValue("licenseserverPassword")),
				data.getServerAccessValue("licenseserverUrl")
		)
		api.delete(serviceId)
		return true
	}
	/*
		Updating license type, not ip
	 */
	Boolean update(String serviceId, ServerModuleData data) throws Exception{
		LicenseApi api = new LicenseApi(
				new BasicCredentials(data.getServerAccessValue("licenseserverLogin"), data.getServerAccessValue("licenseserverPassword")),
				data.getServerAccessValue("licenseserverUrl")
		)
		api.update(serviceId, serviceId, data.getAccountField("licenseName"))
		return true
	}
	Boolean unsuspend(String serviceId, ServerModuleData data) throws Exception{
		LicenseApi api = new LicenseApi(
				new BasicCredentials(data.getServerAccessValue("licenseserverLogin"), data.getServerAccessValue("licenseserverPassword")),
				data.getServerAccessValue("licenseserverUrl")
		)
		/*def order = GrailsUtils.getDomainClass("Order").findByServiceId(serviceId);
		if(!order) throw new Exception("Unable to find order with service id: " + order.serviceId)
		def props = GrailsUtils.getDomainClass("PacketProps").findByPacketAndKey(order.packet, "licenseName");
		if (!props) throw new Exception("Unable to find PacketProps for packet #" + order.packet.id)*/
		/*api.create(props.value, serviceId)*/
        api.create(data.getAccountField("licenseName"), serviceId)
		return true
	}
}
