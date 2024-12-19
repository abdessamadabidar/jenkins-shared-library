#!user/bin/env groovy

def deploy(String ansibleHost, String nexusHost) {
    return new Ansible(this).deploy(ansibleHost, nexusHost);
}