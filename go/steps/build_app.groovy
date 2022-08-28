void call(){
    stage("Go: Build"){
	println "Printing config variable :\n" + config
        println "Listing directory content"
        sh("ls")
    	withDockerContainer(image: 'golang:latest'){
		println "Listing directory content in container"
		sh("ls")
        	echo "Before running go build command"
		//sh("go build")
		sh("mkdir afterBuild")
    	}
	sh("ls")
    }
	
    stage("Go: Test"){
	println "Listing directory content before checkout"
        sh("ls")
	checkout scm
	println "Listing directory content after checkout"
        sh("ls")

    	withDockerContainer(image: 'golang:latest'){
        	echo "Running go test command"
		sh("go test")
    	}
    }
}
