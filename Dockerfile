FROM adoptopenjdk:11-jre-hotspot
EXPOSE 8080
ARG JAR_FILE=target/givemehand.jar
ADD ${JAR_FILE} givemehand.jar 
ENTRYPOINT ["java","-jar", "givemehand.jar"]

