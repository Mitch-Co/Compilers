This directory contains all the required files for building a scanner and parser for the C- language.

To build the scanner/parser, type "make" in the current directory.
If CUP/Jflex are in different install directories from what is in the makefile, change them in the makefile before running.

To run the parser, run the command:

java -classpath /usr/share/java/cup.jar:. CM filename.cm

where "/usr/share/java/cup.jar" is your own java-cup-11b.jar directory
and filename.cm is the program you want to generate an abstract syntax tree for.

--- TESTING ---

In the test folder, programs [12345].cm exist. They can be put through the scanner/parser
to print an abstract syntax tree for each program. 1.cm should run without error, however 
[2345].cm have errors in them. The errors in each program are discribed at the top of the program
within a comment. Dispite these errors, an abstract syntax tree should be generated for all programs, 
along with warnings for the relevant errors.



NOTE: This project was based off the SimpleParser provided for CIS*4650