package com.developer.nefarious.zjoule.plugin.login.api;

import java.net.URI;

public interface ILoginClientHelper {

	URI createAuthUri(final String tokenEndpoint);

	GetDeploymentsResponse parseDeploymentsResponseToObject(final String responseBody);

	GetResourceGroupsResponse parseResourceGroupsResponseToObject(final String responseBody);

}