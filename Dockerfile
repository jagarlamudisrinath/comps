FROM openjdk:11
RUN ["mkdir", "/opt/comps","/opt/comps/config"]
COPY ./build/distributions/comps.zip /opt/comps
RUN ["unzip","/opt/comps/comps.zip","-d","/opt/comps"]
COPY ./upload-dir/application.properties /opt/comps/config
ENV SPRING_CONFIG_LOCATION=file:///opt/comps/config/
ENV SPRING_CONFIG_NAME=application
ENV JAVA_OPTS="-Xms4G -Xmx4G -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/opt/comps/logs"
ENTRYPOINT ["sh", "/opt/comps/comps/bin/comps"]






