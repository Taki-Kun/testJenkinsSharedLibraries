#!/usr/bin/env groovy

def call(Map parameters = [:], body) {

    def defaultLabel = buildId('clients')
    echo defaultLabel
    // def label = parameters.get('label', defaultLabel)

    pipeline {
        agent none

        stages {
            stage('Test') {
                agent {
                    node {
                        label 'master'
                        customWorkspace "workspace/${JOB_NAME.replace('%2F', '/')}"
                    }
                }
                when {
                    beforeAgent true
                    branch "master"
                }
                steps {
                    sh "echo test."
                }
            }
        }
    }
}
