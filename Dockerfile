FROM adoptopenjdk:11-jre-hotspot
EXPOSE 8080
ARG JAR_FILE=*.jar
COPY ${JAR_FILE} givemehand.jar
COPY . .
ENTRYPOINT ["java","-jar", "givemehand.jar"]

