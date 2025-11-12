package com.codeplace.postsandroidapp.core.domain

enum class LocalStorageError: Error{
    SERIALIZATION_ERROR,
    IO_ERROR,
    CORRUPTION_ERROR,
    KEY_NOT_FOUND,
    UNKNOWN
}