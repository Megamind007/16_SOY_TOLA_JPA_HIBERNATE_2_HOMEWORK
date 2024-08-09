FROM gradle:8.2.1-jdk17 AS build
WORKDIR /app
COPY . .
EXPOSE 8080
CMD ["jav","-jar","/app/*.jar"]
