package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.CoreFactory

/**
 * Represents the [AssertionGroupType] for [AssertionGroup]s which contain some kind of feature assertions.
 */
interface FeatureAssertionGroupType : AssertionGroupType

/**
 * This class is only used as identifier for [CoreFactory.registerTextAssertionFormatterCapabilities].
 */
class PrefixFeatureAssertionGroupHeader private constructor() : BulletPointIdentifier

/**
 * The [AssertionGroupType] for [AssertionGroup]s which contain any kind of feature assertions.
 */
object DefaultFeatureAssertionGroupType : FeatureAssertionGroupType
