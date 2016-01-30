src=src/*.java
pkg=catch.jar
main=PlayCatch


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
