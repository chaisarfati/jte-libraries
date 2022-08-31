void call(){
    stage("SonarQube Analysis"){
        println "Starting sonarqube static code analysis"
        def scannerHome = tool 'SonarScanner';
        withSonarQubeEnv() {
            sh "${scannerHome}/bin/sonar-scanner"
        }
}
