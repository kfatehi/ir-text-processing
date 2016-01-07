BUILD=_build
PKG=ir.assignments.one.a
SRC=src/ir/assignments/one/a
TEST_SRC=test/src
TEST_FIXTURES=test/fixtures
TEST_LIB=test/lib
JUNIT=$(TEST_LIB)/hamcrest-core-1.3.jar:$(TEST_LIB)/junit-4.12.jar
JAVAC_COPY_FOR_TEST=$(TEST_LIB)/junit-4.12.jar:$(TEST_FIXTURES)
SHORTSTACK_BIN=test/support/shortstack/bin/shortstack

# Path to libs, classes and jars used during the tests
CLASSPATH=.:$(JUNIT):$(BUILD)

# Source files to compile, in order
JAVAC_SRCLIST_FOR_TEST=    \
						$(SRC)/Frequency.java \
						$(SRC)/Utilities.java \
						$(TEST_SRC)/UtilitiesTest.java \

# Tests to execute, in order
JAVA_PKGLIST_FOR_TEST=    \
						$(PKG).UtilitiesTest \

default: test

tdd:
	watchy -w src,test -- bash -c "clear; make test"

autotest: tdd

compile-for-test: clean
	@mkdir -p $(BUILD)
	@javac -Xlint -g -d $(BUILD) -cp $(JAVAC_COPY_FOR_TEST) $(JAVAC_SRCLIST_FOR_TEST)

test: compile-for-test
	@java -cp fixtures -classpath $(CLASSPATH) org.junit.runner.JUnitCore $(JAVA_PKGLIST_FOR_TEST) | $(SHORTSTACK_BIN)

clean:
	@rm -rf $(BUILD)
