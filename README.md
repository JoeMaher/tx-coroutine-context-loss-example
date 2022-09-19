Minimum reproducible example for spring framework issue [Propagate CoroutineContext in reactive transaction](https://github.com/spring-projects/spring-framework/pull/27308).

Test cases:

- Create example entity - PASSES
- Create example entity in `@Transaction` - FAILS
- Create example entity in `TransactionalOperator` - FAILS

The failures are due to the [coroutine context assertion](https://github.com/JoeMaher/tx-coroutine-context-loss-example/blob/d71ba4f615d2fc7ae15dc05cd842d2be29b33559/src/main/kotlin/com/jmaher/txcoroutineexample/service/ExampleService.kt#L29) performed in `ExampleService`.

How to run:
1. `docker compose up -d` (simple postgres database)
2. `./gradlew test`