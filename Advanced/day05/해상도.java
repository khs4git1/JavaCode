import java.awt.Toolkit;
import java.awt.Dimension;
  
public class 해상도 {
	public static void main(String[] agrs){
		Dimension res = Toolkit.getDefaultToolkit().getScreenSize();
		System.out.println("해상도 : " + res.width + " x " + res.height);  
	}
}