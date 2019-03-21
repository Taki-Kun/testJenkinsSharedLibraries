#!/usr/bin/env groovy

def call(Map parameters = [:], Closure body={}) {

    def defaultLabel = buildId('binary')
    def _label = parameters.get('label', defaultLabel)

    pipeline {
        agent none

        stages {
            stage('Test') {
                agent {
                    node {
                        label "${_label}"
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
