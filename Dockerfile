FROM hseeberger/scala-sbt:latest

# caching dependencies
COPY ["build.sbt", "/root/tmp/build/"]
COPY ["project/plugins.sbt", "project/build.properties", "/root/tmp/build/project/"]
RUN cd /root/tmp/build && \
 sbt clean update compile

# copy and build application
RUN mkdir /root/app
COPY . /root/app
WORKDIR /root/app
RUN sbt compile

EXPOSE 9000

CMD ["sbt", "run"]