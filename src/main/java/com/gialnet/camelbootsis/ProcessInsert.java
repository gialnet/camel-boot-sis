package com.gialnet.camelbootsis;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class ProcessInsert implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {


        String idemployee = exchange.getIn().getHeader("idemployee", String.class);
        String Nombre = exchange.getIn().getHeader("name", String.class);
        String Apellidos= exchange.getIn().getHeader("dob", String.class);
        String Salario = exchange.getIn().getHeader("salary", String.class);
        String InsertStatement = "insert into Employee values('"+idemployee+"','"+Nombre+"','"+Apellidos+"','"+Salario+"');";

        exchange.getIn().setBody(InsertStatement);
    }
}
