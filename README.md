# Lóra
Lóra is a Czech card game, similar to Mariáš. Check out official rules here: http://lora.nikde.eu/lora-pravidla.htm

This app aims to bring this card game to mobile devices. It's is currently in very early stages of development.

## Development:

### Coding conventions
Project uses [Detekt](https://detekt.dev/) to enforce code style. We try to follow [official Kotlin coding conventions](https://kotlinlang.org/docs/coding-conventions.html) with few minor adjustments
If you want to run checks locally, type ``` ./gradlew detekt --continue ``` into your terminal or ``` ./gradlew detekt --auto-correct --continue ``` for automatic corrections.

### Architecture
Project aims to folow Clean Architerture.