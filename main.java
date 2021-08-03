import java.io.File;

import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/* Author: Dilay SAPMAZ
 * ID: 041701032
 * Finish Date: 14.03.2018
 * Explanation:This code reads a file from a txt file, and creates new students with a name and grades. And then it shows us that. Number of grades are
 * width of rectangles. And rectangles color is random every time. 
 */
public class Dilay_Sapmaz extends Application {

	public void start(Stage primaryStage) throws FileNotFoundException{

		//Scene parameters
		int sceneWidth = 1000; 
		int sceneHeight = 800;
		Pane pane = new Pane();

		//create file object
		File file = new File("student_info1.txt"); 
		Scanner input = new Scanner(file);   // open a scanner object to read from file
		Scanner input2 = new Scanner(file);

		//if file is not found, report and exit program
		if(!file.exists()) {    
			System.out.println("File can not be found! exiting program...");
			System.exit(1);
		}


		int countArraysSize=0;
		while(input2.hasNext()) { //for reading the file
			input2.nextLine();
			countArraysSize++;
		}

		Student[] studentArray = new Student[countArraysSize-1]; //for students, I create an array.

		int numberQuiz = input.nextInt(); 

		while(input.hasNext()) { //it looks for is it first names or grades

			int[] array = new int[numberQuiz];

			for(int c=0; c< studentArray.length; c++) {

				studentArray[c] = new Student(input.next());
				//studentArray[c].setName(input.next());  //taking students name
				int[] tempArray = new int[numberQuiz ];

				for(int i=0;i<numberQuiz ;i++) {
					tempArray[i] = input.nextInt();

				}

				studentArray[c].grades = tempArray;
				studentArray[c].printInfo();

			}

			//input.close(); //closing input


			//sort
			for(int i=0;i<countArraysSize-1;i++) {
				for(int j=0;j<i;j++) {
					if(sumGrade(studentArray[i].grades) < sumGrade(studentArray[j].grades)) {
						Student change = new Student();
						change = studentArray[j];
						studentArray[j] = studentArray[i];
						studentArray[i] = change;
					}
				}
			}

			int scaleFactor = 5;
			int y = 80;

			//these are for rectangles.
			for (int t = 0; t < studentArray.length; t++) {
				int x = 100; 
				int h = 25; 
				for (int i = 0; i <array.length ; i++) {

					//grades rectangles
					Rectangle rectangle = new Rectangle(x, y, studentArray[t].grades[i] * scaleFactor, h);
					rectangle.setFill(randomColor());
					rectangle.setStroke(Color.BLACK);
					pane.getChildren().add(rectangle);

					x = x + studentArray[t].grades[i] * scaleFactor;
				}

				//for students grades text
				Text myText = new Text();
				myText.setFont(Font.font("verdana", FontWeight.THIN, FontPosture.REGULAR, 10));
				myText.setX(sumGrade(studentArray[t].grades)*scaleFactor+103);
				myText.setY(y + 15);
				myText.setText(Integer.toString(sumGrade(studentArray[t].grades)));
				pane.getChildren().add(myText);

				//student names
				Text myText2 = new Text();
				myText2.setFont(Font.font("verdana", FontWeight.THIN, FontPosture.REGULAR, 15));  //visuality of names
				myText2.setX(25);
				myText2.setY(y + 10);
				myText2.setText(studentArray[t].getName() + " ");
				pane.getChildren().add(myText2);

				//background rectangle
				Rectangle rectangle = new Rectangle(x, y,(100 - (studentArray[t].grades[0] + studentArray[t].grades[1] + studentArray[t].grades[2] + studentArray[t].grades[3]+ studentArray[t].grades[4])) * scaleFactor, h);
				//I did it for 5 students, I cannot think for other text files.. 
				rectangle.setFill(null);
				rectangle.setStroke(Color.BLACK);

				//these are for visuality
				y = y + 40;
				x = x - 600;
				pane.getChildren().add(rectangle);


			}
			//needed parameters for new pane
			Scene scene = new Scene(pane, sceneWidth, sceneHeight);
			primaryStage.setTitle("Student Grades"); 
			primaryStage.setScene(scene); 
			primaryStage.setResizable(false);
			primaryStage.show();
		}
	}
	public static void main(String[] args) {
		launch(args);
	}
	public Color randomColor() { //method that random colors
		Random random=new Random();
		int red=random.nextInt(255);
		int green=random.nextInt(255);
		int blue=random.nextInt(255);
		return Color.rgb(red, green, blue);

	}

	public int sumGrade(int[] tempArray) { //the method that calculating the sum of all grades of one student
		int sum =0;
		for(int i=0; i<tempArray.length;i++) {
			sum += tempArray[i];
		}
		return sum;	
	}

}



