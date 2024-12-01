package com.HappyCow.Plugins.PluginManager;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface PluginMetadata
{
	String name();
	String description();
	String version();
}
