pipeline {
    agent any

    tools {
        maven 'Maven-3.9.6'   // Must match the Maven name in Jenkins Global Tool Configuration
        jdk 'Java-17'         // Must match the JDK name in Jenkins Global Tool Configuration
    }

    stages {

        stage('Checkout') {
            steps {
                // Pull latest code from your GitHub repo
                git branch: 'master', url: 'https://github.com/QUTUBZ/PRODUCTAPI.git'
            }
        }

        stage('Build') {
            steps {
                echo 'Building project...'
                sh 'mvn clean install -DskipTests'
            }
        }

        stage('Test') {
            steps {
                echo 'Running tests...'
                sh 'mvn test'
            }
        }

        stage('Deploy') {
            steps {
                echo 'Deploying application...'
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
