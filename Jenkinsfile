pipeline{
	agent any
	
	tools {
	    maven 'mvn3.9.8' // The name given in Global Tool Configuration
	}
	stages{
	        //Checkout
			stage('Checkout') {
            	steps {
            		// Checkout code from version control
                	git 'https://github.com/vinod812/azure-voting-app-redisall.git'
          		}
       		}
		
		    //Verify the branch
			stage("Verify Branch")
			{
				steps {
					echo "$GIT_BRANCH"
				}
			}
			
			// Build
			stage('Build') {
           		steps {
                	// Compile the project and run unit tests
                	bat 'mvn clean install'
            	}
       		}

			// Deploy the build
			stage('Run Deploy the build') {
				steps {
					catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
			                		retry(3){
                						bat 'mvn deploy'
									}
            		}
        		}
			}
		
			// Run REST Assured tests
			stage('Run REST Assured Tests') {
            	steps {
					bat 'mvn test'
            	}
        	}
	
		
		}
}
