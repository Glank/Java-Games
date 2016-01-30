src=src/*.java

make_game_pkg=makepong.jar
make_game_main=MakeGame

join_game_pkg=joinpong.jar
join_game_main=JoinGame


all:
	mkdir -p bin
	javac ${src} -d bin/

package: all
	../package.sh ${make_game_pkg} ${make_game_main}
	../package.sh ${join_game_pkg} ${join_game_main}

doc:
	javadoc ${src} -d doc/

make_game: all
	java -cp bin/ ${make_game_main}

join_game: all
	java -cp bin/ ${join_game_main}

clean:
	rm -rf bin
	rm -rf doc
	rm -f *.jar
