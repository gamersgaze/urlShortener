import groovy.json.JsonSlurperClassic

node('master') {

    def workSpaceHome = pwd()
    def instanceObjKey
    def work="total-----------"
    def birds = new String[3]
    		birds[0] = "Parrot"
    		birds[1] = "Cockatiel"
    		birds[2] = "Pigeon"

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

            for bbb in $birds
            do

                echo \${bbb}
            done

         """
         sh "cd /home"
         sh "ls"
        println("deployed succesfully......")
    }
}