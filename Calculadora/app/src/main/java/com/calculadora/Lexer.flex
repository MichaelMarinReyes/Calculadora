package com.calculadora;

import java_cup.runtime.*;
import com.calculadora.ErrorLexico;
import com.calculadora.Token;
import java.util.ArrayList;

%%
%public
%class Lexer
%unicode
%line
%column
%ignorecase
%cup
%{
    public static ArrayList<ErrorLexico> errores = new ArrayList<>();
    public static ArrayList<Token> tokens = new ArrayList<>();

     public ArrayList<ErrorLexico> getErrores() {
            return errores;
     }
%}

%%
[ \t\n\r\f]       { /* Ignorar espacios en blanco */ }

[0-9]+\.[0-9]+    { return new Symbol(ParserSym.NUMERO, Double.parseDouble(yytext())); }
[0-9]+            { return new Symbol(ParserSym.NUMERO, Integer.parseInt(yytext())); }
"+"               { return new Symbol(ParserSym.SUMA, yyline+1, yycolumn+1, yytext()); }
"-"               { return new Symbol(ParserSym.RESTA, yyline+1, yycolumn+1, yytext()); }
"*"               { return new Symbol(ParserSym.MULTIPLICACION, yyline+1, yycolumn+1, yytext()); }
"/"               { return new Symbol(ParserSym.DIVISION, yyline+1, yycolumn+1, yytext()); }
"("               { return new Symbol(ParserSym.PARENTESIS_ABRE, yyline+1, yycolumn+1, yytext()); }
")"               { return new Symbol(ParserSym.PARENTESIS_CIERRA, yyline+1, yycolumn+1, yytext()); }
<<EOF>>           { return new Symbol(ParserSym.EOF); }
[^]               { errores.add(new ErrorLexico(yytext(), yyline + 1, yycolumn + 1, "Léxico", "Caracter desconocido: " + yytext()));
          System.err.println("Error léxico: " + yytext() + " linea: " + String.valueOf(yyline + 1) + " columna: " + String.valueOf(yycolumn + 1));}