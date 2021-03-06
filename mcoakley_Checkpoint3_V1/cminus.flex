/*
  Created By: Fei Song
  File Name: tiny.flex
  To Build: jflex tiny.flex

  and then after the parser is created
    javac Lexer.java
*/
   
/* --------------------------Usercode Section------------------------ */
   
import java_cup.runtime.*;
      
%%
   
/* -----------------Options and Declarations Section----------------- */

/* A state for comments to be eaten in */
%state COMMENT 

/* 
   The name of the class JFlex will create will be Lexer.
   Will write the code to the file Lexer.java. 
*/
%class Lexer

%eofval{
  return null;
%eofval};

/*
  The current line number can be accessed with the variable yyline
  and the current column number with the variable yycolumn.
*/
%line
%column
    
/* 
   Will switch to a CUP compatibility mode to interface with a CUP
   generated parser.
*/
%cup
   
/*
  Declarations
   
  Code between %{ and %}, both of which must be at the beginning of a
  line, will be copied letter to letter into the lexer class source.
  Here you declare member variables and functions that are used inside
  scanner actions.  
*/
%{   
    /* To create a new java_cup.runtime.Symbol with information about
       the current token, the token will have no value in this
       case. */
    private Symbol symbol(int type) {
        return new Symbol(type, yyline, yycolumn);
    }
    
    /* Also creates a new java_cup.runtime.Symbol with information
       about the current token, but this object has a value. */
    private Symbol symbol(int type, Object value) {
        return new Symbol(type, yyline, yycolumn, value);
    }
%}
   

/*
  Macro Declarations
  
  These declarations are regular expressions that will be used latter
  in the Lexical Rules Section.  
*/
   
/* A line terminator is a \r (carriage return), \n (line feed), or
   \r\n. */
LineTerminator = \r|\n|\r\n
   
/* White space is a line terminator, space, tab, or form feed. */
WhiteSpace = \s

Comments = \/\*+([^\*])*\*+\/
   
/* A literal integer is is a number beginning with a number between
   one and nine followed by zero or more numbers between zero and nine
   or just a zero.  */
number = [0-9]+
   
/* A identifier integer is a word beginning a letter between A and
   Z, a and z, or an underscore followed by zero or more letters
   between A and Z, a and z, zero and nine, or an underscore. */
letter = [a-zA-Z]
identifier = [_a-zA-Z][_a-zA-Z0-9]*
   
%%
/* ------------------------Lexical Rules Section---------------------- */
   
/*
   This section contains regular expressions and actions, i.e. Java
   code, that will be executed when the scanner matches the associated
   regular expression. */


/* C- KEYWORDS */

<YYINITIAL>"if"               { return symbol(sym.IF); }
<YYINITIAL>"else"             { return symbol(sym.ELSE); }
<YYINITIAL>"while"            { return symbol(sym.WHILE); }

<YYINITIAL>"int"              { return symbol(sym.INT); }
<YYINITIAL>"void"             { return symbol(sym.VOID); }

<YYINITIAL>"return"           { return symbol(sym.RETURN); }

/* SPECIAL SYMBOLS */

<YYINITIAL>"+"                { return symbol(sym.PLUS); }
<YYINITIAL>"-"                { return symbol(sym.MINUS); }
<YYINITIAL>"*"                { return symbol(sym.TIMES); }
<YYINITIAL>"/"                { return symbol(sym.OVER); }

<YYINITIAL>"<"                { return symbol(sym.LT); }
<YYINITIAL>">"                { return symbol(sym.GT); }
<YYINITIAL>"<="               { return symbol(sym.LTEQ); }
<YYINITIAL>">="               { return symbol(sym.GTEQ); }
<YYINITIAL>"!="               { return symbol(sym.NOTEQ); }
<YYINITIAL>"=="               { return symbol(sym.EQUALS); }

<YYINITIAL>"="                { return symbol(sym.ASSIGN); }
<YYINITIAL>";"                { return symbol(sym.SEMI); }
<YYINITIAL>","                { return symbol(sym.COMMA); }

<YYINITIAL>"("                { return symbol(sym.LCURVED); }
<YYINITIAL>")"                { return symbol(sym.RCURVED); }
<YYINITIAL>"["                { return symbol(sym.LSQUARE); }
<YYINITIAL>"]"                { return symbol(sym.RSQUARE); }
<YYINITIAL>"{"                { return symbol(sym.LSQUIG); }
<YYINITIAL>"}"                { return symbol(sym.RSQUIG); }


/* OTHER TOKENS */

<YYINITIAL>{number}           { return symbol(sym.NUM, yytext()); }
<YYINITIAL>{identifier}       { return symbol(sym.ID, yytext()); }


/* COMMENTS, WHITESPACE, AND UNKNOWN PATTERNS */
<YYINITIAL>{WhiteSpace}+      { /* skip whitespace */ }   

<YYINITIAL>"/*"               { yybegin(COMMENT);}
<COMMENT>.|\s                    { /* eat comments */ }
<COMMENT>"*/"                 { yybegin(YYINITIAL); }

/* TODO: DISPLAY ERROR HERE */
<YYINITIAL>.                  { System.err.println("ERROR: UNKNOWN TOKEN AT LINE " + yyline + " COLUMN " + yycolumn); }
