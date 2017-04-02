FROM hseeberger/scala-sbt:latest

# caching dependencies
COPY ["./build.sbt", "/root/app/"]
COPY ["./build.gradle", "/root/app/"]
COPY ["./project/build.properties", "/root/app/project/"]
COPY ["./project/plugins.sbt", "/root/app/project/"]
COPY ["./project/scaffold.sbt", "/root/app/project/"]
RUN cd /root/app && \
 sbt update compile

# copy and compile application
COPY [".", "/root/app"]
WORKDIR /root/app
RUN sbt compile

EXPOSE 9000

CMD ["sbt", "run"]