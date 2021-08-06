import com.google.protobuf.gradle.*
plugins {
    id("org.jetbrains.kotlin.jvm") version "1.4.32"
    id("org.jetbrains.kotlin.kapt") version "1.4.32"
    id("com.github.johnrengelman.shadow") version "7.0.0"
    id("io.micronaut.application") version "1.5.3"
    id("org.jetbrains.kotlin.plugin.allopen") version "1.4.32"
    id("com.google.protobuf") version "0.8.15"
}

version = "0.1"
group = "com.bpsw.cabs"

val exposedVersion=project.properties.get("exposedVersion")
val grpcKotlinVersion=project.properties.get("grpcKotlinVersion")
val grpcVersion=project.properties.get("grpcVersion")
val junitVersion=project.properties.get("junitVersion")
val kotlinVersion=project.properties.get("kotlinVersion")
val micronautGrpcVersion=project.properties.get("micronautGrpcVersion")
val micronautKotlinVersion=project.properties.get("micronautKotlinVersion")
val micronautVersion=project.properties.get("micronautVersion")
val protocVersion=project.properties.get("protocVersion")

repositories {
    mavenCentral()
}

micronaut {
    testRuntime("junit5")
    processing {
        incremental(true)
        annotations("com.bpsw.cabs.*")
    }
}

dependencies {
    implementation("io.micronaut:micronaut-runtime:${micronautVersion}")
    implementation("io.micronaut.grpc:micronaut-grpc-server-runtime:${micronautGrpcVersion}")
    implementation("io.micronaut.kotlin:micronaut-kotlin-runtime:${micronautKotlinVersion}")
    implementation("org.jetbrains.kotlin:kotlin-reflect:${kotlinVersion}")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:${kotlinVersion}")
    implementation("ch.qos.logback:logback-classic:1.2.5")
    implementation("io.micronaut:micronaut-validation:${micronautVersion}")

    implementation("io.grpc:grpc-services:${grpcKotlinVersion}")

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.12.4")

    implementation("org.jetbrains.exposed:exposed-core:${exposedVersion}")
    implementation("org.jetbrains.exposed:exposed-dao:${exposedVersion}")
    implementation("org.jetbrains.exposed:exposed-jdbc:${exposedVersion}")

    implementation("com.h2database:h2")
    implementation("com.zaxxer:HikariCP")
    // ?? implementation("org.slf4j:slf4j-nop")

    testImplementation("io.micronaut:micronaut-http-client:${micronautVersion}")
    testAnnotationProcessor("io.micronaut:micronaut-inject-java")
    testImplementation("org.junit.jupiter:junit-jupiter-api:${junitVersion}")
    testImplementation("io.micronaut.test:micronaut-test-junit5:2.3.7")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:${junitVersion}")

}


application {
    mainClass.set("com.bpsw.cabs.ApplicationKt")
}
java {
    sourceCompatibility = JavaVersion.toVersion("1.8")
}

tasks {
    compileKotlin {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }
    compileTestKotlin {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }


}
sourceSets {
    main {
        java {
            srcDirs("build/generated/source/proto/main/grpc")
            srcDirs("build/generated/source/proto/main/java")
        }
    }
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:${protocVersion}"
    }
    plugins {
        id("grpc") {
            artifact = "io.grpc:protoc-gen-grpc-java:${grpcVersion}"
        }
    }
    generateProtoTasks {
        ofSourceSet("main").forEach {
            it.plugins {
                // Apply the "grpc" plugin whose spec is defined above, without options.
                id("grpc")
            }
        }
    }
}

