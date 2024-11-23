package com.developer.nefarious.zjoule.test.memory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.developer.nefarious.zjoule.auth.AccessToken;
import com.developer.nefarious.zjoule.memory.ObjectSerializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ObjectSerializerTest {
	
	private ObjectSerializer cut;
	
	private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
	
	@BeforeEach
	public void setUp() {
		cut = new ObjectSerializer();
	}
	
	@Test
	public void shouldSerializeObject() {
		// Arrange
		AccessToken someObject = new AccessToken();
		someObject.setAccessToken("doesn't matter");
		String expectedValue = gson.toJson(someObject);
		// Act
		String returnValue = cut.serialize(someObject);
		// Assert
		assertEquals(returnValue, expectedValue);
	}
	
	@Test
	public void shouldThrowAnErrorInTheSerializationIfTheObjectIsNull() {
		// Arrange
		String expectedErrorMessage = "Object to be serialized cannot be null";
		// Act
		try {
			cut.serialize(null);
		} catch (IllegalArgumentException error) {
			// Assert
			assertEquals(error.getMessage(), expectedErrorMessage);
		}
	}
	
	@Test
	public void shouldDeserializeObject() {
		// Arrange
		String mockSerializedObject = "{\n"
			+ "  \"access_token\": \"doesn\\u0027t matter\",\n"
			+ "  \"expires_in\": 0,\n"
			+ "  \"receivedAt\": \"Nov 22, 2024, 6:16:56 PM\"\n"
			+ "}";
		AccessToken expectedObject = gson.fromJson(mockSerializedObject, AccessToken.class);
		// Act
		AccessToken returnObject = cut.deserialize(mockSerializedObject, AccessToken.class);
		// Assert
		assertEquals(returnObject.getAccessToken(), expectedObject.getAccessToken());
	}
	
	@Test
	public void shouldThrowAnErrorInTheDeserializationIfTheObjectIsNull() {
		// Arrange
		String expectedErrorMessage = "JSON string cannot be null or empty";
		// Act
		try {
			cut.deserialize(null, AccessToken.class);
		} catch (IllegalArgumentException error) {
			// Assert
			assertEquals(error.getMessage(), expectedErrorMessage);
		}
	}
	
}