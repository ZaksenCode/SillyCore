# SillyCore (SCore)
My personal library for easier and faster plugin creation. <br>
To use SCore, you must first add a maven repository to your build configuration:
```
maven {
  url = uri("https://maven.pkg.github.com/ZaksenCode/SkillifyCore")
  credentials {
    username = project.findProperty("gpr.user") as String? ?: System.getenv("GITHUB_USERNAME")
    password = project.findProperty("gpr.key") as String? ?: System.getenv("GITHUB_TOKEN")
  }
}
```
To make this work you will need to create a token and specify the environment variables GITHUB_USERNAME and GITHUB_TOKEN, you can do this using `export` commands for linux: <br>
```
export GITHUB_TOKE="YOUR_TOKEN"
export GITHUB_USERNAME="YOUR_USERNAME"
```
Now need to add SCore as a dependency, also need to specify `silly-core-version` in gradle.properties:
```
implementation("me.zaksen:silly-core:${property("silly-core-version")}")
```
