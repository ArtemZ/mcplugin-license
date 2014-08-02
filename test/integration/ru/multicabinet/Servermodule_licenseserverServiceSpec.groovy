package ru.multicabinet

import ru.mulicabinet.Servermodule_licenseserverService
import ru.multicabinet.module.server.ServerModuleData
import spock.lang.*

/**
 *
 */
class Servermodule_licenseserverServiceSpec extends Specification {
    Servermodule_licenseserverService servermodule_licenseserverService
    String licenserServerUrl, licenserServerUsername, licenserServerPassword
    def setup() {
        servermodule_licenseserverService = new Servermodule_licenseserverService()
        licenserServerUrl = "http://dns.netdedicated.ru:8080"
        licenserServerPassword = "admin"
        licenserServerUsername = "admin"
    }

    def cleanup() {
    }

    void "test creating a new license"() {
        setup:
            ServerModuleData data = new ServerModuleData()
            data.setServerAccessValue("licenseserverUrl", licenserServerUrl)
            data.setServerAccessValue("licenseserverLogin", licenserServerUsername)
            data.setServerAccessValue("licenseserverPassword", licenserServerPassword)
            data.setAccountField("licenseName", "testlicense")
            data.setCustomField("licenseIp", "94.242.222.22")
        when:
            String result = servermodule_licenseserverService.create(data)
        then:
            assert  result == "94.242.222.22"
    }
    void "test updating license type"(){
        setup:
           ServerModuleData data = new ServerModuleData()
           data.setServerAccessValue("licenseserverUrl", licenserServerUrl)
           data.setServerAccessValue("licenseserverLogin", licenserServerUsername)
           data.setServerAccessValue("licenseserverPassword", licenserServerPassword)
           data.setAccountField("licenseName", "updatedlicense")
           data.setCustomField("licenseIp", "94.242.222.22")
        when:
            servermodule_licenseserverService.update("94.242.222.22", data)
        then:
            notThrown(Exception)
    }
    void "test deleting license"(){
        setup:
            ServerModuleData data = new ServerModuleData()
            data.setServerAccessValue("licenseserverUrl", licenserServerUrl)
            data.setServerAccessValue("licenseserverLogin", licenserServerUsername)
            data.setServerAccessValue("licenseserverPassword", licenserServerPassword)
            data.setAccountField("licenseName", "updatedlicense")
            data.setCustomField("licenseIp", "94.242.222.22")
        when:
            servermodule_licenseserverService.terminate("94.242.222.22", data)
        then:
            notThrown(Exception)
    }
}
