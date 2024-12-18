#ifndef ADVANCED_MORE_H
#define ADVANCED_MORE_H

#include <sys/sysinfo.h>
#include <sys/utsname.h>
#include <sys/statvfs.h>

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
		printf("Disk (free/total)  : %llu MB / %llu MB\n",
				freeDisk / (1024 * 1024),
				totalDisk / (1024 * 1024));
	}
	else perror("DiskInfo");
}

#endif // ADVANCED_MORE_H