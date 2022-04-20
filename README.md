# cicd-demo
## Building the Docker Image
Use spring boot build-image goal to create the docker image:
```
.\mvnw spring-boot:build-image
```
Run created image with:
```
docker run -p 8080:8080 demo:0.0.1-SNAPSHOT
```
Run Sonar with Docker
```
docker pull sonarqube:8.9.7-community
docker run -d --name cicd-sonarqube -p 9000:9000 sonarqube:8.9.7-community
mvnw clean verify sonar:sonar -D sonar.password= -D sonar.login=<your_token> -D sonar.projectName=CICD-Demo
```

