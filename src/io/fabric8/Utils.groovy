#!/usr/bin/groovy
package io.fabric8


// helper to get the repo name from the job name when using org + branch github plugins
def getRepoName() {

    def jobName = env.JOB_NAME

    // job name from the org plugin
    if (jobName.count('/') > 1) {
        return jobName.substring(jobName.indexOf('/') + 1, jobName.lastIndexOf('/'))
    }
    // job name from the branch plugin
    if (jobName.count('/') > 0) {
        return jobName.substring(0, jobName.lastIndexOf('/'))
    }
    // normal job name
    return jobName
}
