void call(){
    stage("SonarQube Analysis"){
        checkout scm
        
        echo "creating file sonar-project.properties"
        sh("echo sonar.projectKey=vindecodex-msgo > sonar-project.properties")
        sh("echo sonar.login=jenkins >> sonar-project.properties")
        sh("echo sonar.password=jenkins >> sonar-project.properties")

        sh("ls")
        echo "Starting sonarqube static code analysis"
        def scannerHome = tool 'SonarScanner';
        echo "scannerHome = ${scannerHome}"
        withSonarQubeEnv() {
            sh "${scannerHome}/bin/sonar-scanner"
        }
    }
}
