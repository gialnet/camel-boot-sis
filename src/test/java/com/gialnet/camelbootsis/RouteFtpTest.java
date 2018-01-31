package com.gialnet.camelbootsis;

import org.apache.camel.Endpoint;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RouteFtpTest extends CamelTestSupport {

    /*
    @Autowired
    private ConsumerTemplate consumerTemplate;

    @Autowired
    private ProducerTemplate producerTemplate;*/

    private Endpoint in;
    private MockEndpoint out;

    @Override
    public RoutesBuilder createRouteBuilder() throws Exception {

        //this.in = context.getEndpoint("ftp://192.168.1.2:21/pub?username=anonymous&password=anonymous&passiveMode=true&binary=true");
        this.out = context.getEndpoint("mock:result", MockEndpoint.class);

        return new RouteBuilder() {

            public void configure() {
                from("ftp://192.168.1.2:21/pub?username=anonymous&password=anonymous&passiveMode=true&binary=true").to("mock:result");
            }
        };
    }

    @Before
    public void setup() throws Exception {

        RoutesBuilder rb= createRouteBuilder();

    }

    @Test
    public void test() throws Exception {

        this.out.expectedMessageCount(1);
        this.out.assertIsSatisfied();
    }


}
