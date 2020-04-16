import groovy.json.JsonSlurperClassic

node('master') {
    def workSpaceHome = pwd()

    def configData
    def jira7Jar="QmetryTestManager-4.0.0_jira7.jar"
    def jira8Jar="QmetryTestManager-4.0.0.jar"

    stage('Clean') {
        deleteDir()
    }

    stage('Checkout') {
        checkout scm
    }

    stage('Run') {

            configData = load "config.groovy"
            def licenseCheck="-Dlicense.check=false"
            if(configData.license.equals(true)){
                licenseCheck="-Dlicense.check=true"
            }
            println( "atlas-package -DskipTests $licenseCheck -DdevToolboxEnabled=false -DquickReload=false")
            println( "atlas-package -DskipTests $licenseCheck -Pjira7 -DdevToolboxEnabled=false -DquickReload=false")

    }

    stage('Deploy') {

        if(configData.autoDeploy.equalsIgnoreCase("NA")){
            return;
        }
  /*
         sh """
            cd target/
            cp $jira7Jar ../deployment/$jira7Jar
            cp $jira8Jar ../deployment/$jira8Jar
           """
*/
        def instanceFile = new File(workSpaceHome+'/deployment/instances.json')
        def instanceJSON = new JsonSlurperClassic().parse(instanceFile)

        def instances=str.split(",")
        for(String instanceObjKey:instances){
            def data=instanceObjKey.split("\\.")
            if(data.length==3){
                def baseUrl = instanceJSON."$instanceObjKey".baseUrl
                def username = instanceJSON."$instanceObjKey".username
                def password = instanceJSON."$instanceObjKey".password
                def jira=data[1].trim()
                def appFile

                if(jira.equals("j7")){
                   appFile = jira7Jar
                }else{
                   appFile = jira8Jar
                }
                println("java -jar installer.jar $username,$password,$baseUrl,$appFile")
                /*
                sh """
                    cd deployment/
                    java -jar installer.jar $username,$password,$baseUrl,$appFile
                   """
                   */
            }
        }
         println("deployed successfully")
    }
}