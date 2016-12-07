# Build: docker build -t user/ubuntu:game_server ./git/server/
# Run: docker run -t -i user/ubuntu:game_server /bin/bash
FROM ubuntu:16.04
MAINTAINER nexgen

# Ставим окружение
ENV DEBIAN_FRONTEND noninteractive
RUN apt-get update && apt-get install software-properties-common -y
RUN \
	echo oracle-java8-installer shared/accepted-oracle-license-v1-1 select true | debconf-set-selections && \
	add-apt-repository -y ppa:webupd8team/java && \
	apt-get update && \
	apt-get install -y oracle-java8-installer && \
	rm -rf /var/lib/apt/lists/* && \
	rm -rf /var/cache/oracle-jdk8-installer
RUN apt-get install -y \
        git
	
ENV JAVA_HOME /usr/lib/jvm/java-8-oracle
#RUN source /etc/environment
RUN echo $JAVA_HOME

# Выкачиваем исходники рассылятора
RUN cd ~ \
        && git clone https://github.com/Nexgen90/server.git

