#ifndef SHELL_H
#define SHELL_H

#define BUFFER			256
#define CLEAR_SCREEN	"\033[H\033[2J\033[3J"

#include "function.h"
#include "plugin.h"

char cmd[BUFFER];

const char* version = "0.3.6";
const char* releasedate = "01/01/2025";

// For plugins.
extern Plugin* loadplugin(const char* pluginName);

extern void discover();

#endif // SHELL_H
