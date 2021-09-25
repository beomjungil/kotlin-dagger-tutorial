# `kotlin-dagger-tutorial`

> **[Dagger Tutorial](dagger.dev/tutorial/)** Final Code. Written in Kotlin


## Try it out.

```bash
$ ./gradlew run -q --console=plain

   ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
              A  T  M
   ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━┛ 
```

Then, enter command!

### Commands

| Command      | Usage                            |
|--------------|----------------------------------|
| Login        | `login <ANY NAME>`               |
| Deposit      | `deposit <DOUBLE ex: 20, 20.2>`  |
| Withdraw     | `withdraw <DOUBLE ex: 20, 20.2>` |
| Logout       | `logout`                         |
| Hello World! | `hello`                          |

## Compare with [Java Example](https://github.com/google/dagger/tree/b5990a0641a7860b760aa9055b90a99d06186af6/java/dagger/example/atm)

- To use Dagger with kotlin, `kapt("com.google.dagger:dagger-compiler:<version>")` is important.
- In this Kotlin example, `DoubleCommand` is implemented instead of [`BigDecimalCommand`](https://github.com/google/dagger/blob/b5990a0641a7860b760aa9055b90a99d06186af6/java/dagger/example/atm/BigDecimalCommand.java)
- [`SystemOutModule`](https://github.com/google/dagger/blob/b5990a0641a7860b760aa9055b90a99d06186af6/java/dagger/example/atm/SystemOutModule.java) is little different. But never mind.
- Some logic could be simplified. Yes, that's the reason why Kotlin exists.
  - Compare simplified logic with [Java Example](https://github.com/google/dagger/tree/b5990a0641a7860b760aa9055b90a99d06186af6/java/dagger/example/atm) yourself.
