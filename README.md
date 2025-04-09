Эту библиотеку я писал для своих плагинов Spigot/Paper. Но ее можете использовать и вы. (Чуть ниже будет инструкция).
Версия Java: JDK 16

Чуть ниже представлена инструкция для удаленного доступа к библиотеке.
Внимание! Там где VERSION надо указать версию исходя из названия под Releases(Например: v1.0-SNAPSHOT).

Для Maven:
```
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>

<dependency>
    <groupId>com.github.purehe4rt</groupId>
    <artifactId>pureAPI</artifactId>
    <version>VERSION</version>
</dependency>
```
Для Gradle:
```
dependencyResolutionManagement {
  repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
  repositories {
    mavenCentral()
    maven { url 'https://jitpack.io' }
  }
}

dependencies {
      implementation 'com.github.purehe4rt:pureAPI:VERSION'
}
```
