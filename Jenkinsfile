pipeline {
    agent any

    tools {
        jdk 'JDK17'
        maven 'Maven3'
    }

    environment {
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
               withSonarQubeEnv('SonarQube') {
                   bat 'mvn clean verify sonar:sonar'
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
