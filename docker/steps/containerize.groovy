void call(){
    stage("Docker: Containerization"){        
        def imageName = config.imageName ? config.imageName : ${JOB_NAME}
        echo "imageName = ${imageName}"
        def pushedImageFullName = imageName + ((env.BRANCH_NAME == "master") ? "" : "${env.BRANCH_NAME}-") + env.BUILD_ID
        echo "pushedImageFullName = ${pushedImageFullName}"
        def dockerFilePath = config.dockerFilePath ? config.dockerFilePath : "."
        echo "dockerFilePath = ${dockerFilePath}"
        
        
        sh "docker build ${dockerFilePath} ${pushedImageFullName}"
        sh "docker tag ${pushedImageFullName} registry:5000/${pushedImageFullName}"
        sh "docker push localhost:5000/${pushedImageFullName}"

    }
}
