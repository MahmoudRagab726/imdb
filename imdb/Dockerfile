FROM openjdk:11-jre

LABEL Name="Imdb"
LABEL Description="Lunatech imdb assessment "
LABEL Maintainer="mahmoudragab726@gmail.com"
LABEL Url="https://github.com/lunatech-labs/imdb_04032021_MahmoudSakr"
COPY ./target/imdb*.jar imdb.jar

EXPOSE 8080

HEALTHCHECK --start-period=60s \
  CMD curl -f http://localhost:8080/actuator/health || exit 1

USER 1001
ENTRYPOINT ["java","-jar","/imdb.jar"]
