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

            // Write minOf function: 2 values
        context("minOf function") {
            val name1 = "Alice"
            val name2 = "Bob"

            it("returns min") {
                minOf(name1.length, name2.length) shouldBe 3
            }
        }

            // Write *minOf function: 3 values

        context("*minOf function: 3 values_1") {
            val x:Int = -3
            val y:Int = 5
            val z:Int = 10
            it("returns min") {

                val min: Int = if(x <= y && x <= z) {
                    x
                }
                else if(z <= x && z <= y) {
                    z
                }
                else {
                    y
                }

                min shouldBe -3
            }
        }

        context("*minOf function: 3 values_2") {
            val a = 1
            val b = 4
            val c = -1

            it("returns min") {
                minOf(a, b, c, compareBy { it }) shouldBe c
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

            // Write mapOf function for fruits
        context("collections: mapOf function") {
            val fruits = listOf("Banana", "Apple", "Avocado", "Watermelon", "Avocado")

            it("returns mapOf<String, Int>") {
               fruits.groupingBy{it}.eachCount()["Avocado"] shouldBe 2
            }
        }

        // QA : Engineer check boundary values
        context("boundary values") {

            val features = arrayListOf("Homepage", "Registration flow", "Login", "Forgot password")

            it ("checks releaseTesting Type")
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

    }
})

// MinOf vararg function
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