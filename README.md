A Gradle plugin to log project dependencies.

---

Publishing
-----------

Publish the plugin to the local Maven repository (`~/.m2`):

```bash
./gradlew publishToMavenLocal
```

---

Including to a consumer project
--------------------------------

Project-level `build.gradle`:

```groovy
repositories {
    // Local Maven repository should take priority over the central one:
    mavenLocal()
    // ...
}

dependencies {
    classpath group: 'jp.neechan.akari', name: 'LogDependenciesPlugin', version: '1.0.0'
}
```

Module-level `build.gradle`:

```groovy
plugins {
    id 'jp.neechan.akari.LogDependenciesPlugin'
}

// At the bottom of the file you can configure the plugin to print types of dependencies,
// e.g. print implementation, testImplementation, etc. types.
logDependencies {
    configuration = ["implementation"]
}
```

---

Run the plugin in a consumer project
------------------------------------

```bash
./gradlew logDependencies
```
