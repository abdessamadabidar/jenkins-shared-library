#!user/bin/env groovy

class Maven implements Serializable {

    def script

    Maven(script) {
        this.script = script
    }


    def build(String serviceName) {
        script.sh "mvn -f /var/jenkins_home/workspace/shopino-microservices/config-service/services/${serviceName} clean package"
    }
}
