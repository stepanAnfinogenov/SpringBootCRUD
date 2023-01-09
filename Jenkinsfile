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
                sh 'docker build -t stepananfi/spring_boot_crud_app:'+"BUILD_NUMBER-${env.BUILD_NUMBER}" + ' .'
                echo "-------------End of stage Build-------------"
            }
        }
        stage('Login') {
            steps {
                echo "-------------Start of stage Login-------------"
                sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
                echo "-------------End of stage Login-------------"
            }
        }
        stage('Push') {
            steps {
                echo "-------------Start of stage Push-------------"
                sh 'docker push stepananfi/spring_boot_crud_app:'+"BUILD_NUMBER-${env.BUILD_NUMBER}"
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
        stage('Deploy') {
            steps {
                echo "-------------Start of stage Deploy-------------"
                echo "Deploying"
                echo "-------------End of stage Deploy-------------"
            }
        }
    }
    post {
        always {
            sh 'docker logout'
        }
    }
}
