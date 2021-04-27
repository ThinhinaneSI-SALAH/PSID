FROM adoptopenjdk:11-jre-hotspot
#VOLUME /tmp
EXPOSE 8080
ARG JAR_FILE= target/*.jar
COPY ${JAR_FILE} app.jar
RUN ls -la | grep app.jar 
RUN find -name "app.jar"
#RUN locate "app.jar"
COPY . /app
RUN ls /app
#ADD givemehand-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar", "/app/app.jar"]

