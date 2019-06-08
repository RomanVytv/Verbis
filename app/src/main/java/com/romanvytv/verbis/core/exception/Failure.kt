package com.romanvytv.verbis.core.exception

/**
 * Base Class for handling errors/failures/exceptions.
 * Every feature specific failure should extend [FeatureFailure] class.
 */
sealed class Failure {
    object NetworkConnection : Failure()
    object ServerError : Failure()

    /** * Extend this class for feature specific failures.*/
    abstract class FeatureFailure : Failure()

    override fun toString(): String = when (this) {
        is NetworkConnection -> "Network error"
        is ServerError -> "Server Error"
        is FeatureFailure -> "Feature Error"
        else -> "Unknown error"
    }
}

