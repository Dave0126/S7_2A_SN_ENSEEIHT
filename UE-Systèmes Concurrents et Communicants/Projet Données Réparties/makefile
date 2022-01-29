# Output directory
OUT_DIR = ./build

# Tests directory
TEST_DIR = linda/test

# Source files
findSourceFiles = $(shell find . -name "*.java")
SOURCE_FILES = $(findSourceFiles) 

# Find all "*.class" files
findTargetFile = $(shell find . -name "*.class")
TARGET_FILES = $(findTargetFile)

# Java compiler
JAVAC = javac


# Show help message
help:
	@echo ">>> make build : Complier all the file '.java' defined in SOURCE_FILES."
	@echo ">>> make server : Run /linda/server/LindaServerImpl.java."
	@echo ">>> make tests : Run all the tests in './linda/test' ."
	@echo ">>> make clean : Clear classes generated."

# Compiles all "*.java"
build:
	$(JAVAC) $(SOURCE_FILES)

# Executes all tests
tests:
	$(foreach file, $(wildcard $(TEST_DIR)/*.java), java $(file);)

# Starts Linda server
server:
	java linda/server/LindaServerImpl.java

# Clear classes generated
clean:
	rm -rf $(TARGET_FILES)