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

           	// Use of try/catch
           	stage('Deploy:Error handling with try catch') {
				steps {
					script{
					      try{
					     		bat 'mvn deploy'
				          } catch(Exception e){
				          		echo "Caught exception: ${e}"
				          	    //Error handling
				          	    currentBuild.result = 'FAILURE'
				          }
					}
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
		
		post{
			always{
				echo "Always run this"
				cleanupWorkspace()
			}
			
			success{
			    echo 'The pipeline succeeded!'
            	sendEmailNotification('Build Succeeded')
			}
		
			failure {
            	echo 'The pipeline failed.'
            	sendEmailNotification('Build Failed')
       		}
       		
       		unstable {
            	echo 'The pipeline is unstable.'
            	sendEmailNotification('Build Unstable')
        	}
        	
        	changed {
            	echo 'The pipeline result has changed compared to the last run.'
            	sendEmailNotification('Build Result Changed')
        	}
		}
}

// Example functions that might be defined elsewhere in the Jenkinsfile
def cleanupWorkspace() {
    deleteDir() // Deletes the workspace to clean up after the build
}

def sendEmailNotification(String message) {
	mail to: 'vinod812@gmail.com',
	subject: "Jenkins Build: ${message}",
    body: "The Jenkins build has finished with the status: ${message}"
}
