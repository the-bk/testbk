import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildFeatures.perfmon
import jetbrains.buildServer.configs.kotlin.buildSteps.maven
import jetbrains.buildServer.configs.kotlin.triggers.vcs

/*
The settings script is an entry point for defining a TeamCity
project hierarchy. The script should contain a single call to the
project() function with a Project instance or an init function as
an argument.

VcsRoots, BuildTypes, Templates, and subprojects can be
registered inside the project using the vcsRoot(), buildType(),
template(), and subProject() methods respectively.

To debug settings scripts in command-line, run the

    mvnDebug org.jetbrains.teamcity:teamcity-configs-maven-plugin:generate

command and attach your debugger to the port 8000.

To debug in IntelliJ Idea, open the 'Maven Projects' tool window (View
-> Tool Windows -> Maven Projects), find the generate task node
(Plugins -> teamcity-configs -> teamcity-configs:generate), the
'Debug' option is available in the context menu for the task.
*/

version = "2023.05"

project {
    val bts = sequential {
        buildType(Maven("Build","clean compile"))
        parrallel{
            buildType(Maven("Fast Test","clean test","-Dmaven.test.failure.ignore=true -Dtest=*.unit.*Test"))
            buildType(Maven("Slow Test","clean test","-Dmaven.test.failure.ignore=true -Dtest=*.integration.*Test"))
        }
        buildType(Maven("Package","clean package","-Dmaven.test.failure.ignore=true"))
    }.buildSteps()

    bts.forEach{ buildType(it) }
    bts.last().triggers {
        vcs {

        }
    }
}

class Maven(name: String, goals: Strings, runnerArgs: String? = null) : BuildType {
    this.name = name

    vcs {
        root(DslContext.settingsRoot)
    }

    steps {
        maven {
            this.goals = goals
            this.runnerArgs = runnerArgs
        }
    }    
}

// object Build : BuildType({
//     name = "Build"

//     vcs {
//         root(DslContext.settingsRoot)
//     }

//     steps {
//         maven {
//             goals = "clean compile"
//             runnerArgs = "-Dmaven.test.failure.ignore=true"
//         }
//     }
// })

// object FastTest : BuildType({
//     name = "Fast Test"

//     vcs {
//         root(DslContext.settingsRoot)
//     }

//     steps {
//         maven {
//             goals = "clean test"
//             runnerArgs = "-Dmaven.test.failure.ignore=true -Dtest=*.unit.*Test"
//         }
//     }
// })

// object SlowTest : BuildType({
//     name = "Slow Test"

//     vcs {
//         root(DslContext.settingsRoot)
//     }

//     steps {
//         maven {
//             goals = "clean test"
//             runnerArgs = "-Dmaven.test.failure.ignore=true -Dtest=*.integration.*Test"
//         }
//     }
// })

// object Package : BuildType({
//     name = "Package"

//     vcs {
//         root(DslContext.settingsRoot)
//     }

//     steps {
//         maven {
//             goals = "clean package"
//             runnerArgs = "-Dmaven.test.failure.ignore=true"
//         }
//     }

//     triggers {
//         vcs {
//         }
//     }
// })