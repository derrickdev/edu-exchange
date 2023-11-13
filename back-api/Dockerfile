FROM eclipse-temurin:17-jdk-alpine as build

# Set the working directory
WORKDIR /app/back-api

# Copy the code for your backend application into the container
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

RUN ./mvnw install -DskipTests\
&& mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

FROM eclipse-temurin:17-jdk-alpine as production

WORKDIR /app/back-api

COPY --from=build --chown=1000:1000 /app/back-api/. .

EXPOSE 9898

VOLUME [ "/edu-exchange-data" ]

CMD ["java", "-jar", "/app/back-api/target/back-api-0.0.1-SNAPSHOT.jar"]
