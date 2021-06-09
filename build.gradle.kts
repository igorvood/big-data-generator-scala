plugins {
    scala
}

repositories {
    mavenCentral()
}

sourceSets {
    main {
        withConvention(ScalaSourceSet::class) {
            scala {
                setSrcDirs(listOf("src/main/scala"))
            }
        }
    }
    test {
        withConvention(ScalaSourceSet::class) {
            scala {
                setSrcDirs(listOf("test/main/scala"))
            }
        }
    }
}

dependencies {

    implementation("org.scalacheck:scalacheck_2.13:1.15.2")
    implementation("org.scala-lang:scala-library:2.13.6")

    testImplementation("junit:junit:4.13")
    testImplementation("com.novocode:junit-interface:0.11")


    scalaCompilerPlugins("org.typelevel:kind-projector_2.13.1:0.11.0")
}

tasks.withType<ScalaCompile>().configureEach {
    scalaCompileOptions.apply {
        isForce = true
    }

    scalaCompileOptions.forkOptions.apply {
        memoryMaximumSize = "1g"
        jvmArgs = listOf("-XX:MaxPermSize=512m")
    }
}



