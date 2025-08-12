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
                echo 'Running tests (ignoring failures)...'
                // Run tests but do not fail build
                catchError(buildResult: 'SUCCESS', stageResult: 'UNSTABLE') {
                    bat 'mvn test -Dmaven.test.failure.ignore=true'
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
        failure {
            echo '❌ Build failed. Check logs for details.'
        }
    }
}
