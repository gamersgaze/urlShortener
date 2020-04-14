import groovy.json.JsonSlurperClassic

node('master') {

    def workSpaceHome = pwd()
    def instanceObjKey
    stage('Clean') {
        deleteDir()
    }
    environment {
        tops = "loooooooooooooooooooooooooooooooooooooooooooooop"
    }
    stage('Checkout') {
        checkout scm
    }

    stage('Run') {

    }

    stage('Deploy') {

        sh """
            echo "seee---"
         """
         sh "cd /home"
         sh "ls"
        println("deployed succesfully......")
    }
}