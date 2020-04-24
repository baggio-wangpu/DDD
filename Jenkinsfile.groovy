pipeline {
    agent none
    environment {
        ARTIFACTORY_CREDS = credentials('ali-artifactory')
    }
    stages {
//        stage('OWASP Dependency Check') {
//            agent {
//                label 'master'
//            }
//            steps {
//                script {
//                    try {
//                        sh './gradlew dependencyCheckAnalyze'
//                    } finally {
//                        dependencyCheckPublisher pattern: 'build/reports/dependency-check-report.xml'
//                    }
//                }
//            }
//        }
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
                HOST = 'dev.beemaster.hello-bees.com'
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
                HOST = 'qa.beemaster.hello-bees.com'
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
                HOST = 'uat.beemaster.hello-bees.com'
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
                HOST = 'beemaster.hello-bees.com'
            }
            steps {
                sh './ci deploy'
            }
        }
    }
}
