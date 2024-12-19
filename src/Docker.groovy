#!user/bin/env groovy

class Docker implements Serializable {

    def script

    Docker(script) {
        this.script = script
    }


    def dockerLogin(String nexusHostname, String dockerRepositoryPort) {
        script.withCredentials([
                script.usernamePassword(credentialsId: 'nexus-credentials', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')
        ]) {
            script.sh "echo '${script.PASSWORD}' | docker login -u '${script.USERNAME}' --password-stdin ${nexusHostname}:${dockerRepositoryPort}"
        }
    }

    def buildImage(String nexusHostname, String dockerRepositoryPort, String imageName, String imageVersion, String path) {
        script.sh "docker build -t ${nexusHostname}:${dockerRepositoryPort}/${imageName}:${imageVersion} ${path}"
    }

    def pushImage(String nexusHostname, String dockerRepositoryPort, String imageName, String imageVersion) {
        script.sh "docker push ${nexusHostname}:${dockerRepositoryPort}/${imageName}:${imageVersion}"
    }
}
