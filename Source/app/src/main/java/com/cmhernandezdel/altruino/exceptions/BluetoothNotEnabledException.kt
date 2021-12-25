package com.cmhernandezdel.altruino.exceptions

/**
 * Represents an error thrown when the device has bluetooth capabilities, but it is not enabled.
 */
class BluetoothNotEnabledException(message: String) : Exception(message) {}