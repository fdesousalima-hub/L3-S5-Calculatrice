.PHONY: all Calculatrice

all: Calculatrice

Calculatrice:
	cd CalculatriceProjet && mvn clean compile assembly:single
	cd CalculatriceProjet && mvn test
	cp CalculatriceProjet/target/CalculatriceProjet-1.0-jar-with-dependencies.jar Calculatrice.jar

Doc:
	cd CalculatriceProjet && mvn javadoc:javadoc
	cp -r CalculatriceProjet/target/site/apidocs/ Doc

clean:
	rm -rf Doc
	cd CalculatriceProjet && mvn clean
	rm -f Calculatrice.jar
