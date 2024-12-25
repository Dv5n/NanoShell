#=========================================================================================================#
# NanoShell - simple, but useful C shell!                                                                 #
#=[Options]===============================================================================================#
# The C compiler to use.
C := gcc

# NanoShell version.
VER := 0.3.5

# Output directory.
OUT_DIR := build

# Plugins directory.
PLUGIN_DIR := plugins

# Main executable.
EXEC := $(OUT_DIR)/NanoShell-$(shell uname -m)

# C Arguments for more efficient code.
ARG := -Werror -Wall -Wextra -Wundef -Wshadow -Wconversion -Wunreachable-code -flto -pipe -c  -O2
#=========================================================================================================#
build:
	$(info #=[NanoShell C Edition, version $(VER)]==#)
	$(info #============[Build Options]============#)
	$(info # Compiler    : $(C))
	$(info # Output dir  : $(OUT_DIR))
	$(info # Architecture: $(shell uname -m))
	$(info # Final binary: $(EXEC))
	$(info #=======================================#)
	@mkdir -p $(OUT_DIR)
	$(C) $(ARG) main.c -o $(OUT_DIR)/main.o
	$(C) $(ARG) shell.c -o $(OUT_DIR)/shell.o
	$(C) $(ARG) plugin.c -o $(OUT_DIR)/plugin.o
	$(C) -s $(OUT_DIR)/main.o $(OUT_DIR)/shell.o $(OUT_DIR)/plugin.o -o $(EXEC)
	@cd ./plugins && $(MAKE)

run: $(EXEC)
	$(EXEC)

clean:
	rm -rf $(OUT_DIR)/*
	rm -rf $(PLUGIN_DIR)/*.so
	rm -rf $(PLUGIN_DIR)/**/*.so

.PHONY: build run clean

# (NANOSHELL_C_EDITION) key: C-HcrcF@TrVKITNU87GcUZV8h8px#yFe
# CatKeyStyle VERSION 2
# Note: a CatKeyStyle key is a 32 characters long (2-prefix"C-"+30-key) key that must be valid,
# to be valid it must pass all checks in a specific checker.
#=========================================================================================================#
