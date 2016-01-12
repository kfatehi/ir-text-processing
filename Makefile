BUILD=_build
TEST_FIXTURES=test/fixtures
TEST_LIB=test/lib
JUNIT=$(TEST_LIB)/hamcrest-core-1.3.jar:$(TEST_LIB)/junit-4.12.jar
JAVAC_COPY_FOR_TEST=$(TEST_LIB)/junit-4.12.jar:$(TEST_FIXTURES)
SHORTSTACK=node test/support/shortstack.js

# Path to libs, classes and jars used during the tests
CLASSPATH=.:$(JUNIT):$(BUILD)

# Source files to compile, in order
JAVAC_SRCLIST_FOR_TEST= \
						src/ir/assignments/two/a/Frequency.java \
						src/ir/assignments/two/a/Utilities.java \
						src/ir/assignments/two/b/WordFrequencyCounter.java \
						src/ir/assignments/two/c/TwoGramFrequencyCounter.java \
						src/ir/assignments/two/d/PalindromeFrequencyCounter.java \
						test/support/TestHelper.java \
						test/support/Fixtures.java \
						test/src/UtilitiesTest.java \
						test/src/WordFrequencyCounterTest.java \
						test/src/TwoGramFrequencyCounterTest.java \
						test/src/PalindromeFrequencyCounterTest.java \

# Tests to execute, in order
JAVA_PKGLIST_FOR_TEST= \
						ir.test.UtilitiesTest \
						ir.test.WordFrequencyCounterTest \
						ir.test.TwoGramFrequencyCounterTest \
						ir.test.PalindromeFrequencyCounterTest \

default: test

tdd:
	watchy -w src,test -- bash -c "clear; make test"

autotest: tdd

compile: clean
	@mkdir -p $(BUILD)
	@javac -g -d $(BUILD) -cp $(JAVAC_COPY_FOR_TEST) $(JAVAC_SRCLIST_FOR_TEST)

test: compile
	@java -cp fixtures -classpath $(CLASSPATH) org.junit.runner.JUnitCore $(JAVA_PKGLIST_FOR_TEST) | $(SHORTSTACK)

clean:
	@rm -rf $(BUILD)

docs:
	@rm -rf _docs
	@mkdir -p _docs
	javadoc -d _docs -classpath $(CLASSPATH) $(JAVAC_SRCLIST_FOR_TEST)

autodoc:
	watchy -w src,test -- bash -c "clear; make docs"

analyzer-run-collector:
	@PREFIX="java -classpath _build ir.assignments.two.d.PalindromeFrequencyCounter" \
				 PARALLEL=2 \
				 START_SIZE=1 \
				 test/support/analyzer/collect.sh

analyzer-collect:
	rm -f test/support/analyzer/data.js
	make analyzer-run-collector > test/support/analyzer/data.js

.PHONY: docs
