package com.developer.nefarious.zjoule.test.core.ui;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPartSite;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;
import com.developer.nefarious.zjoule.core.ui.BrowserFactory;
import com.developer.nefarious.zjoule.core.ui.IViewRender;
import com.developer.nefarious.zjoule.core.ui.ViewListener;
import com.developer.nefarious.zjoule.core.ui.ViewRender;
import com.developer.nefarious.zjoule.core.events.SelectionListener;
import com.developer.nefarious.zjoule.core.functions.ClearHandler;
import com.developer.nefarious.zjoule.core.functions.LoginHandler;
import com.developer.nefarious.zjoule.core.functions.LogoutHandler;
import com.developer.nefarious.zjoule.core.functions.PromptHandler;
import com.developer.nefarious.zjoule.core.events.Initialization;
import com.developer.nefarious.zjoule.core.events.PartListener;

public class ViewListenerTest {

	private ViewListener cut;
	
	private MockedStatic<BrowserFactory> mockedStaticBrowserFactory;
	
	private MockedStatic<SelectionListener> mockedStaticSelectionListener;
	
	private MockedStatic<ViewRender> mockedStaticViewRender;
	
	private MockedStatic<PartListener> mockedStaticPartListener;
	
	private MockedStatic<PromptHandler> mockedStaticPromptHandler;
	
	private MockedStatic<Display> mockedStaticDisplay;
	
	private MockedStatic<LoginHandler> mockedStaticLoginHandler;
	
	private MockedStatic<ClearHandler> mockedStaticClearHandler;
	
	private MockedStatic<LogoutHandler> mockedStaticLogoutHandler;
	
	@Mock
	private Shell mockShell;
	
	@Mock
	private Browser mockBrowser;
	
	@Mock
	private ISelectionListener mockSelectionListener;
	
	@Mock
	private IViewRender mockViewRender;
	
	@Mock
	private PartListener mockPartListener;
	
	@Mock
	private Composite mockParent;
	
	@Mock
	private PromptHandler mockPromptHandler;
	
	@Mock
	private Display mockDisplay;
	
	@Mock
	private LoginHandler mockLoginHandler;
	
	@Mock
	private ClearHandler mockClearHandler;
	
	@Mock
	private LogoutHandler mockLogoutHandler;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		
		mockedStaticBrowserFactory = mockStatic(BrowserFactory.class);
		mockedStaticSelectionListener = mockStatic(SelectionListener.class);
		mockedStaticViewRender = mockStatic(ViewRender.class);
		mockedStaticPartListener = mockStatic(PartListener.class);
		mockedStaticPromptHandler = mockStatic(PromptHandler.class);
		mockedStaticDisplay = mockStatic(Display.class);
		mockedStaticLoginHandler = mockStatic(LoginHandler.class);
		mockedStaticClearHandler = mockStatic(ClearHandler.class);
		mockedStaticLogoutHandler = mockStatic(LogoutHandler.class);
		
		mockedStaticBrowserFactory.when(() -> BrowserFactory.create(mockParent, SWT.WEBKIT)).thenReturn(mockBrowser);
		mockedStaticSelectionListener.when(() -> SelectionListener.getInstance(mockBrowser)).thenReturn(mockSelectionListener);
		mockedStaticViewRender.when(ViewRender::getInstance).thenReturn(mockViewRender);
		mockedStaticPartListener.when(() -> PartListener.getInstance(mockBrowser)).thenReturn(mockPartListener);
		mockedStaticPromptHandler.when(() -> PromptHandler.create(mockBrowser, "getAIResponse")).thenReturn(mockPromptHandler);
		mockedStaticDisplay.when(Display::getDefault).thenReturn(mockDisplay);
		mockedStaticLoginHandler.when(() -> LoginHandler.create(mockShell, mockBrowser)).thenReturn(mockLoginHandler);
		mockedStaticClearHandler.when(() -> ClearHandler.create(mockBrowser)).thenReturn(mockClearHandler);
		mockedStaticLogoutHandler.when(() -> LogoutHandler.create(mockBrowser)).thenReturn(mockLogoutHandler);
		
