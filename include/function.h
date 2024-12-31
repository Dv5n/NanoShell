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

#define FILE_BUFFER	1024

extern const char* version; // NanoShell version.
extern const char* releasedate; // The date of the newest changes.

const char* identifier_name = "NANOSHELL_C_EDITION"; // Identifier, not really used.
const char* original_author = "Dv5n"; // The original author for NanoShell, don't change.

/**
* Displays files and directories, in the specified path,
* use "." for the current directory.
* @param path The directory to look in.
*/
void Ls(const char* path)
{
	struct dirent *entry;
	DIR *directory = opendir(path); // Open the directory.

	if (directory == NULL)
	{
		perror("Error");
		return; // Return (exit) if NULL. (failure)
	}
	while ((entry = readdir(directory)) != NULL)
	{
		if (strcmp(entry->d_name, ".") == 0 || strcmp(entry->d_name, "..") == 0) continue; // Skip "." and ".." directories.

		if (entry->d_type == DT_DIR) printf("[dir ] %s\n", entry->d_name); // Directories.

		else if (entry->d_type == DT_REG) printf("[file] %s\n", entry->d_name); // Files.
	}
	closedir(directory); // Close the directory to prevent memory leaks.
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

	if (chdir(path) != 0) perror("Error");
}

/**
* Displays the full path to the current working directory.
*/
void Pwd()
{
	char path[512];
	if (getcwd(path, sizeof(path)) != NULL) printf("%s\n", path);

	else perror("Error");
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

	if (mkdir(dir, permissions) == -1) perror("Error");
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

	if (rmdir(dir) == -1) perror("Error");
}

/**
* Removes a specified file.
* param file The file to remove.
*/
void Rm(const char* file)
{
	if (file == NULL)
	{
		printf("File string empty.\n");
		return;
	}

	if (unlink(file) == -1) perror("Error");
}

/**
* Creates an empty file.
* @param filename The file to create.
*/
void Touch(const char* filename)
{
	if (filename == NULL)
	{
		printf("File string empty.\n");
		return;
	}

	FILE *file = fopen(filename, "a"); // Open file in append mode.
	if (file == NULL)
	{
		perror("Error");
		return;
	}

	fclose(file); // Close the file to prevent memory leaks.
}

/**
* Displays the contents of a specified file.
* @param filename The file to view.
*/
void Cat(const char* filename)
{
	if (filename == NULL)
	{
		printf("File string empty.\n");
		return;
	}

	FILE* file = fopen(filename, "r"); // Open file in read mode.

	if (file == NULL) // If fopen(...) returned NULL, or failure, display the error message, and exit.
	{
		perror("Error");
		return;
	}

	char line[1024];

	while (fgets(line, sizeof(line), file) != NULL)
	{
		printf("%s", line);
	}

	printf("\n");
	fclose(file); // Close the file to prevent memory leaks.
}

