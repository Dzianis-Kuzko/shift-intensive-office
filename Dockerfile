FROM gradle:8.5-jdk17 AS builder
WORKDIR /app
COPY . .
RUN ./gradlew build -x test
EXPOSE 8080
CMD ["java", "-jar", "build/libs/template-0.0.1.jar"]