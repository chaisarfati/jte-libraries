void call(){
	
    checkout scm
    ls()
	
    stage("Go: Build"){
    	withDockerContainer(args:'-e GOCACHE=/tmp/' ,image: 'golang:latest'){
        	echo "Running go build command"
		sh("go build")
    	}
    }
	
    stage("Go: Test"){

    	withDockerContainer(args:'-e GOCACHE=/tmp/' ,image: 'golang:latest'){
        	echo "Running go test command"
		sh("go test")
    	}
    }

    ls()
}

void ls() {
	echo "Listing current directory content"
	sh("ls")
}
