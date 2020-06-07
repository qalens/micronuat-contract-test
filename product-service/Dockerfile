FROM oracle/graalvm-ce:20.0.0-java8 as graalvm
# For JDK 11
#FROM oracle/graalvm-ce:20.0.0-java11 as graalvm
RUN gu install native-image

COPY . /home/app/product-service
WORKDIR /home/app/product-service

RUN native-image --no-server -cp build/libs/product-service-*-all.jar

FROM frolvlad/alpine-glibc
RUN apk update && apk add libstdc++
EXPOSE 8080
COPY --from=graalvm /home/app/product-service/product-service /app/product-service
ENTRYPOINT ["/app/product-service"]
