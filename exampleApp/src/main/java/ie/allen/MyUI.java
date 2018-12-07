package ie.allen;

import javax.servlet.annotation.WebServlet;
import java.sql.*;
import java.util.*;
import com.vaadin.ui.*;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of an HTML page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        Connection connection = null;
    
        String connectionString = "jdbc:sqlserver://myclassdb.database.windows.net:1433;" + 
			  "database={myclassdb};" + 
			  "user=allen@myclassdb;" + 
			  "password=@llenV1974;" + 
			  "encrypt=true;" + 
			  "trustServerCertificate=false;" + 
			  "hostNameInCertificate=*.database.windows.net;" +
			  "loginTimeout=30;";




        final VerticalLayout layout = new VerticalLayout();

        try 
        {
        // Connect with JDBC driver to a database
        connection = DriverManager.getConnection(connectionString);
        // Add a label to the web app with the message and name of the database we connected to 
        //to check the database run the layout below
         layout.addComponent(new Label("Connected to database: " + connection.getCatalog()));
        //query below - as loop:
        ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM PartyRooms;");
        // Convert the resultset that comes back into a List - we need a Java class to represent the data (Customer.java in this case)
        List<PartyRooms> rooms = new ArrayList<PartyRooms>();
        // While there are more records in the resultset
        while(rs.next())
        {   
            // Add a new Customer instantiated with the fields from the record (that we want, we might not want all the fields, note how I skip the id)
            rooms.add(new PartyRooms(rs.getString("room"), 
                        rs.getDouble("capacity"), 
                        rs.getString("feature"),
                        rs.getString("alcohol_allowed")));
    
        }
            // Add my component, grid is templated with Customer
            Grid<PartyRooms> myGrid = new Grid<PartyRooms>();
            // Set the items (List)
             myGrid.setItems(rooms);
            // Configure the order and the caption of the grid
             myGrid.addColumn(PartyRooms::getRooms).setCaption("Rooms");
             myGrid.addColumn(PartyRooms::getCapacity).setCaption("Capacity");
             myGrid.addColumn(PartyRooms::getFeature).setCaption("Feature");
            myGrid.addColumn(PartyRooms::getAlcoholAllowed).setCaption("Alcohol_Allowed");

            // Add the grid to the list
            layout.addComponent(myGrid);
        
       }
       catch (Exception e) 
       {
        // This will show an error message if something went wrong
        layout.addComponent(new Label(e.getMessage()));
       }
        setContent(layout);
    
    
    
        }
    
        @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
        @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
        public static class MyUIServlet extends VaadinServlet {
        }
    }
    


        
       
