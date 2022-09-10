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
                sh("echo sonar.sources=src/main >> sonar-project.properties")
                sh("echo sonar.sourceEncoding=UTF-8 >> sonar-project.properties")
                sh("echo sonar.language=java >> sonar-project.properties")
                sh("echo sonar.tests=src/test >> sonar-project.properties")
                sh("echo sonar.junit.reportsPath=target/surefire-reports >> sonar-project.properties")
                sh("echo sonar.surefire.reportsPath=target/surefire-reports >> sonar-project.properties")
                sh("echo sonar.jacoco.reportPath=target/jacoco.exec >> sonar-project.properties")
                sh("echo sonar.java.coveragePlugin=jacoco >> sonar-project.properties")
                                sh("${scannerHome}/bin/sonar-scanner")

                //sh("mvn org.sonarsource.scanner.maven:sonar-maven-plugin:3.7.0.1746:sonar")
            } else {
                sh("${scannerHome}/bin/sonar-scanner")
            }
        }
    }
}
