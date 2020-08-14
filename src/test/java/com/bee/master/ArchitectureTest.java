package com.bee.master;

import com.bee.master.common.ddd.framework.Entity;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

public class ArchitectureTest {

    public static final String COMMON = "..common..";
    public static final String DOMAIN = "..domain..";
    public static final String APPLICATION = "..application..";
    public static final String ADAPTER = "..adapter..";

    @Test
    public void common_module_should_not_depends_on_any_other_module() {
        JavaClasses importedClasses = new ClassFileImporter().importPackages(this.getClass().getPackage().getName());

        ArchRule rule = noClasses().that()
                        .resideInAPackage(COMMON)
                .should().dependOnClassesThat().resideInAPackage(DOMAIN)
                .orShould().dependOnClassesThat().resideInAPackage(APPLICATION)
                .orShould().dependOnClassesThat().resideInAPackage(ADAPTER);

        rule.check(importedClasses);
    }

    @Test
    public void domain_module_should_not_depends_on_application_or_adapter_module() {
        JavaClasses importedClasses = new ClassFileImporter().importPackages(this.getClass().getPackage().getName());

        ArchRule rule = noClasses().that()
                .resideInAPackage(DOMAIN)
                .should().dependOnClassesThat().resideInAPackage(APPLICATION)
                .orShould().dependOnClassesThat().resideInAPackage(ADAPTER);

        rule.check(importedClasses);
    }

    @Test
    public void domain_model_should_not_export_public_setter_and_constructor() {
        JavaClasses importedClasses = new ClassFileImporter().importPackages(this.getClass().getPackage().getName());

        ArchRule rule = classes().that()
          .resideInAPackage(DOMAIN)
          .and()
          .areAnnotatedWith(Entity.class)
          .should().haveOnlyPrivateConstructors();

        rule.check(importedClasses);
    }

}
