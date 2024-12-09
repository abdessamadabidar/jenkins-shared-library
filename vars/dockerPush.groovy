#!user/bin/env groovy

def call(String nexusHostname, String dockerRepositoryPort, String imageName, String imageVersion) {
    return new Docker(this).pushImage(nexusHostname, dockerRepositoryPort, imageName, imageVersion)
}