FROM maven:3.9-jdk-21
LABEL authors="aoperezg"

WORKDIR /app

COPY . /app

RUN mvn clean package

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "target/*.jar"]
