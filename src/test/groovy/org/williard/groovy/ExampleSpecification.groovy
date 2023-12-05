package org.williard.groovy

import spock.lang.Specification
import spock.lang.Unroll

class ExampleSpecification extends Specification{

    def "should be a simple assertion"() {
        expect:
        1 == 1
    }

    def "should demonstrate given-when-then" () {
        given:
        def list = new ArrayList<String>();
        list.add("One")
        list.add("Two")
        list.add("Three")

        when:
        int size = list.size()

        then:
        size == 3
    }

    def "should demonstrate when-then only" () {
        when:
        int size = new ArrayList<String>(Arrays.asList("one","two","three")).size()

        then:
        size == 3
    }

    def "should expect Exceptions"() {
        given:
        String text = null;

        when:
        text.size();

        then:
        thrown(NullPointerException)
    }


    def "using Exception as a variable should expect Exceptions"() {
        given:
        String text = "abcdef";

        when:
        text.substring(10)

        then:
        def exception = thrown(StringIndexOutOfBoundsException)
        exception.message.contains("-4")
    }

    //the #index will include the variable name in the test results output.
    @Unroll
    def "should expect an Exception to be thrown for invalid input: #index"() {
        //index here is variable placeholder defined in the where clause below
        when:
        String subStringText = "abcdef".substring(index)

        then:
        def exception = thrown(StringIndexOutOfBoundsException)

        //groovy will run 6 individual tests based on the 6 variables in the list below
        // this is known as a "data pipe"
        where:
        index << [7,8,9,10,11,12]

    }

    def "should be able to substring the name with a length of #index and return #name"() {
        expect:
        name.substring(0,index).equalsIgnoreCase(name)

        //This is known as a data table
        where:
        index | name
        7     | "Matthew"
        4     | "Fred"
        5     | "Willy"
        4     | "Kale"
        5     | "Lucas"
        5     | "Adams"
    }

}
