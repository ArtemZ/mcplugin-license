import ru.netdedicated.GAHolder

class McpluginLicenseGrailsPlugin {
    // the plugin version
    def version = "0.4.2"
    // the version or versions of Grails the plugin is designed for
    def grailsVersion = "2.2 > *"
	def configurationClass = "ru.netdedicated.license.PluginConfig"
    // resources that are excluded from plugin packaging
	def pluginExcludes = [
			"grails-app/views/error.gsp",
			"grails-app/domain/ru/multicabinet/Order.groovy",
			"grails-app/domain/ru/multicabinet/OrderProps.groovy",
			"grails-app/domain/ru/multicabinet/Packet.groovy",
			"grails-app/domain/ru/multicabinet/PacketProps.groovy",
			"grails-app/domain/ru/multicabinet/ShiroRole.groovy",
			"grails-app/domain/ru/multicabinet/ShiroUser.groovy",
            "grails-app/domain/ru/multicabinet/UserProps.groovy",
			"grails-app/domain/ru/multicabinet/ModulefieldsValues.groovy",
			"grails-app/domain/ru/multicabinet/Server.groovy",
			"grails-app/domain/ru/multicabinet/ServerProps.groovy",
			//"src/groovy/ru/multicabinet/OrderStatus.groovy"
	]
	def pluginControllers = [
			"license"
	]
    // TODO Fill in these fields
    def title = "Licensing plugin for Multicabinet" // Headline display name of the plugin
    def author = "Artem Zhirkov"
    def authorEmail = "az@develdynamic.com"
    def description = '''\
Multicabinet plugin for netdedicated license server.
'''

    // URL to the plugin's documentation
    def documentation = "http://grails.org/plugin/mc-license"
	def groupId="ru.netdedicated"

	// Extra (optional) plugin metadata

    // License: one of 'APACHE', 'GPL2', 'GPL3'
//    def license = "APACHE"

    // Details of company behind the plugin (if there is one)
    def organization = [ name: "Netdedicated Solutions", url: "http://netdedicated.net" ]

    // Any additional developers beyond the author specified above.
//    def developers = [ [ name: "Joe Bloggs", email: "joe@bloggs.net" ]]

    // Location of the plugin's issue tracker.
//    def issueManagement = [ system: "JIRA", url: "http://jira.grails.org/browse/GPMYPLUGIN" ]

    // Online location of the plugin's browseable source code.
//    def scm = [ url: "http://svn.codehaus.org/grails-plugins/" ]

    def doWithWebDescriptor = { xml ->
        // TODO Implement additions to web.xml (optional), this event occurs before
    }

    def doWithSpring = {
	    GAHolder.setGrailsApplication(application)
        // TODO Implement runtime spring config (optional)
    }

    def doWithDynamicMethods = { ctx ->
        // TODO Implement registering dynamic methods to classes (optional)
    }

    def doWithApplicationContext = { applicationContext ->
        // TODO Implement post initialization spring config (optional)
    }

    def onChange = { event ->
        // TODO Implement code that is executed when any artefact that this plugin is
        // watching is modified and reloaded. The event contains: event.source,
        // event.application, event.manager, event.ctx, and event.plugin.
    }

    def onConfigChange = { event ->
        // TODO Implement code that is executed when the project configuration changes.
        // The event is the same as for 'onChange'.
    }

    def onShutdown = { event ->
        // TODO Implement code that is executed when the application shuts down (optional)
    }
}
