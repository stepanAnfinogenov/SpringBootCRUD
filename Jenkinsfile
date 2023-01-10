pipeline {
    agent any

    environment {
        DOCKERHUB_CREDENTIALS = credentials('stepananfi-dockerhubAccess')
    }

    tools {
        maven "maven-3"
    }

    stages {
        stage('Build') {
            steps {
                echo "-------------Start of stage Build-------------"
                sh 'mvn clean package'
                sh 'docker build -t stepananfi/spring_boot_crud_app:'+"BuildNumber-${env.BUILD_NUMBER}" + ' .'
                echo "-------------End of stage Build-------------"
            }
        }
        stage('LoginDockerHub') {
            steps {
                echo "-------------Start of stage Login-------------"
                sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
                echo "-------------End of stage Login-------------"
            }
        }
        stage('PushDockerHub') {
            steps {
                echo "-------------Start of stage Push-------------"
                sh 'docker push stepananfi/spring_boot_crud_app:'+"BuildNumber-${env.BUILD_NUMBER}"
                echo "-------------End of stage Push-------------"
            }
        }
        stage('Test') {
            steps {
                echo "-------------Start of stage Test-------------"
                echo "Testing"
                echo "-------------End of stage Test-------------"
            }
        }

        stage('StopRunningApp') {
            steps {
                script{
                    echo "-------------Start of stage StopRunningApp-------------"
                    killRunningProject()
                    echo "-------------End of stage StopRunningApp-------------"
                }
            }
        }
        stage('DeployServer') {
            steps {
                script{
                    echo "-------------Start of stage DeployServer-------------"
                    def server = "ubuntu@34.252.51.97"
                    def path = "/home/ubuntu/app"
                    def sshKey = "/keyAppServerIreland.pem"
                    def projectJar = "SpringBootCRUD-0.0.1-SNAPSHOT.jar"
                    deployJar(server, path, projectJar)
                    startJarOnServer()
//                     sh 'ssh -f -i /keyAppServerIreland.pem ubuntu@34.252.51.97 '+ "bash /home/ubuntu/app/runProject.sh"
                    echo "-------------End of stage DeployServer-------------"
                }
            }
        }
    }
    post {
        always {
            sh 'docker logout'
        }
    }
}
def deployJar(def server, def path, def projectJar) {
    def deploy = sh (script: "scp -r -i /keyAppServerIreland.pem **/${projectJar} ${server}:${path}")
}
def startJarOnServer() {
    sh 'ssh -f -i /keyAppServerIreland.pem ubuntu@34.252.51.97 '+ "bash /home/ubuntu/app/runProject.sh"
}
def killRunningProject() {
    def processJava = sh (script: 'ssh -f -i /keyAppServerIreland.pem ubuntu@34.252.51.97 '+ "ps -ef | grep java", returnStdout:true)
    def numberJavaProcess = processJava.split('      ')[1].substring(0,4)
    sh 'ssh -f -i /keyAppServerIreland.pem ubuntu@34.252.51.97 '+ "kill -9 ${numberJavaProcess}"
}