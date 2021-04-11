Example: https://auth0.com/blog/spring-boot-authorization-tutorial-secure-an-api-java/

Steps to run:
1. Build jar with Maven
2. docker network create services-network NETWORK
3. Create image
   <pre>docker build -t coghlada/todo-dashboard</pre>
4. Run image as container
   <pre>docker run --network services-network --name todo-dashboard -p 8081:8081 coghlada/todo-dashboard</pre>


Steps to run as docker-compose:
1. Build jars with Maven
2. Dd to directory with docker-compose.yml file
3. Build docker-compose file
    <pre>docker-compose build</pre>
4. Start containers with docker-compose
    <pre>docker-compose up --build -d</pre>
5. Check containers are running
    <pre>docker-compose ps</pre>
6. Tail logs of services, eg:
    <pre>docker logs --follow *container-id*</pre>