#===========================================================================================#
# NanoShell, Simple UNIX shell in Java.
# GNU GPL License.
#=[Options]===================================================================================#
JAVAC=javac
PATH_M=com/HappyCow/NanoShell
PATH_N=com/HappyCow/SimpleClock
PATH_O=com/HappyCow/ShellUtilities
#PATH_P=com/HappyCow/ShellFun
#===========================================================================================#
build:
	$(info Info: Compiler: $(JAVAC))
	$(JAVAC) $(PATH_M)/*.java
	$(JAVAC) $(PATH_N)/*.java
	$(JAVAC) $(PATH_O)/*.java
	jar cfe NanoShell.jar $(PATH_M)/NanoShell $(PATH_M)/*.class $(PATH_N)/*.class $(PATH_O)/*.class

run:
	java $(PATH_M)/NanoShell

runJar:
	java -jar NanoShell.jar
