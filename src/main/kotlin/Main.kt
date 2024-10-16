package org.example

import org.example.IotCoreUtils.shadowLink
import java.io.File
import java.io.FileOutputStream

// TODO list functions and script usages
/**
 * Refactored scripts:
 * - shadowScript:
 * - versionDiffScript:
 * -
 */

fun masterTypes(shadows: List<ShadowWrapper>): Map<String, Int> {
  return shadows.groupBy { it.master?.get("model_id") }
    .mapValues { (_, list) -> list.size }
    .mapKeys { (modelId, _) ->
      // Convert modelId to Int and map it to the TYPE array
      val index = modelId?.toString()?.toIntOrNull() ?: 0
      if (index in TYPES.indices) TYPES[index] else "Unknown"
    }
    .entries
    .sortedByDescending { it.value }
    .associate { it.toPair() }
}

fun oldStuff() {
  /*
if (!shadow.isMinValid()) {
  val missingServices = shadow.getMissingServices()
  if (missingServices.isNotEmpty()) {
    val sanitizedMissingServices = missingServices.toMutableMap()
      .filterNot {
        // TODO check if only missing: error, winter_mode, server_on, date_mes
        it.key.startsWith("lynx") && it.value.size == 4
      }
      .filterNot {
        // check for devices missing only the winter_mode, which is "normal"
        it.value.size == 1 && it.value.first() == "winter_mode" && when (it.key.substringBefore("_")) {
          "anteam", "anteavs", "x312", "vigiwatt", "niva", "vigipac" -> true
          else -> false
        }
      }
    if (sanitizedMissingServices.isNotEmpty()) {
      shadow.masterDeviceId()?.let {
        println("${index++}\t${shadowLink(it)}\t$missingServices")
      }
    }
  }
}
*/


//    val masterType = shadow.name?.substringBefore("_")
//    val deviceConfig = DEVICES[masterType] ?: listOf()
//    // check if the shadow has all the required services
//    val missingServices = deviceConfig.filterNot { shadow.master?.keys?.contains(it) ?: false }
//    val unexpectedServices = shadow.master?.keys?.filterNot {
//      deviceConfig.contains(it) //&& !OPTIONALS.contains(it)
//    }?.toMutableList() ?: mutableListOf()
//    unexpectedServices.remove("connected")
//    OPTIONNALS.forEach { unexpectedServices.remove(it) }
//    if (missingServices.isNotEmpty() || unexpectedServices.isNotEmpty()) {
//      shadow.masterDeviceId()?.let {
//        index++
//        if (missingServices.isNotEmpty()) println("$index\t${shadowLink(it)}\tmissing: $missingServices")
//        if (unexpectedServices.isNotEmpty()) println("$index\t${shadowLink(it)}\tunexpected: $unexpectedServices")
//      }
//    }

}

fun shadowScript() {
  val shadowFilesCount = File(LocalShadowUtils.SHADOW_DIR)
    .listFiles { _, name -> name.endsWith(".json") }?.size ?: 0

  val shadows = LocalShadowUtils.getShadows()
  val connectedShadows = LocalShadowUtils.getConnectedShadows()

  println("Files: $shadowFilesCount")
  println("Shadows: ${shadows.size}")
  println("Connected: ${connectedShadows.size}")

  // make a map with the count of master devices by modelId
  val modelIds = masterTypes(shadows)
  val connectedModelIds = masterTypes(connectedShadows)

  println("Master ModelIds: $modelIds")
  println("Connected Master ModelIds: $connectedModelIds")

  // check for missing modelIds and print the shadow link
  connectedShadows.forEach { shadow ->
    val modelId = shadow.master?.get("model_id")
    val modelIdIndex = modelId?.toString()?.toIntOrNull()
    if (modelIdIndex !in TYPES.indices) {
      val masterDeviceId = shadow.name?.substringBefore(".")
      if (masterDeviceId != null) {
        System.err.println("Unknown modelId: $modelId [${shadow.name}] => ${shadowLink(masterDeviceId)}")
      } else {
        System.err.println("Unknown modelId: $modelId [${shadow.name}]")
      }
    }
  }
}

fun headVersions(
  ts: String = LocalShadowUtils.SHADOW_DIR.substringAfter("_"),
  lines: Int = 10,
  iotLink: Boolean = false
) {
  val versionFile = getVersionFile(ts, false)
  val linesToRead = if (lines > 0) lines else Int.MAX_VALUE
  versionFile.readLines().take(linesToRead).forEachIndexed { index, line ->
    val (name, version) = line.split(",")
    val link = if (iotLink) shadowLink(name) else ""
    println("${index + 1}\t$name\t$version\t$link")
  }
}

data class VersionDiff(val name: String, val oldVersion: Int, val newVersion: Int, val diff: Int)

