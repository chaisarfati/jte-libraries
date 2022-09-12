void call(){
    
    echo "creating file sonar-project.properties"
    sh("echo sonar.projectKey=${config.projectKey} > sonar-project.properties")
    sh("echo sonar.login=${config.token} >> sonar-project.properties")

    stage("SonarQube Analysis"){
        echo "Starting sonarqube static code analysis"  
        def scannerHome = tool 'SonarScanner';
        echo "scannerHome = ${scannerHome}"
        withSonarQubeEnv() {
            if(config.isJavaProject){
                withDockerContainer(args:'--network=sonarqube_default', image:"maven:3.5.0-jdk-8-alpine") { 
                    sh("mvn org.sonarsource.scanner.maven:sonar-maven-plugin:sonar -X")
                }
            } else {
                sh("${scannerHome}/bin/sonar-scanner")
            }
        }
    }
}
