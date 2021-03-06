import io.kotlintest.matchers.collections.shouldContain
import io.kotlintest.matchers.haveSubstring
import io.kotlintest.matchers.numerics.shouldBeGreaterThan
import io.kotlintest.matchers.numerics.shouldBeGreaterThanOrEqual
import io.kotlintest.matchers.numerics.shouldBeLessThan
import io.kotlintest.matchers.numerics.shouldBeLessThanOrEqual
import io.kotlintest.matchers.types.shouldBeTypeOf
import io.kotlintest.shouldBe
import io.kotlintest.specs.DescribeSpec


class BasicsDescribeSpec : DescribeSpec({
    describe("Checks on Kotlin basics implementations") {
        context("functions") {
            it("sum1 and sum2 works the same") {
                val a = 1
                val b = 2
                sum1(a, b) shouldBe 3
                sum1(a, b) shouldBeLessThan 4
                sum1(0, 3) shouldBe 3
                sum1(-1, 1) shouldBe 0
                sum1(a, b) shouldBeGreaterThan 1
                sum2(-3, 10) shouldBeLessThanOrEqual 7
                sum2 (a, b) shouldBeGreaterThanOrEqual 2

                // Add greater less checks
            }
        }

        context("variables") {
            val readOnly = 11
            var reassignable = 3
//            it("val can not be reassigned") {
//                readOnly = 12
//            }

            it("can be reassigned") {
                reassignable = 6
                reassignable shouldBe 6
            }
        }

        context("strings") {
            val toge = "toge"
            val ther = "ther"
            val together = "together"

            it("concatenation works") {
                toge + ther shouldBe together
            }

            it("string interpolation works") {
                "$toge$ther" shouldBe together
            }

            it("is not empty") {
                together.isNotBlank() shouldBe true
            }
        }

        context("conditional expressions") {
            val max = 100
            val min = 0

            it("returns max") {
                maxOf(min, max) shouldBe max
            }
        }

        context("when expression") {
            describe(1) shouldBe "One"
            describe("Hello") shouldBe "Greeting"
            describe(2L) shouldBe "Long"
            describe(true) shouldBe "Not a string"
            describe("other") shouldBe "Unknown"

            // Add other checks
        }

        context("collections") {
            val fruits = arrayListOf("Apple", "Orange", "Grapes", "Cherry")

            it("") {
                fruits.count() shouldBe 4
                fruits shouldContain "Apple"
                count(fruits) shouldBe 4
            }
        }


//Home works

// Write minOf 2 values
        context("minOf function") {
            val name1 = "Alice"
            val name2 = "Bob"

            it("returns min") {
                minOf(name1.length, name2.length) shouldBe 3
            }
        }

// Write minOf 3 values

        context("minOf function: 3 values_1") {
            val x:Int = -3
            val y:Int = 5
            val z:Int = 10
            val min: Int = if(x <= y && x <= z) {
                x
            }
            else if(z <= x && z <= y) {
                z
            }
            else {
                y
            }

            it("returns min") {
                min shouldBe -3
            }
        }

        context("minOf function: 3 values_2") {
            val a = 1
            val b = 4
            val c = -1

            it("returns min") {
                minOf(a, b, c, compareBy { it }) shouldBe c
            }
        }

        // mapOf function for fruits
        context("collections: mapOf function") {
            val fruits = listOf("Banana", "Apple", "Avocado", "Watermelon", "Avocado")

            it("returns mapOf<String, Int>") {
                fruits.groupingBy{it}.eachCount()["Avocado"] shouldBe 2
            }
        }

        // QA : Engineer check boundary values for releaseTesting
        context("boundary values") {

            val features = arrayListOf("Homepage", "Registration flow", "Login", "Forgot password")

            it("checks daysLeft is not null")
            QA(null, "Altynai", "QA").releaseTesting(features) shouldBe false

            it("checks releaseTesting Type")
            QA(3, "Altynai", "QA").releaseTesting(features).shouldBeTypeOf<Boolean>()

            it("checks daysLeft = features.size / 2")
            QA(2, "Altynai", "QA").releaseTesting(features) shouldBe true

            it("checks daysLeft > features.size / 2")
            QA(10, "Altynai", "QA").releaseTesting(features) shouldBe true

            it("checks daysLeft = 0")
            QA(0, "Altynai", "QA").releaseTesting(features) shouldBe false

            it("checks daysLeft < 0")
            QA(-10, "Altynai", "QA").releaseTesting(features) shouldBe false

            it("checks daysLeft < features.size / 2")
            QA(1, "Altynai", "QA").releaseTesting(features) shouldBe false
        }

        //Strings check
        context("unique letters") {

            it("checks if upper case = low case")
            uniqueChars("Google") shouldBe false
            uniqueChars("TRumP") shouldBe true

            it("checks whitespace is not considered")
            uniqueChars("  King") shouldBe true
            uniqueChars("W o o d o o ") shouldBe false
        }

        context("two strings"){
            it("checks if upper case = low casee")
            twoStrings("caT", "tac") shouldBe true
            twoStrings("black", "WHITE") shouldBe false

            it("checks if whitespace is considered")
            twoStrings("S W I F T", "TFIWS") shouldBe false
        }

        context("unique chars1") {
            it("checks input data are unique letters")
            uniqueChars1("Kotlin") shouldBe true

            it("checks input are not unique letters")
            uniqueChars1("Java") shouldBe false

            it("checks input data are not unique letters and have different letter cases")
            uniqueChars1("JavA") shouldBe false

            it("checks output data type")
            uniqueChars1("Type").shouldBeTypeOf<Boolean>()
        }

        context("reversed strings 2") {
            it("checks input data when one string is the other one reversed")
            twoStrings1("kotlin", "niltok") shouldBe true

            it("checks input data when one string is not the other one reversed")
            twoStrings1("kotlin", "java") shouldBe false

            it("checks output data type")
            twoStrings1("Type", "Type").shouldBeTypeOf<Boolean>()

        }

    }
})

