reset

echo "-- Compiling --"
echo ">javac /bugger/*.java"

javac -classpath ".:mysql-connector-java-8.0.16.jar" bugger/*.java bugger/*/*.java

#echo "-- Starting the server program -- "
echo ">java server/main:8080 \n"
java -ea -classpath ".:mysql-connector-java-8.0.16.jar"  bugger/Bugger 3000
