package jpmorgan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"org.jpmorgan"})
public class JpMorganTest {

    public static void main(String[] args) {

        SpringApplication.run(JpMorganTest.class, args);

    }
}

