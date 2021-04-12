
C MINUS COMPILER
-----------------------------------------
Mitchell Coakley
CIS*4650
mcoakley@uoguelph.ca
-----------------------------------------
TO RUN:
This directory contains all the required files for building a scanner/parser/compiler for the C- language.

To run the tool, first make the project:
> make

Then run the project with:
> java -classpath /usr/share/java/cup.jar:. CM filename.cm <flag>

The two possible flags are 
"-a" for abstract syntax tree generation
"-c" for compiled .tm output

-----------------------------------------
LIMITATIONS:
The compiler cannot understand function calls and only reads the "main" function of a C Minus program.
The compiler does not do real time checking of array index. Any array value, even negative, will be accepted.
-----------------------------------------

NOTE: This project was based off the SimpleParser provided for CIS*4650