package com.gialnet.camelbootsis;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@ConfigurationProperties
@Component
public class FtpRoute extends RouteBuilder {

    @Value("${ftp.servidor}")
    private String desde;

    @Autowired
    DataSource dataSource;

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void configure() throws Exception {

        // lets shutdown faster in case of in-flight messages stack up
        // "{{fpt.servidor}}"
        getContext().getShutdownStrategy().setTimeout(10);

        from( desde )
                .setHeader("idemployee", xpath("/company/id/text()"))
                .setHeader("name", xpath("/company/name/text()"))
                .setHeader("dob", xpath("/company/dob/text()"))
                .setHeader("salary", xpath("/company/salary/text()"))
                .log("${header.idemployee}")
                .log("${header.name}")
                .log("msg: ${body}")
                .process(new ProcessInsert())
                .to("jdbc:dataSource");

    }


}
