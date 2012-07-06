/*
 * Copyright 2012 samson.ph.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ph.samson.sample.spi.app;

import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import java.util.List;
import java.util.ServiceLoader;
import javax.servlet.annotation.WebListener;

import static com.google.inject.Guice.createInjector;
import static com.google.inject.util.Modules.override;
import static java.util.Arrays.asList;
import static java.util.ServiceLoader.load;

@WebListener()
public class AppInit extends GuiceServletContextListener {

    @Override
    protected Injector getInjector() {
        System.out.println("\n\n\n\n\n************** AppInit ****************");
        ServiceLoader<Module> spiModules = load(Module.class);
        for (Module module : spiModules) {
            System.out.println("SPI Module: " + module);
        }
        System.out.println("************** AppInit ****************\n\n\n\n\n");
        return createInjector(override(defaultModules()).with(spiModules));
    }

    private static List<? extends Module> defaultModules() {
        return asList(new DefaultModule(), new ServletModule());
    }
}
