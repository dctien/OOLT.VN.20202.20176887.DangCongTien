// package Lab01;
import javax.swing.JOptionPane;
public class ShowTwoNumber {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String strNum1, strNum2;
		String strNotification = "You've just entered: ";
		strNum1 = JOptionPane.showInputDialog(null, "Please input first number: ", "Input the first number", JOptionPane.INFORMATION_MESSAGE);
		strNotification+= strNum1 + " and ";
		
		strNum2 = JOptionPane.showInputDialog(null, "Please input second number: ", "Input the second number", JOptionPane.INFORMATION_MESSAGE);
		strNotification+= strNum2;
		
		JOptionPane.showMessageDialog(null, strNotification, "Show two numbers", JOptionPane.INFORMATION_MESSAGE);
		System.exit(0);
	}

}
