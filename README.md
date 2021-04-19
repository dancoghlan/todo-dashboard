<h3>Steps to run:</h3>
1. Build jar with Maven
2. docker network create services-network NETWORK
3. Create image
   <pre>docker build -t coghlada/todo-dashboard</pre>
4. Run image as container
   <pre>docker run --network services-network --name todo-dashboard -p 8081:8081 coghlada/todo-dashboard</pre>


<h3>Steps to run using docker-compose:</h3>
1. Build jars with Maven
2. Cd to directory with docker-compose.yaml file
3. Start containers with docker-compose
    <pre>docker-compose up --build -d</pre>
4. Check containers are running
    <pre>docker-compose ps</pre>
5. Tail logs of services, eg:
    <pre>docker logs --follow *container-id*</pre>

<h2>Useful Links</h2>
- https://auth0.com/blog/spring-boot-authorization-tutorial-secure-an-api-java/
- https://dzone.com/articles/deploying-springboot-in-ecs-part-1
- https://dzone.com/articles/jenkins-pipeline-with-sonarqube-and-gitlab
- https://bidhankhatri.com.np/system/docker-compose-file-for-mysql-and-phpmyadmin/
- https://tomgregory.com/building-a-spring-boot-application-in-jenkins/
- https://www.linode.com/docs/guides/zipkin-server-configuration-using-docker-and-mysql/
- https://livebook.manning.com/book/spring-microservices-in-action-second-edition/chapter-11/v-8/21