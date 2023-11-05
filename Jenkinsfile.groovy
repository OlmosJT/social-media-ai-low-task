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
    }
}
