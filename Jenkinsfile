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

    stage('Checkout') {
        checkout scm
    }

    stage('Run') {

    }

    stage('Deploy'){
        //def config=load "${workSpaceHome}@script/config.Groovy"
        def config=load "config.groovy"
        println(config.database);
        println(config.jira);

    //println(config.jira);

/*
     for(String bbb:birds){
        sh """
          cd /home
          echo $bbb
        """
     }
*/
        println("deployed succesfully......")
    }
}