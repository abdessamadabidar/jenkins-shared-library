#!user/bin/env groovy

def call(String imageName, String imageVersion, String path) {
    return new Docker(this).buildImage(imageName, imageVersion, path)
}