import ru.multicabinet.Order
import ru.netdedicated.GrailsUtils
import ru.multicabinet.OrderStatus
import ru.multicabinet.ShiroRole
import ru.multicabinet.ShiroUser
import ru.netdedicated.license.PluginConfig

/**
 * Created with IntelliJ IDEA.
 * User: artemz
 * Date: 3/27/14
 * Time: 2:57 PM
 * To change this template use File | Settings | File Templates.
 */
class BootStrap {
	def init = { ctx ->
		if (ctx == null) throw new NullPointerException()
		//creating users and roles
		ShiroRole adminRole
		ShiroRole userRole
		if(!GrailsUtils.getDomainClass("ShiroRole").findByName("admin")){
			adminRole = new ShiroRole(name : "admin")
			adminRole.addToPermissions("*:*")
			adminRole.save()
		} else {
			adminRole = ShiroRole.findByName("admin")
		}
		if(!ShiroRole.findByName("user")){
			userRole = new ShiroRole(name : "user")
			userRole.addToPermissions("user:*")
			userRole.save()
		} else {
			userRole = ShiroRole.findByName("user")
		}

		ShiroUser user
		if(!(user = ShiroUser.findByUsername("admin")) ){
			user = new ShiroUser(username : "admin", passwordHash : "admin", email : "info@netdedicated.ru", sendNotify : false)
			user.addToRoles(adminRole)
			user.save()
		}

		Order order = new Order(serviceId : "192.168.100.1", user : user, status : OrderStatus.ACTIVE).save(failOnError: true)
		PluginConfig.findByKey("licenseUrl") ?: new PluginConfig(key: "licenseUrl", value: "http://localhost:8089").save(failOnError: true)
		PluginConfig.findByKey("licensePassword") ?: new PluginConfig(key: "licensePassword", value: "admin").save(failOnError: true)
		PluginConfig.findByKey("licenseUsername") ?: new PluginConfig(key: "licenseUsername", value: "admin").save(failOnError: true)

	}
	def destroy = {

	}
}
