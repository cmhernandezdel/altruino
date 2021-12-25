package com.cmhernandezdel.altruino.exceptions

/**
 * Represents an error thrown when the device has no bluetooth capabilities.
 */
class BluetoothNotAvailableException(message: String) : Exception(message) {}