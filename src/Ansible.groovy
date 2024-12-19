#!user/bin/env groovy

class Ansible implements Serializable {

    def script

    Ansible(script) {
        this.script = script
    }

    def copy(String ansibleHost) {
        script.sshagent(['ansible-ssh-key']) {
            script.sh """
                ssh abdessamadabidar@${ansibleHost} 'mkdir -p ~/ansible_home ~/helm/helm-chart/'
            """
            script.sh """
                chmod u+x nexushost.sh
                chmod -R u+rwx ~/helm/helm-chart/
            """
            script.sh """
                scp -o StrictHostKeyChecking=no ansible/inventory_aws_ec2.yaml abdessamadabidar@${ansibleHost}:~/ansible_home/
                scp -o StrictHostKeyChecking=no ansible/ping-playbook.yaml abdessamadabidar@${ansibleHost}:~/ansible_home/
                scp -o StrictHostKeyChecking=no ansible/deployJar-playbook.yaml abdessamadabidar@${ansibleHost}:~/ansible_home/
                scp -o StrictHostKeyChecking=no ansible/.ansible.cfg abdessamadabidar@${ansibleHost}:~/
                scp -o StrictHostKeyChecking=no nexushost.sh abdessamadabidar@${ansibleHost}:~/
                scp -o StrictHostKeyChecking=no -r helm-chart/* abdessamadabidar@${ansibleHost}:~/helm/helm-chart/
            """
        }
    }

    def deploy(String ansibleHost, String nexusHost) {
        def remote = [:]
        remote.name = "ansible-server"
        remote.user = "abdessamadabidar"
        remote.host = "${ansibleHost}" as String
        remote.allowAnyHosts = true

        script.withCredentials([
                script.sshUserPrivateKey(credentialsId: "ansible-ssh-key", keyFileVariable: 'keyFile'),
                script.string(credentialsId: "GITHUB_PUBLIC_ACCESS_TOKEN", variable: 'GITHUB_TOKEN')
        ]) {
            remote.identityFile = keyFile
            script.sshCommand remote: remote, command: """
                ansible-playbook ~/ansible_home/deployJar-playbook.yaml --extra-vars "NexusHost=${nexusHost} GITHUB_PUBLIC_ACCESS_TOKEN=${GITHUB_TOKEN}"
            """
        }
    }
}
