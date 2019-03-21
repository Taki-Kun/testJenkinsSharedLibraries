#!/usr/bin/env groovy

def call(Map parameters = [:], Closure body={}) {

    def defaultLabel = buildId('iOS')
    echo defaultLabel
    def _label = parameters.get('label', defaultLabel)
    echo _label

    pipeline {
        agent none

        stages {
            stage('Test') {
                agent {
                    node {
                        label _label
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
