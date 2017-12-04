id=0
n=0
initialDelay=0
bearer=false
JFLAGS = -g
JC = javac

.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
        ClienteProceso.java \
        Servidor.java \

default: classes

classes: $(CLASSES:.java=.class)

run-rmiregistry:
		rmiregistry 54321&

run-server:
	java -Djava.security.policy=ALL.permisos Servidor&

run-process:
	java -Djava.security.policy=ALL.permisos ClienteProceso $(id) $(n) $(initialDelay) $(bearer)

clean:
	rm *.class
