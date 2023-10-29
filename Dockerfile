FROM openjdk:17-alpine
ENV DB_URL_CONNECTION="jdbc:h2:mem:palindrome;DB_CLOSE_DELAY=-1;DATABASE_TO_UPPER=false"
ENV DB_USER_NAME="sa"
ENV DB_PASSWORD="password"
ENV SERVER_PORT="8080"
VOLUME /tmp
EXPOSE 8082
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
