void call(){
    stage("Docker: Containerization"){        
        def imageName = config?.imageName ? config.imageName : env.JOB_NAME
        def taggedImage = imageName + ((env.BRANCH_NAME == "master") ? ":" : "${env.BRANCH_NAME}:") + env.BUILD_ID
        def dockerFilePath = config.dockerFilePath ? config.dockerFilePath : "."
        
        
        sh "docker build ${dockerFilePath} -t ${taggedImage}"
        sh "docker tag ${taggedImage} registry:5000/${taggedImage}"
        sh "docker push registry:5000/${taggedImage}"
    }
}
