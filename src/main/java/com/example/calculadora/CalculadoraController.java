package com.example.calculadora;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class CalculadoraController {
    @FXML
    private TextField display;

    private double numero1 = 0;
    private String operador = "";
    private boolean comecarNovoNumero = true;

    @FXML
    private void handleNumber(javafx.event.ActionEvent event) {
        String valor = ((javafx.scene.control.Button) event.getSource()).getText();

        if (comecarNovoNumero) {
            display.setText(valor);
            comecarNovoNumero = false;
        } else {
            display.setText(display.getText() + valor);
        }
    }

    @FXML
    private void handleOperator(javafx.event.ActionEvent event) {
        String novoOperador = ((javafx.scene.control.Button) event.getSource()).getText();

        if (!operador.isEmpty()) {
            calcular();
        }

        numero1 = Double.parseDouble(display.getText());
        operador = novoOperador;
        comecarNovoNumero = true;
    }

    @FXML
    private void handleEquals() {
        if (!operador.isEmpty()) {
            calcular();
            operador = "";
        }
    }

    @FXML
    private void handleClear() {
        display.setText("0");
        numero1 = 0;
        operador = "";
        comecarNovoNumero = true;
    }

    @FXML
    private void handleDecimal() {
        if (comecarNovoNumero) {
            display.setText("0.");
            comecarNovoNumero = false;
        } else if (!display.getText().contains(".")) {
            display.setText(display.getText() + ".");
        }
    }

    private void calcular() {
        double numero2 = Double.parseDouble(display.getText());
        double resultado = 0;

        switch (operador) {
            case "+":
                resultado = numero1 + numero2;
                break;
            case "-":
                resultado = numero1 - numero2;
                break;
            case "*":
                resultado = numero1 * numero2;
                break;
            case "/":
                if (numero2 != 0) {
                    resultado = numero1 / numero2;
                } else {
                    display.setText("Erro");
                    return;
                }
                break;
        }

        display.setText(String.valueOf(resultado));
        numero1 = resultado;
        comecarNovoNumero = true;
    }
}