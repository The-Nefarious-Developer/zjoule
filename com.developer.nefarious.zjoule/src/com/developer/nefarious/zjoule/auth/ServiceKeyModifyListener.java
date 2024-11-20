package com.developer.nefarious.zjoule.auth;

import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;

import com.google.gson.Gson;

public class ServiceKeyModifyListener implements ModifyListener {

	private FirstLoginWizardPage firstLoginWizardPage;

	public ServiceKeyModifyListener(FirstLoginWizardPage firstLoginWizardPage) {
		this.firstLoginWizardPage = firstLoginWizardPage;
	}

	@Override
	public void modifyText(ModifyEvent event) {
		String inputText = firstLoginWizardPage.getInputText().trim();
		// Enable or disable the Next button based on textField content
		if (inputText.isEmpty()) {
			firstLoginWizardPage.setPageComplete(false); // Disable Next button
		} else {
			if (JsonValidator.isValidJson(inputText)) {
				ServiceKey serviceKey = parseInputToObject(inputText);
				if (serviceKey.isValid()) {
					enableNextButton();
				} else {
					disableNextButton();
				}
			} else {
				disableNextButton();
			}
		}
	}

	private ServiceKey parseInputToObject(String inputText) {
		Gson gson = new Gson();
		return gson.fromJson(inputText, ServiceKey.class);
	}

	private void enableNextButton() {
		firstLoginWizardPage.setPageComplete(true);
	}

	private void disableNextButton() {
		firstLoginWizardPage.setPageComplete(false);
	}

}
