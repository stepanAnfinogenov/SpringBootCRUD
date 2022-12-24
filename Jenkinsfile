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
                sh 'docker build -t stepananfi/spring_boot_crud_app:latest .'
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
                sh 'docker push stepananfi/spring_boot_crud_app:latest'
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





// Template from Jenkins
// pipeline {
//     agent any
//
//     tools {
//         // Install the Maven version configured as "M3" and add it to the path.
//         maven "M3"
//     }
//
//     stages {
//         stage('Build') {
//             steps {
//                 // Get some code from a GitHub repository
//                 git 'https://github.com/jglick/simple-maven-project-with-tests.git'
//
//                 // Run Maven on a Unix agent.
//                 sh "mvn -Dmaven.test.failure.ignore=true clean package"
//
//                 // To run Maven on a Windows agent, use
//                 // bat "mvn -Dmaven.test.failure.ignore=true clean package"
//             }
//
//             post {
//                 // If Maven was able to run the tests, even if some of the test
//                 // failed, record the test results and archive the jar file.
//                 success {
//                     junit '**/target/surefire-reports/TEST-*.xml'
//                     archiveArtifacts 'target/*.jar'
//                 }
//             }
//         }
//     }
// }
