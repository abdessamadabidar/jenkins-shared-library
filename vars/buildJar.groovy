#!user/bin/env groovy

def call(String directory) {
    return new Maven(this).build(directory)
}