FROM adoptopenjdk:11-jre-hotspot
#ARG JAR_FILE=*.jar
#COPY ${JAR_FILE} app.jar 
#RUN echo ${JAR_FILE}
#RUN find -name "app.jar"
ADD /givemehand.jar givemehand.jar
EXPOSE 8080
RUN PWD 
RUN ls -la
RUN find -name ".jar"
ENTRYPOINT ["java","-jar", "givemehand.jar"]

