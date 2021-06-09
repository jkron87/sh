FROM gradle:7.0.2-jdk8

COPY src src
COPY settings.gradle.kts settings.gradle.kts
COPY build.gradle.kts build.gradle.kts

RUN gradle build assemble

ENTRYPOINT ["java","-jar","build/libs/kron-0.0.1-SNAPSHOT.jar"]