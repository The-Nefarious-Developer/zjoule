package com.developer.nefarious.zjoule.plugin.memory;

public class MemoryResourceGroup implements IMemoryResourceGroup {

	private static MemoryResourceGroup instance;

	public static MemoryResourceGroup getInstance() {
		if (instance == null) {
			throw new IllegalStateException("MemoryResourceGroup not initialized. Call initialize() first.");
		}
		return instance;
	}

	public static void initialize(final IEclipseMemory eclipseMemory) {
		if (instance == null) {
			instance = new MemoryResourceGroup(eclipseMemory);
		}
	}

	public static void resetInstance() {
		instance = null;
	}

	private IEclipseMemory eclipseMemory;

	private MemoryResourceGroup(final IEclipseMemory eclipseMemory) {
		this.eclipseMemory = eclipseMemory;
	}

	@Override
	public Boolean isEmpty() {
		String resourceGroup = load();
		if ((resourceGroup == null) || resourceGroup.isEmpty() || resourceGroup.isBlank()) {
			return true;
		}
		return false;
	}

	@Override
	public String load() {
		return eclipseMemory.loadFromEclipsePreferences(KEY);
	}

	@Override
	public void save(final String resourceGroup) {
		eclipseMemory.saveOnEclipsePreferences(KEY, resourceGroup);
	}

}