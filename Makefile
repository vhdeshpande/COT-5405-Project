JCFLAGS = -g
JC = javac

.SUFFIXES: .java .class

.java.class:
		$(JC) $(JCFLAGS) *.java

CLASSES = *.java

default: clean classes jar

classes: $(CLASSES:.java=.class)

jar: $(classes)
	jar cfe Stocks.jar BuySellStock *.class

clean:
		$(RM) *.class
		$(RM) *.jar