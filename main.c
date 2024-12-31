// Licensed under GPL (General Public License)
/*
Copyright (C) 2024 2025 Dv5n

	This program is free software: you can redistribute it and/or modify
	it under the terms of the GNU General Public License as published by
	the Free Software Foundation, either version 3 of the License, or
	(at your option) any later version.

	This program is distributed in the hope that it will be useful,
	but WITHOUT ANY WARRANTY; without even the implied warranty of
	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
	GNU General Public License for more details.

	You should have received a copy of the GNU General Public License
	along with this program.  If not, see <https://www.gnu.org/licenses/>.
*/

#include "include/main.h"

int main(void)
{
	printf(WELCOME); // Prints the WELCOME macro defined in main.h

	NanoShell("HappyCow :-) "); // Running the shell, with a specified prompt.
	return 0; // Exits with no errors.
}
