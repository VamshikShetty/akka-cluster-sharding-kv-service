FROM openjdk:8

RUN echo "deb [arch=amd64] http://storage.googleapis.com/bazel-apt stable jdk1.8" | tee /etc/apt/sources.list.d/bazel.list \
  && curl https://bazel.build/bazel-release.pub.gpg | apt-key add -

RUN apt-get update \
  && apt-get install -y bazel \
  && rm -rf /var/lib/apt/lists/*

WORKDIR /akka-sharded-kv

COPY BUILD BUILD
COPY WORKSPACE WORKSPACE

COPY src ./src

RUN bazel build //:kv-akka-sharded

CMD bazel run //:kv-akka-sharded