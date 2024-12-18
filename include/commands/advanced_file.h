#ifndef ADVANCED_FILE_H
#define ADVANCED_FILE_H

#include <dirent.h>

void Cat(const char* filename)
{
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

void Stat(const char* filename)
{
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

void Print_tree(const char* dirN, int depth)
{
	DIR* dir = opendir(dirN);
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
		snprintf(path, sizeof(path), "%s/%s", dirN, entry->d_name);

		struct stat entry_stat;
		if (stat(path, &entry_stat) == 0 && S_ISDIR(entry_stat.st_mode)) Print_tree(path, depth + 1);
	}
	closedir(dir);
}

void Tree(const char* dir)
{
	Print_tree(dir, 0);
}

#endif // ADVANCED_FILE_H
