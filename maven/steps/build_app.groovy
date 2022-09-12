void call(){
    stage("Maven: Build"){
        withDockerContainer("maven:3.5.0-jdk-8-alpine") { 
            sh("mvn clean install")
        }
    }
}
