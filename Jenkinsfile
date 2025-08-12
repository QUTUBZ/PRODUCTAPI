pipeline {
    agent any

    tools {
        maven 'Maven-3.9.6'
        jdk 'Java-17'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'master', url: 'https://github.com/QUTUBZ/PRODUCTAPI.git'
            }
        }

        stage('Build') {
            steps {
                echo 'Building project...'
                bat 'mvn clean install -DskipTests'
            }
        }

        stage('Test') {
            steps {
                catchError(buildResult: 'UNSTABLE', stageResult: 'FAILURE') {
                    echo 'Running tests...'
                    bat 'mvn test'
                }
            }
        }

        stage('Deploy') {
            steps {
                echo 'Deploying application...'
                bat 'mvn deploy'
            }
        }
    }

    post {
        success {
            echo '✅ Build, Test, and Deploy completed successfully!'
        }
        unstable {
            echo '⚠️ Build is unstable due to failed tests, but deployment was done.'
        }
        failure {
            echo '❌ Build failed. Check logs for details.'
        }
    }
}
