FROM adoptopenjdk:11-jre-hotspot
ARG JAR_FILE=*.jar
COPY ${JAR_FILE} givemehand.jar 
EXPOSE 8080
RUN apt-get install mlocate
RUN locate .jar
RUN pwd 
RUN ls -la
ENTRYPOINT ["java","-jar", "givemehand.jar"]

