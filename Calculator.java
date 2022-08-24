
// package application; // uncomment, if that's what you used in CS112

import java.util.Stack;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class Calculator extends Application 
{
	// the calculator dimensions
	public static int CALC_WIDTH = 400;
	public static int CALC_HEIGHT = 300;

	// the calculator screen
	private TextField screen; 

	// the calculator buttons
	private Button button0;
	private Button button1;
	private Button button2;
	private Button button3;
	private Button button4;
	private Button button5;
	private Button button6;
	private Button button7; 
	private Button button8;
	private Button button9;
	private Button buttonDot;
	private Button buttonAdd;
	private Button buttonSub;
	private Button buttonEq;
	private Button buttonDiv;
	private Button buttonMult;
	private Button buttonRightPar;
	private Button buttonLeftPar;
	private Button buttonPower;
	private Button buttonC;

	//non-GUI variable
	private Stack<Operator> operator;
	private Stack<Double> number;
	
	private String currNum;

	@Override
	public void start(Stage primaryStage) 
	{
		// create the calculator screen
		screen = new TextField(); 
		screen.setDisable(true);

		operator = new Stack<>();
		number = new Stack<>();
		
		currNum = "";

		// create the buttons
		button0 = new Button("0");
		button1 = new Button("1");
		button2 = new Button("2");
		button3 = new Button("3");
		button4 = new Button("4");
		button5 = new Button("5");
		button6 = new Button("6");
		button7 = new Button("7");
		button8 = new Button("8");
		button9 = new Button("9");
		buttonDot = new Button(".");
		buttonAdd = new Button("+");
		buttonSub = new Button("-");
		buttonDiv = new Button("/");
		buttonMult = new Button("×");
		buttonEq = new Button("=");
		buttonC = new Button("C");
		buttonPower = new Button("^");
		buttonLeftPar = new Button("(");
		buttonRightPar = new Button(")");

		// attach a handler to process button clicks 
		ButtonHandler handler = new ButtonHandler();
		button0.setOnAction(handler);
		button1.setOnAction(handler);
		button2.setOnAction(handler);
		button3.setOnAction(handler);
		button4.setOnAction(handler);
		button5.setOnAction(handler);
		button6.setOnAction(handler);
		button7.setOnAction(handler);
		button8.setOnAction(handler);
		button9.setOnAction(handler);
		buttonDot.setOnAction(handler);
		buttonMult.setOnAction(handler);
		buttonAdd.setOnAction(handler);
		buttonSub.setOnAction(handler);
		buttonDiv.setOnAction(handler);
		buttonEq.setOnAction(handler);
		buttonC.setOnAction(handler);
		buttonPower.setOnAction(handler);
		buttonLeftPar.setOnAction(handler);
		buttonRightPar.setOnAction(handler);

		// setup a grid panel for the keypad
		GridPane keypad = new GridPane();  
		keypad.setMinSize(CALC_WIDTH, CALC_HEIGHT); 
		keypad.setPadding(new Insets(10, 10, 10, 10));  
		keypad.setVgap(5); 
		keypad.setHgap(5);       
		keypad.setAlignment(Pos.CENTER); 

		// attach the buttons to the keypad grid
		keypad.add(button1, 0, 0); 
		keypad.add(button2, 1, 0); 
		keypad.add(button3, 2, 0);       
		keypad.add(button4, 0, 1);
		keypad.add(button5, 1, 1);
		keypad.add(button6, 2, 1);
		keypad.add(button7, 0, 2);
		keypad.add(button8, 1, 2);
		keypad.add(button9, 2, 2);
		keypad.add(button0, 0, 4);
		keypad.add(buttonDot, 1, 4);
		keypad.add(buttonAdd, 3, 0);
		keypad.add(buttonMult, 3, 1);
		keypad.add(buttonLeftPar, 3, 2);
		keypad.add(buttonRightPar, 4, 2);
		keypad.add(buttonSub, 4, 0);
		keypad.add(buttonDiv, 4, 1);
		keypad.add(buttonEq, 2, 4);
		keypad.add(buttonPower, 3, 4);
		keypad.add(buttonC, 4, 4);

		// put screen and keypad together
		BorderPane gui = new BorderPane();
		gui.setTop(screen);
		gui.setCenter(keypad);

		// set up the scene
		Scene scene = new Scene(gui); 
		primaryStage.setTitle("Calculator");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public void manageOp(Operator curr) 
	{
		if(!operator.empty()) {
			Operator topOp = operator.peek();

			while((!operator.empty()) && curr.getPrecedence() < topOp.getPrecedence()) {
				topOp = operator.pop();
				double result = topOp.evaluate(number.pop(), number.pop());
				number.push(result);

				if(!operator.empty()) {
					topOp = operator.peek();
				}
			}
		}
		operator.push(curr);
	} 

	// Handler for processing the button clicks 
	private class ButtonHandler implements EventHandler<ActionEvent>
	{ 
		@Override 
		public void handle(ActionEvent e) 
		{
			if (e.getSource() == button1) {
				screen.appendText("1");
				currNum = currNum + 1;
			}
			else if (e.getSource() == button2) {
				screen.appendText("2");
				currNum = currNum + 2;
			}
			else if (e.getSource() == button3) {
				screen.appendText("3");
				currNum = currNum + 3;
			}
			else if (e.getSource() == button4) {
				screen.appendText("4");
				currNum = currNum + 4;
			}
			else if(e.getSource() == button5) {
				screen.appendText("5");
				currNum = currNum + 5;
			}
			else if(e.getSource() == button6) {
				screen.appendText("6");
				currNum = currNum + 6;
			}
			else if(e.getSource() == button7) {
				screen.appendText("7");
				currNum = currNum + 7;
			}
			else if(e.getSource() == button8) {
				screen.appendText("8");
				currNum = currNum + 8;
			}
			else if(e.getSource() == button9) {
				screen.appendText("9");
				currNum = currNum + 9;
			}
			else if (e.getSource() == button0) {
				screen.appendText("0");
				currNum = currNum + 0;
			}
			else if (e.getSource() == buttonDot) {
				screen.appendText(".");
				currNum = currNum + ".";
			}
			else if(e.getSource() == buttonMult) {
				if(currNum != null) {
					number.push(Double.parseDouble(currNum));
				}
				
				currNum = "";
				screen.appendText("×");
				MultOperator mult = new MultOperator();
				manageOp(mult);
			}
			else if(e.getSource() == buttonAdd) {
				if(currNum != null) {
					number.push(Double.parseDouble(currNum));
				}
				currNum = "";
				
				screen.appendText("+");
				AddOperator add = new AddOperator();
				manageOp(add);
			}
			else if(e.getSource() == buttonSub) {
				if(currNum != null) {
					number.push(Double.parseDouble(currNum));
				}
				currNum = "";
				
				screen.appendText("-");
				SubOperator sub = new SubOperator();
				manageOp(sub);
			}
			else if(e.getSource() == buttonEq) {
				if(currNum != null) {
					number.push(Double.parseDouble(currNum));
				}
				currNum = "";
				screen.appendText("=");
				
				EqualOperator equal = new EqualOperator();
				manageOp(equal);
				operator.pop();
				screen.setText("" + number.peek());
				System.out.println(number.pop());
			}
			else if(e.getSource() == buttonDiv) {
				if(currNum != null) {
					number.push(Double.parseDouble(currNum));
				}
				currNum = "";
				screen.appendText("/");
				
				DivideOperator div = new DivideOperator();
				manageOp(div);
			}
			else if(e.getSource() == buttonPower) {
				if(currNum != null) {
					number.push(Double.parseDouble(currNum));
				}
				currNum = "";
				screen.appendText("^");
				PowerOperator pow = new PowerOperator();
				manageOp(pow);
			}
			else if(e.getSource() == buttonLeftPar) {
				currNum = "";
				screen.appendText("(");
				LeftParenOperator leftPar = new LeftParenOperator();
				operator.push(leftPar);
			}
			else if(e.getSource() == buttonRightPar) {
				number.push(Double.parseDouble(currNum));
				currNum = null;
				
				screen.appendText(")");
				RightParenOperator rightPar = new RightParenOperator();
				manageOp(rightPar);
				operator.pop();
				operator.pop();
			}
			else if(e.getSource() == buttonC) {
				number.removeAllElements();
				operator.removeAllElements();
				currNum = "";

				screen.setText("");
			}

		} 
	}  

	public static void main(String[] args) 
	{
		launch(args);
	}
}