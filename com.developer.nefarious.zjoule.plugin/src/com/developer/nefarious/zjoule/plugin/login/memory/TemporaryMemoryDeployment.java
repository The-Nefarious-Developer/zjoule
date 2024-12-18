package com.developer.nefarious.zjoule.plugin.login.memory;

import com.developer.nefarious.zjoule.plugin.memory.IEclipseMemory;
import com.developer.nefarious.zjoule.plugin.memory.IMemoryDeployment;
import com.developer.nefarious.zjoule.plugin.memory.utils.IObjectSerializer;
import com.developer.nefarious.zjoule.plugin.models.Deployment;

public class TemporaryMemoryDeployment implements IMemoryDeployment, ITemporaryMemoryObject {

	private static TemporaryMemoryDeployment instance;

	public static final String KEY = "tmp-" + IMemoryDeployment.KEY;

	public static TemporaryMemoryDeployment getInstance() {
		if (instance == null) {
			throw new IllegalStateException("TemporaryMemoryDeployment not initialized. Call initialize() first.");
		}
		return instance;
	}

	public static void initialize(final IObjectSerializer objectSerializer, final IEclipseMemory eclipseMemory) {
		if (instance == null) {
			instance = new TemporaryMemoryDeployment(objectSerializer, eclipseMemory);
		}
	}

	public static void resetInstance() {
		instance = null;
	}

	IObjectSerializer objectSerializer;

	IEclipseMemory eclipseMemory;

	private TemporaryMemoryDeployment(final IObjectSerializer objectSerializer, final IEclipseMemory eclipseMemory) {
		this.objectSerializer = objectSerializer;
		this.eclipseMemory = eclipseMemory;
	}

	@Override
	public Boolean isEmpty() {
		Deployment deployment = load();
		if ((deployment == null) || (deployment.getConfigurationName() == null) || deployment.getConfigurationName().isEmpty() ||
				deployment.getConfigurationName().isBlank()) {
			return true;
		}
		return false;
	}

	@Override
	public Deployment load() {
		try {
			String serializedObject = eclipseMemory.loadFromEclipsePreferences(KEY);
			return objectSerializer.deserialize(serializedObject, Deployment.class);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public void persist() {
		String serializedObject = eclipseMemory.loadFromEclipsePreferences(KEY);
		eclipseMemory.saveOnEclipsePreferences(IMemoryDeployment.KEY, serializedObject);
	}

	@Override
	public void save(final Deployment deployment) {
		String serializedObject = objectSerializer.serialize(deployment);
		eclipseMemory.saveOnEclipsePreferences(KEY, serializedObject);
	}
}