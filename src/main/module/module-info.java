open module spring.boot.ssl.client {
    requires static lombok;

    requires org.slf4j;
    requires static spring.core;

    requires static spring.context;
    requires static spring.boot;
    requires static spring.boot.autoconfigure;
    requires static spring.web;
    requires static spring.webflux;
    requires static spring.beans;

    requires static io.netty.handler;
    requires static reactor.netty.http;
    requires static reactor.netty.core;
    requires static feign.reactor.webclient;

    exports m4gshm.springframework.ssl.client;

}
