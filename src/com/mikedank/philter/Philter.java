/*
 * Philter 0.1
 * Mike Dank
 */
package com.mikedank.philter;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import com.mikedank.philter.panel.*;

public class Philter{

	private static Map<String, String[]> regionalRails = new HashMap<String, String[]>();
	private static Map<String, String> zipCodes = new HashMap<String, String>();
	private static String[] septaLines = {"Airport","Chestnut Hill East","Chestnut Hill West","Cynwood","Fox Chase","Glenside","Lansdale/Doylestown","Manayunk/Norristown","Media/Elwyn","Paoli/Thorndale","Trenton","Warminster","West Trenton","Wilmington/Newark"};
	private static String[] stations = {"Wallingford","30th Street Station"};
	
	// variableSetup
	// We just need to set up some variables for our GUI.
	private static void variableSetup(){
		regionalRails.put(septaLines[0],new String[]{"Airport Terminal E-F","Airport Terminal C-D","Airport Terminal B","Airport Terminal A", "Eastwick","University City","30th Street Station","Suburban Station","Market East Station","Temple University"});
		regionalRails.put(septaLines[1],new String[]{"Chestnut Hill East","Gravers","Wyndmoor","Mount Airy","Sedgwick","Stenton","Washington Lane","Germantown","Wister","Wayne Junction","Temple University","Market East Station","Suburban Station","30th Street Station"});
		regionalRails.put(septaLines[2],new String[]{"Chestnut Hill West","Highland","St Martins","Allen Lane","Carpenter","Upsal","Tulpehocken","Chelten Avenue","Queen Lane","North Philadelphia","30th Street Station","Suburban Station","Market East Station","Temple University"});
		regionalRails.put(septaLines[3],new String[]{"Cynwyd","Bala","Wynnefield Avenue","30th Street Station","Suburban Station"});
		regionalRails.put(septaLines[4],new String[]{"Fox Chase","Ryers","Cheltenham","Lawndale","Olney","Wayne Junction","Temple University","Market East Station","Suburban Station","30th Street Station"});
		regionalRails.put(septaLines[5],new String[] {"Glenside","Jenkintown","Elkins Park","Melrose Park","Fern Rock TC","Wayne Junction","Doylestown","Del Val College","New Britain","Chalfont","Link Belt","Colmar","Fortuna","Lansdale","Pennbrook","North Wales","Gwynedd Valley","Penllyn","Ambler","Fort Washington","Oreland","North Hills","Glenside","Jenkintown","Elkins Park","Melrose Park","Fern Rock TC","Wayne Junction","Temple University","Market East Station","Suburban Station","30th Street Station","University City"});
		regionalRails.put(septaLines[6],new String[]{"Doylestown","Del Val College","New Britain","Chalfont","Link Belt","Colmar","Fortuna","Lansdale","Pennbrook","North Wales","Gwynedd Valley","Penllyn","Ambler","Fort Washington","Oreland","North Hills","Glenside","Jenkintown","Elkins Park","Melrose Park","Fern Rock TC","Wayne Junction","Temple University","Market East Station","Suburban Station","30th Street Station"});
		regionalRails.put(septaLines[7],new String[]{"Elm Street","Main Street","Norristown TC","Conshohocken","Spring Mill","Miquon","Ivy Ridge","Manayunk","Wissahickon TC","East Falls","Allegheny","North Broad","Temple University","Market East Station","Suburban Station","30th Street Station","University City"});
		regionalRails.put(septaLines[8],new String[]{"Elwyn","Media","Moylan-Rose Valley","Wallingford","Swarthmore","Morton","Secane","Primos","Clifton-Aldan","Gladstone","Lansdowne","Fernwood-Yeadon","Angora","49th Street","University City","30th Street Station","Suburban Station","Market East Station"});
		regionalRails.put(septaLines[9],new String[]{"Thorndale","Downingtown","Whitford","Exton","Malvern","Paoli","Daylesford","Berwyn","Devon","Strafford","Wayne","St Davids","Radnor (STN*)","Villanova","Rosemont","Bryn Mawr","Haverford","Ardmore","Wynnewood","Narberth","Merion","Overbrook","30th Street Station","Suburban Station","Market East Station","Temple University"});
		regionalRails.put(septaLines[10],new String[]{"Trenton","Levittown","Bristol","Croydon","Eddington","Cornwells Heights","Torresdale","Holmesburg","Tacony","Bridesburg","North Philadelphia","30th Street Station","Suburban Station","Market East Station","Temple University"});
		regionalRails.put(septaLines[11],new String[]{"Warminster","Hatboro","Willow Grove","Crestmont","Roslyn","Ardsley","Glenside","Jenkintown","Elkins Park","Melrose Park","Fern Rock TC","Wayne Junction","Temple University","Market East Station","Suburban Station","30th Street Station","University City"});
		regionalRails.put(septaLines[12],new String[]{"West Trenton","Yardley","Woodbourne","Langhorne","Neshaminy Falls","Trevose","Somerton","Forest Hills","Philmont","Bethayres","Meadowbrook","Rydal","Noble","Jenkintown","Elkins Park","Melrose Park","Fern Rock TC","Wayne Junction","Temple University","Market East Station","Suburban Station","30th Street Station","University City"});
		regionalRails.put(septaLines[13],new String[]{"Newark","Churchmans Crossing","Wilmington","Claymont","Marcus Hook","Highland Avenue","Chester","Eddystone","Crum Lynne","Ridley Park","Prospect Park","Norwood","Glenolden","Folcroft","Sharon Hill","Curtis Park","Darby","University City","30th Street Station","Suburban Station","Market East Station","Temple University"});
		
		zipCodes.put("Philadelphia, PA", "19104");
		zipCodes.put("Harrisburg, PA", "17112");
		zipCodes.put("Lancaster, PA", "17602");
		zipCodes.put("Dover, DE", "19901");
		zipCodes.put("Camden, NJ", "08101");
		zipCodes.put("Baltimore, MD", "21201");
	}
	
