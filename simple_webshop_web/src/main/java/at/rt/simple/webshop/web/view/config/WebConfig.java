package at.rt.simple.webshop.web.view.config;

import at.rt.simple.webshop.core.config.CoreConfig;
import at.rt.simple.webshop.web.view.spring.ViewScope;
import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.HashMap;
import java.util.Map;

/**
 * Spring Konfiguration fuer das Core-Paket<br>
 * Beinhaltet:
 * <ul>
 * <li>Component-Scan fuer die Controller und Converter-Packages</li>
 * <li>Importiert die Perstistence Konfiguration {@link at.rt.simple.webshop.core.config.CoreConfig}</li>
 * </ul>
 */
@Configuration
@ComponentScan(basePackages = {"at.rt.simple.webshop.web.view.controller",
        "at.rt.simple.webshop.web.view.converter"})
@Import(value = {CoreConfig.class})
public class WebConfig {

    /**
     * Konfiguriert den ViewScope.
     *
     * @return CustomScopeConfigurer mit eigenem ViewScope
     */
    @Bean
    public CustomScopeConfigurer viewScope() {
        Map<String, Object> scopes = new HashMap<>(1);
        scopes.put("view", new ViewScope());

        CustomScopeConfigurer customScopeConfigurer = new CustomScopeConfigurer();
        customScopeConfigurer.setScopes(scopes);
        return customScopeConfigurer;
    }

}