fun getVersionMap(
  ts: String = LocalShadowUtils.SHADOW_DIR.substringAfter("_")
): Map<String, Int> {
  val connectedShadows = LocalShadowUtils.getShadows(ts)
  val versionMap = mutableMapOf<String, Int>()
  connectedShadows.forEach { shadowWrapper ->
    shadowWrapper.name?.let {
      versionMap[it.substringBefore(".json")] = shadowWrapper.shadow?.version ?: 0
    }
  }
  return versionMap.toList()
    .sortedByDescending { (_, version) -> version }
    .toMap()
}

fun getVersionFile(
  ts: String = LocalShadowUtils.SHADOW_DIR.substringAfter("_"),
  deleteExisting: Boolean = true
):File {
  val logFile = File("data/output/versions_$ts.csv")
  if (!logFile.parentFile.exists())
    logFile.mkdirs()
  if (deleteExisting && logFile.exists())
    logFile.delete()
  return logFile
}

fun compareVersions(ts1: String, ts2: String): List<VersionDiff> {
  val versions1 = getVersionMap(ts1)
  val versions2 = getVersionMap(ts2)
  val diff: MutableMap<String, Int> = mutableMapOf()
  versions2.forEach { (name, newVersion) ->
    val oldVersion = versions1[name] ?: 0
    diff[name] = newVersion - oldVersion
  }

  val versionDiff = mutableListOf<VersionDiff>()
  diff.toList()
    .sortedByDescending { (_, diff) -> diff }
    .toMap()
    .forEach {
      val (name, diff2) = it
      val oldVersion = versions1[name] ?: 0
      val newVersion = versions2[name] ?: 0
      versionDiff.add(VersionDiff(name, oldVersion, newVersion, diff2))
    }
  return versionDiff
}

fun versionDiffScript(ts1: String, ts2: String) {
  val ts1 = "20240903"
  val ts2 = "20240909"
  val diff = compareVersions(ts1, ts2)

  // load IOTDevicesList to get the lat/lon
  val iotDeviceList = DynamoDbUtils.loadIOTDevicesList()

  val logFile = getVersionFile("diff_${ts1}_$ts2")
  var index = 0
  FileOutputStream(logFile, true).bufferedWriter().use { writer ->
    writer.appendLine("deviceId,old,new,diff,shadow,map")
    diff.forEach { (name, old, new, diff) ->
      //check if the shadow is connected in ts2
      val shadowWrapper = LocalShadowUtils.parseShadowFile(name, ts2)
      if (!shadowWrapper.isConnected()) return@forEach

      val shadowLink = shadowWrapper.shadowLink()
      // get the google maps link either from the shadow geolocation (if lat/long)
      val googleMapLink = shadowWrapper.googleMapsLink().ifEmpty {
        // ... or from the IOTDevicesList
        iotDeviceList.getDevice(name.substringAfter("_"))?.let { device ->
          googleMapLink(device.latitude.toString(), device.longitude.toString())
        } ?: ""
      }

      println("$index\t$name\t$old\t$new\t$diff\t$shadowLink\t$googleMapLink")
      writer.appendLine("$name,$old,$new,$diff,$shadowLink,$googleMapLink")
      index++
    }
  }
}

fun main() {
  val ts1 = "20240903"
  val ts2 = "20240909"
  val diff = compareVersions(ts1, ts2)

  // load IOTDevicesList to get the lat/lon
  val iotDeviceList = DynamoDbUtils.loadIOTDevicesList()

  val logFile = getVersionFile("diff_anteam_${ts1}_$ts2")
  var index = 1
  FileOutputStream(logFile, true).bufferedWriter().use { writer ->
    writer.appendLine("deviceId,old,new,diff,shadow,map")
    diff.forEach { (name, old, new, diff) ->
      //check if the shadow is connected in ts2
      val shadowWrapper = LocalShadowUtils.parseShadowFile(name, ts2)
      if (!shadowWrapper.isConnected()) return@forEach

      val shadowLink = shadowWrapper.shadowLink()
      // get the google maps link either from the shadow geolocation (if lat/long)
      val googleMapLink = shadowWrapper.googleMapsLink().ifEmpty {
        // ... or from the IOTDevicesList
        iotDeviceList.getDevice(name.substringAfter("_"))?.let { device ->
          googleMapLink(device.latitude.toString(), device.longitude.toString())
        } ?: ""
      }

      if (shadowWrapper.hasDeviceType("anteam")) {
        shadowWrapper.firstDeviceOfType("anteam")?.let { anteamDeviceId ->
          val updateAvailable = shadowWrapper.checkStateBit(anteamDeviceId, 28)
          if (updateAvailable) {
            println("$index\t$name\t$old\t$new\t$diff\t$shadowLink\t$googleMapLink")
            writer.appendLine("$name,$old,$new,$diff,$shadowLink,$googleMapLink")
            index++
          }
        }
      }

//      println("$index\t$name\t$old\t$new\t$diff\t$shadowLink\t$googleMapLink")
//      writer.appendLine("$name,$old,$new,$diff,$shadowLink,$googleMapLink")
//      index++
    }
  }
}