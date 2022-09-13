void call(){
    
    stage("SonarQube Analysis"){
        echo "Starting sonarqube static code analysis"  
        def scannerHome = tool 'SonarScanner';
        echo "scannerHome = ${scannerHome}"
        withCredentials([string(credentialsId: 'sonarqube_token', variable: 'TOKEN')]){
            withSonarQubeEnv() {
                if(config.isJavaProject){
                    withDockerContainer(args:'--network=sonarqube_default', image:"maven:3.8.6-jdk-11-slim") { 
                        sh("mvn org.sonarsource.scanner.maven:sonar-maven-plugin:sonar -X -Dsonar.projectKey=${config.projectKey} -Dsonar.login=$TOKEN")
                    }
                } else {
                    echo "creating file sonar-project.properties"
                    sh("echo sonar.projectKey=${config.projectKey} > sonar-project.properties")
                    sh("echo sonar.login=$TOKEN >> sonar-project.properties")
                    sh("${scannerHome}/bin/sonar-scanner")
                }
            }
        }
    }
}
