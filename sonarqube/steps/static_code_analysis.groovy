void call(){
    stage("SonarQube Analysis"){
        echo "Starting sonarqube static code analysis"
        def scannerHome = tool 'SonarScanner';
        echo "scannerHome = ${scannerHome}"
        withSonarQubeEnv() {
            sh "${scannerHome}/bin/sonar-scanner"
        }
}
