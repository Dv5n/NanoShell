#ifndef FUNCTION_H
#define FUNCTION_H

#include <string.h>
#include <stdlib.h>
#include <unistd.h>
#include <dirent.h>
#include <time.h>
#include <sys/stat.h>
#include <sys/sysinfo.h>
#include <sys/utsname.h>
#include <sys/statvfs.h>

extern const char* version;
extern const char* releasedate;
extern const char* key;

const char* identifier_name = "NANOSHELL_C_EDITION";
const char* original_author = "Dv5n";

/**
* Displays files and directories, in the specified path,
* use "." for the current directory.
* @param path The directory to look in.
*/
void Ls(const char* path)
{
	struct dirent *entry;
	DIR *directory = opendir(path);

	if (directory == NULL)
	{
		perror("ls");
		return;
	}
	while ((entry = readdir(directory)) != NULL)
	{
		if (strcmp(entry->d_name, ".") == 0 || strcmp(entry->d_name, "..") == 0) continue;

		if (entry->d_type == DT_DIR) printf("[dir ] %s\n", entry->d_name);

		else if (entry->d_type == DT_REG) printf("[file] %s\n", entry->d_name);
	}
	closedir(directory);
}

/**
* Changes to the specified directory.
* @param path The directory to change in.
*/
void Cd(const char* path)
{
	if (path == NULL)
	{
		printf("Directory string empty.\n");
		return;
	}

	if (chdir(path) != 0) perror("cd");
}

/**
* Displays the full path to the current working directory.
*/
void Pwd()
{
	char path[512];
	if (getcwd(path, sizeof(path)) != NULL) printf("%s\n", path);

	else perror("pwd");
}

/**
* Creates an empty folder.
* @param dir The folder name to be created.
* @param permissions The folder permissions.
*/
void Mkdir(const char* dir, unsigned int permissions)
{
	if (dir == NULL)
	{
		printf("Directory string empty.\n");
		return;
	}

	if (mkdir(dir, permissions) == -1) perror("mkdir");
}

/**
* Removes an empty directory.
* @param dir The directory to remove.
*/
void Rmdir(const char* dir)
{
	if (dir == NULL)
	{
		printf("Directory string empty.\n");
		return;
	}

	if (rmdir(dir) == -1) perror("rmdir");
}

/**
* Removes a specified file.
* param file The filename of the file to remove.
*/
void Rm(const char* file)
{
	if (file == NULL)
	{
		printf("File string empty.\n");
		return;
	}

	if (unlink(file) == -1) perror("rm");
}

/**
* Creates an empty file.
* @param filename The filename of the file to create.
*/
void Touch(const char* filename)
{
	if (filename == NULL)
	{
		printf("File string empty.\n");
		return;
	}

	FILE *file = fopen(filename, "a");
	if (file == NULL)
	{
		perror("touch");
		return;
	}

	fclose(file);
}

/**
* Displays the contents of a specified file.
* @param filename The filename of the file to view.
*/
void Cat(const char* filename)
{
	if (filename == NULL)
	{
		printf("File string empty.\n");
		return;
	}

	FILE* file = fopen(filename, "r");

	if (file == NULL)
	{
		perror("cat");
		return;
	}

	char line[1024];

	while (fgets(line, sizeof(line), file) != NULL)
	{
		printf("%s", line);
	}

	printf("\n");
	fclose(file);
}

/**
* Displays the information of a specified file.
* (e.g. Size, Last modified, etc)
* @param filename The filename of the file to check.
*/
void Stat(const char* filename)
{
	if (filename == NULL)
	{
		printf("File string empty.\n");
		return;
	}

	struct stat fileStat;
	if (stat(filename, &fileStat) < 0)
	{
		perror("stat");
		return;
	}

	printf("Filename     : %s\n", filename);
	printf("Size         : %ld bytes\n", fileStat.st_size);
	printf("Last Modified: %s", ctime(&fileStat.st_mtime));
	printf("Permissions  : %o\n", fileStat.st_mode & 0777);
}

