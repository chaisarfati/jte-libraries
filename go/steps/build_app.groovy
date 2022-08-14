void call(){
    stage("Go: Build"){
        println "Listing directory content"
        sh("ls")
        node {
    		checkout scm
    		docker.image('golang:1.19.0-alpine3.16').withRun() { c ->
			sh 'ls' 
		}
	}


        println "Printing config variable"
        println config
    }
    stage("Go: Test"){
        println "TODO Implement here a go test"
        sh("ls")
    }
}
