# NanoShell
Simple Java shell, but with many useful features!

It has 15+ commands including useful commands like: `mkdir`, `rmdir`, `ls`, `pwd`, `rm`, `touch`, `cat`, `stat`, `ping`, and *many* others!

## Running
You can compile it using `make` or `make build` and then `make run` or running the jar file with `java -jar NanoShell.jar`.

If there are any error messages, while compiling or running, I will be happy to fix! Just open an issue and I will help you.

## Plugins
Plugins are apps, features or even games to NanoShell.

Plugins are located in: `com/HappyCow/Plugins`.

You can create additional plugins by creating a java file and following the example file `ExamplePlugin.java` or `ClockScreensaver.java`.

Run plugins using: `pluginload com.HappyCow.Plugins.<YourPluginNameHere!>`.

> Note:
> Make sure to don't use the file extension (e.g. `.java` or `.class`) because it will not work, use just the name of the file (e.g. `pluginload com.HappyCow.Plugins.ExamplePlugin`.

## Contribute
If you want to contribute to NanoShell, fork the repo, do changes and create a pull request, if I like it, I will merge it into the main branch.
