package outputter

import dagger.Module
import dagger.Provides
import outputter.Outputter

@Module
abstract class SystemOutModule {
    companion object {
        @Provides
        fun textOutputter() = object : Outputter {
            override fun output(output: String) = println(output)
        }
    }
}