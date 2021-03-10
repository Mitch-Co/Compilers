JAVA=java
JAVAC=javac

CUP=$(JAVA) $(CLASSPATH) java_cup.Main

# Jflex needs to be installed to PATH
JFLEX=jflex

# CUP needs to be installed to /usr/share/java/
# cup.jar needs to be present in the directory
# java-cup-11b.jar
CLASSPATH=-cp /usr/share/java/cup.jar:.

all: Main.class

Main.class: absyn/*.java parser.java sym.java Lexer.java ShowTreeVisitor.java Scanner.java Main.java

%.class: %.java
	$(JAVAC) -Xdiags:verbose $(CLASSPATH) $^

Lexer.java: cminus.flex
	$(JFLEX) cminus.flex

parser.java: cminus.cup
	#$(CUP) -dump -expect 3 tiny.cup
	$(CUP) -expect 3 cminus.cup

clean:
	rm -f parser.java Lexer.java sym.java *.class absyn/*.class *~
