#!/usr/bin/env groovy

def call(Map parameters = [:], Closure body={}) {

    def defaultLabel = buildId('binary')
    def _label = parameters.get('label', defaultLabel)

    binaryTemplate(parameters) {
        node(_label) {
            body()
        }
    }
}
