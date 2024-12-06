#!user/bin/env groovy

class Maven implements Serializable {

    def script

    Maven(script) {
        this.script = script
    }


    def build(String directory) {
        script.sh "mvn -f services/${directory} clean package -DskipTests"
    }

    def install(String directory) {
        script.sh "mvn -f services/${directory} clean install -DskipTests"
    }
}
