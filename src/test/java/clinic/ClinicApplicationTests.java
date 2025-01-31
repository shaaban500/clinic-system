package clinic;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.modulith.core.ApplicationModules;

@SpringBootTest
class ClinicApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void verifyModularity() {
		var modules = ApplicationModules.of(ClinicApplication.class);

		modules.forEach(System.out::println);

		ApplicationModules.of(ClinicApplication.class).verify();

	}
}
