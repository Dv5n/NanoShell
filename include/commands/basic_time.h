#ifndef BASIC_TIME_H
#define BASIC_TIME_H

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

#endif // BASIC_TIME_H