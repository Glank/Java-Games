src=src/*.java
src+=src/gameutil/*.java

comet_pkg=comet.jar
comet_main=Play

soccer_pkg=soccer.jar
soccer_main=GravitySoccer


all:
	mkdir -p bin
	javac ${src} -d bin/

package: all
	../package.sh ${comet_pkg} ${comet_main}
	../package.sh ${soccer_pkg} ${soccer_main}

doc:
	javadoc ${src} -d doc/

comet: all
	java -cp bin/ ${comet_main}

soccer: all
	java -cp bin/ ${soccer_main}

clean:
	rm -rf bin
	rm -rf doc
	rm -f ${comet_pkg}
	rm -f ${soccer_pkg}
