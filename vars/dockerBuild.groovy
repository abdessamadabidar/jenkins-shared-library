#!user/bin/env groovy

def call(String nexusHostname,String imageName, String imageVersion, String path) {
    return new Docker(this).buildImage(nexusHostname, imageName, imageVersion, path)
}