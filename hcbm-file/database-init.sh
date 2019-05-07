#!/usr/bin/env bash
mkdir -p tool-jar
if [ ! -f tool-jar/choerodon-tool-liquibase.jar ]
then
    curl https://nexus.choerodon.com.cn/repository/choerodon-release/io/choerodon/choerodon-tool-liquibase/0.9.2.RELEASE/choerodon-tool-liquibase-0.9.2.RELEASE.jar -o ../tool-jar/choerodon-tool-liquibase.jar
fi

java -Dspring.datasource.url="jdbc:mysql://localhost:3306/hzero_file?useUnicode=true&characterEncoding=utf-8&useSSL=false"  \
     -Dspring.datasource.username=root \
     -Dspring.datasource.password=123456 \
     -Dspring.datasource.tomcat.max-active=200 \
     -Dspring.datasource.tomcat.max-idle=10 \
     -Dspring.datasource.tomcat.min-idle=10 \
     -Ddata.drop=false -Ddata.init=init \
     -Ddata.dir=Please edit... \
     -jar tool-jar/choerodon-tool-liquibase.jar