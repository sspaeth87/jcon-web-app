
package de.xdevsoftware.jconwebapp.ui.desktop;

import java.util.Arrays;
import java.util.List;

import com.rapidclipse.framework.server.data.renderer.RenderedComponent;
import com.rapidclipse.framework.server.resources.CaptionUtils;
import com.rapidclipse.framework.server.ui.filter.FilterComponent;
import com.rapidclipse.framework.server.ui.filter.GridFilterSubjectFactory;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.server.WebBrowser;

import de.xdevsoftware.jconwebapp.HasTitle;
import de.xdevsoftware.jconwebapp.model.Customer;
import de.xdevsoftware.jconwebapp.repository.CustomerRepository;
import de.xdevsoftware.jconwebapp.ui.MainLayout;
import de.xdevsoftware.jconwebapp.ui.column.CustomerEditColumn;
import de.xdevsoftware.jconwebapp.ui.mobile.CustomerMobileView;


@Route(value = "", layout = MainLayout.class)
public class CustomerView extends VerticalLayout implements HasTitle
{
	public CustomerView()
	{
		super();
		this.initUI();
	}
	
	@Override
	public String getTitle()
	{
		return "Customers";
	}
	
	/**
	 * Event handler delegate method for the {@link VerticalLayout}.
	 *
	 * @see ComponentEventListener#onComponentEvent(ComponentEvent)
	 * @eventHandlerDelegate Do NOT delete, used by UI designer!
	 */
	private void this_onAttach(final AttachEvent event)
	{
		if(this.isMobileDevice())
		{
			UI.getCurrent().navigate(CustomerMobileView.class);
		}
		else
		{
			this.loadData();
		}
	}
	
	public void loadData()
	{
		final List<Customer> customers = CustomerRepository.findAll();
		this.grid.setDataProvider(DataProvider.ofCollection(customers));

		this.filterComponent.connectWith(this.grid.getDataProvider());
	}

	public boolean isMobileDevice()
	{
		final WebBrowser webBrowser = VaadinSession.getCurrent().getBrowser();
		return webBrowser.isAndroid() || webBrowser.isIPhone() || webBrowser.isWindowsPhone();
	}
	
	/* WARNING: Do NOT edit!<br>The content of this method is always regenerated by the UI designer. */
	// <generated-code name="initUI">
	private void initUI()
	{
		this.filterComponent = new FilterComponent();
		this.grid            = new Grid<>(Customer.class, false);

		this.grid.addColumn(Customer::getFirstname).setKey("firstname")
			.setHeader(CaptionUtils.resolveCaption(Customer.class, "firstname")).setSortable(true);
		this.grid.addColumn(Customer::getLastname).setKey("lastname")
			.setHeader(CaptionUtils.resolveCaption(Customer.class, "lastname")).setSortable(true);
		this.grid.addColumn(Customer::getEmail).setKey("email")
			.setHeader(CaptionUtils.resolveCaption(Customer.class, "email")).setSortable(true);
		this.grid.addColumn(RenderedComponent.Renderer(CustomerEditColumn::new)).setKey("renderer").setHeader("...")
			.setSortable(false).setFlexGrow(0);
		this.grid.setSelectionMode(Grid.SelectionMode.SINGLE);

		this.filterComponent.connectWith(this.grid.getDataProvider());
		this.filterComponent.setFilterSubject(GridFilterSubjectFactory.CreateFilterSubject(this.grid,
			Arrays.asList("firstname", "lastname", "email"), Arrays.asList("firstname", "lastname", "email")));

		this.filterComponent.setWidthFull();
		this.filterComponent.setHeight(null);
		this.grid.setSizeFull();
		this.add(this.filterComponent, this.grid);
		this.setFlexGrow(1.0, this.grid);
		this.setSizeFull();

		this.addAttachListener(this::this_onAttach);
	} // </generated-code>
	
	// <generated-code name="variables">
	private Grid<Customer>  grid;
	private FilterComponent filterComponent;
	// </generated-code>
	
}
