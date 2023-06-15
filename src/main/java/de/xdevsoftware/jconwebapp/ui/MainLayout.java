
package de.xdevsoftware.jconwebapp.ui;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasElement;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.server.WebBrowser;
import com.vaadin.flow.theme.Theme;

import de.xdevsoftware.jconwebapp.HasTitle;
import de.xdevsoftware.jconwebapp.ui.desktop.CustomerCreateView;
import de.xdevsoftware.jconwebapp.ui.desktop.CustomerView;
import de.xdevsoftware.jconwebapp.ui.mobile.CustomerCreateMobileView;
import de.xdevsoftware.jconwebapp.ui.mobile.CustomerMobileView;


@Theme(themeFolder = "Default")
public class MainLayout extends AppLayout
{
	private final H2 title = new H2();
	
	public MainLayout()
	{
		this.addToNavbar(new DrawerToggle(), this.title);
		
		if(!this.isMobileDevice())
		{
			// mobile views
			this.addDrawerEntry(VaadinIcon.USERS, "Customers", CustomerMobileView.class);
			this.addDrawerEntry(VaadinIcon.PLUS_CIRCLE_O, "Add Customer", CustomerCreateMobileView.class);
		}
		else
		{
			// desktop views
			this.addDrawerEntry(VaadinIcon.USERS, "Customers", CustomerView.class);
			this.addDrawerEntry(VaadinIcon.PLUS_CIRCLE_O, "Add Customer", CustomerCreateView.class);
		}
	}

	public boolean isMobileDevice()
	{
		final WebBrowser webBrowser = VaadinSession.getCurrent().getBrowser();
		return webBrowser.isAndroid() || webBrowser.isIPhone() || webBrowser.isWindowsPhone();
	}
	
	private void addDrawerEntry(final VaadinIcon icon, final String label, final Class<? extends Component> linkTarget)
	{
		final HorizontalLayout layout = new HorizontalLayout(icon.create(), new RouterLink(label, linkTarget));
		layout.setMargin(true);
		layout.setSpacing(true);
		layout.setPadding(false);
		this.addToDrawer(layout);
	}
	
	@Override
	public void showRouterLayoutContent(final HasElement content)
	{
		this.title.setText(content instanceof HasTitle ? ((HasTitle)content).getTitle() : "");
		super.showRouterLayoutContent(content);
	}
}
