#!user/bin/env groovy

def call(String nexusHostname, String dockerRepositoryPort,String imageName, String imageVersion, String path) {
    return new Docker(this).buildImage(nexusHostname, dockerRepositoryPort, imageName, imageVersion, path)
}