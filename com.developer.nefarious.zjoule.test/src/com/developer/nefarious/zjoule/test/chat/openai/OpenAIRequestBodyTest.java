package com.developer.nefarious.zjoule.test.chat.openai;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;
import org.junit.jupiter.api.Test;
import com.developer.nefarious.zjoule.chat.openai.OpenAIMessage;
import com.developer.nefarious.zjoule.chat.openai.OpenAIRequestBody;
import com.developer.nefarious.zjoule.models.Role;

public class OpenAIRequestBodyTest {
	
	// CHECKSTYLE:OFF - Deactivate checkstyle to avoid magic number issues on this test
	@Test
	public void shouldConvertObjectToString() {
		// Arrange
		OpenAIRequestBody cut = new OpenAIRequestBody();
		
		String expectedValue = "{\"messages\":["
				+ "{\"role\":\"user\","
				+ "\"content\":\"Hello!\"},"
				+ "{\"role\":\"assistant\","
				+ "\"content\":\"Hi there!\"}],"
				+ "\"max_tokens\":100,"
				+ "\"temperature\":0.7,"
				+ "\"frequency_penalty\":0.5,"
				+ "\"presence_penalty\":0.6,"
				+ "\"stop\":\"done\"}";
		
		OpenAIMessage message1 = new OpenAIMessage();
		message1.setRole(Role.USER);
		message1.setContent("Hello!");

		OpenAIMessage message2 = new OpenAIMessage();
		message2.setRole(Role.ASSISTANT);
		message2.setContent("Hi there!");

		cut.setMessages(List.of(message1, message2));
		cut.setMaxTokens(100);
		cut.setTemperature(0.7);
		cut.setFrequencyPenalty(0.5);
		cut.setPresencePenalty(0.6);
		cut.setStop("done");
		
		// Act
		String returnValue = cut.toString();
		
		// Assert
		assertEquals(expectedValue, returnValue);
	}
	
	@Test
	public void shouldContainNullValuesWhenDoubleQuoted() {
		// Arrange
		OpenAIRequestBody cut = new OpenAIRequestBody();
		
		String expectedValue = "{\"messages\":["
				+ "{\"role\":\"user\","
				+ "\"content\":\"Hello!\"},"
				+ "{\"role\":\"assistant\","
				+ "\"content\":\"Hi there!\"}],"
				+ "\"max_tokens\":100,"
				+ "\"temperature\":0.7,"
				+ "\"frequency_penalty\":0.5,"
				+ "\"presence_penalty\":0.6,"
				+ "\"stop\":\"null\"}";
		
		OpenAIMessage message1 = new OpenAIMessage();
		message1.setRole(Role.USER);
		message1.setContent("Hello!");

		OpenAIMessage message2 = new OpenAIMessage();
		message2.setRole(Role.ASSISTANT);
		message2.setContent("Hi there!");

		cut.setMessages(List.of(message1, message2));
		cut.setMaxTokens(100);
		cut.setTemperature(0.7);
		cut.setFrequencyPenalty(0.5);
		cut.setPresencePenalty(0.6);
		cut.setStop("null");
		
		// Act
		String returnValue = cut.toString();
		
		// Assert
		assertEquals(expectedValue, returnValue);
	}
	// CHECKSTYLE:ON

}