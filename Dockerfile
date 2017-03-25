FROM hseeberger/scala-sbt:latest

# caching dependencies
COPY ["build.sbt", "/tmp/build/"]
COPY ["project/plugins.sbt", "project/build.properties", "/tmp/build/project/"]
RUN cd /tmp/build && \
 sbt update && \
 sbt compile && \
 rm -rf /tmp/build

# copy and build application
COPY . /app
WORKDIR /app
RUN sbt compile

EXPOSE 9000 9000

CMD ["sbt", "run"]