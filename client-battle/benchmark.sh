#!/usr/bin/env bash
CLIENT_JAR="./client_jars/JettyHttpClientKotlin.jar" docker-compose up
docker-compose down && docker-compose down
CLIENT_JAR="./client_jars/jetty.jar" docker-compose up
docker-compose down && docker-compose down
CLIENT_JAR="./client_jars/async-http-client.jar" docker-compose up
docker-compose down && docker-compose down
CLIENT_JAR="./client_jars/FuelHttpClient.jar" docker-compose up
docker-compose down && docker-compose down
CLIENT_JAR="./client_jars/ApacheHttpClientJava.jar" docker-compose up
docker-compose down && docker-compose down
CLIENT_JAR="./client_jars/ApacheHttpClient.jar" docker-compose up
docker-compose down && docker-compose down
CLIENT_JAR="./client_jars/finagle.jar" docker-compose up
docker-compose down && docker-compose down
CLIENT_JAR="./client_jars/jdk.jar" docker-compose up
docker-compose down && docker-compose down
CLIENT_JAR="./client_jars/rx-netty.jar" docker-compose up
docker-compose down && docker-compose down
CLIENT_JAR="./client_jars/wizzardo.jar" docker-compose up
docker-compose down && docker-compose down
CLIENT_JAR="./client_jars/skinny.jar" docker-compose up
docker-compose down && docker-compose down
CLIENT_JAR="./client_jars/google-http-client.jar" docker-compose up
docker-compose down && docker-compose down
CLIENT_JAR="./client_jars/jodd.jar" docker-compose up
docker-compose down && docker-compose down
