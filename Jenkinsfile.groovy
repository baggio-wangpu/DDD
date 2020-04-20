pipeline {
    agent none
    environment {
        ARTIFACTORY_CREDS = credentials('ali-artifactory')
    }
    stages {
        stage('MySQL Test') {
            agent {
                label 'master'
            }
            steps {
                script {
                    try {
                        withSonarQubeEnv('sonar') {
                            sh './ci testMysql'
                        }
                    } finally {
                        publishHTML(target: [
                                allowMissing         : false,
                                alwaysLinkToLastBuild: false,
                                keepAll              : false,
                                reportDir            : 'build/reports/tests/test',
                                reportFiles          : 'index.html',
                                reportName           : "Test Report"
                        ])
                    }
                }
            }
        }
        stage('PostgreSQL Test') {
            agent {
                label 'master'
            }
            steps {
                script {
                    try {
                        sh './ci testPG'
                    } finally {
                        sh './ci closePG'
                    }
                }
            }
        }
        stage('Build Image') {
            agent {
                label 'master'
            }
            steps {
                sh './ci build'
            }
        }
        stage('Deploy DEV') {
            agent {
                label 'master'
            }
            environment {
                ENV = 'dev'
                NAMESPACE = 'gaia-dev'
                BEEART_HOST = 'dev.beeart.hello-bees.com'
                GAIA_HOST = 'dev.gaiafuture.cn'
            }
            steps {
                sh './ci deploy'
            }
        }
        stage('Approve of Deploy QA') {
            steps {
                input message: 'deploy to QA?'
            }
        }
        stage('Deploy QA') {
            agent {
                label 'master'
            }
            environment {
                ENV = 'qa'
                NAMESPACE = 'gaia-qa'
                BEEART_HOST = 'qa.beeart.hello-bees.com'
                GAIA_HOST = 'qa.gaiafuture.cn'
            }
            steps {
                sh './ci deploy'
            }
        }
        stage('Approve of Deploy UAT') {
            steps {
                input message: 'deploy to UAT?'
            }
        }
        stage('Deploy UAT') {
            agent {
                label 'master'
            }
            environment {
                ENV = 'uat'
                NAMESPACE = 'gaia-uat'
                BEEART_HOST = 'uat.beeart.hello-bees.com'
                GAIA_HOST = 'uat.gaiafuture.cn'
            }
            steps {
                sh './ci deploy'
            }
        }
        stage('Approve of Deploy PROD') {
            steps {
                input message: 'deploy to PROD?'
            }
        }
        stage('Deploy PROD') {
            agent {
                label 'master'
            }
            environment {
                ENV = 'prod'
                NAMESPACE = 'gaia-prod'
                BEEART_HOST = 'beeart.hello-bees.com'
                GAIA_HOST = 'www.gaiafuture.cn'
            }
            steps {
                sh './ci deploy'
            }
        }
    }
}
