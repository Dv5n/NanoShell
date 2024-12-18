#ifndef SHELL_H
#define SHELL_H

#define BUFFER			256
#define CLEAR_SCREEN	"\033[H\033[2J\033[3J"

#include "function.h"
#include "plugin.h"

char cmd[BUFFER];

const char* version = "0.3.1";
const char* key = "C-HcrcF@TrVKITNU87GcUZV8h8px#yFe";
const char* releasedate = "18/12/2024";
uint32_t running; // Used to exit the shell.

extern Plugin* loadplugin(const char* pluginN);

#endif // SHELL_H
