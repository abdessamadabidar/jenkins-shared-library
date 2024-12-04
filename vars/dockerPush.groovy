#!user/bin/env groovy

def call(String imageName, String imageVersion) {
    return new Docker(this).pushImage(imageName, imageVersion)
}