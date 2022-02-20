/* 
 * Copyright 2022, Emanuel Rabina (http://www.ultraq.net.nz/)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package nz.net.ultraq.groovy.ioextensions

import spock.lang.Specification

/**
 * Tests for the {@link ResourceExtensions} methods.
 * 
 * @author Emanuel Rabina
 */
class ResourceExtensionsTests extends Specification {

	def "Is a shortcut to ClassLoader.getResourceAsStream"() {
		given:
			def resourcePath = 'nz/net/ultraq/groovy/ioextensions/ResourceExtensionsTestsFile.txt'
		when:
			def result = getResourceAsStream(resourcePath)
		then:
			assert result.text == this.class.classLoader.getResourceAsStream(resourcePath).text
	}

	def "Throws an exception if the path leads to nothing"() {
		given:
			def resourcePath = 'path/to/nothing'
		when:
			getResourceAsStream(resourcePath)
		then:
			def ex = thrown(IllegalArgumentException)
			assert ex.message == "Resource not found: ${resourcePath}"
	}
}
