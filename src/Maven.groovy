#!user/bin/env groovy

class Maven implements Serializable {

    def script

    Maven(script) {
        this.script = script
    }


    def build() {
        script.sh 'mvn clean package'
    }
}
