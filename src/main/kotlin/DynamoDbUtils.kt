package org.example

import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.annotations.SerializedName
import org.example.Config.awsRegion
import org.example.Config.getAwsCommand
import org.example.Config.outputDirPath
import java.io.File
import java.lang.reflect.Type

/**
 * Utility functions to interact with AWS DynamoDB.
 */
object DynamoDbUtils {

  /**
   * Class IOTDevice.
   */
  data class IOTDevice(
    val deviceName: String,
    val deviceId: String,
    val deviceType: String,
    val latitude: Double,
    val longitude: Double,
    val userId: String
  ) {
    fun hasLocation(): Boolean = latitude != 0.0 && longitude != 0.0
  }

  /**
   * Class IOTDevicesList.
   */
  data class IOTDevicesList(
    @SerializedName("Items") val items: List<IOTDevice>,
    @SerializedName("Count") val count: Int,
    @SerializedName("ScannedCount") val scannedCount: Int
  ) {
    fun getDevice(deviceId: String): IOTDevice? = items.find { it.deviceId == deviceId }
  }

  /**
   * Class IOTDeviceDeserializer.
   */
  class IOTDeviceDeserializer : JsonDeserializer<IOTDevice> {
    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): IOTDevice {
      val jsonObject = json.asJsonObject

      // Extract the string and number values from "S" and "N"
      val deviceName = jsonObject["deviceName"]?.asJsonObject?.get("S")?.asString ?: ""
      val deviceId = jsonObject["deviceId"]?.asJsonObject?.get("S")?.asString ?: ""
      val deviceType = jsonObject["deviceType"]?.asJsonObject?.get("S")?.asString ?: ""
      val latitude = jsonObject["latitude"]?.asJsonObject?.get("N")?.asDouble ?: 0.0
      val longitude = jsonObject["longitude"]?.asJsonObject?.get("N")?.asDouble ?: 0.0
      val userId = jsonObject["userId"]?.asJsonObject?.get("S")?.asString ?: ""

      return IOTDevice(deviceName, deviceId, deviceType, latitude, longitude, userId)
    }
  }

  // Registering the deserializer with Gson
  private val gson = GsonBuilder()
    .registerTypeAdapter(IOTDevice::class.java, IOTDeviceDeserializer())
    .create()

  fun loadIOTDevicesList(fileName:String = "IOT_devicesList.json"): IOTDevicesList {
    val file = File("$outputDirPath/$fileName")
    return gson.fromJson(file.readText(), IOTDevicesList::class.java)
  }

  // method to dump a table to json file
  fun dumpTable(tableName: String, fileName: String = tableName) {
    val command = getAwsCommand("dynamodb", "scan", "--table-name", tableName, "--region", awsRegion)
    val outputFile = File("$outputDirPath/$fileName.json").apply { if (exists()) delete() else parentFile.mkdirs() }
    IotCoreUtils.execCommand(command, outputFile)
  }

}

fun main() {
  DynamoDbUtils.dumpTable("IOT_devicesList")
//  val iotDeviceList = DynamoDbUtils.loadIOTDevicesList()
//  val device = iotDeviceList.getDevice("C049EFEC97C8")
//  println(device)
}