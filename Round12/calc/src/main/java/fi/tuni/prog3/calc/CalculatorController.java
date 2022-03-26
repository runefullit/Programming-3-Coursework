package fi.tuni.prog3.calc;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class CalculatorController {

    private enum operation {
        ADD,
        SUBTRACT,
        MULTIPLY,
        DIVIDE
    }

    @FXML
    TextField fieldOp1;
    @FXML
    TextField fieldOp2;
    @FXML
    Label fieldRes;

    @FXML
    private void addClicked() {
        buttonClicked(operation.ADD);
    }

    @FXML
    private void subtractClicked() {
        buttonClicked(operation.SUBTRACT);
    }

    @FXML
    private void multiplyClicked() {
        buttonClicked(operation.MULTIPLY);
    }

    @FXML
    private void divideClicked() {
        buttonClicked(operation.DIVIDE);
    }

    private void buttonClicked(operation op) {
        try {
        double op1 = Double.parseDouble(fieldOp1.getText());
        double op2 = Double.parseDouble(fieldOp2.getText());
        double result = switch (op) {
            case ADD -> op1 + op2;
            case SUBTRACT -> op1 - op2;
            case MULTIPLY -> op1 * op2;
            case DIVIDE -> op1 / op2;
            default -> throw new UnsupportedOperationException();
        };
        fieldRes.setStyle("-fx-background-color: white");
        fieldRes.setText(String.valueOf(result));
        }
        catch (NumberFormatException e) {
            fieldRes.setStyle("-fx-background-color: red");
            fieldRes.setText("Please give two numbers.");
        }

    }
}