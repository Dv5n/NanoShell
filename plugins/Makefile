#==============================================================================================================================================#
# NanoShell Plugins                                                                                                                            #
#=[Options]====================================================================================================================================#
C := gcc
ARG := -shared -fPIC -Werror -Wall -Wextra -Wconversion -flto -O2
#==============================================================================================================================================#
all:
	$(info #=[Compiling Plugins...]============================================#)
	$(C) $(ARG) ExamplePlugin.c -o ExamplePlugin.so
	$(C) $(ARG) ExampleAnimation.c -o ExampleAnimation.so
	$(C) $(ARG) ClockScreensaver.c -o ClockScreensaver.so
	$(C) $(ARG) ExampleFPSDisplay.c -o ExampleFPSDisplay.so
#==============================================================================================================================================#
