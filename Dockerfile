FROM registry.redhat.io/ubi8/ubi
USER root
RUN mkdir /opt/app/
COPY target/springboot-sso-demo.jar /opt/app/

RUN yum update --disableplugin=subscription-manager -y \
  && rm -rf /var/cache/yum \
  && yum install java-11-openjdk

EXPOSE 8080
USER 1001
CMD java -jar /opt/app/springboot-sso-demo.jar
