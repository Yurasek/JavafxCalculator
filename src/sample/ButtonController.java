package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;

public class ButtonController {

    @FXML
    private Text displayHistory;

    @FXML
    private Text displayOperand;
    //tralyalyalyalyalya
    @FXML
    private Text displayInfo;

    private boolean pressedOperator = false;
    private Operators operators = new Operators();
    private StringBuilder history = new StringBuilder();
    private StringBuilder operand = new StringBuilder(10);

    @FXML
    private void keyboardInput(KeyEvent event){
        if(event.getCode().isDigitKey()){
            if(operand.length() < 9){
                pressedOperator = false;
                operand.append(event.getText());
                displayOperand.setText(operand.toString());
            }else{
                displayInfo.setText("Число цифр > 10!");
            }
        }
        if(isOperator(event) && !displayOperand.getText().equals("0")) {
            if (history.toString().equals("")) {
                history.append(operand.toString());
                history.append(event.getText());
                displayHistory.setText(history.toString());
                operators.result = Integer.parseInt(operand.toString());
                clearStringBuilder(operand);
                pressedOperator = true;
            }else if(!history.toString().equals("") && !pressedOperator){
                history.append(operand.toString());
                displayOperand.setText(operators.makeResult(operand.toString(),
                        popPrevOperator(operand.length(), history.toString())));
                history.append(event.getText());
                displayHistory.setText(history.toString());
                clearStringBuilder(operand);
                pressedOperator = true;
            }else if(pressedOperator){
                history.deleteCharAt(history.length() - 1);
                history.append(event.getText());
                displayHistory.setText(history.toString());
            }
        }
    }

    private String popPrevOperator(int length, String history){
        char[] charOperator = {history.charAt(history.length() - length - 1)};
        String stringOperator = new String(charOperator);
        return stringOperator;
    }

    @FXML
    private void result(){
        if(!pressedOperator && operand.length() > 0) {
            displayOperand.setText(operators.makeResult(operand.toString(),
                    history.substring(history.length()-1)));
            clearStringBuilder(operand);
            clearStringBuilder(history);
            operand.append(operators.result);
            displayHistory.setText("");
        }

    }

    private boolean isOperator(KeyEvent event){

        return  event.getCode().equals(KeyCode.MULTIPLY) ||
                event.getCode().equals(KeyCode.SUBTRACT) ||
                event.getCode().equals(KeyCode.ADD) ||
                event.getCode().equals(KeyCode.DIVIDE);

    }

    @FXML
    private void toDisplayDigit(ActionEvent event){
            if(operand.length() < 9){
                Button clickedButton = (Button) event.getTarget();
                pressedOperator = false;
                operand.append(clickedButton.getText());
                displayOperand.setText(operand.toString());
            }else{
                displayInfo.setText("Не более 9 цифр!");
            }
    }

    @FXML
    private void operatorHandler(ActionEvent event){
        if(!displayOperand.getText().equals("0")) {
            Button clickedButton = (Button) event.getTarget();
            if (history.toString().equals("")) {
                history.append(operand.toString());
                history.append(clickedButton.getText());
                displayHistory.setText(history.toString());
                operators.result = Integer.parseInt(operand.toString());
                clearStringBuilder(operand);
                pressedOperator = true;
            }else if(!history.toString().equals("") && !pressedOperator){
                history.append(operand.toString());
                displayOperand.setText(operators.makeResult(operand.toString(),
                        popPrevOperator(operand.length(), history.toString())));
                history.append(clickedButton.getText());
                displayHistory.setText(history.toString());
                clearStringBuilder(operand);
                pressedOperator = true;
            }else if(pressedOperator){
                history.deleteCharAt(history.length() - 1);
                history.append(clickedButton.getText());
                displayHistory.setText(history.toString());
            }
        }
    }

    private void clearStringBuilder(StringBuilder text){
        int len = text.length();
        text.delete(0, len);
    }

    @FXML
    private void clearExpression(){
        clearStringBuilder(history);
        clearStringBuilder(operand);
        operators.result = 0;
        displayOperand.setText("0");
        displayHistory.setText("");
        displayInfo.setText("");
    }
}