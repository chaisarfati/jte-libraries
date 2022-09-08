void call(){
    stage("SonarQube Analysis"){
        checkout scm
        
        echo "creating file sonar-project.properties"
        sh("echo sonar.projectKey=vindecodex-msgo > sonar-project.properties")
        sh("echo sonar.login=sqp_084b1eb182741292fcdb60e97411edef440f29cb >> sonar-project.properties")

        sh("ls")
        echo "Starting sonarqube static code analysis"
        def scannerHome = tool 'SonarScanner';
        echo "scannerHome = ${scannerHome}"
        withSonarQubeEnv() {
            sh "${scannerHome}/bin/sonar-scanner"
        }
    }
}
