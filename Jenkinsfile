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
                echo 'Running tests (but not failing build if they fail)...'
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    bat 'mvn test'
                }
            }
        }
    }

    post {
        success {
            echo '✅ Build and Test completed (tests may have failed, but build succeeded).'
        }
        failure {
            echo '❌ Build failed. Check logs for details.'
        }
    }
}
