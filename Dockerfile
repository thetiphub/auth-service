FROM hseeberger/scala-sbt:latest

COPY . /app
WORKDIR /app

RUN sbt update
RUN sbt compile

EXPOSE 9000 9000

CMD ["sbt", "run"]