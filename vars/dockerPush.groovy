#!user/bin/env groovy

def call(String nexusHostname, String imageName, String imageVersion) {
    return new Docker(this).pushImage(nexusHostname, imageName, imageVersion)
}