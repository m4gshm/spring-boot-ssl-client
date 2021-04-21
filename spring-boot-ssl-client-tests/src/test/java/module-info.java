open module spring.boot.ssl.client.tests {
    requires static lombok;
    requires transitive spring.boot.ssl.client;
    requires org.slf4j;
    requires spring.test;
    requires spring.boot.autoconfigure;
    requires spring.beans;
    requires jdk.httpserver;
    requires junit;
    requires reactor.netty.http;
}
