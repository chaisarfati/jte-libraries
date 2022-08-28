void call(){
    stage("Go: Build"){
	println "Printing config variable :\n" + config
        println "Listing directory content"
        sh("ls")
	node{
		checkout scm
    		withDockerContainer(image: 'golang:latest'){
        		echo "Before running go build command"
			sh("go build")
			sh("mkdir afterBuild")
    		}
		sh("ls")
    	}
    }
	
    stage("Go: Test"){
        node{
		checkout scm
    		withDockerContainer(image: 'golang:latest'){
        		echo "Running go test command"
			sh("go test")
    		}
    	}
    }
}
