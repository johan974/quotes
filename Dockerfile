FROM java:8-jdk-alpine
COPY ./target/quotes-0.0.1-SNAPSHOT.jar /usr/application/
WORKDIR /usr/application
ENTRYPOINT ["java","-jar","quotes-0.0.1-SNAPSHOT.jar"]