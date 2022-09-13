final static String GOLANG_IMAGE = "golang:latest"
final static String GOLANG_CONTAINER_ARGUMENT = "-e GOCACHE=/tmp/"

void call(){
    ls()
	
    stage("Go: Build"){
    	withDockerContainer(args: 'golang:latest' ,image: '-e GOCACHE=/tmp/'){
		sh("go build")
    	}
    }
	
    stage("Go: Test"){
    	withDockerContainer(args: 'golang:latest' ,image: '-e GOCACHE=/tmp/'){
		sh("go test")
    	}
    }

    ls()
}

void ls() {
	echo "Listing current directory content"
	sh("ls")
}
