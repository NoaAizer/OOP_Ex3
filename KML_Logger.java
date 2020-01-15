package gameClient;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import utils.Point3D;


public class KML_Logger {

	private int stage;
	private StringBuilder info;

	public KML_Logger(int stage) {
		this.stage = stage;
		info = new StringBuilder();
		kmlStart();
	}

	public void kmlStart()
	{
		info.append(
				"<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" +
						"<kml xmlns=\"http://earth.google.com/kml/2.2\">\r\n" +
						"  <Document>\r\n" +
						"    <name>" + "Game stage :"+this.stage + "</name>" +"\r\n"+
						" <Style id=\"node\">\r\n" +
						"      <IconStyle>\r\n" +
						"        <Icon>\r\n" +
						"          <href>http://maps.google.com/mapfiles/kml/pal3/icon35.png</href>\r\n" +
						"        </Icon>\r\n" +
						"        <hotSpot x=\"32\" y=\"1\" xunits=\"pixels\" yunits=\"pixels\"/>\r\n" +
						"      </IconStyle>\r\n" +
						"    </Style>" +
						" <Style id=\"fruit-banana\">\r\n" +
						"      <IconStyle>\r\n" +
						"        <Icon>\r\n" +
						"          <href>http://maps.google.com/mapfiles/kml/pal5/icon49.png</href>\r\n" +
						"        </Icon>\r\n" +
						"        <hotSpot x=\"32\" y=\"1\" xunits=\"pixels\" yunits=\"pixels\"/>\r\n" +
						"      </IconStyle>\r\n" +
						"    </Style>" +
						" <Style id=\"fruit-apple\">\r\n" +
						"      <IconStyle>\r\n" +
						"        <Icon>\r\n" +
						"          <href>http://maps.google.com/mapfiles/kml/pal5/icon56.png</href>\r\n" +
						"        </Icon>\r\n" +
						"        <hotSpot x=\"32\" y=\"1\" xunits=\"pixels\" yunits=\"pixels\"/>\r\n" +
						"      </IconStyle>\r\n" +
						"    </Style>" +
						" <Style id=\"robot\">\r\n" +
						"      <IconStyle>\r\n" +
						"        <Icon>\r\n" +
						"          <href>http://maps.google.com/mapfiles/kml/pal4/icon26.png></href>\r\n" +
						"        </Icon>\r\n" +
						"        <hotSpot x=\"32\" y=\"1\" xunits=\"pixels\" yunits=\"pixels\"/>\r\n" +
						"      </IconStyle>\r\n" +
						"    </Style>"
				);
	}

	public void addPlaceMark(String id, Point3D location)
	{
		//Create formatter
		DateTimeFormatter FOMATTER = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		//Local date time instance
		LocalDateTime localDateTime = LocalDateTime.now();
		//Get formatted String
		String ldtString = FOMATTER.format(localDateTime);

		info.append(
				"    <Placemark>\r\n" +
						"      <TimeStamp>\r\n" +
						"        <when>" + ldtString+ "</when>\r\n" +
						"      </TimeStamp>\r\n" +
						"      <styleUrl>#" + id + "</styleUrl>\r\n" +
						"      <Point>\r\n" +
						"        <coordinates>" + location.toString() + "</coordinates>\r\n" +
						"      </Point>\r\n" +
						"    </Placemark>\r\n"
				);

	}
	public void kmlEnd()
	{
		info.append(
				"  \r\n</Document>\r\n" +
						"</kml>"
				);
		try
		{
			File f=new File("data/"+this.stage+".kml");
			PrintWriter pw=new PrintWriter(f);
			pw.write(info.toString());
			pw.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void saveKML(String KML) {
		try {
			FileWriter f1 = new FileWriter("test1.kml");
			f1.write(KML);
			f1.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}