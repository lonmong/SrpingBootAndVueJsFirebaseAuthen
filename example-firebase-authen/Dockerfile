FROM stakater/java8-alpine
ENV NPM_CONFIG_LOGLEVEL info
ENV TZ Asia/Bangkok
RUN apk add --no-cache tzdata \
   && cp /usr/share/zoneinfo/Asia/Bangkok /etc/localtime \
   && echo "Asia/Bangkok" >  /etc/timezone \
   && rm -rf /var/cache/apk/*
RUN mkdir /ssl
ADD /src/main/resources/ssl /ssl
LABEL maintainer="aik.nit2018@gmail.com"
EXPOSE 8080
ADD /target/Click-Glasses-Store-Spring-Boot-0.0.1-SNAPSHOT.jar Click-Glasses-Store-Spring-Boot-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","Click-Glasses-Store-Spring-Boot-0.0.1-SNAPSHOT.jar"]
#ENTRYPOINT ["java","-jar","-Djavax.net.ssl.trustStore=/ssl/trustStore.p12","-Djavax.net.ssl.trustStorePassword=niwat24120","Click-Glasses-Store-Spring-Boot-0.0.1-SNAPSHOT.jar"]
