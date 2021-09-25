package command

abstract class SingleArgCommand: Command {
    protected abstract fun handleArg(arg: String): Command.Result

    override fun handleInput(input: List<String>): Command.Result {
        return if (input.size == 1) handleArg(input.first()) else Command.Result(Command.Status.INVALID)
    }
}