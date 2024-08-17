pipeline{
	agent any
	tools {
    maven 'mvn3.9.8' // The name given in Global Tool Configuration
}
	stages{
			stage('Checkout') {
            			steps {
                			// Checkout code from version control
                			git 'https://github.com/vinod812/azure-voting-app-redisall.git'
          			}
       			}
		
			stage("Verify Branch")
			{
				steps {
					echo "$GIT_BRANCH"
				}
			}

	}
}
