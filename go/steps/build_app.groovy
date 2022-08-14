void call(){
    stage("Go: Build"){
        println "Listing directory content"
        sh("ls")
        String uid = UUID.randomUUID().toString()
	    def label = "slave-${uid}"
        node(label){
            echo "hi , from node"
        }
        println "Printing config variable"
        println config
    }
    stage("Go: Test"){
        println "TODO Implement here a go test"
        sh("ls")
    }
}
