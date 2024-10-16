package org.example

open class VigipoolDevice {

  companion object {

    val TYPE = listOf(
      "unknown", // 0x00
      "phileox", // 0x01
      "ziphox", // 0x02
      "daisyph", // 0x03
      "lynx", // 0x04
      "tild", // 0x05
      "zelix", // 0x06
      "limpido", // 0x07
      "oxeox", // 0x08
      "daisyox", // 0x09
      "ofi", // 0x0A
      "limpidoez", // 0x0B
      "brio_will", // 0x0C
      "intellichlor2", // 0x0D
      "vigipac", // 0x0E
      "x312", // 0x0F
      "ofi_smart", // 0x10
      "oxeox lt", // 0x11
      "noria", // 0x12
      "anteam", // 0x13
      "timeo", // 0x14
      "harry", // 0x15
      "bello", // 0x16
      "garry", // 0x17
      "anteavs", // 0x18
      "cs4z", // 0x19
      "zeliafx", // 0x1A
      "wix3z4", // 0x1B
      "vigiwatt", // 0x1C
      "niva", // 0x1D
      "oxeox oem", // 0x1E
      "oxeox lt oem", // 0x1F
      "vigichlor", // 0x20
      "skimini", // 0x21
      "lineo", // 0x22
      "anteaox", // 0x23
      "nexteu", // 0x24
      "mika", // 0x25
    )

    const val UUID_BONDING = "uuid_bonding"
    const val BONDED = "bonded"
    const val BLE_VERS = "ble_vers"
    const val WIFI_SSID = "wifi_ssid"
    const val WIFI_PWD = "wifi_pwd"
    const val TRY_CONNECT = "try_connect"
    const val WIFI_CONNECTED = "wifi_connected"
    const val AWS_CONNECTED = "aws_connected"
    const val MODEL_ID = "model_id"
    const val HW_VERS = "hw_vers"
    const val SW_VERS = "sw_vers"
    const val SERIAL_NUM = "serial_num"
    const val STATE = "state"
    const val ERROR = "error"
    const val NAME = "name"
    const val ACTION = "action"
    const val DEVICE_ID = "device_id"
    const val UPDATE_OTA = "update_ota"
    const val WINTER_MODE = "winter_mode"
    const val MQTT_CONNECTED = "mqtt_connected"
    const val SERVER_ON = "server_on"
    const val DATE_MES = "date_mes"
    const val PROTOCOL_VERSION = "protocol_version"
    const val AP_BROADCAST_DURATION = "ap_broadcast_duration"
    const val JEEDOM_INTERVAL = "jeedom_interval"
    const val RSSI = "rssi"

    val MASTER_BLE = arrayOf(UUID_BONDING, BONDED, BLE_VERS)
    val MASTER_WIFI = arrayOf(WIFI_SSID, WIFI_PWD, TRY_CONNECT, WIFI_CONNECTED, AWS_CONNECTED)
    val REQUIRED = arrayOf(MODEL_ID, HW_VERS, SW_VERS, SERIAL_NUM, STATE, ERROR, NAME, ACTION, DEVICE_ID, UPDATE_OTA, WINTER_MODE, MQTT_CONNECTED, SERVER_ON, DATE_MES)
    val OPTIONALS = arrayOf(PROTOCOL_VERSION, AP_BROADCAST_DURATION, JEEDOM_INTERVAL, RSSI)
  }

}