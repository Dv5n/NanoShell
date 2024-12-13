#===========================================================================================#
# NanoShell, Simple Java UNIX shell.
# Simple, but useful!
#=[Options]=================================================================================#
JAVAC=javac
M_PATH=com/HappyCow
MAIN_CLASS=com.HappyCow.NanoShell.NanoShell
CLASS=$(wildcard $(M_PATH)/**/*.class)
#===========================================================================================#

.PHONY: build run runJar clean

build: $(CLASS)
	$(info Building JAR...)
	@jar cfe NanoShell.jar \
	$(M_PATH)/NanoShell/NanoShell \
	$(M_PATH)/NanoShell/*.class \
	$(M_PATH)/SimpleClock/*.class \
	$(M_PATH)/ShellUtilities/*.class \
	$(M_PATH)/Plugins/*.class \
	$(M_PATH)/Plugins/PluginManager/*.class

%.class: %.java
	@$(info Compiling $<...)
	@$(JAVAC) -Xdiags:verbose $<

run:
	$(info Running...)
	@java $(MAIN_CLASS)

runJar:
	$(info Running JAR...)
	@java -jar NanoShell.jar

clean:
	$(info Cleaning up...)
	@rm -f $(CLASS) NanoShell.jar
