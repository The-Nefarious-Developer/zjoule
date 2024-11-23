package com.developer.nefarious.zjoule.memory;

import com.developer.nefarious.zjoule.auth.ServiceKey;

public class MemoryServiceKey implements IMemoryServiceKey {
	
	IObjectSerializer objectSerializer;
	IEclipseMemory eclipseMemory;
	
	public MemoryServiceKey() {
		objectSerializer = new ObjectSerializer();
		eclipseMemory = new EclipseMemory();
	}
	
	public MemoryServiceKey(IObjectSerializer objectSerializer, IEclipseMemory eclipseMemory) {
		this.objectSerializer = objectSerializer;
		this.eclipseMemory = eclipseMemory;
	}

	@Override
	public void save(ServiceKey serviceKey) {
		String serializedObject = objectSerializer.serialize(serviceKey);
		eclipseMemory.saveOnEclipsePreferences(KEY, serializedObject);
	}

	@Override
	public ServiceKey load() {
		String serializedObject = eclipseMemory.loadFromEclipsePreferences(KEY);
		return objectSerializer.deserialize(serializedObject, ServiceKey.class);
	}

}