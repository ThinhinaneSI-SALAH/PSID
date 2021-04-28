FROM adoptopenjdk:11-jre-hotspot
ARG JAR_FILE=*.jar
COPY ${JAR_FILE} givemehand.jar 
EXPOSE 8080
RUN find -name "givemehand.jar"
RUN PWD 
RUN ls -la
ENTRYPOINT ["java","-jar", "givemehand.jar"]

