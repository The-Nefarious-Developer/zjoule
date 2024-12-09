package com.developer.nefarious.zjoule.core.ui;

import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.BrowserFunction;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.ViewPart;
import com.developer.nefarious.zjoule.core.functions.PromptHandler;
import com.developer.nefarious.zjoule.core.functions.LoginHandler;
import jakarta.inject.Inject;

public class ChatViewListener extends ViewPart implements ISelectionListener {

	@Inject
	Shell shell;

	private Browser browser;
	
	private final IBrowserFactory browserFactory;

	private final IViewRender viewRender;

	public ChatViewListener() {
		browserFactory = new BrowserFactory();
		viewRender = new ChatViewRender();
	}

	public ChatViewListener( // Used for tests
			final IBrowserFactory browserFactory, 
			final IViewRender viewRender) {
		this.browserFactory = browserFactory;
		this.viewRender = viewRender;
	}

	public void setBrowser(final Browser browser) {
		this.browser = browser;
	}

	public void setShell(final Shell shell) {
		this.shell = shell;
	}

	@Override
	public void selectionChanged(final IWorkbenchPart part, final ISelection selection) {
		// TODO Auto-generated method stub
	}

	public IToolBarManager getToolbar() {
		return getViewSite().getActionBars().getToolBarManager();
	}

	@Override
	public void createPartControl(final Composite parent) {
		browser = browserFactory.createBrowser(parent, SWT.WEBKIT);
		browser.setText(viewRender.build());
		BrowserFunction getAIResponseFunction = PromptHandler.create(browser, "getAIResponse");
		browser.addDisposeListener(e -> getAIResponseFunction.dispose());
		getSite().getPage().addSelectionListener(this);
		setUpToolbar();
	}

	private void setUpToolbar() {
		IToolBarManager toolbar = getToolbar();
		toolbar.add(LoginHandler.create(shell));
	}

	@Override
	public void setFocus() {
		browser.setFocus();
	}

	@Override
	public void dispose() {
		getSite().getPage().removeSelectionListener(this);
		super.dispose(); // TODO: Add code coverage for super method call
	}

}