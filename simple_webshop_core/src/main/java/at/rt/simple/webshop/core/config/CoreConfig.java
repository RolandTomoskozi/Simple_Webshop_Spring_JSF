package at.rt.simple.webshop.core.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Spring Konfiguration fuer das Core-Paket<br>
 * Beinhaltet:
 * <ul>
 * <li>Component-Scan fuer die Service-Packages</li>
 * <li>Importiert die Perstistence Konfiguration {@link PersistenceConfig}</li>
 * </ul>
 *
 * @author Roland Tömösközi (roland.toemoeskoezi@outlook.com)
 * Created on 18.04.2021
 */
@Configuration
@ComponentScan(basePackages = {"at.rt.simple.webshop.core.service"})
@Import(value = {PersistenceConfig.class})
public class CoreConfig {
}
