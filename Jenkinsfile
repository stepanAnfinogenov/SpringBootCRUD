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

        stage('PushDockerHub') {
            steps {
                echo "-------------Start of stage Push-------------"
                sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
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

        stage('StopRunningAppOnServer') {
            steps {
                script{
                    echo "-------------Start of stage StopRunningAppOnServer-------------"
                    killRunningProject()
                    echo "-------------End of stage StopRunningAppOnServer-------------"
                }
            }
        }

        stage('DeployToServer') {
            steps {
                script{
                    echo "-------------Start of stage DeployServer-------------"
                    def server = "ubuntu@34.252.51.97"
                    def path = "/home/ubuntu/app"
                    def sshKey = "/keyAppServerIreland.pem"
                    def projectJar = "SpringBootCRUD-0.0.1-SNAPSHOT.jar"
                    deployJar(server, path, projectJar)
                    startJarOnServer()
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

def killRunningProject() {
    def processJava = sh (script: 'ssh -f -i /keyAppServerIreland.pem ubuntu@34.252.51.97 '+ "ps -ef | grep java", returnStdout:true)
    def numberJavaProcess = processJava.split('      ')[1].substring(0,4)
    sh 'ssh -f -i /keyAppServerIreland.pem ubuntu@34.252.51.97 '+ "kill -9 ${numberJavaProcess}"
}

def deployJar(def server, def path, def projectJar) {
    def deploy = sh (script: "scp -r -i /keyAppServerIreland.pem **/${projectJar} ${server}:${path}")
}

def startJarOnServer() {
    sh 'ssh -f -i /keyAppServerIreland.pem ubuntu@34.252.51.97 '+ "bash /home/ubuntu/app/runProject.sh"
}
