import groovy.json.JsonSlurperClassic

node('master') {

    def workSpaceHome = pwd()
    def instanceObjKey
    def config=load "config.groovy"

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


        println(config.database);
        println(config.jira);

        if(1==1){
            return;
        }

        def req="main";

        sh  """
           cd src/$req
           ls
        """
        req="";

        sh  """
           cd src/$req
           ls
        """
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