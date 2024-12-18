#ifndef BASIC_MORE_H
#define BASIC_MORE_H

#include <time.h>

extern const char* version;
extern const char* releasedate;
extern const char* key;

const char* identifier_name = "NANOSHELL_C_EDITION";
const char* original_author = "Dv5n";

int Christmas()
{
	time_t now = time(NULL);
	struct tm* tm_info = localtime(&now);

	// Check if December.
	if (tm_info->tm_mon == 11) return 1;

	return 0; // Not December.
}

void MoreHelp(const char* help)
{
	char* ascii_art[9];

	if (Christmas() == 1)
	{

		ascii_art[0]="        *         ";
		ascii_art[1]="       /*\\       ";
		ascii_art[2]="      /o*o\\      ";
		ascii_art[3]="     /*o*o*\\     ";
		ascii_art[4]="    /o*o*o*o\\    ";
		ascii_art[5]="   /*o*o*o*o*\\   ";
		ascii_art[6]="  /o*o*o*o*o*o\\  ";
		ascii_art[7]="       |||        ";
		ascii_art[8]="[Merry Christmas!]";
	}
	else
	{
		ascii_art[0]="        ^         ";
		ascii_art[1]="       ^^^        ";
		ascii_art[2]="      ^^^^^       ";
		ascii_art[3]="     ^^^^^^^      ";
		ascii_art[4]="    ^^^^^^^^^     ";
		ascii_art[5]="   ^^^^^^^^^^^    ";
		ascii_art[6]="  ^^^^^^^^^^^^^   ";
		ascii_art[7]="       |||        ";
		ascii_art[8]="   [NanoShell]    ";
	}

	if (strcmp(help, "system") == 0)
	{
		printf("%s", ascii_art[0]); printf(" NanoShell C Edition, Version: %s\n", version);
		printf("%s", ascii_art[1]); printf(" --------------------------------------------------\n");
		printf("%s", ascii_art[2]); printf(" # Identifier Key : %s\n", key);
		printf("%s", ascii_art[3]); printf(" # Identifier Name: %s\n", identifier_name);
		printf("%s", ascii_art[4]); printf(" # Original Author: %s\n", original_author);
		printf("%s", ascii_art[5]); printf(" # Latest Release : %s\n", releasedate);
		printf("%s", ascii_art[6]); printf(" # First Release  : %s\n", "07/12/2024");
		printf("%s", ascii_art[7]); printf("--------------------------------------------------\n");
		printf("%s", ascii_art[8]); printf(" <- NanoShell official ASCII art, \"The Nano Tree\".\n");
	}

	else if (strcmp(help, "help") == 0)
	{
		printf("#=[Extended help]==================================#\n"
				"1. help   = Shows information on \"help\" arguments.\n"
				"2. system = Shows information about NanoShell.\n"
				"#=================================================#\n");
	}

	else printf("Command not found: \"%s\"\n"
				"Type \"help help\" for information on commands.\n", help);
}

#endif // BASIC_MORE_H