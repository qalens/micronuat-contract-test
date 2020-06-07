FROM oracle/graalvm-ce:20.0.0-java8 as graalvm
# For JDK 11
#FROM oracle/graalvm-ce:20.0.0-java11 as graalvm
RUN gu install native-image

COPY . /home/app/order-service
WORKDIR /home/app/order-service

RUN native-image --no-server -cp build/libs/order-service-*-all.jar

FROM frolvlad/alpine-glibc
RUN apk update && apk add libstdc++
EXPOSE 8080
COPY --from=graalvm /home/app/order-service/order-service /app/order-service
ENTRYPOINT ["/app/order-service"]
