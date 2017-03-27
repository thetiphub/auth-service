FROM hseeberger/scala-sbt:latest

# caching dependencies
COPY ["build.sbt", "/tmp/build/"]
COPY ["project/plugins.sbt", "project/build.properties", "/tmp/build/project/"]
RUN cd /tmp/build && \
 sbt clean update compile && \
 rm -rf /tmp/build

# copy and build application
COPY . /
RUN sbt compile

EXPOSE 9000

CMD ["sbt", "run"]