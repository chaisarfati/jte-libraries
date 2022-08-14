void call(){
    stage("Go: Build"){
        println "TODO Implement here a go build"
        sh("ls")
        println "Printing config variable"
        println config
    }
    stage("Go: Test"){
        println "TODO Implement here a go test"
        sh("ls")
    }
}
