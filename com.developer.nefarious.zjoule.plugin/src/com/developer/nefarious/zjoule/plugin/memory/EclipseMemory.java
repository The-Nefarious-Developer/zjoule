package com.developer.nefarious.zjoule.plugin.memory;

import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.osgi.service.prefs.BackingStoreException;

public class EclipseMemory implements IEclipseMemory {

	public static IEclipsePreferences getEclipsePreferences() {
		return InstanceScope.INSTANCE.getNode(PLUGIN_ID);
	}

	private IEclipsePreferences preferences;

	public EclipseMemory() {
		preferences = EclipseMemory.getEclipsePreferences();
	}

	@Override
	public void clearAll() {
		try {
			preferences.clear();
			preferences.flush();
		} catch (BackingStoreException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteFromEclipsePreferences(final String key) {
		try {
	        preferences.remove(key);
	        preferences.flush();
	    } catch (BackingStoreException e) {
	        e.printStackTrace();
	    }
	}

	@Override
	public String loadFromEclipsePreferences(final String key) {
		return preferences.get(key, null);
	}

	@Override
	public void saveOnEclipsePreferences(final String key, final String value) {
		preferences.put(key, value);
		try {
			preferences.flush(); // Persist the data
		} catch (BackingStoreException e) {
			e.printStackTrace();
		}
	}

}