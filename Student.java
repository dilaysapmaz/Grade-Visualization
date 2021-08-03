import java.util.Arrays;

/* Author: Dilay SAPMAZ
 * ID: 041701032
 * Finish Date: 14.03.2018
 * Explanation: This code reads a file from a txt file, and creates new students with a name and grades. And then it shows us that. Number of grades are
 * width of rectangles. And rectangles color is random every time. 
 */
public class Student {

	//data fields
	private String name;  
	public int[] grades;

	Student(){ 
	}

	//tip:create grades array in the second constructor??
	Student(String inputName){
		name=inputName;
	}

	public void printInfo() {
		System.out.println("Name: " +name+ "Grades" +Arrays.toString(grades));
		}
		public void setName(String name) {//Sets the name of of student
			this.name=name;
		}
		public String getName () {
			return name;
		}
		
	
}
