package com.bpsw.cabs.exceptions

class CabNotFoundException(message: String) : BaseCabsException(CabErrorCode.CAB_NOT_FOUND, message) {
}
