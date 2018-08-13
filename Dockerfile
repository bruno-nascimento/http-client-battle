#FROM anapsix/alpine-java:8u172b11_server-jre_unlimited
FROM openjdk:10.0.2-13-slim

ADD ./target/http-client-battle-0.0.1-jar-with-dependencies.jar /opt/http-client-battle/http-client-battle.jar
RUN mkdir /opt/http-client-battle/client-jar
CMD java --add-modules jdk.incubator.httpclient -cp "/opt/http-client-battle/http-client-battle.jar:/opt/http-client-battle/client-jar/*" -server -XX:+UseNUMA -XX:+UseCondCardMark -XX:-UseBiasedLocking -Xms2048M -Xmx4096M -Xss256M -XX:+UseParallelGC -XX:+AggressiveOpts br.com.labbs.workout.httpclientbattle.Main

