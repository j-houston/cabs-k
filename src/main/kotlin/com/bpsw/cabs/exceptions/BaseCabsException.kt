package com.bpsw.cabs.exceptions

open class BaseCabsException: Exception {
    private val _code : CabErrorCode
    val code get() = this._code

    constructor(code: CabErrorCode): super() {
        _code = code
    }
    constructor(code: CabErrorCode, message: String) : super(message) {
        _code = code
    }
    constructor(code: CabErrorCode, message: String, cause: Throwable) : super(message, cause) {
        _code = code
    }
    constructor(code: CabErrorCode, cause: Throwable): super(cause) {
        _code = code
    }
}
