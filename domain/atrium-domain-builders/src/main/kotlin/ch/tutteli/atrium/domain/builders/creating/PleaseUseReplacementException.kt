package ch.tutteli.atrium.domain.builders.creating

/**
 * Indicates a problem which was indicated by a `@Depreacted` annotation but was ignored by you ;-)
 */
class PleaseUseReplacementException(reason: String) : Exception(reason)
