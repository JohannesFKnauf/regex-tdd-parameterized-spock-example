@Grab('org.spockframework:spock-core:1.3-groovy-2.5')
@GrabExclude('org.codehaus.groovy:groovy-nio')
@GrabExclude('org.codehaus.groovy:groovy-macro')
@GrabExclude('org.codehaus.groovy:groovy-sql')
@GrabExclude('org.codehaus.groovy:groovy-xml')

import spock.lang.Unroll

class RegexSpec extends spock.lang.Specification {
  String REGEX = /[-+]?\d+(\.\d+)?([eE][-+]?\d+)?/
	
  @Unroll
    def 'matching example #example for case "#description" should yield #isMatchExpected'(String description, String example, Boolean isMatchExpected) {
    expect:
    isMatchExpected == (example ==~ REGEX)

    where:
    description                                  | example        || isMatchExpected
    "empty string"                               | ""             || false
    "single non-digit"                           | "a"            || false
    "single digit"                               | "1"            || true
    "integer"                                    | "123"          || true
    "integer, negative sign"                     | "-123"         || true
    "integer, positive sign"                     | "+123"         || true
    "float"                                      | "123.12"       || true
    "float with exponent extension but no value" | "123.12e"      || false
    "float with exponent"                        | "123.12e12"    || true
    "float with uppercase exponent"              | "123.12E12"    || true
    "float with non-integer exponent"            | "123.12e12.12" || false
    "float with exponent, positive sign"         | "123.12e+12"   || true
    "float with exponent, negative sign"         | "123.12e-12"   || true
  }
}
