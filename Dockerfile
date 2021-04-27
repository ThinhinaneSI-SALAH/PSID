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

# Copy all project sources to app directory
COPY . /usr/src/app
# Set the working directory to /usr/src/app
WORKDIR /usr/src/app
# Starting from jre8 to build our onw in second step
FROM adoptopenjdk:11-jre-hotspot
# Optional, expose the container port
EXPOSE 8080
# From fist step, copy build jar file to target directory
COPY --from=BUILD /usr/src/app/target/*.jar /opt/target/app.jar
# Set the working directory to /opt/target
WORKDIR /opt/target

# Run the application
ENTRYPOINT ["java","-jar", "/app.jar"]



