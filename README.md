# NanoShell
![1000103669](https://github.com/user-attachments/assets/6dea937f-714b-4e24-935e-107b597cbc3c)

Simple C shell, but with many useful features!

NanoShell has 15+ commands, including useful commands like: `mkdir`, `rmdir`, `ls`, `pwd`, `rm`, `touch`, `cat`, `stat`, `sysinfo`, `tree`, and *many* others!

## Running
You can compile NanoShell using `make` and then `make run`.

If you run into any errors while compiling or running the shell, let me know! Just open an issue, and I will be happy to fix it.

## Plugins
Plugins are apps, features or even games to NanoShell.

**Important** NOTE: Plugins are experimental and might have bugs. I'd love to hear your feedback!

Plugins are located in: `plugins/`.

You can create additional plugins by creating a `.c` file and following the example file `ExamplePlugin.c` or `ClockScreensaver.c`.

**Make sure** to also add your plugin in the Makefile.

Run plugins using: `lplugin plugins/<YourPluginHere!>`.

## Contribute
If you'd like to contribute to NanoShell, fork the repository, make your changes, and submit a pull request. If I like the changes, I will merge them into the main (dev) branch.
