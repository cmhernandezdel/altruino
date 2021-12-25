package com.cmhernandezdel.altruino.exceptions

import java.io.IOException

/**
 * Represents an error in a bluetooth connection.
 */
class BluetoothConnectionException(message: String) : IOException(message) {}