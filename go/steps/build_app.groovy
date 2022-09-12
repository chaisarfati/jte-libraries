@Field final String GOLANG_IMAGE = "golang:latest"
@Field final String GOLANG_CONTAINER_ARGUMENT = "-e GOCACHE=/tmp/"

void call(){
    ls()
	
    stage("Go: Build"){
    	withDockerContainer(args: GOLANG_CONTAINER_ARGUMENT ,image: GOLANG_IMAGE){
		sh("go build")
    	}
    }
	
    stage("Go: Test"){
    	withDockerContainer(args: GOLANG_CONTAINER_ARGUMENT ,image: GOLANG_IMAGE){
		sh("go test")
    	}
    }

    ls()
}

void ls() {
	echo "Listing current directory content"
	sh("ls")
}
