#!user/bin/env groovy

def call(String nexusHostname, String dockerRepositoryPort) {
    return new Docker(this).dockerLogin(nexusHostname, dockerRepositoryPort)
}