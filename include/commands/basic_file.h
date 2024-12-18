#ifndef BASIC_FILE_H
#define BASIC_FILE_H

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

void Cd(const char* path)
{
	if (path == NULL)
	{
		printf("Directory \"%s\" not found.", path);
		return;
	}

	if (chdir(path) != 0) perror("cd");
}

void Pwd()
{
	char path[512];
	if (getcwd(path, sizeof(path)) != NULL) printf("%s\n", path);

	else perror("pwd");
}

void Mkdir(const char* dir, unsigned int permissions)
{
	if (mkdir(dir, permissions) == -1) perror("mkdir");
}

void Rmdir(const char* dir)
{
	if (rmdir(dir) == -1) perror("rmdir");
}

void Rm(const char* file)
{
	if (unlink(file) == -1) perror("rm");
}

void Touch(const char* filename)
{
	FILE *file = fopen(filename, "a");
	if (file == NULL)
	{
		perror("touch");
		return;
	}

	fclose(file);
}

#endif // BASIC_FILE_H