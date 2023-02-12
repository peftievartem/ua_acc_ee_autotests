String cron_string = BRANCH_NAME == "master" ? "0 2 * * 1-5" : ""
pipeline {
    agent any


    triggers {
        cron(cron_string)
    }

    stages {
        stage('Init') {
            steps {
                echo 'Starting!'
                }
            }
        stage('download selenium') {
            steps {
                sh 'curl -O http://selenium-release.storage.googleapis.com/3.5/selenium-server-standalone-3.5.3.jar'
                sh 'java -jar selenium-server-standalone-3.5.3.jar -log selenium.log &'
            }
        }
        stage('mvn dependency') {
            steps {
                sh 'mvn dependency:go-offline'
            }
        }
        stage('test') {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    sh 'mvn clean test'
                }
            }
        }
        stage('reports') {
            steps {
                script {
                    allure([
                            includeProperties: false,
                            jdk: '',
                            properties: [],
                            reportBuildPolicy: 'ALWAYS',
                            results: [[path: 'target/allure-results']]
                    ])
                }
            }
        }
    }
}
