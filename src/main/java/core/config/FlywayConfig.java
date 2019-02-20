package core.config;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FlywayConfig {

    @Value("${spring.flyway.need.to.clean}")
    private boolean needToClean;

    @Bean("flyway")
    public Flyway createFlywayConfig() {
        Flyway flyway = Flyway.configure()
                .dataSource("jdbc:mysql://localhost:3306/musiks?serverTimezone=UTC", "root", "root")
                .baselineOnMigrate(true)
                .load();

        if (needToClean) {
            flyway.clean();
        }
        flyway.migrate();

        return flyway;
    }
}
