#===========================================================================================#
# NanoShell, Simple Java UNIX shell.
# Simple, but useful!
#=[Options]=================================================================================#
JAVAC=javac
MAIN_CLASS=com.HappyCow.NanoShell.NanoShell
SOURCE=$(wildcard com/HappyCow/**/*.java)
CLASS=$(SOURCE:.java=.class)
## Colors
RED = \033[31m
GREEN = \033[32m
BLUE = \033[34m
RESET = \033[0m
#===========================================================================================#

.PHONY: build run runJar clean

build: $(CLASS)
	$(info Building JAR...)
	@jar cfe NanoShell.jar $(MAIN_CLASS) -C com .

%.class: %.java
	@echo "$(RED)Compiling$(RESET)$(BLUE) $<...$(RESET)"
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
