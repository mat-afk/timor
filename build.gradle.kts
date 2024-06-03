plugins {
    id("java")
    id("antlr")
    id("application")
}

group = "org.timor"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    antlr("org.antlr:antlr4:4.13.1")
    implementation("org.antlr:antlr4-runtime:4.13.1")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
}

application {
    mainClass.set("org.timor.Main")
}

tasks {

    generateGrammarSource {
        arguments = arguments + listOf("-visitor", "-long-messages")
    }

    compileJava {
        dependsOn(generateGrammarSource)
    }

    getByName<Test>("test") {
        useJUnitPlatform()
    }
}
