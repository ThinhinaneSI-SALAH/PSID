#FROM adoptopenjdk:11-jre-hotspot
#EXPOSE 8080
#ARG JAR_FILE=*.jar
#COPY ${JAR_FILE} app.jar 
#RUN echo ${JAR_FILE}
#RUN find -name "app.jar"
#ADD givemehand-0.0.1-SNAPSHOT.jar app.jar
#ENTRYPOINT ["java","-jar", "app.jar"]
#CMD ["-start"]
FROM adoptopenjdk:11-jre-hotspot as builder
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} application.jar
RUN java -Djarmode=layertools -jar application.jar extract

FROM adoptopenjdk:11-jre-hotspot
COPY --from=builder dependencies/ ./
COPY --from=builder snapshot-dependencies/ ./
COPY --from=builder spring-boot-loader/ ./
COPY --from=builder application/ ./
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]

