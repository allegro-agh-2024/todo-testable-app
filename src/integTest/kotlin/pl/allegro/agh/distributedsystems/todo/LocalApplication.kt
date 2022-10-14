package pl.allegro.agh.distributedsystems.todo

fun main(args: Array<String>) {
    createSpringApplication()
        .apply {
            setAdditionalProfiles("local")
        }
        .run(*args)
}
