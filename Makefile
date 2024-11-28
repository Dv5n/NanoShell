#===========================================================================================#
# NanoShell, Simple UNIX shell in Java.
# GNU GPL v3 License.
#=[Options]=================================================================================#
JAVAC=javac
PATH_M=com/HappyCow/NanoShell
PATH_N=com/HappyCow/SimpleClock
PATH_O=com/HappyCow/ShellUtilities
PATH_P=com/HappyCow/Plugins
PATH_P_M=com/HappyCow/Plugins/PluginManager
#===========================================================================================#
build:
	$(info Info: Compiler: $(JAVAC))
	$(JAVAC) $(PATH_M)/*.java
	$(JAVAC) $(PATH_N)/*.java
	$(JAVAC) $(PATH_O)/*.java
	$(JAVAC) $(PATH_P)/*.java
	jar cfe NanoShell.jar $(PATH_M)/NanoShell $(PATH_M)/*.class $(PATH_N)/*.class $(PATH_O)/*.class $(PATH_P)/*.class $(PATH_P_M)/*.class

run:
	java $(PATH_M)/NanoShell

runJar:
	java -jar NanoShell.jar

build-1:
	$(JAVAC) $(PATH_M)/*.java
build-2:
	$(JAVAC) $(PATH_N)/*.java
build-3:
	$(JAVAC) $(PATH_O)/*.java
build-4:
	$(JAVAC) $(PATH_P)/*.java
build-5:
	jar cfe NanoShell.jar $(PATH_M)/NanoShell $(PATH_M)/*.class $(PATH_N)/*.class $(PATH_O)/*.class $(PATH_P)/*.class $(PATH_P_M)/*.class
