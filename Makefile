###############################################################################
#
# Makefile for Java project
#
# Students:
# David Benchimol, ID 332510908
# Omer Rom, ID 301544383 
#
###############################################################################

JAVAC=javac
JAVACFLAGS=

SRCS=src/com/davidex6/*.java
EXEC=Assembler

TAR=tar
TARFLAGS=cvf
TARNAME=proj6.tar
TARSRCS=$(SRCS) $(EXEC) README Makefile

all: compile

compile:
	$(JAVAC) $(JAVACFLAGS) $(SRCS)
	chmod +x $(EXEC)

tar:
	$(TAR) $(TARFLAGS) $(TARNAME) $(TARSRCS)

clean:
	rm -f *.class *~

