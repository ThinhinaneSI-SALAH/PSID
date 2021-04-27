FROM adoptopenjdk:11-jre-hotspot
#VOLUME /tmp
EXPOSE 8080
COPY . /app
#ARG JAR_FILE=*.jar
#COPY target/${JAR_FILE} app.jar
#ADD givemehand-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar", "/app/build/libs/givemehand-0.0.1-SNAPSHOT.jar"]