/**
* Helper function to Tree(...)
* You should not run this.
* @param dirName the directory to view.
* @param depth the depth.
*/
void Print_tree(const char* dirName, int depth)
{
	DIR* dir = opendir(dirName);
	if (dir == NULL)
	{
		perror("tree");
		return;
	}

	struct dirent* entry;
	while ((entry = readdir(dir)) != NULL)
	{
		if (entry->d_name[0] == '.') continue;

		for (int i=0; i<depth; ++i)
		{
			printf("\t");
		}

		printf("%s\n", entry->d_name);
		char path[1024];
		snprintf(path, sizeof(path), "%s/%s", dirName, entry->d_name);

		struct stat entry_stat;
		if (stat(path, &entry_stat) == 0 && S_ISDIR(entry_stat.st_mode)) Print_tree(path, depth + 1);
	}
	closedir(dir);
}

/**
* Displays the directory structure,
* of the specified directory.
* Main Tree function, this is what you should use.
* @param dir The folder to view.
*/
void Tree(const char* dir)
{
	if (dir == NULL)
	{
		printf("Directory string empty.\n");
		return;
	}

	Print_tree(dir, 0);
}

/**
* Displays the current date and time,
* in a cool looking style.
*/
void Date()
{
	time_t now = time(NULL);

	struct tm *tm_struct = localtime(&now);

	if (tm_struct == NULL)
	{
		perror("Error");
		return;
	}

	printf("#=[Date&Time]==============#\n"
			"# %s"
			"#==========================#\n", asctime(tm_struct));
}

/**
* Check if it is Christmas.
* If yes then return 1,
* If not then return 0.
*/
int Christmas()
{
	time_t now = time(NULL);
	struct tm* tm_info = localtime(&now);

	// Check if December.
	if (tm_info->tm_mon == 11) return 1;

	return 0; // Not December.
}

/**
* Displays more information, and others,
* this should be used with the "help" command.
* And btw a cool Easter egg, did you find it?
* @param help The command.
*/
void MoreHelp(const char* help)
{
	if (help == NULL)
	{
		printf("No argument.\n");
		return;
	}

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
		printf("%s", ascii_art[7]); printf(" # License        : %s\n", "GNU General Public License V3");
		printf("%s", ascii_art[8]); printf("--------------------------------------------------\n");
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

/**
* Displays the information about the host system.
*/
void Sysinfo()
{
	struct utsname sys_info;
	if (uname(&sys_info) < 0)
	{
		perror("sysinfo");
		return;
	}

	printf("Operating System   : %s\n", sys_info.sysname);
	printf("Architecture       : %s\n", sys_info.machine);
	printf("Node Name          : %s\n", sys_info.nodename);
	printf("Release            : %s\n", sys_info.release);

	long cores = sysconf(_SC_NPROCESSORS_ONLN);
	if (cores > 0) printf("Available cores    : %ld cores\n", cores);
	else perror("Cores");
	struct sysinfo mem_info;
	if (sysinfo(&mem_info) == 0)
	{
		unsigned long total = mem_info.totalram / (1024 * 1024);
		unsigned long free = mem_info.freeram / (1024 * 1024);
		printf("Memory (free/total): %lu MB / %lu MB\n", free, total);
	}
	else perror("MemoryInfo");

	struct statvfs disk;
	if (statvfs("/", &disk) == 0)
	{
		unsigned long long totalDisk = (unsigned long long)disk.f_blocks * disk.f_frsize;
		unsigned long long freeDisk = (unsigned long long)disk.f_bfree * disk.f_frsize;
		printf("Disk   (free/total): %llu MB / %llu MB\n",
				freeDisk / (1024 * 1024),
				totalDisk / (1024 * 1024));
	}
	else perror("DiskInfo");
}

#endif // FUNCTION_H
