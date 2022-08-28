void call(){
    stage("Go: Build"){
	println "Printing config variable :\n" + config
        println "Listing directory content"
        sh("ls")
        node {
    		checkout scm
		echo "Before running go build command"
		sh("go build")
		echo "After running go build command"
	}

	println "Listing directory content"
	sh("ls")

    }
	
    stage("Go: Test"){
        sh("go test")
    }
}
