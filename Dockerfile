FROM openjdk:11.0.10-slim

COPY target/todo-dashboard-1.0.0-SNAPSHOT.war todo-dashboard-1.0.0-SNAPSHOT.war
ENTRYPOINT ["java","-jar","/todo-dashboard-1.0.0-SNAPSHOT.war"]