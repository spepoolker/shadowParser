package org.example

import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

// global time formatter for the logs
val timeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss")
val getTimeStamp = LocalDateTime.now().format(timeFormatter)

fun timestampFilename(prefix: String, suffix: String = "json"): String = "$prefix-$getTimeStamp.$suffix"

val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
val jsonParser = JsonParser()
fun prettyPrint(json: String): String = gsonBuilder.toJson(jsonParser.parse(json))

fun sCol(string: String): String = String.format("%30s", string)
fun iCol(index: Int): String = String.format("%03d", index)

fun logInfo(message: String, logFile: File? = null) {
  println(message)
  logFile?.appendText("$message\n")
}

fun logError(message: String, logFile: File? = null) {
  System.err.println(message)
  logFile?.appendText("$message\n")
}

fun camelToSnake(input: String): String {
  val result = StringBuilder()
  input.forEachIndexed { index, char ->
    if (index > 0 && char.isUpperCase())
      result.append('_').append(char.lowercase())
    else
      result.append(char.lowercase())
  }
  return result.toString()
}

fun googleMapLink(lat: String, lon: String): String = "https://www.google.com/maps/search/?api=1&query=$lat,$lon"