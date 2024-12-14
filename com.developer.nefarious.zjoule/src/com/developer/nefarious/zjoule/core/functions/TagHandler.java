package com.developer.nefarious.zjoule.core.functions;

import org.eclipse.swt.browser.Browser;
import com.developer.nefarious.zjoule.chat.utils.EditorContentReader;

public abstract class TagHandler {
	
	public static void update(final Browser browser) {
		String nameOfTheCurrentActiveFile = EditorContentReader.getActiveEditorFileName();
		browser.execute("updateTag('" + nameOfTheCurrentActiveFile + "');");
	}

}