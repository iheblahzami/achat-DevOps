pipeline {
    agent any
     environment {
        GRAFANA_API_USER = 'admin'
        GRAFANA_API_KEY = 'grafana'
        PROMETHEUS_METRICS_URL = 'http://192.168.100.177:9090/metrics'
        GRAFANA_URL = 'http://192.168.100.177:3000'
        GRAFANA_DASHBOARD_IMPORT_URL = 'http://192.168.100.177:3000/dashboard/import'
    }

    tools {
        // Install the Maven version configured as "M3" and add it to the path.
        maven "M2_HOME"
    }

    stages {
        stage('Maven') {
            steps {
                // Get some code from a GitHub repository
                checkout scmGit(branches: [[name: '*/iheb_lahzami']], extensions: [], userRemoteConfigs: [[credentialsId: 'ihebtoken', url: 'https://github.com/faroukbs/CLOUDHEIMER']])
                sh "mvn clean compile"

            }
        }
        stage('Sonarqube') {
            steps {
                sh "mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=sonar"
            }
        }
        stage('Mockito') {
            steps {
                // Étape pour exécuter les tests unitaires avec Maven
                sh 'mvn test'  // Exécute les tests unitaires
            }
        }
       stage('Nexus') {
            steps {
                sh "mvn  deploy "
            }
        }
        stage('Build Docker Image') {
            steps {
                script {
                        // Build a Docker image from the Dockerfile in the project directory
                        sh " docker build -t iheb49/achat ."
                }
            }
        }
        stage('Push Docker Image to Docker Hub') {
            steps {
               script {
           withCredentials([string(credentialsId: 'dockerhub-pwd', variable: 'dockerhubpwd')]) {
               sh 'docker login -u iheb49 -p ${dockerhubpwd}'}

               sh 'docker push iheb49/achat'
            }
            }
        }
         stage('Run Docker Compose') {
            steps {
                script {
                        sh "docker compose up -d"
                }
            }
        }

                stage('Prometheus') {
            steps {
                script {
                    // This step is used to scrape metrics from your application
                    // and send them to Prometheus.

                    // Example: Use curl to scrape metrics
                    sh "curl -X POST -H 'Content-Type: text/plain' --data-binary '@your-metrics-file' $PROMETHEUS_METRICS_URL"
                }
            }
        }

        stage('Grafana') {
            steps {
                script {
                    // This step is used to create/update Grafana dashboards
                    // using the Grafana API.

                    // Example: Import a dashboard from a URL
                    sh '''
                        curl -X POST -H "Authorization: Basic $(echo -n $GRAFANA_API_USER:$GRAFANA_API_KEY | base64)" \
                        -H "Content-Type: application/json" \
                        --data-binary '{"dashboard": "your-dashboard-json-data"}' \
                        $GRAFANA_DASHBOARD_IMPORT_URL
                    '''
                }
            }
        }

    }
    post {
        success {
            script {
                // This post-build step is executed if the pipeline succeeds.
                // Include the Grafana link in the success email.
                emailext (
                    to: 'siiheb64@gmail.com',
                    subject: 'Jenkins Pipeline Successful',
                    body: 'Your Jenkins pipeline has succeeded. Grafana Dashboard: ' + GRAFANA_URL + '/d/haryan-jenkins/jenkins3a-performance-and-health-overview?orgId=1'
                )
            }
        }

        failure {
            script {
                // This post-build step is executed if the pipeline fails.
                // You can add error handling or notifications here.
                def prometheusOutput = sh(
                    script: "curl -X POST -H 'Content-Type: text/plain' --data-binary '@your-metrics-file' $PROMETHEUS_METRICS_URL",
                    returnStatus: true,
                    returnStdout: true
                )

                emailext (
                    to: 'siiheb64@gmail.com',
                    subject: 'Jenkins Pipeline Failed',
                    body: "Your Jenkins pipeline has failed. Please investigate.\n\nPrometheus scraping output:\n\n$prometheusOutput"
                )
            }
        }
    }
    }
