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
			
			stage('Build') {
           		steps {
                	// Compile the project and run unit tests
                	bat 'mvn clean install'
            	}
       		}

				stage('Run Deploy the build') {
					catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
            					steps {
               					 // Run REST Assured tests
			                		retry(3){
                						bat 'mvn deploy'
							}
            					}
        				}
				}
		

			stage('Run REST Assured Tests') {
            	steps {
                // Run REST Assured tests
                			bat 'mvn test'
            	}
        	}
	
		
		}
}
