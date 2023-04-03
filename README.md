## Micronaut Redis Playground

### CRaC how to build and check
1. Start redis server `docker compose -f localinfrastrucutre/docker-compose.yml up -d`
2. Build docker image `./gradlew -i dockerBuildCrac`
3. Uncomment `redis-playground-crac` section in `localinfrastrucutre/docker-compose.yml` and execute again command from the 1st point.
4. Execute script `customWarmupScript.sh` and check responses
5. Get logs from the container and check if any errors occurred `docker logs localinfrastrucutre-redis-playground-crac-1`

### GraalVM how to build and check
TBD.

### Regular how to build and check
TBD.

## Run benchmark tool
TBD.

## Micronaut 3.9.0-SNAPSHOT Documentation

- [User Guide](https://docs.micronaut.io/snapshot/guide/index.html)
- [API Reference](https://docs.micronaut.io/snapshot/api/index.html)
- [Configuration Reference](https://docs.micronaut.io/snapshot/guide/configurationreference.html)
- [Micronaut Guides](https://guides.micronaut.io/index.html)
---

- [Shadow Gradle Plugin](https://plugins.gradle.org/plugin/com.github.johnrengelman.shadow)
## Feature crac documentation

- [Micronaut Support for CRaC (Coordinated Restore at Checkpoint) documentation](https://micronaut-projects.github.io/micronaut-crac/latest/guide)

- [https://wiki.openjdk.org/display/CRaC](https://wiki.openjdk.org/display/CRaC)


## Feature reactor documentation

- [Micronaut Reactor documentation](https://micronaut-projects.github.io/micronaut-reactor/snapshot/guide/index.html)


## Feature http-client documentation

- [Micronaut HTTP Client documentation](https://docs.micronaut.io/latest/guide/index.html#httpClient)


## Feature lombok documentation

- [Micronaut Project Lombok documentation](https://docs.micronaut.io/latest/guide/index.html#lombok)

- [https://projectlombok.org/features/all](https://projectlombok.org/features/all)


