# NanoShell
![1000103669](https://github.com/user-attachments/assets/6dea937f-714b-4e24-935e-107b597cbc3c)

Simple Java shell, but with many useful features!

NanoShell has 28+ commands, including useful commands like: `mkdir`, `rmdir`, `ls`, `pwd`, `rm`, `touch`, `cat`, `stat`, `ping`, `find`, `systeminfo`, `cp`, `mv`, `tree`, and *many* others!

NanoShell also supports running multiple commands with `&&`.
Example use: `clear && ls && echo example`

## Running
You can compile NanoShell using `make` and then `make run` or running the jar file with `make runJar`.

If you run into any errors while compiling or running the shell, let me know! Just open an issue, and I will be happy to fix it.

## Plugins
Plugins are apps, features or even games to NanoShell.

**Important** NOTE: Plugins are experimental and might have bugs. I'd love to hear your feedback!

Plugins are located in: `com/HappyCow/Plugins`.

You can create additional plugins by creating a `.java` file and following the example file `ExamplePlugin.java` or `ClockScreensaver.java`.

Run plugins using: `pluginload com.HappyCow.Plugins.<YourPluginNameHere!>`.

> Note:
> Make sure to use just the name of the class file (e.g. `pluginload com.HappyCow.Plugins.ExamplePlugin`.

## Contribute
If you'd like to contribute to NanoShell, fork the repository, make your changes, and submit a pull request. If I like the changes, I will merge them into the main (dev) branch.

## Additional Notes
**WARNING:** NanoShell is almost complete, and as a result, future commits may be delayed, or the project might even be discontinued. However, with your support, I can keep this project alive for years to come.

I have been working on and testing the C version, which is faster and has simpler code.

The transition to C is coming!

UPDATE: Plugin system implemented in C!