		cut = spy(new ViewListener());
		cut.setShell(mockShell);
	}
	
	@AfterEach
	public void tearDown() {
		if (mockedStaticBrowserFactory != null) {
			mockedStaticBrowserFactory.close();
		}
		if (mockedStaticSelectionListener != null) {
			mockedStaticSelectionListener.close();
		}
		if (mockedStaticViewRender != null) {
			mockedStaticViewRender.close();
		}
		if (mockedStaticPartListener != null) {
			mockedStaticPartListener.close();
		}
		if (mockedStaticPromptHandler != null) {
			mockedStaticPromptHandler.close();
		}
		if (mockedStaticDisplay != null) {
			mockedStaticDisplay.close();
		}
		if (mockedStaticLoginHandler != null) {
			mockedStaticLoginHandler.close();
		}
		if (mockedStaticClearHandler != null) {
			mockedStaticClearHandler.close();
		}
		if (mockedStaticLogoutHandler != null) {
			mockedStaticLogoutHandler.close();
		}
	}
	
	@Test
	public void shouldPlumbPartControl() {
		// Arrange
		String mockBuildResult = "html-text";
		when(mockViewRender.build()).thenReturn(mockBuildResult);
		
		IWorkbenchPartSite mockSite = mock(IWorkbenchPartSite.class);
		doReturn(mockSite).when(cut).getSite();
		
		IWorkbenchPage mockPage = mock(IWorkbenchPage.class);
		when(mockSite.getPage()).thenReturn(mockPage);
		
		IViewSite mockViewSite = mock(IViewSite.class);
		doReturn(mockViewSite).when(cut).getViewSite();
		IActionBars mockActionBars = mock(IActionBars.class);
		when(mockViewSite.getActionBars()).thenReturn(mockActionBars);
		
		IToolBarManager mockToolBarManager = mock(IToolBarManager.class);
		when(mockActionBars.getToolBarManager()).thenReturn(mockToolBarManager);
		
		IMenuManager mockMenuManager = mock(IMenuManager.class);
		when(mockActionBars.getMenuManager()).thenReturn(mockMenuManager);
		
		// Act
		cut.createPartControl(mockParent);
		
		// Assert
		verify(mockBrowser).setText(mockBuildResult);
		verify(mockBrowser).addDisposeListener(any(DisposeListener.class));
		verify(mockPage).addPartListener(mockPartListener);
		verify(mockPage).addSelectionListener(mockSelectionListener);
		
		ArgumentCaptor<Runnable> captor = ArgumentCaptor.forClass(Runnable.class);
        verify(mockDisplay).asyncExec(captor.capture());
        Runnable actualRunnable = captor.getValue();
        assertTrue(actualRunnable instanceof Initialization);
        
        verify(mockToolBarManager).add(mockLoginHandler);
        verify(mockMenuManager).add(mockClearHandler);
        verify(mockMenuManager).add(any(Separator.class));
        verify(mockMenuManager).add(mockLogoutHandler);
	}
	
	@Test
	public void testSetFocus() {
		// Arrange
		cut.setBrowser(mockBrowser);
		
		// Act
		cut.setFocus();
		
		// Assert
		verify(mockBrowser).setFocus();
	}
	
	@Test
	public void testDispose() {
		// Arrange
		IWorkbenchPartSite mockSite = mock(IWorkbenchPartSite.class);
		doReturn(mockSite).when(cut).getSite();

		IWorkbenchPage mockPage = mock(IWorkbenchPage.class);
		when(mockSite.getPage()).thenReturn(mockPage);

		// Act
		cut.dispose();

		// Assert
		verify(mockPage).removeSelectionListener(cut.getSelectionListener());
	}

}