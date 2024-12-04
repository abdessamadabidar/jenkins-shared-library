#!user/bin/env groovy

def call(String serviceName) {
    return new Maven(this).build(serviceName)
}