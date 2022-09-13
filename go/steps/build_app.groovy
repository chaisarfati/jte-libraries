void call(){
    ls()
	
    stage("Go: Build"){
    	withDockerContainer(args: "-e GOCACHE=/tmp/" ,image: 'golang:latest'){
		sh("go build")
    	}
    }
	
    stage("Go: Test"){
    	withDockerContainer(args: "-e GOCACHE=/tmp/" ,image: 'golang:latest'){
		sh("go test")
    	}
    }

    ls()
}

void ls() {
	echo "Listing current directory content"
	sh("ls")
}
