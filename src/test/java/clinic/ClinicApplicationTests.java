package clinic;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.modulith.core.ApplicationModules;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

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

	@Test
	void validateCleanArchitectureDependencies() {
		JavaClasses importedClasses = new ClassFileImporter()
				.withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
				.importPackages("clinic.appointmentBooking");

		layeredArchitecture()
				.consideringOnlyDependenciesInLayers()
				// Define layers
				.layer("API").definedBy("clinic.appointmentBooking.internal.api..")
				.layer("Application").definedBy("clinic.appointmentBooking.internal.application..")
				.layer("Domain").definedBy("clinic.appointmentBooking.internal.domain..")
				.layer("Infrastructure").definedBy("clinic.appointmentBooking.internal.infrastructure..")

				// Define layer dependencies
				.whereLayer("Domain").mayNotAccessAnyLayer()
				.whereLayer("Application").mayOnlyAccessLayers( "Domain")
				.whereLayer("Infrastructure").mayOnlyAccessLayers("API", "Application", "Domain")
				.whereLayer("API").mayOnlyAccessLayers("Infrastructure", "Application", "Domain")

				.check(importedClasses);
	}

	@Test
	void validateHexagonalArchitectureDependencies() {
		JavaClasses importedClasses = new ClassFileImporter()
				.withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
				.importPackages("clinic.appointmentManagement.internal");

		layeredArchitecture()
				.consideringOnlyDependenciesInLayers()
				// Define layers
				.layer("Core").definedBy("clinic.appointmentManagement.internal.core..")
				.layer("Shell").definedBy("clinic.appointmentManagement.internal.shell..")

				// Define layer dependencies
				.whereLayer("Shell").mayOnlyAccessLayers("Core")
				.whereLayer("Core").mayNotAccessAnyLayer()

				.check(importedClasses);
	}

	@Test
	void validateLayeredArchitectureDependencies() {
		JavaClasses importedClasses = new ClassFileImporter()
				.withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
				.importPackages("clinic.doctorAvailability.internal");

		layeredArchitecture()
				.consideringOnlyDependenciesInLayers()
				// Define layers
				.layer("API").definedBy("clinic.doctorAvailability.internal.api..")
				.layer("Business").definedBy("clinic.doctorAvailability.internal.service..")
				.layer("Data").definedBy("clinic.doctorAvailability.internal.data..")

				// Define layer dependencies
				.whereLayer("API").mayOnlyAccessLayers("Business", "Data")
				.whereLayer("Business").mayOnlyAccessLayers("Data")
				.whereLayer("Data").mayNotAccessAnyLayer()

				.check(importedClasses);
	}
}
