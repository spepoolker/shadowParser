package org.example

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import java.io.File

object LocalShadowUtils {

  const val LOG_MISSING_PROPERTIES = false

  const val TS = "20240909"
  const val BASE_SHADOW_DIR = "/Users/sylvain/ShellScriptsProjects/old/"
  const val ROOT_SHADOW_DIR = "$BASE_SHADOW_DIR/shadows"
  const val SHADOW_DIR = "${ROOT_SHADOW_DIR}_$TS"

  fun listShadowDirectories(): List<String> {
    return File(ROOT_SHADOW_DIR).parentFile
      .listFiles { _, name -> name.startsWith("shadows_") }
      ?.map { it.name }
      ?: emptyList()
  }

  fun directorySize(shadowDirectoryName: String) {
    var totalSize = 0L
    File(shadowDirectoryName).walk().forEach {
      totalSize += it.length()
    }
  }

  fun parseShadowDirectory(ts: String = TS): List<ShadowWrapper> {
    val directory = File("${ROOT_SHADOW_DIR}_$ts")
    return directory
      .listFiles { _, name -> name.endsWith(".json") }
      ?.map { parseShadowFile(it) }
      ?: emptyList()
  }

  fun parseShadowFile(deviceId: String, ts:String): ShadowWrapper {
    val file = File("${ROOT_SHADOW_DIR}_$ts/$deviceId.json")
    return parseShadowFile(file)
  }

  fun parseShadowFile(file: File): ShadowWrapper {
    val shadowWrapper = ShadowWrapper().apply {
      name = file.name
      rawJson = file.readText()
      shadow = Gson().fromJson(rawJson, Shadow::class.java)
    }

    // get master device id from file name
    val masterDeviceId = file.nameWithoutExtension

    // get master device from shadow
    val shadow = JsonParser.parseString(shadowWrapper.rawJson).asJsonObject
    val reportedState = shadow.getAsJsonObject("state").getAsJsonObject("reported") ?: JsonObject()
    // get all prefixed properties
    shadowWrapper.master = reportedState.keySet()
      .filter { key -> key.startsWith("$masterDeviceId/") }
      .associate { key -> key.split("/")[1] to reportedState.get(key).asString }
      .toMutableMap()
      .let { DeviceWrapper().apply { putAll(it) } }
    // add the connected property which is not prefixed
    if (reportedState.has("connected"))
      shadowWrapper.master?.put("connected", reportedState.get("connected").asBoolean)
    else {
      shadowWrapper.master?.put("connected", false)
      if (LOG_MISSING_PROPERTIES) System.err.println("Missing connected property in shadow: ${shadowWrapper.name}")
    }

    // get the unique device ids from the shadow
    val deviceIds = reportedState.keySet()
      .filter { key -> key.contains("/") }
      .map { key -> key.split("/")[0] }
      .distinct()

    // TODO check validity of the device ids
    // TODO look for missing device ids / model ids
    // TODO look for missing state

    val clientIds = deviceIds.filter { it != masterDeviceId }
    shadowWrapper.slaves = clientIds.associateWith { clientId ->
      reportedState.keySet()
        .filter { key -> key.startsWith("$clientId/") }
        .associate { key -> key.split("/")[1] to reportedState.get(key).asString }
        .toMutableMap()
        .let { DeviceWrapper().apply { putAll(it) } }
    }

    return shadowWrapper
  }

  fun getShadows(ts: String = TS): List<ShadowWrapper> = parseShadowDirectory(ts)

  fun getConnectedShadows(ts: String = TS): List<ShadowWrapper> {
    return parseShadowDirectory(ts)
      .filter { it.master?.get("connected") as Boolean }
  }

}

fun main() {
  LocalShadowUtils.listShadowDirectories().forEach {
    println("Directory: $it")
    //LocalShadowUtils.getShadows(it).forEach { println(it) }
  }
}