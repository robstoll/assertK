# List of APIs
Atrium provides different APIs where the API differ in its style and the language in which it is written.
You have the choice which one(s) you want to use. 
Hence it is up to you if you want to mix and match different styles or enforce just one style.

Atrium provides so called bundle-modules which merely bundle dependencies (they do not provide additional functionality).
These modules bundle:
- an API module
- a translation module (the language used in reporting)
- an implementation of the domain of Atrium
- an implementation of the core of Atrium


Following a list of the available bundle-modules. 
The links point to the KDoc of their included API where you find an overview of all available assertion functions of the API.

- [atrium-cc-de_CH-robstoll](https://robstoll.github.io/atrium/latest#/doc/ch.tutteli.atrium.api.cc.de_-c-h/index.html)
- [atrium-cc-en_GB-robstoll](https://robstoll.github.io/atrium/latest#/doc/ch.tutteli.atrium.api.cc.en_-g-b/index.html)
- [atrium-cc-infix-en_GB-robstoll](https://robstoll.github.io/atrium/latest#/doc/ch.tutteli.atrium.api.cc.infix.en_-g-b/index.html)

----

Following an excerpt of a build.gradle file which uses two APIs (see 
[README#Installation](https://github.com/robstoll/atrium/tree/naster/README.md#installation)
for the rest):
```
dependencies {
    testCompile "ch.tutteli:atrium-cc-en_GB-robstoll:$atrium_version"
    testCompile "ch.tutteli:atrium-api-cc-infix-en_GB-jvm:$atrium_version"
}
```

The first dependency points to a bundle-module, the second one just adds the infix-API in addition.

:warning: if you want to use the same API in different languages, 
then you have to make sure that you exclude all translation modules but one (I suggest you keep the one which is your primary language).
If you forget to do it, then the compiler will complain that you have the same enums multiple times on your classpath.

# Different API styles

Atrium provides different APIs where the API differ in its style and the language in which it is written.
This site focuses on the different styles of APIs and compares their en_GB versions. 
We do not show every single difference but merely where the APIs differ in naming.
For instance, the assertion function `Assert<Any>.toBe`:

*atrium-api-cc-en_GB*
```kotlin
assert(x).toBe(2)
``` 
*atrium-api-cc-infix-en_GB*
```kotlin
assert(x) toBe 2
``` 

is too similar, we will not list it here (ok, we did now but I guess you get the point).

## Table of Content
- [Empty CharSequence / Collection](#empty-charsequence--collection)
- [`and` property](#and-property)
- [CharSequence contains](#charsequence-contains)
- [Iterable contains](#iterable-contains)
    - [in any order](#iterable-contains-in-any-order)
    - [in order](#iterable-contains-in-order)
- [Iterable contains not](#iterable-contains-not)
- [Iterable predicate-like assertions](#iterable-predicate-like-assertions)
- [List get](#list-get)
- [Map getExisting](#map-getexisting)
- [Map contains](#map-contains)

## Empty CharSequence / Collection

*atrium-api-cc-en_GB*
```kotlin
assert(x).isEmpty()
assert(x).isNotEmpty()
```

*atrium-api-cc-infix-en_GB*

```kotlin
assert(x) toBe Empty
assert(x) notToBe Empty
```

## `and` property

*atrium-api-cc-en_GB*
```kotlin
assert(x).isGreaterThan(1).and.isLessThan(10)
assert(x) { /*...*/ } and { /*...*/ }
```

*atrium-api-cc-infix-en_GB*
```kotlin
// does only support the group syntax
assert(x) { /*...*/ } and { /*...*/ }
```

## CharSequence contains

*atrium-api-cc-en_GB*
```kotlin
assert(x).contains("hello", "world")
assert(x).contains.atLeast(1).butAtMost(2).value("hello")
assert(x).contains.exactly(1).values("hello", "robert")
assert(x).contains.atMost(2).regex("h(e|a)llo")
assert(x).contains.ignoringCase.notOrAtMost(1).regex("h(e|a)llo", "[Rr]obert")
```
Notice that the final steps
`value`, `values` and `regex` 
in the sophisticated assertion building process
are applicable to all shown examples 
(e.g. `exactly(1).values("hello", "robert")` could have been finished with `exactly(1).regex("h(e|a)llo")` as well).


*atrium-api-cc-infix-en_GB*
```kotlin
assert(x) contains Values("hello", "world")
assert(x) to contain atLeast 1 butAtMost 2 value "hello"
assert(x) to contain exactly 1 the Values("hello", "robert")
assert(x) to contain atMost 2 regex "h(e|a)llo"
assert(x) to contain ignoring case notOrAtMost 1 the RegexPatterns("h(e|a)llo", "[Rr]obert")
```
Notice that the final steps 
`value`, `Values(...)`, `regex` and `RegexPatterns(..)` 
in the sophisticated assertion building process
are applicable to all shown examples 
(e.g. `exactly(1).values("hello", "robert")` could have been finished with `exactly(1).regex("h(e|a)llo")` as well).

## Iterable contains

### Iterable contains in any order

*atrium-api-cc-en_GB*
```kotlin
assert(x).contains(1.2)
assert(x).contains(1.2, 5.7)
assert(x).contains { isLessThan(2) }
assert(x).contains({ isLessThan(2) }, { isGreaterThan 5 })

assert(x).contains.inAnyOrder.atLeast(1).butAtMost(2).value(3.2)
assert(x).contains.inAnyOrder.exactly(1).values("hello", "robert")
assert(x).contains.inAnyOrder.atMost(2).entry { isLessOrEquals(2) }
assert(x).contains.inAnyOrder.notOrAtMost(2).entries({ notToBe(3) }, { isGreaterOrEquals(2) })
assert(x).contains.inAnyOrder.only.value("hello")
assert(x).contains.inAnyOrder.only.values(personA, personB)
assert(x).contains.inAnyOrder.only.entry { isLessThan(2) }
assert(x).contains.inAnyOrder.only.entries({ toBe(3) }, { isLessThan(2) })
```
Notice that the final steps 
`value`, `values`, `entry` and `entries` 
in the sophisticated assertion building process
are applicable to all shown examples
(e.g. `butAtMost(2).value(3.2)` could have been finished with `entries(...)` as well)

*atrium-api-cc-infix-en_GB*
```kotlin
assert(x) contains 1.2
assert(x) contains Values(1.2, 5.7) // or Objects as alternative
assert(x) contains { o isLessThan 2 }
assert(x) contains Entries({ o isLessThan 2 }, { o isGreaterThan 5 })

assert(x) to contain inAny order atLeast 1 butAtMost 2 value 3.2
assert(x) to contain inAny order exactly 1 the Values("hello", "robert")
assert(x) to contain inAny order atMost 2 entry { o isLessOrEquals 2 }
assert(x) to contain inAny order notOrAtMost 2 the Entries({ o notToBe 3 }, { o isGreaterOrEquals 2 })
assert(x) to contain inAny order but only value "hello")
assert(x) to contain inAny order but only the Values(personA, personB)
assert(x) to contain inAny order but only entry { o isLessThan 2 } 
assert(x) to contain inAny order but only the Entries({ o toBe 3 }, { o isLessThan 2 })
```
Notice that the final steps 
`value`, `Values(...)`, `entry` and `Entries(...)` 
in the sophisticated assertion building process,
are applicable to all shown examples 
(e.g. `butAtMost 2 value 3.2` could have been finished with `Entries(...)` as well)

### Iterable contains in order

*atrium-api-cc-en_GB*
```kotlin
assert(x).containsExactly(1.2)
assert(x).containsExactly(1.2, 5.7)
assert(x).containsExactly({ isLessThan(2) })
assert(x).containsExactly({ isLessThan(2) }, { isGreaterThan 5 })

assert(x).contains.inOrder.only.value("hello")
assert(x).contains.inOrder.only.values("hello", "world")
assert(x).contains.inOrder.only.entry { isLessThan(2) }
assert(x).contains.inOrder.only.entries({ toBe(3) }, { isLessThan(2) })
assert(x).contains.inOrder.only.grouped.within.inAnyOrder(
    Value(1), 
    Values(1, 2), 
    Values(3, 4)
)
assert(x).contains.inOrder.only.grouped.within.inAnyOrder(
    Entry({ toBe(1) }), 
    Entries({ isLessThan(2) },{ isGreaterThan(2) }), 
    Entries({ toBe(3) }, { toBe(4) })
)
```

*atrium-api-cc-infix-en_GB*
```kotlin
assert(x) containsExactly 1.2
assert(x) containsExactly Values(1.2, 5.7) // or Objects as alternative
assert(x) containsExactly { o isLessThan 2 }
assert(x) containsExactly Entries({ o isLessThan 2 }, { o isGreaterThan 5 })

assert(x) contains inGiven order and only value "hello"
assert(x) contains inGiven order and only the Values("hello", "world")
assert(x) contains inGiven order and only entry { o isLessThan 2 }
assert(x) contains inGiven order and only the Entries({ o toBe 3 }, { o isLessThan 2 })
assert(x) contains inGiven order and only grouped entries within group inAny Order(
    Value(1), 
    Values(1, 2), 
    Values(3, 4)
)
assert(x) contains inGiven order and only grouped entries within group inAny Order(
    Entry({ o toBe(1) }), 
    Entries({ o isLessThan(2) },{ o isGreaterThan(2) }), 
    Entries({ o toBe(3) }, { o toBe(4) })
)
```

## Iterable contains not

*atrium-api-cc-en_GB*
```kotlin
assert(x).containsNot(1.2)
assert(x).containsNot(1.2, 5.7)

assert(x).containsNot.value(null)
assert(x).containsNot.values(null, 1)
assert(x).containsNot.entry { isLessThan(2) }
assert(x).containsNot.entries(null, { isLessThan(2) }, { isGreaterThan 5 })
```

*atrium-api-cc-infix-en_GB*
```kotlin
assert(x) containsNot 1.2
assert(x) containsNot Values(1.2, 5.7)

assert(x) notTo contain value null
assert(x) notTo contain the Values(null, 1)
assert(x) notTo contain entry { o isLessThan 2 }
assert(x) notTo contain the Entries(null, { o isLessThan 2 }, { o isGreaterThan 5 })
```

# Iterable predicate-like assertions
For more sophisticated assertions such as "there should be two matches", use the sophisticated assertion builder `contains.inAnyOrder` 
-&gt; see [Iterable contains in any order](#iterable-contains-in-any-order) for more information 

*atrium-api-cc-en_GB*
```kotlin
assert(x).any { startsWith("hello") }
assert(x).none { endsWith(".") }
assert(x).all { isNumericallyEqualTo(12.2) }

assert(x).any(null)
assert(x).none(null)
assert(x).all(null)
```

*atrium-api-cc-infix-en_GB*
```kotlin
assert(x) any { o startsWith "hello" }
assert(x) none { o endsWith "." }
assert(x) all { o isNumericallyEqualTo 12.2 }

assert(x) any null
assert(x) none null
assert(x) all null
```

# List get
*atrium-api-cc-en_GB*
```kotlin
assert(x).get(0).isLessThan(1)
assert(x).get(0) { isGreaterThan(1) }

//in case of a nullable element type
assert(x).get(0).toBe(null)
```

*atrium-api-cc-infix-en_GB*
```kotlin
assert(x) get 0 isLessThan 1
assert(x) get Index(0) assertIt { o isGreaterThan 1 }

//in case of a nullable element type
assert(x) get 0 toBe null
```

# Map getExisting
*atrium-api-cc-en_GB*
```kotlin
assert(x).getExisting("a").isLessThan(1)
assert(x).getExisting("a") { isGreaterThan(1) }

//in case of a nullable value type
assert(x).getExisting("a").notToBeNull { isGreaterThan(1) }
```

*atrium-api-cc-infix-en_GB*
```kotlin
assert(x) getExisting "a" isLessThan 1
assert(x) getExisting Key("a") assertIt { o isGreaterThan 1 }

//in case of a nullable value type
assert(x) getExisting Key("a") notToBeNull { o isGreaterThan 1 }
```

# Map contains
*atrium-api-cc-en_GB*
```kotlin
assert(x).contains("a" to 1)
assert(x).contains("a" to 1, "b" to 2)
assert(x).contains(KeyValue("a") { isGreaterThan(3).and.isLessThan(10) })
assert(x).contains(KeyValue("a") { toBe(2) }, KeyValue("b") { isLessThan(3) })

//in case of a nullable value type
assert(x).contains("a" to null)
assert(x).contains("a" to null, "b" to 2)
assert(x).contains(KeyValue("a", null))
assert(x).contains(
  KeyValue("a", null) 
  KeyValue("b") { isLessThan(2) }
)
```

*atrium-api-cc-infix-en_GB*
```kotlin
assert(x) contains ("a" to 1)
assert(x) contains Pairs("a" to 1, "b" to 2)
assert(x) contains KeyValue("a") { 
  o isGreaterThan 3
  o isLessThan 10 
}
assert(x) contains All(KeyValue("a") { o toBe 2 }, KeyValue("b") { o isLessThan 3 })

//in case of a nullable value type
assert(x) contains ("a" to null)
assert(x) contains Pairs("a" to null, "b" to 2)
assert(x) contains KeyValue("a", null)
assert(x) contains All(
  KeyValue("a", null), 
  KeyValue("b") { o isLessThan 2 }
)
```
