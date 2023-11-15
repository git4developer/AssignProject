FROM openjdk:8
EXPOSE 9191
ADD target/assign-project.jar assign-project.jar
ENTRYPOINT ["java","-jar","/assign-project.jar"]