pipeline {
    agent any
    tools {
        gradle 'gradle'
        git '2.39.1.windows.1'
    }
    options {
        buildDiscarder(logRotator(daysToKeepStr: '10', numToKeepStr: '10'))
        timeout(time: 12, unit: 'HOURS')
        timestamps()
    }
    triggers {
        pollSCM '*/5 * * * *'
    }
    stages {
        stage('Checkout') {
            steps { checkout scm }
        }
        stage('Test') {
            steps { bat 'gradle clean test' }
        }
        stage('Build') {
            steps { bat 'gradle build' }
        }
        stage('Code Coverage') {
            steps {
                bat 'gradlew clean'
                bat 'gradlew jacocoTestReport'
            }
        }
        stage('SonarQube Analyze') {
            environment {
                scannerHome = tool "sonar-scanner"
            }
            steps {
                withSonarQubeEnv("sonar-server") {
                    bat 'gradle clean sonar'
                }
            }
        }
        stage('Archive Artifacts') {
            steps {
                bat 'gradlew war'
                archiveArtifacts artifacts: 'build/libs/*.war', followSymlinks: false
            }
        }
        stage('Deploy') {
            steps {
                deploy adapters: [tomcat9(credentialsId: 'tomcat.admin', path: '', url: 'http://localhost:8181/')], contextPath: 'social-media-low-ai', onFailure: false, war: '**/*.war'
            }
        }
    }
}
