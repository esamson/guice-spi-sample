# Using the Guice Module interface as an SPI

This is a sample project illustrating how the [Module][javadoc-Module]
interface from [Guice][guice] can be used as a service provider interface in an
application.
This pattern allows for a service provider to supply its own implementation of
a service at application deployment time.

## Components

### The **service**

The **service** module defines the `Service` interface, which represents a
service that may be implemented in different ways.

### The **app**

The **app** module provides its own default `Service` implementation but also
uses [ServiceLoader][javadoc-ServiceLoader] to locate alternate implementations
from service providers.

`AppInit` illustrates the service loading mechanism while the service itself is
exercised in `index.jsp`.

### The **overrider**

The **overrider** module is an example of a service provider. It provides its
own implementation of `Service` and exposes this through its own `Module`
implementation. Note how neither **app** nor **overrider** are aware of the
other's existence. Note also that **overrider** is able to define its own
internal service and that it may provides bindings for these in its own
`Module`.

## Running the sample

The sample should be able to run on any Servlet 3.0 container. To make things
easier, we use [embedded GlassFish][emb-gf].

    mvn clean install && mvn embedded-glassfish:run --projects app

Once GlassFish is up and running you can check out the result on your browser:

    http://localhost:8080/spi/

That should display a message provided by the service implementation in use.

The `override` profile is defined to include **overrider** jar in the resulting
WAR file. This causes the alternate service implementation to be used by
**app**.

    mvn clean install -Poverride && mvn embedded-glassfish:run --projects app

Refreshing your browser should now show the alternate service message.

[guice]: http://code.google.com/p/google-guice/
[javadoc-Module]: http://google-guice.googlecode.com/git/javadoc/com/google/inject/Module.html
[javadoc-ServiceLoader]: http://docs.oracle.com/javase/6/docs/api/java/util/ServiceLoader.html
[emb-gf]: http://embedded-glassfish.java.net/

