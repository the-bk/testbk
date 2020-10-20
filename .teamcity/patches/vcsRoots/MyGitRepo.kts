package patches.vcsRoots

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.ui.*
import jetbrains.buildServer.configs.kotlin.v2019_2.vcs.GitVcsRoot

/*
This patch script was generated by TeamCity on settings change in UI.
To apply the patch, change the vcsRoot with id = 'MyGitRepo'
accordingly, and delete the patch script.
*/
changeVcsRoot(RelativeId("MyGitRepo")) {
    val expected = GitVcsRoot({
        id("MyGitRepo")
        name = "huhu"
        url = "https://www.got.com"
        authMethod = password {
            userName = "Hello"
            password = "credentialsJSON:9b2z13a9-4ce5-431b-b5c5-cb67fc007bf4"
        }
    })

    check(this == expected) {
        "Unexpected VCS root settings"
    }

    (this as GitVcsRoot).apply {
        authMethod = password {
            userName = "Hello"
            password = "credentialsJSON:9bef65b4-fe14-447c-9aaa-907d157a0da8"
        }
    }

}