package com.cmhernandezdel.altruino.exceptions

import java.io.IOException

/**
 * Represents an error in a bluetooth socket.
 */
class BluetoothSocketException(message: String) : IOException(message) {}