import groovy.json.JsonSlurperClassic

node('master') {

    def workSpaceHome = pwd()
    def instanceObjKey
    stage('Clean') {
        deleteDir()
    }
    stage('Checkout') {
        checkout scm
    }

    stage('Run') {
         println("----------------------------------hahahahah")
        def configFile = new File(workSpaceHome+'/config.json')
        def configData = new JsonSlurperClassic().parse(configFile)
        withMaven(jdk: 'JDK', maven: 'maven3', mavenLocalRepo: '', mavenOpts: '', mavenSettingsFilePath: '/opt/qtmserverdependency/settings.xml') {
            sh "atlas-package -DskipTests -Pjira7 '-Dlicense.check=false'"
        }
    }

    stage('Deploy') {

        def instanceFile = new File(workSpaceHome+'/deployment/instances.json')
        def instanceJSON = new JsonSlurperClassic().parse(instanceFile)

        def baseUrl = instanceJSON."$instanceObjKey".baseUrl
        def username = instanceJSON."$instanceObjKey".username
        def password = instanceJSON."$instanceObjKey".password
        def appFile = instanceJSON."$instanceObjKey".appFile

        sh """
           cd target/
           cp qtm4jserver-*.jar ../deployment/qtm4jserver-to-deploy.jar
           cd ../deployment/
           java -jar qtm4jDeployer-1.0.0-SNAPSHOT-jar-with-dependencies.jar $username,$password,$baseUrl,$appFile
         """
        println("deployed succesfully......")
    }
}