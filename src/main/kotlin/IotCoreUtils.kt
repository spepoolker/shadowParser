package org.example

import org.example.Config.awsRegion
import org.example.Config.getAwsCommand
import org.example.Config.outputDirPath
import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

/**
 * Utility functions to interact with AWS IoT Device Shadows.
 */
@OptIn(ExperimentalStdlibApi::class)
object IotCoreUtils {

  private val SHADOW_LINK = "https://$awsRegion.console.aws.amazon.com/iot/home?region=$awsRegion#/thing/%s/namedShadow/Classic Shadow"

  fun shadowLink(deviceId: String) = String.format(SHADOW_LINK, deviceId).replace(" ", "%20")

  fun mapRatio(map: Map<*, Int>, total: Int = map.size): Map<*, String> {
    return map.mapValues { "%.1f".format(it.value.toDouble() / total * 100) }
  }

  fun parseSwVersion(swVersion: Int): String {
    val majorVersion = ((swVersion shr 8) and 0xFF).toHexString()
    val minorVersion = (swVersion and 0xFF).toHexString()
    return "$majorVersion.$minorVersion"
  }

  fun execCommand(command: List<String>, outputFile: File? = null) {
    val processBuilder = ProcessBuilder(command).apply { redirectErrorStream(true) }
    val process = processBuilder.start()
    val reader = BufferedReader(InputStreamReader(process.inputStream))
    var line: String?
    while (reader.readLine().also { line = it } != null) {
      println(line)
      outputFile?.appendText("$line\n")
    }

    process.waitFor()
    println("> $command: ${process.exitValue()}")
  }

  fun listThings(suffix: String? = null): String {
    val command = getAwsCommand("iot", "list-things")
    val outputFileName = "$outputDirPath/things${suffix?.let{ "_$it" }}.json"
    // TODO add exponential backoff in case of throttling
    execCommand(command, File(outputFileName))
    return outputFileName
  }

//  fun getThingsShadows(fileName: String) {
//    val things = File(fileName).readText()
//  }

  fun getThingShadow(
    deviceId: String,
    outputDirectory: File? = File("."),
    prettyPrint: Boolean? = false
  ) {
    val outputFile = File(outputDirectory, "$deviceId.json")
    val command = getAwsCommand("iot-data", "get-thing-shadow", "--thing-name", deviceId, outputFile.absolutePath)
    execCommand(command)

    // once this is done, overwrite the file with a pretty-printed version
    if (prettyPrint == true)
        outputFile.writeText(prettyPrint(outputFile.readText()))
  }

  fun updateThingShadow(
    deviceId: String,
    property: String,
    value: Any, // will wrap value in quotes if String
  ) {
    val valueString = if (value is String) "\"$value\"" else value.toString()
    val payload = """{"state":{"desired":{"$deviceId/$property":$valueString}}}"""
    val base64Payload = Base64.getEncoder().encodeToString(payload.toByteArray())
    val command = getAwsCommand("iot-data", "update-thing-shadow", "--thing-name", deviceId, "--payload", base64Payload, "output.json")
    execCommand(command)
  }

}

fun main() {
  val today = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
  val thingsFileName = IotCoreUtils.listThings(today)

//  IotCoreUtils.getThingShadow(
//    deviceId = "tild_94B97E784C7C",
//    prettyPrint = true
//  )
}