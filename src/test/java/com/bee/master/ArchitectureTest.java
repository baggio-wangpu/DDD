package com.bee.master;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

public class ArchitectureTest {

    @Test
    public void common_module_should_not_depends_on_any_other_module() {
        JavaClasses importedClasses = new ClassFileImporter().importPackages(this.getClass().getPackage().getName());

        ArchRule rule = noClasses().that()
                        .resideInAPackage("..common..")
                .should().dependOnClassesThat().resideInAPackage("..domain..")
                .orShould().dependOnClassesThat().resideInAPackage("..application..")
                .orShould().dependOnClassesThat().resideInAPackage("..adapter..");

        rule.check(importedClasses);
    }

    @Test
    public void domain_module_should_not_depends_on_application_or_adapter_module() {
        JavaClasses importedClasses = new ClassFileImporter().importPackages(this.getClass().getPackage().getName());

        ArchRule rule = noClasses().that()
                .resideInAPackage("..domain..")
                .should().dependOnClassesThat().resideInAPackage("..application..")
                .orShould().dependOnClassesThat().resideInAPackage("..adapter..");

        rule.check(importedClasses);
    }
}
