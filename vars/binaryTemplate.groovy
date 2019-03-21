#!/usr/bin/env groovy

def call(Map parameters = [:], Closure body={}) {

    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = parameters
    def defaultLabel = buildId('iOS')
    def _label = parameters.get('label', defaultLabel)

    body()
}
