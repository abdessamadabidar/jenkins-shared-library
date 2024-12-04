class Docker implements Serializable {

    def script

    Docker(script) {
        this.script = script
    }


    def dockerLogin() {
        script.withCredentials([
                script.usernamePassword(credentialsId: 'dockerhub-credentials', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')
        ]) {
            script.sh "echo '${script.PASSWORD}' | docker login -u '${script.USERNAME}' --password-stdin"
        }
    }
}
