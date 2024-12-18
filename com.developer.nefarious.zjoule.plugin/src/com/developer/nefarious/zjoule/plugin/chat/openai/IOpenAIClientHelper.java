package com.developer.nefarious.zjoule.plugin.chat.openai;

import java.net.http.HttpRequest.BodyPublisher;
import java.util.List;

import com.developer.nefarious.zjoule.plugin.chat.IChatMessage;

public interface IOpenAIClientHelper {

	static final int MAX_TOKENS = 4096;

	static final double TEMPERATURE = 0;

	static final double FREQUENCY_PENALTY = 0;

	static final double PRESENCE_PENALTY = 0;

	static final String STOP = "null";

	IChatMessage convertResponseToObject(final String responseBody);

	BodyPublisher createRequestBody(final List<IChatMessage> messages);

}