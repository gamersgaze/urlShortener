import groovy.json.JsonSlurperClassic

license=true


if(license.equals(Boolean.TRUE)){
    println("its true")
}

def str="dev.j7.mssql ,dev.j7.mssql "
def instances=str.split(",")

for(String instance:instances){
    def data=instance.split("\\.")
    if(data.length==3){
        def env=data[0].trim()
        def jira=data[1].trim()
        def db=data[2].trim()
        println(db)
    }
}

