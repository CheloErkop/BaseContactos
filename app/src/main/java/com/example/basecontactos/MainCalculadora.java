package com.example.basecontactos;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainCalculadora extends AppCompatActivity {
    TextView tvResultado;
    float valor= 0f;
    float numero1 = 0f;
    float numero2 = 0f;
    String operacion = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_calculadora);

        tvResultado = findViewById(R.id.tvResultado);

    }
    public void escribirUno(View view) {
        valor = Float.parseFloat(tvResultado.getText().toString());
        if (valor == 0f){
            tvResultado.setText("1");
        }else{
            tvResultado.setText(tvResultado.getText()+"1");
        }
    }
    public void escribirDos(View view) {
        valor = Float.parseFloat(tvResultado.getText().toString());
        if (valor == 0f){
            tvResultado.setText("2");
        }else{
            tvResultado.setText(tvResultado.getText()+"2");
        }
    }
    public void escribirTres(View view) {
        valor = Float.parseFloat(tvResultado.getText().toString());
        if (valor == 0f){
            tvResultado.setText("3");
        }else{
            tvResultado.setText(tvResultado.getText()+"3");
        }
    }
    public void escribirCuatro(View view) {
        valor = Float.parseFloat(tvResultado.getText().toString());
        if (valor == 0f){
            tvResultado.setText("4");
        }else{
            tvResultado.setText(tvResultado.getText()+"4");
        }
    }

    public void escribirCinco(View view) {
        valor = Float.parseFloat(tvResultado.getText().toString());
        if (valor == 0f){
            tvResultado.setText("5");
        }else{
            tvResultado.setText(tvResultado.getText()+"5");
        }
    }
    public void escribirSeis(View view) {
        valor = Float.parseFloat(tvResultado.getText().toString());
        if (valor == 0f){
            tvResultado.setText("6");
        }else{
            tvResultado.setText(tvResultado.getText()+"6");
        }
    }
    public void escribirSiete(View view) {
        valor = Float.parseFloat(tvResultado.getText().toString());
        if (valor == 0f){
            tvResultado.setText("7");
        }else{
            tvResultado.setText(tvResultado.getText()+"7");
        }
    }
    public void escribirOcho(View view) {
        valor = Float.parseFloat(tvResultado.getText().toString());
        if (valor == 0f){
            tvResultado.setText("8");
        }else{
            tvResultado.setText(tvResultado.getText()+"8");
        }
    }
    public void escribirNueve(View view) {
        valor = Float.parseFloat(tvResultado.getText().toString());
        if (valor == 0f){
            tvResultado.setText("9");
        }else{
            tvResultado.setText(tvResultado.getText()+"9");
        }
    }
    public void escribirMas(View view) {
        numero1 = Float.parseFloat(tvResultado.getText().toString());
        operacion = "+";
        tvResultado.setText("0");
    }
    public void escribirCero(View view) {
        valor = Float.parseFloat(tvResultado.getText().toString());
        if (valor == 0f){
            tvResultado.setText("0");
        }else{
            tvResultado.setText(tvResultado.getText()+"0");
        }
    }

    public void escribirMenos(View view) {
        numero1 = Float.parseFloat(tvResultado.getText().toString());
        operacion = "-";
        tvResultado.setText("0");
    }
    public void escribirMultiplicar(View view) {
        numero1 = Float.parseFloat(tvResultado.getText().toString());
        operacion = "*";
        tvResultado.setText("0");
    }
    public void escribirDividir(View view) {
        numero1 = Float.parseFloat(tvResultado.getText().toString());
        operacion = "/";
        tvResultado.setText("0");
    }
    public void escribirResultado(View view) {
        numero2 = Float.parseFloat(tvResultado.getText().toString());
        if (operacion.equals("/")){
            if (numero2 == 0){
                tvResultado.setText("0");
                Toast.makeText(this, "Operacion no Valida", Toast.LENGTH_SHORT).show();
            }else{
                float result = numero1 / numero2;
                tvResultado.setText(result + "");
            }
        }else if (operacion.equals("*")){
            float result = numero1 * numero2;
            tvResultado.setText(result + "");
        }else if (operacion.equals("-")){
            float result = numero1 - numero2;
            tvResultado.setText(result + "");
        }else if (operacion.equals("+")){
            float result = numero1 + numero2;
            tvResultado.setText(result + "");
        }
        numero1 = 0;
        numero2 = 0;
        operacion = "";
    }
    public void escribirRestaurar(View view) {
        tvResultado.setText("0");
        numero1 = 0f;
        numero1 = 0f;
        operacion = "";
    }
}

