FROM openjdk:11-jdk-slim
WORKDIR /app
COPY ./build/api-0.0.1-SNAPSHOT.jar /app
CMD ["java", "-jar", "api-0.0.1-SNAPSHOT.jar"]