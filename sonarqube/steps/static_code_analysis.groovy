void call(){
    stage("SonarQube Analysis"){
        checkout scm
        
        echo "creating file sonar-project.properties"
        sh("echo sonar.projectKey=${config.projectName} > sonar-project.properties")
        sh("echo sonar.login=${config.token} >> sonar-project.properties")

        sh("ls")
        echo "Starting sonarqube static code analysis"
        def scannerHome = tool 'SonarScanner';
        echo "scannerHome = ${scannerHome}"
        withSonarQubeEnv() {
            sh "${scannerHome}/bin/sonar-scanner"
        }
    }
}
