package com.developer.nefarious.eclipse.copilot.ui;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.ISelectionListener;

import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.part.ViewPart;

public class ChatViewListener extends ViewPart implements ISelectionListener {

	private Browser browser;
	private IBrowserFactory browserFactory;
//	private IFunctionFactory functionFactory;
	
	private IViewRender viewRender;

	public ChatViewListener() {
		this(new BrowserFactory());
		viewRender = new ChatViewRender();
//		functionFactory = new FunctionFactory();
	}

	public ChatViewListener(IBrowserFactory browserFactory) {
		this.browserFactory = browserFactory;
	}
	
	public ChatViewListener(IBrowserFactory browserFactory, IViewRender viewRender 
//			IFunctionFactory functionFactory
			) {
		this.browserFactory = browserFactory;
		this.viewRender = viewRender;
//		this.functionFactory = functionFactory;
	}
	
	/**
	 * Set a mocked browser instance for unit tests execution.
	 *
	 * @param A mocked browser instance
	 */
	public void setBrowser(Browser browser) { 
		this.browser = browser;
	}
	
	/**
	 * Expose the getSite from ViewPart/WorkbenchPart for unit tests.
	 *
	 * @return Instance that allows interaction with the workbench in Eclipse
	 */
	public IWorkbenchPartSite getWorkbenchPartSite() {
		return getSite();
	}

	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		// TODO Auto-generated method stub
	}

	@Override
	public void createPartControl(Composite parent) {
		browser = browserFactory.createBrowser(parent, SWT.WEBKIT);
		browser.setText(viewRender.build());
		getWorkbenchPartSite().getPage().addSelectionListener(this);
	}

	@Override
	public void setFocus() {
		browser.setFocus();
	}
	
	@Override
	public void dispose() {
		getWorkbenchPartSite().getPage().removeSelectionListener(this);
		super.dispose(); // TODO: Add code coverage for super method call
	}

}