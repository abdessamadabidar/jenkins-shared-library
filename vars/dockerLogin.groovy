#!user/bin/env groovy

def call(String nexusHostname) {
    return new Docker(this).dockerLogin(nexusHostname)
}