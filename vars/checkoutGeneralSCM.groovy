#!/usr/bin/env groovy

def call(Map parameters = [:]) {

    def _browser = parameters.get('browser', 'auto')
    echo _browser
    def _url = parameters.get('url', scm.userRemoteConfigs[0].url)
    echo _url
    def _name = parameters.get('name') ? [[name: "*/${parameters.get('name')}"]] : scm.branches
    echo _name

    checkout(changelog: true, poll: true, scm: [
        $class: 'GitSCM',
        branches: "${_name}",
        // browser: [$class: 'GithubWeb', repoUrl: 'https://github.com'],
        // browser: [$class: 'GitLab', repoUrl: 'http://gitlab.hellotalk.com', version: '10.7'],
        doGenerateSubmoduleConfigurations: false,
        gitTool: 'git',
        extensions: scm.extensions + [
            [
                $class: 'CloneOption',
                // depth: 2147483647,
                // depth: 10000,
                honorRefspec: true,
                noTags: false,
                reference: '',
                shallow: true,
                timeout: 30
            ],
            [
                $class: 'LocalBranch',
                localBranch: '**'
                // localBranch: "${env.BRANCH_NAME}"
            ],
            [
                $class: 'GitTagMessageExtension'
            ],
            [
                $class: 'CleanBeforeCheckout'
            ],
            [
                $class: 'CleanCheckout'
            ]
        ],
        submoduleCfg: scm.submoduleCfg,
        userRemoteConfigs: [
            [
                credentialsId: scm.userRemoteConfigs[0].credentialsId,
                url: "${_url}"
                // name
                // refspec
            ]
        ]
    ])
}