	// Philter
	// Let's put the pieces together.
	public Philter(){
		// Setup some layout
		GridLayout grid = new GridLayout(0,2);
		JPanel panel = new JPanel();
		panel.setLayout(grid);
		final JFrame frame = new JFrame("Philter - Filtering Philly");
		
		// Run basic parsing and panel creation
        final RegionalRailPanel spanel = new RegionalRailPanel();
        spanel.regionalRailParse("Wallingford","30th Street Station");
        final BusPanel bpanel = new BusPanel();
        bpanel.busParse("17");	
        final TrafficPanel trpanel = new TrafficPanel();
        trpanel.trafficParse();
        final RedditPanel rpanel = new RedditPanel();
        rpanel.redditParse();
        final WeatherPanel wpanel = new WeatherPanel();
        wpanel.weatherParse("19104");
        wpanel.forecastParse("19104");		
        final TwitterPanel phillytpanel = new TwitterPanel();
        phillytpanel.twitterParse("PhillyNews");	
        final TwitterPanel septatpanel = new TwitterPanel();
        septatpanel.twitterParse("SEPTA");	
        final TwitterPanel njtransittpanel = new TwitterPanel();
        njtransittpanel.twitterParse("NJ_TRANSIT");
        final TwitterPanel patcotpanel = new TwitterPanel();
        patcotpanel.twitterParse("RidePATCO");	
        
        // Septa Regional Rail Setup
        JLabel fromLineLabel = new JLabel("From Line:");
        JLabel fromStationLabel = new JLabel("From Station:");
        JLabel toLineLabel = new JLabel("     To Line:");
        JLabel toStationLabel = new JLabel("     To Station:");
        JComboBox fromLine = new JComboBox(septaLines);
        fromLine.setSelectedIndex(8);
        
        String[] temp = regionalRails.get("Media/Elwyn");
        final JComboBox fromStation = new JComboBox(temp);
        fromStation.setSelectedIndex(3);
        JComboBox toLine = new JComboBox(septaLines);
        toLine.setSelectedIndex(8);
        final JComboBox toStation = new JComboBox(regionalRails.get("Media/Elwyn"));
        toStation.setSelectedIndex(15);
        
        spanel.add(fromLineLabel);
        spanel.add(fromLine);
        spanel.add(fromStationLabel);
        spanel.add(fromStation);
        spanel.add(toLineLabel);
        spanel.add(toLine);
        spanel.add(toStationLabel);
        spanel.add(toStation);
        
        String col[] = {"From Station","To Station","Train #","Departure Time","Arrival Time","Delay"};
        spanel.getDefaultTableModel().setColumnIdentifiers(col);
        final JTable table = new JTable(spanel.getDefaultTableModel());
        table.setEnabled(false);
        JScrollPane tablescroll = new JScrollPane(table);
        tablescroll.setPreferredSize(new Dimension(450,103));
        spanel.add(tablescroll, BorderLayout.SOUTH);
        
        // Setup Weather
        Vector<String> zipList = new Vector<String>();
        for(String k : zipCodes.keySet()) {
            zipList.add(k);
        }
        JComboBox zipCombo = new JComboBox(zipList.toArray());
        zipCombo.setSelectedIndex(4);
        wpanel.add(zipCombo);
        
        // Twitter Setup
        JButton ptwitterUpdate = new JButton("Update");
        phillytpanel.add(ptwitterUpdate);
        JButton stwitterUpdate = new JButton("Update");
        septatpanel.add(stwitterUpdate);
        JButton ntwitterUpdate = new JButton("Update");
        njtransittpanel.add(ntwitterUpdate);
        JButton rtwitterUpdate = new JButton("Update");
        patcotpanel.add(rtwitterUpdate);
        
        // Reddit Setup
        JButton redditUpdate = new JButton("Update");
        rpanel.add(redditUpdate);
        
        
        // Bus Setup
        JLabel bus = new JLabel("Bus Route: ");
        final JTextField busField = new JTextField(6);
        JButton busUpdate = new JButton("Update");
        bpanel.add(bus);
        bpanel.add(busField);
        bpanel.add(busUpdate);
        
        // Traffic Setup
        JButton trafficUpdate = new JButton("Update");
        trpanel.add(trafficUpdate);
        
      
        // All these buttons and boxes need listeners
        trafficUpdate.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent _ev)
            {
            	trpanel.trafficParse();
                trpanel.repaint();
            }
        }); 
        busUpdate.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent _ev)
            {
            	bpanel.busParse(busField.getText());
                bpanel.repaint();
            }
        }); 
        ptwitterUpdate.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent _ev)
            {
            	phillytpanel.twitterParse("PhillyNews");
                phillytpanel.repaint();
            }
        }); 
        stwitterUpdate.addActionListener(new ActionListener()
        {
           public void actionPerformed(ActionEvent _ev)
           {
        	    septatpanel.twitterParse("Septa");
                septatpanel.repaint();
           }
        }); 
        ntwitterUpdate.addActionListener(new ActionListener()
        {
           public void actionPerformed(ActionEvent _ev)
           {
        	   njtransittpanel.twitterParse("NJ_TRANSIT");
                njtransittpanel.repaint();
           }
        }); 
        rtwitterUpdate.addActionListener(new ActionListener()
        {
           public void actionPerformed(ActionEvent _ev)
           {
        	   patcotpanel.twitterParse("RidePATCO");
           		patcotpanel.repaint();
           }
        }); 
        redditUpdate.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent _ev)
            {
            	rpanel.redditParse();
                rpanel.repaint();
            }
        });
        
        ItemListener fromListener = new ItemListener() {
            @SuppressWarnings("unchecked")
			public void itemStateChanged(ItemEvent itemEvent) {
              ItemSelectable is = itemEvent.getItemSelectable();
              Object selected[] = is.getSelectedObjects();
              String key = (String) selected[0];
              fromStation.setModel(new JComboBox<>(regionalRails.get(key)).getModel());
            }
        };
        fromLine.addItemListener(fromListener);
          
        ItemListener toListener = new ItemListener() {
            @SuppressWarnings("unchecked")
  			public void itemStateChanged(ItemEvent itemEvent) {
                ItemSelectable is = itemEvent.getItemSelectable();
                Object selected[] = is.getSelectedObjects();
                String key = (String) selected[0];
                toStation.setModel(new JComboBox<>(regionalRails.get(key)).getModel());
              }
        };
        toLine.addItemListener(toListener);
            
        ItemListener toStationListener = new ItemListener() {
            @SuppressWarnings("unchecked")
            public void itemStateChanged(ItemEvent itemEvent) {
              ItemSelectable is = itemEvent.getItemSelectable();
              Object selected[] = is.getSelectedObjects();
              String key = (String) selected[0];
              stations[1] = key;
              spanel.regionalRailParse(stations[0],stations[1]);
              table.setModel(spanel.getDefaultTableModel());
            }
        };
        toStation.addItemListener(toStationListener);
        
        ItemListener fromStationListener = new ItemListener() {
            @SuppressWarnings("unchecked")
  	        public void itemStateChanged(ItemEvent itemEvent) {
              ItemSelectable is = itemEvent.getItemSelectable();
              Object selected[] = is.getSelectedObjects();
              String key = (String) selected[0];
              stations[0] = key;
              spanel.regionalRailParse(stations[0],stations[1]);
              table.setModel(spanel.getDefaultTableModel());
            }
        };
        fromStation.addItemListener(fromStationListener);
                
	    ItemListener zipListener = new ItemListener() {
	        @SuppressWarnings("unchecked")
			public void itemStateChanged(ItemEvent itemEvent) {
	          ItemSelectable is = itemEvent.getItemSelectable();
	          Object selected[] = is.getSelectedObjects();
	          String key = (String) selected[0];
	          stations[0] = key;
	          wpanel.weatherParse(zipCodes.get(key));
	          wpanel.forecastParse(zipCodes.get(key));
	          wpanel.repaint();
	        }
	    };
        zipCombo.addItemListener(zipListener);
        
        // Add all the panels to the border factory
        JTabbedPane tabbedPane = new JTabbedPane(); 
        JTabbedPane ttabbedPane = new JTabbedPane();
        tabbedPane.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Transportation"),
                BorderFactory.createEmptyBorder(0,0,0,0)));
        rpanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("/r/Philadelphia"),
                BorderFactory.createEmptyBorder(0,10,10,0)));
        wpanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Weather Underground"),
                BorderFactory.createEmptyBorder(0,10,10,0)));
        ttabbedPane.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Twitter"),
                BorderFactory.createEmptyBorder(0,0,0,0)));
        
        
        // Populate transportation tabbed panel
        tabbedPane.addTab("Septa Regional Rail", null, spanel,"Septa Regional Rail"); 
        tabbedPane.addTab("Traffic", null, trpanel,"Traffic");
        tabbedPane.addTab("Buses", null, bpanel,"Buses");
        
        // Populate twitter tabbed panel
        ttabbedPane.addTab("PhillyNews", null, phillytpanel,"PhillyNews");
        ttabbedPane.addTab("Septa", null, septatpanel,"Septa");
        ttabbedPane.addTab("RidePatco", null, patcotpanel,"RidePatco");
        ttabbedPane.addTab("NJ_Transit", null, njtransittpanel,"NJ_Transit");
        
        // Add everything to the master panel
        panel.add(tabbedPane);
		panel.add(rpanel);
		panel.add(wpanel);
		panel.add(ttabbedPane);
 
		frame.add(panel);
		frame.setSize(1025,565);
		frame.setVisible(true);
		frame.setResizable(false);
	}
	
	// I am main, king of kings
	public static void main(String[] args) throws IOException 
	{
		variableSetup();
		new Philter();
	}

}