pipeline {
    agent any

    tools {
        maven 'Maven-3.9.6'   // Make sure Maven is installed and configured in Jenkins
        jdk 'Java-17'         // Your Jenkins must have Java 17 installed
    }

    stages {

        stage('Checkout') {
            steps {
                // Pull latest code from your GitHub repo
                git branch: 'master',
                    url: 'https://github.com/QUTUBZ/PRODUCTAPI.git'
            }
        }

        stage('Build') {
            steps {
                echo 'Building project...'
                sh 'mvn clean install -DskipTests'  // compile & package without running tests
            }
        }

        stage('Test') {
            steps {
                echo 'Running tests...'
                sh 'mvn test'  // run unit & integration tests
            }
        }

        stage('Deploy') {
            steps {
                echo 'Deploying application...'
                // Replace below with your real deployment command
                sh 'mvn deploy'
            }
        }
    }

    post {
        success {
            echo '✅ Build, Test, and Deploy completed successfully!'
        }
        failure {
            echo '❌ Build failed. Check logs for details.'
        }
    }
}
