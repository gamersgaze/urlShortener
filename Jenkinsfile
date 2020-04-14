import groovy.json.JsonSlurperClassic

node('master') {

    def workSpaceHome = pwd()
    def instanceObjKey
    def work="total-----------"
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
            cd /home
            ls
            echo $work
         """
         sh "cd /home"
         sh "ls"
        println("deployed succesfully......")
    }
}