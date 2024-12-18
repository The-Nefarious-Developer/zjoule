package com.developer.nefarious.zjoule.plugin.memory;

import com.developer.nefarious.zjoule.plugin.core.Activator;

public interface IEclipseMemory {

	static final String PLUGIN_ID = Activator.PLUGIN_ID;

	void clearAll();

	void deleteFromEclipsePreferences(String key);

	String loadFromEclipsePreferences(final String key);

	void saveOnEclipsePreferences(final String key, final String value);

}