FROM eclipse-temurin:23

RUN mkdir -p /opt/app
COPY . /opt/app

RUN apt-get update -y && apt-get install -qqy xauth libxrender1 libxtst6 libxi6

WORKDIR /opt/app
# Tests + dependency embedding
RUN CI=true ./gradlew runAll

VOLUME /opt/app

ENTRYPOINT ["sh", "./gradlew"]
CMD ["runAll"]
