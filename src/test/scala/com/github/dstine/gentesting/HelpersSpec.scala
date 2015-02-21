package com.github.dstine.gentesting

import org.junit.runner.RunWith
import org.scalatest.prop.PropertyChecks
import org.scalatest.{Matchers, FlatSpec}
import org.scalatest.junit.JUnitRunner

/**
 * References:
 * http://www.scalatest.org/user_guide/using_junit_runner
 * http://www.scalatest.org/user_guide/property_based_testing
 */
@RunWith(classOf[JUnitRunner])
class HelpersSpec extends FlatSpec with Matchers with PropertyChecks {

  // Table-driven input
  "addition" should "properly sum specific integers" in {
    val positives = Table (
      ("a", "b"),
      (5, 6),
      (45, 71),
      (-85, -6),
      (-15, 333)
    )
    forAll (positives) { (a: Int, b: Int) =>
      Helpers.add(a, b) should be(a + b)
    }
  }

  // Generated input
  "addition" should "be commutative" in {
    forAll ("a", "b") { (a: Int, b: Int) =>
      Helpers.add(a, b) should be (Helpers.add(b, a))
    }
  }

  // Generated input
  "addition" should "be associative" in {
    forAll ("a", "b", "c") { (a: Int, b: Int, c: Int) =>
      Helpers.add(Helpers.add(a, b), c) should be (Helpers.add(a, Helpers.add(b, c)))
    }
  }
}
