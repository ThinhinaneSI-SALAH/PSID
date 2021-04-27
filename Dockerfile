FROM adoptopenjdk:11-jre-hotspot
#VOLUME /tmp
EXPOSE 8080
COPY . /app
ARG JAR_FILE=*.jar
COPY ${JAR_FILE} app.jar
RUN ls /app
#ADD givemehand-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar", "/app/app.jar"]

