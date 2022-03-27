
test:
	javac -deprecation Test.java
	java -ea Test
	
clean: 
	rm -rf *.class
