import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class Loader extends Applet {

	public static Properties client_parameters = new Properties();
	public JFrame frame;
	public JPanel panel = new JPanel();

	public static boolean usingRS = false;
	public static boolean useIsaac = false;
	
	public static String IP = "127.0.0.1";
	public static boolean LOBBY_ENABLED = false;
	public static boolean DISABLE_XTEA_CRASH = true;
	public static boolean DISABLE_USELESS_PACKETS = true;
	public static boolean DISABLE_RSA = false;
	
	public static final int REVISION = 718;
	public static final int LOBBY_PORT = 43594;
	public static String LOBBY_IP = "127.0.0.1";
	public static int SUB_REVISION = 3;
	public static Loader instance;
	public static int[] outSizes = new int[256];
	public static boolean localHost;

	public static void main(String[] strings) throws Exception {
		Loader loader = new Loader();
		
		loader.doFrame();
	}
	
    @Override
	public void init() {
		instance = this;
		doApplet();
	}

	void doApplet() {
		setParams();
		startClient();
	}

	public void doFrame() {
		setParams();
		openFrame();
		startClient();
	}

	void setParams() {
		client_parameters.put("separate_jvm", "true");
		client_parameters.put("boxbgcolor", "black");
		client_parameters.put("image", "http://www.runescape.com/img/game/splash2.gif");
		client_parameters.put("centerimage", "true");
		client_parameters.put("boxborder", "false");
		client_parameters.put("java_arguments", "-Xmx256m -Xss2m -Dsun.java2d.noddraw=true -XX:CompileThreshold=1500 -Xincgc -XX:+UseConcMarkSweepGC -XX:+UseParNewGC");
		client_parameters.put("27", "0");
		client_parameters.put("1", "0");
		client_parameters.put("16", "false");
		client_parameters.put("17", "false");
		client_parameters.put("21", "1"); // WORLD ID
		client_parameters.put("30", "false");
		client_parameters.put("20", LOBBY_IP);
		client_parameters.put("29", "");
		client_parameters.put("11", "true");
		client_parameters.put("25", "1378752098");
		client_parameters.put("28", "0");
		client_parameters.put("8", ".runescape.com");
		client_parameters.put("23", "false");
		client_parameters.put("32", "0");
		client_parameters.put("15", "wwGlrZHF5gKN6D3mDdihco3oPeYN2KFybL9hUUFqOvk");
		client_parameters.put("0", "IjGJjn4L3q5lRpOR9ClzZQ");
		client_parameters.put("2", "");
		client_parameters.put("4", "1"); // WORLD ID
		client_parameters.put("14", "");
		client_parameters.put("5", "8194");
		client_parameters.put("-1", "QlwePyRU5GcnAn1lr035ag");
		client_parameters.put("6", "0");
		client_parameters.put("24", "true,false,0,43,200,18,0,21,354,-15,Verdana,11,0xF4ECE9,candy_bar_middle.gif,candy_bar_back.gif,candy_bar_outline_left.gif,candy_bar_outline_right.gif,candy_bar_outline_top.gif,candy_bar_outline_bottom.gif,loadbar_body_left.gif,loadbar_body_right.gif,loadbar_body_fill.gif,6");
		client_parameters.put("3", "hAJWGrsaETglRjuwxMwnlA/d5W6EgYWx");
		client_parameters.put("12", "false");
		client_parameters.put("13", "0");
		client_parameters.put("26", "0");
		client_parameters.put("9", "77");
		client_parameters.put("22", "false");
		client_parameters.put("18", "false");
		client_parameters.put("33", "");
		client_parameters.put("haveie6", "false");
	}

	void openFrame() {
		frame = new JFrame("Virent - GameLoader");
		frame.setLayout(new BorderLayout());
		panel.setLayout(new BorderLayout());
		panel.add(this);
		panel.setPreferredSize(new Dimension(765, 503));
		frame.getContentPane().add(panel, "Center");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);

	}

	void startClient() {
		try {
			client clnt = new client();
			clnt.supplyApplet(this);
			clnt.init();
			clnt.start();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

    @Override
	public String getParameter(String string) {
		return (String) client_parameters.get(string);
	}

    @Override
	public URL getDocumentBase() {
		return getCodeBase();
	}

    @Override
    public URL getCodeBase() {
		URL url;
		try {
			url = new URL("http://" + IP);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return url;
	}

	void printOut() throws IOException {
		String s = "";
		for (int i : outSizes) {
			s += i + ", ";
		}
		System.out.println("in = new int[] {" + s + "};");
	}
}
