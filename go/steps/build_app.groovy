void call(){
    stage("Go: Build"){
        println "Listing directory content"
        sh("ls")
        node {
    		checkout scm
		echo "hello from node guys !!"
		
//     		docker.image('golang:1.19.0-alpine3.16').withRun() { c ->
// 			sh 'ls' 
// 		}
		
		docker.image('mysql:5').withRun('-e "MYSQL_ROOT_PASSWORD=my-secret-pw"' +
                                    ' -p 3306:3306') { c ->
        		sh 'echo "hi"'
       	 		sh 'ls'
    		}
	}


        println "Printing config variable"
        println config
    }
    stage("Go: Test"){
        println "TODO Implement here a go test"
        sh("ls")
    }
}
