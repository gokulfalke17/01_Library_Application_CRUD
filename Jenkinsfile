pipeline {
    agent any

    tools {
        jdk 'JDK17'
        maven 'Maven3'
    }

    environment {
        // This must match the SonarQube Scanner tool name configured in Jenkins
        SONAR_SCANNER_HOME = tool 'SonarScanner'
    }

    stages {

        stage('Checkout') {
            steps {
                echo 'Checkout from GitHub'
                git branch: 'main',
                    url: 'https://github.com/gokulfalke17/Library_Application_CRUD.git'
            }
        }

        stage('Build') {
            steps {
                echo 'Compiling the project'
                bat 'mvn clean compile'
            }
        }

        stage('Test') {
            steps {
                echo 'Running unit tests'
                bat 'mvn test'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                echo 'Running SonarQube code analysis'
                // Use SonarQube environment configured in Jenkins
                withSonarQubeEnv('SonarQube') {
                    bat 'mvn sonar:sonar'
                }
            }
        }
    }

    post {
        always {
            echo 'Publishing test results'
            junit 'target/surefire-reports/*.xml'
        }
    }
}