/**
* Displays the information of a specified file.
* (e.g. Size, Last modified, etc)
* @param filename The file to check.
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
		perror("Error");
		return;
	}

	printf("Filename     : %s\n", filename);
	printf("Size         : %ld bytes\n", fileStat.st_size);
	printf("Last Modified: %s", ctime(&fileStat.st_mtime));
	printf("Permissions  : %o\n", fileStat.st_mode & 0777);
}

/**
* Displays the directory structure.
* Helper function to Tree(...)
* You should not use this.
* @param dirName the directory to view.
* @param depth the depth.
*/
void Print_tree(const char* dirName, int depth)
{
	DIR* dir = opendir(dirName);
	if (dir == NULL)
	{
		perror("Error");
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
* Find a specified file.
* @param filename The file to find.
*/
void Find(const char* filename, const char* dirnow)
{
	if (filename == NULL)
	{
		printf("file string empty.\n");
		return;
	}

	DIR* dir;
	struct dirent* entry;
	char path[128];

	dir = opendir(dirnow);
	if (dir == NULL)
	{
		perror("Error");
	}

	while ((entry = readdir(dir)) != NULL)
	{
		if (strcmp(entry->d_name, ".") == 0 || strcmp(entry->d_name, "..") == 0) continue;

		snprintf(path, sizeof(path), "%s/%s", dirnow, entry->d_name);

		if (strcmp(entry->d_name, filename) == 0) printf("Found: %s\n", path);

		if (entry->d_type == DT_DIR)
		{
			Find(filename, path);
		}
	}
	closedir(dir);
}

/**
* Echo text to file.
* This can be a simple text editor.
* @param text The text to write.
* @param filename The file to write to.
*/
void Fecho(const char* text, const char* filename)
{
	if (text == NULL || filename == NULL)
	{
		printf("Text string empty.\n");
		return;
	}
	
	FILE* file = fopen(filename, "w"); // Open file in write mode.

	if (file == NULL)
	{
		perror("Error");
		return;
	}

	if (fprintf(file, "%s\n", text) < 0) perror("Error");

	fclose(file);
}

/**
* Copy file to specified directory
* @param src The source file.
* @param dest The destination path. (either a file or directory)
*/
void Cp(char* src, char* dest)
{
	struct stat src_stat;
	struct stat dest_stat;

	if (src == NULL || dest == NULL)
	{
		printf("File string empty.\n");
		return;
	}

	if (stat(src, &src_stat) == -1)
	{
		perror("Error");
		return;
	}

	if (stat(dest, &dest_stat) == 0 && S_ISDIR(dest_stat.st_mode))
	{
		char *filename = strrchr(src, '/');
		if (filename == NULL) filename = src;

		else filename++;

		char dest_file[1024];
		snprintf(dest_file, sizeof(dest_file), "%s/%s", dest, filename);

		FILE *source = fopen(src, "rb");
		if (source == NULL)
		{	
			perror("Error");
			return;
		}

		FILE *destination = fopen(dest_file, "wb");
		if (destination == NULL)
		{	
			perror("Error");
			fclose(source);
			return;
		}

		char buffer[FILE_BUFFER];
		size_t bytesRead;
		while ((bytesRead = fread(buffer, 1, sizeof(buffer), source)) > 0)
		{
			fwrite(buffer, 1, bytesRead, destination);
		}
		fclose(source);
		fclose(destination);
		printf("File %s copied successfully to: %s\n", src, dest_file);
	}
	else
	{

		FILE *source = fopen(src, "rb");
		if (source == NULL)
		{
			perror("Error");
			return;
		}

		FILE *destination = fopen(dest, "wb");
		if (destination == NULL)
		{
			perror("Error");
			fclose(source);
			return;
		}

		char buffer[FILE_BUFFER];
		size_t bytesRead;
		while ((bytesRead = fread(buffer, 1, sizeof(buffer), source)) > 0)
		{
			fwrite(buffer, 1, bytesRead, destination);
		}

		// Close both files to prevent memory leaks.
		fclose(source);
		fclose(destination);
		printf("File %s copied successfully to: %s\n", src, dest);
	}
}

/**
* Move file to specified directory
* @param src The source file.
* @param dest The destination path. (either a file or directory)
*/
void Mv(char* src, char* dest)
{
	struct stat src_stat;
	struct stat dest_stat;

	if (src == NULL || dest == NULL)
	{
		printf("File string empty.\n");
		return;
	}

	if (stat(src, &src_stat) == -1)
	{
		perror("Error");
		return;
	}

	if (stat(dest, &dest_stat) == 0 && S_ISDIR(dest_stat.st_mode))
	{
		char *filename = strrchr(src, '/');
		if (filename == NULL) filename = src;

		else filename++;

		char dest_file[1024];
		snprintf(dest_file, sizeof(dest_file), "%s/%s", dest, filename);

		if (rename(src, dest_file) != 0)
		{
			perror("Error");
			return;
		}

		printf("File %s moved successfully to: %s\n", src, dest_file);
	}
	else
	{
		if (rename(src, dest) != 0)
		{
			perror("Error");
			return;
		}

		printf("File %s moved successfully to: %s\n", src, dest);
	}

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
unsigned int Christmas()
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
		ascii_art[7]="       |||         ";
		ascii_art[8]="   [NanoShell]     ";
	}

	if (strcmp(help, "system") == 0)
	{
		printf("%s", ascii_art[0]); printf(" NanoShell C Edition, Version: %s\n", version);
		printf("%s", ascii_art[1]); printf(" ---------------------------------------------------\n");
		printf("%s", ascii_art[2]); printf(" # Identifier Key : %s\n", "C-HcrcF@TrVKITNU87GcUZV8h8px#yFe");
		printf("%s", ascii_art[3]); printf(" # Identifier Name: %s\n", identifier_name);
		printf("%s", ascii_art[4]); printf(" # Original Author: %s\n", original_author);
		printf("%s", ascii_art[5]); printf(" # Latest Release : %s\n", releasedate);
		printf("%s", ascii_art[6]); printf(" # First Release  : %s\n", "07/12/2024");
		printf("%s", ascii_art[7]);  printf("# License        : %s\n", "GNU General Public License V3");
		printf("%s", ascii_art[8]);  printf("---------------------------------------------------\n");
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
		perror("Error");
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
