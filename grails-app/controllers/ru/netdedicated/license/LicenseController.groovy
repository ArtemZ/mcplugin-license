package ru.netdedicated.license

import grails.util.Environment
import net.netdedicated.domain.License
import net.netdedicated.license.BasicCredentials
import net.netdedicated.license.LicenseApi
import org.apache.shiro.SecurityUtils
import org.apache.shiro.authc.AuthenticationException
import org.apache.shiro.subject.Subject
import ru.netdedicated.GrailsUtils
import ru.multicabinet.OrderStatus

class LicenseController {
	def beforeInterceptor = [action: this.&checkUser, except: 'error']
	static def currentUser;
	def orderInstance;
	LicenseApi api;
	private checkUser() {
		Subject currentSubject = SecurityUtils.getSubject();
		try{
			switch(Environment.current){
				case Environment.PRODUCTION:
					if(currentSubject.getPrincipal() == null){
						throw new AuthenticationException("Not authorized!")
					} else {
						currentUser = GrailsUtils.getDomainClass("ShiroUser").findByUsername(currentSubject.getPrincipal().toString())
						if(!currentUser){
							throw new AuthenticationException("Current user not found")
						}
					}
					break
				default:
					if(currentSubject.getPrincipal() == null){
						currentUser =  GrailsUtils.getDomainClass("ShiroUser").get(1);
					} else {
						currentUser = GrailsUtils.getDomainClass("ShiroUser").findByUsername(currentSubject.getPrincipal().toString())
						if(!currentUser){
							throw new Exception("Current user not found")
						}
					}
			}
		} catch(AuthenticationException e){
			flash.message = "ERROR: " + e.getMessage()
			redirect(action : 'login', controller : 'auth')
			return false;
		}
		try {
			//setting orderInstance
			orderInstance = GrailsUtils.getDomainClass("Order").findByUserAndId(currentUser, params.id as Long)
			if(!orderInstance){
				throw new Exception("No order found with id " + params.id)
			}
			if(orderInstance.status != OrderStatus.ACTIVE){
				throw new Exception("This order is not active: " + orderInstance.status)
			}
			if(!orderInstance.serviceId || orderInstance.serviceId.trim().size() == 0){
				throw new Exception("This order has no service ID")
			}
		}catch(Exception e){
			redirect(action: "error", params: [error : e.getMessage()])
			//render(view : '../stylederror', model : [ error : e.getMessage() ])
			return false;
		}

		api = new LicenseApi(
				new BasicCredentials(PluginConfig.findByKey("licenseUsername").value, PluginConfig.findByKey("licensePassword").value),
				PluginConfig.findByKey("licenseUrl").value
		)
	} /* <-- end beforeInterceptor */

    def dashboard() {
	    License licenseInstance = api.getLicenseByIp(orderInstance.serviceId as String)
		[licenseInstance : licenseInstance, orderInstance : orderInstance]
    }
	def updateIp(String ip){
		License oldLicense = api.getLicenseByIp(orderInstance.serviceId as String)
		try {
			api.update(orderInstance.serviceId as String, ip, oldLicense.type);
			orderInstance.serviceId = ip
			orderInstance.save(flush : true, failOnError: true)
			flash.code = "success"
			flash.message = "License IP has been successfully updated"
		} catch (IOException e){
			flash.code = "error"
			flash.message = e.getMessage();
		}
		redirect(action: "dashboard", id : orderInstance.id)

	}

}
