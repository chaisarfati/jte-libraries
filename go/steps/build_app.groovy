void call(){
    stage("Go: Build"){
	println "Printing config variable :\n" + config
        println "Listing directory content"
        sh("ls")
    	withDockerContainer(args:'-e GOCACHE=off' ,image: 'golang:1.19.0-alpine'){
		println "Listing directory content in container"
		sh("ls")
        	echo "Before running go build command"
		sh("go build")
    	}
	sh("ls")
    }
	
    stage("Go: Test"){
	println "Listing directory content before checkout"
        sh("ls")
	checkout scm
	println "Listing directory content after checkout"
        sh("ls")

    	withDockerContainer(args:'-e GOCACHE=off' ,image: 'golang:1.19.0-alpine'){
        	echo "Running go test command"
		sh("go test")
    	}
    }
}
