src=src/*.java
src+=src/gameutil/*.java
src+=src/ioutil/*.java
pkg=blackjack.jar
main=Blackjack


all:
	mkdir -p bin
	javac ${src} -d bin/

package: all
	../package.sh ${pkg} ${main}

doc:
	javadoc ${src} -d doc/

play: all
	java -cp bin/ ${main}

clean:
	rm -rf bin
	rm -rf doc
	rm -f ${pkg}
