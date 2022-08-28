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
			echo "After running go build command"
    		}

    	}
    }
	
    stage("Go: Test"){
        sh("go test")
    }
}
