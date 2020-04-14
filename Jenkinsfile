import groovy.json.JsonSlurperClassic

node('master') {

    def workSpaceHome = pwd()
    def instanceObjKey
    stage('Clean') {
        deleteDir()
    }
    environment {
        LS = "loooooooooooooooooooooooooooooooooooooooooooooop"
    }
    stage('Checkout') {
        checkout scm
    }

    stage('Run') {

    }

    stage('Deploy') {

        sh """
            echo "seee---"
            echo "${LS}"
         """
        println("deployed succesfully......")
    }
}