pipeline {
    agent any 
    stages {
        stage('Compile and Clean') { 
            steps {

                sh "cd user-management-microservice; mvn clean compile"
            }
        }
       
		stage('Junit5 Test') { 
            steps {

                sh "cd user-management-microservice; mvn test"
            }
        }
        

		stage('Jacoco Coverage Report') {
        	steps{
            		jacoco()
			}
		}
        
        stage('SonarQube'){
			steps {
				sh "cd user-management-microservice; mvn sonar:sonar -Dsonar.host.url=http://18.210.22.117:9000 -Dsonar.login=f5149ebc08b1d5b00b3449d7f0cf24e19bee340f"
		         
		     }
   		}
   		
        stage('Maven Build') { 
            steps {
                sh "cd user-management-microservice; mvn package"
            }
        }


        stage('Build Docker image'){
            steps {
              
                sh 'cd user-management-microservice; docker build -t  malkhan52/user_management_service:1.0 .'
            }
        }
        

        stage('Docker Login'){
            
            steps {
                 withCredentials([string(credentialsId: 'DockerId', variable: 'Dockerpwd')]) {
                    sh "cd user-management-microservice; docker login -u malkhan52 -p ${Dockerpwd}"
                }
            }                
        }

        stage('Docker Push'){
            steps {
                sh 'cd user-management-microservice; docker push malkhan52/user_management_service:1.0'
            }
        }
        
        stage('Archiving') { 
            steps {
                 archiveArtifacts '**/target/*.jar'
            }
        }
    }
}