import groovy.json.JsonSlurperClassic
/*
     jira : 7|8
      env :dev|qa|preprod|prod|dc
  database: mysql|postgres|mssql|oracle
*/
node('master') {
    def workSpaceHome = pwd()
    def instanceObjKey

    def QA="qa"
    def DEV="dev"
    def PREPROD="preprod"
    def PROD="prod"
    def DC="dc"

    def jira=""
    def configData = load "config.groovy"

    stage('Clean') {
        deleteDir()
    }

    stage('Checkout') {
        checkout scm
    }

    stage('Run') {
    withMaven(jdk: 'JDK', maven: 'maven3', mavenLocalRepo: '', mavenOpts: '', mavenSettingsFilePath: '/opt/qtmserverdependency/settings.xml') {
            if(configData.env.equalsIgnoreCase("NA"){
                sh "atlas-package -DskipTests -DdevToolboxEnabled=false -DquickReload=false"
                sh "atlas-package -DskipTests -Pjira7 -DdevToolboxEnabled=false -DquickReload=false"
            }else{
                instanceObjKey=configData.env
                if(configData.jira.equals("7") {
                   instanceObjKey=instanceObjKey+".j7."
                   jira="-Pjira7"
                }else{
                   instanceObjKey=instanceObjKey+".j8."
                }

                if(configData.env.equals(DEV) || configData.env.equals(QA) ||configData.env.equals(DC)){
                    sh "atlas-package -DskipTests $jira '-Dlicense.check=false' -DdevToolboxEnabled=false -DquickReload=false"
                }else{
                    sh "atlas-package -DskipTests $jira '-Dlicense.check=true' -DdevToolboxEnabled=false -DquickReload=false"
                }
            }
       }
    }

    stage('Deploy') {

        if(configData.env.equalsIgnoreCase("NA"){
            return;
        }
        def instanceFile = new File(workSpaceHome+'/deployment/instances.json')
        def instanceJSON = new JsonSlurperClassic().parse(instanceFile)

        def dbs=configData.database.split(",");
        def appFile = "qtm4jserver-to-deploy.jar"

        sh """
            cd target/
            cp qtm4jserver-*.jar ../deployment/qtm4jserver-to-deploy.jar
           """
        for(String db : dbs){
            instanceObjKey=instanceObjKey+db
            def baseUrl = instanceJSON."$instanceObjKey".baseUrl
            def username = instanceJSON."$instanceObjKey".username
            def password = instanceJSON."$instanceObjKey".password

            sh """
                cd deployment/
                java -jar installer.jar $username,$password,$baseUrl,$appFile
             """
         }
    }
}