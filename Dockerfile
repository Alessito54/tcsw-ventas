FROM eclipse-temurin:11-jdk

WORKDIR /app

RUN apt-get update && apt-get install -y maven && rm -rf /var/lib/apt/lists/*

COPY . .

CMD ["mvn", "clean", "test"]
