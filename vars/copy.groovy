#!user/bin/env groovy

def copy(String ansibleHost) {
    return new Ansible(this).copy(ansibleHost);
}