open class Engineer(name: String, Department: String)

class QA(val daysLeft: Int?, name: String, department: String) : Engineer(name, department) {

    fun releaseTesting(features: ArrayList<String>) : Boolean {

        if(daysLeft != null && features.size / 2 <= daysLeft) {
            println("Amazing work, QA team! Release is on time!")
            return true
        }
        if(daysLeft != null && features.size / 2 > daysLeft) {
            println("Oops! Release is delayed :(")
            return false
        }
        println("Error! Data input is required.")
        return false
    }
    /*
check daysLeft is not null
check releaseTesting type // <Boolean>
check daysLeft => features.size/2 //true
check daysLeft = 0 // false
check daysLeft < features.size // false
check daysLeft < 0 // false
check features.size <= 0 // true
 */
}

// Write minOf vararg function

fun minNumber(vararg numbers: Int) : Int? {
    var smallest = 0

    return if (numbers.isEmpty()) {
        println("Error! Data input is required.")
        return null
    }  else {
        smallest = numbers[0]
        for (i in numbers) if (smallest > i) {
            smallest = i
        }
        smallest
    }
}

// collection.map method

class User (val name: String, val role: String)

fun main() {

    val users = listOf(User("Angela", "Admin"), User("Monika", "Product Owner"))
    val map = users.map {it.name to it.role}.toMap() //{Angela=Admin, Monika=Product Owner}
    println(map)
}

// true only if unique letters string
fun uniqueChars(someString: String): Boolean {

    val charArray = someString
            .toLowerCase()
            .replace(" ", "")
            .toCharArray()
    charArray.sort(0, charArray.size)

    for (char in 0 until charArray.size - 1) {
        if (charArray[char] != charArray[char + 1] && charArray.isNotEmpty()) continue
        return false
    }
    return true
}

//unique letters 2

fun uniqueChars1(someString: String): Boolean {

    val charArray = someString.toLowerCase().toCharArray()

    for (i in 0 until charArray.size - 1) {
        for (j in i + 1 until charArray.size)
            if (charArray[i] == charArray[j])
                return false
    }
    return true
}

//reversed strings 1

fun twoStrings(string1: String, string2: String) : Boolean {
    return string1.toLowerCase().reversed() ==
            string2.toLowerCase()

}

//reversed strings 2

fun twoStrings1(string1: String, string2: String) : Boolean {
    return string1.length == string2.length && string1.reversed() == string2

}

//reversed strings 3

fun twoStrings2(string1: String, string2: String) : Boolean {
    return string1.length == string2.length && (string1 + string2).contains(string1)
}

//reversed string 4

fun twoStrings3(string1: String, string2: String) : Boolean {
    return string1.length == string2.length &&
            (string1 + string2).indexOf(string1, 0, ignoreCase = true) != -1
}

