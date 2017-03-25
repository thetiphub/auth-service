FROM 1science/sbt:0.13-oracle-jdk-8

COPY . /app
WORKDIR /app

RUN sbt update
RUN sbt compile

CMD ["sbt", "run"]