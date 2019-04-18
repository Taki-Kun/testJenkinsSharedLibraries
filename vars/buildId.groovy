#!/usr/bin/groovy

import io.issenn.environment.EnvironmentConstants
import io.fabric8.Utils

/**
 * Returns the id of the build, which consists of the job name, build number and an optional prefix.
 * @param prefix The prefix to use, defaults in empty string.
 * @return
 */
def call(String prefix = '') {
    // def repo = EnvironmentConstants.GIT_BRANCH
    def repo = new Utils().getRepoName()
    return "${prefix}${repo}_${env.BUILD_NUMBER}".replaceAll('-', '_').replaceAll('/', '_').replaceAll(' ', '_')
}
