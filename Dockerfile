FROM openjdk:17-alpine
COPY ./target/quotes-0.0.1-SNAPSHOT.jar /usr/application/
EXPOSE 8080
WORKDIR /usr/application
ENTRYPOINT ["java","-jar","quotes-0.0.1-SNAPSHOT.jar"]
