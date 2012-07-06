<%@page import="com.google.inject.Injector" %>
<%@page import="com.google.inject.Guice" %>
<%@page import="ph.samson.sample.spi.service.Service" %>
<%
    Injector inj = (Injector) pageContext.getServletContext().getAttribute(Injector.class.getName());
    Service service = inj.getInstance(Service.class);
    String message = service.serve();
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Guice SPI Sample</title>
    </head>
    <body>
        <h1><%=message%></h1>
    </body>
</html>
