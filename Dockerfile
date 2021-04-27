#FROM adoptopenjdk:11-jre-hotspot
#VOLUME /tmp
#EXPOSE 8080
#ARG JAR_FILE= *.jar
#COPY ${JAR_FILE} app.jar 
#RUN echo ${JAR_FILE}
#RUN find -name "app.jar"
#ADD givemehand-0.0.1-SNAPSHOT.jar app.jar
#ENTRYPOINT ["java","-jar", "app.jar"]
#CMD ["-start"]
# Starting from maven jdk8 to build our own in first step
FROM maven:3.6.3-jdk-8 as BUILD
# The owner of this Dockerfile
LABEL MAINTAINER=pasciano007@gmail.com
# Copy compressed repository to maven ref repository available in base image
ADD repository.tar.gz /usr/share/maven/ref/

# Copy all project sources to app directory
COPY . /usr/src/app
# Set the working directory to /usr/src/app
WORKDIR /usr/src/app
# Package the project
RUN mvn -s /usr/share/maven/ref/settings-docker.xml package

# Starting from jre8 to build our onw in second step
FROM openjdk:8-jre
# Optional, expose the container port
EXPOSE 8080
# From fist step, copy build jar file to target directory
COPY --from=BUILD /usr/src/app/target/*.jar /opt/target/app.jar
# Set the working directory to /opt/target
WORKDIR /opt/target

# Run the application
ENTRYPOINT ["java","-jar", "app.jar"]



