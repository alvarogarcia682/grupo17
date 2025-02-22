FROM maven:3.9.6-amazoncorretto-21 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn package -DskipTests

FROM amazoncorretto:21-alpine-jdk
WORKDIR /app
COPY --from=build /app/target/ms-books-payments*.jar ./ms-books-payments.jar
EXPOSE 8082
CMD ["java", "-jar", "ms-books-payments.jar"]