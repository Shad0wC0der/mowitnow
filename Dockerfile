FROM openjdk:17
VOLUME /tmp
ARG JAR_FILE=mower-batch/target/mower-batch-1.0-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]