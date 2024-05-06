JAVAC=javac
JAVA=java

SERVER_SRC=server/*.java
CLIENT_SRC=client/*.java
COMMON_SRC=common/*.java

SERVER_MAIN=server.ServerApp
CLIENT_MAIN=client.ClientApp

# Default action
all: compile

# Compile all Java files
compile:
	$(JAVAC) $(COMMON_SRC)
	$(JAVAC) $(SERVER_SRC)
	$(JAVAC) $(CLIENT_SRC)

# Run the server
run_server: compile
	$(JAVA) -cp . $(SERVER_MAIN)

# Run the client
run_client: compile
	$(JAVA) -cp . $(CLIENT_MAIN)

# Clean the compiled files
clean:
	rm -f server/*.class client/*.class common/*.class

.PHONY: all compile run_server run_client clean
