package org.example

val TYPES = listOf(
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

val COMMON_SERVICES = listOf(
  "model_id",
  "hw_vers",
  "sw_vers",
  "serial_num",
  "state",
  "error",
  "name",
  "action",
  "device_id",
  "update_ota",
  "winter_mode",
  "mqtt_connected",
  "server_on",
  "date_mes",
  //"protocol_version",
  //"ap_bcast_duration",
  //"jeedom_interval",
  "rssi",
)

val OPTIONNALS = listOf(
  "protocol_version",
  "ap_bcast_duration",
  "jeedom_interval",
  "jeedom_dbg"
)

val AUXILIARY2_M_SERVICES = listOf("aux2_mode", "aux2_state", "aux2_sched")
val AUXILIARY_SERVICES = listOf("aux_type", "aux_conf", "aux_state", "aux_min_temp", "consigne_temp", "source_temp", "consigne_orp", "source_orp", "aux_sched")
val AUXILIARY_M_SERVICES = listOf("aux1_type", "aux1_mode", "aux1_state", "consigne_temp", "source_temp", "value_orp", "consigne_orp", "source_orp", "aux1_sched", "aux_conf_m")
val CHLORINE_MEASUREMENT_SERVICES = listOf("value_chlor", "chlor_adjust", "date_mes_chlor")
val CHLORINE_REGULATION_SERVICES = listOf("consigne_chlor", "source_chlor", "chlor_hyst", "cs")
val COVER_SERVICES = listOf("couv_on")
val ELECTROLYSIS_SERVICES = listOf("prod_on", "prod_chlore", "value_cond", "voltage_ely", "current_ely", "ely_duration_compensated", "ely_duration_in_minut", "null", "mode_ely", "mode_choc", "power_ely", "water_hardness", "ely_duration_theo", "temp_min_off_ely", "choc_duration", "consigne_orp", "source_orp", "cell_type")
val ENERGY_MEASUREMENT_SERVICES = listOf("watt_ind", "refresh", "watt", "amp", "volt", "freq", "pf", "meter_sn", "meter_sw")
val FILTRATION_SERVICES = listOf("filt_light_force", "filt_state", "filt_serv_inter", "filt_sched", "aux_temp_hyst", "backwash")
val FILTRATION_M_SERVICES = listOf("filt_mode", "filt_state_m", "filt_sched_m")
val FLOW_SERVICES = listOf("flow_on")
val FLOW_NO_SERVICES = listOf("flow_no")
val FLOW_MEASUREMENT_SERVICES = listOf("debit", "debit_adjust", "bypass")
val FROST_FREE_SERVICES = listOf("frost_free")
val GATEWAY_SERVICES = listOf("mode_gtw")
val HEAT_PUMP_SERVICES = listOf("pac_state", "pac_mode", "pac_speed", "pac_params", "pac_errors", "pac_prio", "value_temp_out", "value_temp_amb", "pac_sched")
val INTERNAL_TEMP_MEASUREMENT_SERVICES = listOf("value_temp_int")
val LIGHTING_SERVICES = listOf("light_type", "light_state", "light_code", "light_speed", "light_bright", "light_timer", "light_sched")
val LIGHTING_M_SERVICES = listOf("light_type_m", "light_mode", "light_state_m", "light_code", "light_speed", "light_bright", "light_sched_m")
val LIGHTING_X_SERVICES = listOf("rgbw", "x3_state", "x3_color", "x3_bright", "x3_speed", "light_timer", "x3_sched", "p1_color", "p2_color", "dmx_ch")
val METEOR_ERRORS_SERVICES = listOf("meteor_errors")
val ORP_AWARE_SERVICES = listOf("value_orp")
val ORP_MEASUREMENT_SERVICES = listOf("value_orp", "orp_adjust", "orp_calibration_target", "date_mes_orp")
val ORP_REGULATION_SERVICES = listOf("mode_orp", "priority", "consigne_orp", "source_orp", "source_ph")
val ORP_SETPOINT_SERVICES = listOf("consigne_orp")
val ORP_SOURCE_SERVICES = listOf("source_orp")
val PH_MEASUREMENT_SERVICES = listOf("value_ph", "ph_adjust", "ph_calibration_target", "date_mes_ph")
val PH_REGULATION_SERVICES = listOf("mode_ph", "consigne_ph", "source_ph")
val PRESSURE_MEASUREMENT_SERVICES = listOf("pression", "pression_adjust")
val PRESSURE_MEASUREMENT_EXTENDED_SERVICES = listOf("pression_h_alerte", "pression_b_alerte", "pression_h_sec", "pression_b_sec", "pression_option", "error_pressure")
val REGULATION_SERVICES = listOf("inject_on", "vol_24h_inject", "vol_tot_inject", "vol_bac", "vol_max_24h")
val TEMP_AWARE_SERVICES = listOf("value_temp", "temp_accuracy")
val TEMP_MEASUREMENT_SERVICES = listOf("temp_adjust")
val TEMP_REGULATION_SERVICES = listOf("consigne_temp")
val TIMEZONE_SERVICES = listOf("tz", "epoch", "sunset", /*"sunrise",*/ "lat", "long")
val VARIABLE_SPEED_PUMP_SERVICES = listOf("pump_id", "pump_conf", "filt_speed")
val VIGIPOOL_SERVICES = listOf("uuid_bonding", "bonded", "ble_vers", "wifi_ssid", "wifi_pwd", "try_connect", "wifi_connected", "aws_connected", "connected", "model_id", "hw_vers", "sw_vers", "serial_num", "state", "error", "name", "action", "device_id", "update_ota", "winter_mode", "mqtt_connected", "server_on", "date_mes", "protocol_version", "ap_bcast_duration", "jeedom_interval", "rssi")
val WATER_LEVEL_MEASUREMENT_SERVICES = listOf("water_ind", "water_lvl", "lvl_state", "evr", "evv", "lvl_conf", "probe_h", "max_h", "levels", "max_fill")

val DEVICES = mapOf(
  "unknown" to listOf(), // 0x00
  "phileox" to COMMON_SERVICES +
      FLOW_SERVICES +
      REGULATION_SERVICES +
      PH_REGULATION_SERVICES +
      PH_MEASUREMENT_SERVICES, // 0x01
  "ziphox" to COMMON_SERVICES +
      FLOW_SERVICES +
      PH_MEASUREMENT_SERVICES +
      ORP_MEASUREMENT_SERVICES +
      TEMP_MEASUREMENT_SERVICES + TEMP_AWARE_SERVICES, // 0x02
  "daisyph" to COMMON_SERVICES +
      REGULATION_SERVICES +
      PH_REGULATION_SERVICES, // 0x03
  "lynx" to COMMON_SERVICES +
      GATEWAY_SERVICES, // 0x04
  "tild" to COMMON_SERVICES +
      TIMEZONE_SERVICES +
      TEMP_MEASUREMENT_SERVICES + TEMP_AWARE_SERVICES +
      FLOW_SERVICES +
      COVER_SERVICES +
      LIGHTING_SERVICES +
      AUXILIARY_SERVICES +
      FILTRATION_SERVICES +
      FROST_FREE_SERVICES, // 0x05
  "zelix" to COMMON_SERVICES +
      ELECTROLYSIS_SERVICES +
      FLOW_SERVICES +
      COVER_SERVICES +
      TEMP_MEASUREMENT_SERVICES + TEMP_AWARE_SERVICES +
      INTERNAL_TEMP_MEASUREMENT_SERVICES, // 0x06
  "limpido" to COMMON_SERVICES +
      ELECTROLYSIS_SERVICES +
      TEMP_MEASUREMENT_SERVICES +
      INTERNAL_TEMP_MEASUREMENT_SERVICES, // 0x07
  "oxeox" to COMMON_SERVICES +
      FLOW_SERVICES +
      REGULATION_SERVICES +
      ORP_MEASUREMENT_SERVICES +
      ORP_REGULATION_SERVICES, // 0x08
  "daisyox" to COMMON_SERVICES +
      REGULATION_SERVICES +
      ORP_REGULATION_SERVICES, // 0x09
  "ofi" to listOf(), // 0x0A
  "limpidoez" to COMMON_SERVICES +
      ELECTROLYSIS_SERVICES +
      TEMP_MEASUREMENT_SERVICES +
      INTERNAL_TEMP_MEASUREMENT_SERVICES, // 0x0B
  "brio_will" to listOf(), // 0x0C
  "intellichlor2" to listOf(), // 0x0D
  "vigipac" to COMMON_SERVICES +
      HEAT_PUMP_SERVICES, // 0x0E
  "x312" to COMMON_SERVICES +
      LIGHTING_X_SERVICES, // 0x0F
  "ofi_smart" to listOf(), // 0x10
  "oxeox lt" to COMMON_SERVICES +
      ORP_MEASUREMENT_SERVICES +
      ORP_REGULATION_SERVICES, // 0x11
  "noria" to listOf(), // 0x12
  "anteam" to COMMON_SERVICES +
      TIMEZONE_SERVICES +
      TEMP_MEASUREMENT_SERVICES + TEMP_AWARE_SERVICES +
      FLOW_SERVICES +
      COVER_SERVICES +
      LIGHTING_M_SERVICES +
      AUXILIARY_M_SERVICES +
      AUXILIARY2_M_SERVICES +
      FILTRATION_M_SERVICES +
      FROST_FREE_SERVICES, // 0x13
  "timeo" to listOf(), // 0x14
  "harry" to listOf(), // 0x15
  "bello" to listOf(), // 0x16
  "garry" to listOf(), // 0x17
  "anteavs" to COMMON_SERVICES +
      VARIABLE_SPEED_PUMP_SERVICES, // 0x18
  "cs4z" to listOf(), // 0x19
  "zeliafx" to listOf(), // 0x1A
  "wix3z4" to listOf(), // 0x1B
  "vigiwatt" to COMMON_SERVICES +
      ENERGY_MEASUREMENT_SERVICES, // 0x1C
  "niva" to COMMON_SERVICES +
      WATER_LEVEL_MEASUREMENT_SERVICES, // 0x1D
  "oxeox oem" to COMMON_SERVICES +
      ORP_MEASUREMENT_SERVICES +
      ORP_REGULATION_SERVICES, // 0x1E
  "oxeox lt oem" to COMMON_SERVICES +
      ORP_MEASUREMENT_SERVICES +
      ORP_REGULATION_SERVICES, // 0x1F
  "vigichlor" to COMMON_SERVICES +
      CHLORINE_REGULATION_SERVICES +
      CHLORINE_MEASUREMENT_SERVICES, // 0x20
  "skimini" to listOf(), // 0x21
  "lineo" to listOf(), // 0x22
  "anteaox" to COMMON_SERVICES +
      ORP_MEASUREMENT_SERVICES +
      FLOW_SERVICES, // 0x23
  "nexteu" to listOf(), // 0x24
  "mika" to COMMON_SERVICES +
      FLOW_SERVICES +
      PRESSURE_MEASUREMENT_SERVICES +
      PRESSURE_MEASUREMENT_EXTENDED_SERVICES +
      FLOW_MEASUREMENT_SERVICES, // 0x25
)

data class Shadow(
  val state: State?,
  val metadata: Metadata? = null,
  val version: Int? = null,
  val timestamp: Long? = null
)

data class State(
  val reported: LinkedHashMap<String, Any>,
  val desired: LinkedHashMap<String, Any>? = null,
  val delta: LinkedHashMap<String, Any>? = null
)

data class Metadata(
  val reported: LinkedHashMap<String, Any>,
  val desired: LinkedHashMap<String, Any>? = null,
  val delta: LinkedHashMap<String, Any>? = null
)

data class Thing(
  val thingName: String,
  val thingTypeName: String,
  val thingArn: String,
  val attributes: LinkedHashMap<String, Any>,
  val version: Int
)

data class ThingsResponse(
  val things: List<Thing>
)

data class DeviceId(
  val id: String,
  val mac: String,
  val type: String
)


class DeviceWrapper : LinkedHashMap<String, Any>()

class ShadowWrapper {
  var name: String? = null
  var shadow: Shadow? = null
  var rawJson: String? = null
  var master: DeviceWrapper? = null
  var slaves: Map<String,DeviceWrapper>? = null

  fun count(): Int {
    return slavesCount() + (if (master != null) 1 else 0)
  }

  fun slavesCount (): Int = slaves?.size ?: 0

  fun hasDeviceType(type: String): Boolean {
    return name?.substringBefore("_") == type || // master
        slaves?.keys?.any { it.substringBefore("_") == type } ?: false // slaves
  }

  fun firstDeviceOfType(type: String): String? {
    return if (name?.substringBefore("_") == type)
      name?.substringBefore(".json")
    else
      slaves?.keys?.firstOrNull { it.substringBefore("_") == type }
  }

  fun getTimestamp(deviceId: String, property: String): Long {
    return shadow?.metadata?.reported?.get("$deviceId/$property")?.toString()?.toLongOrNull() ?: 0
  }
  fun masterDeviceId(): String? = name?.substringBefore(".json")

  fun clientDeviceIds(): List<String> = slaves?.keys?.toList() ?: emptyList()

  fun isMinMasterValid(): Boolean = master?.keys?.containsAll(COMMON_SERVICES) ?: false

  fun isMinClientValid(): Boolean =
    slaves?.all { it.value.keys.containsAll(COMMON_SERVICES) } ?: false

  fun isMinValid(): Boolean = isMinMasterValid() && isMinClientValid()

  fun getMasterMissingServices(): Map<String, List<String>> {
    return mapOf(masterDeviceId()!! to COMMON_SERVICES.filter { !master?.containsKey(it)!! })
  }
  fun getMissingServices(): Map<String, List<String>> {
    val missingServices = mutableMapOf<String, List<String>>()
    val masterMissingServices = COMMON_SERVICES.filter { !master?.containsKey(it)!! }
    if (masterMissingServices.isNotEmpty())
      missingServices[masterDeviceId()!!] = masterMissingServices
    clientDeviceIds().forEach { deviceId ->
      val clientMissingServices = COMMON_SERVICES.filter { !slaves?.get(deviceId)?.containsKey(it)!! }
      if (clientMissingServices.isNotEmpty())
        missingServices[deviceId] = clientMissingServices
    }
    return missingServices
  }
  fun hasMasterModelId(modelId: Int): Boolean = modelId == (master?.get("model_id") ?: 0).toString().toInt()

  fun hasClientModelId(modelId: Int): Boolean {
    return slaves?.values?.any {
      val thisModelId = (it["model_id"] ?: 0).toString().toInt()
      thisModelId == modelId
    } ?: false
  }

  fun hasModelId(modelId: Int): Boolean =
    hasMasterModelId(modelId) || hasClientModelId(modelId)

  fun getLocation(): Pair<String,String>? {
    // try from the master first
    val lat = master?.get("lat")?.toString()
    val lon = master?.get("long")?.toString()
    if (lat != null && lon != null)
      return Pair(lat, lon)

    // then try the first who shows up
    slaves?.forEach { (_, device) ->
      val lats = device["lat"]?.toString()
      val lons = device["long"]?.toString()
      if (lats != null && lons != null)
        return Pair(lats, lons)
    }
    return null
  }

  fun isConnected(): Boolean = master?.get("connected") as? Boolean ?: false

  fun shadowLink(): String = IotCoreUtils.shadowLink(name!!.substringBefore(".json"))

  fun googleMapsLink(): String {
    val location = getLocation()
    return if (location != null)
     googleMapLink(location.first, location.second)
    else
      ""
  }

  fun checkStateBit(deviceId: String, bit: Int): Boolean {
    val deviceState = (shadow?.state?.reported?.get("$deviceId/state") as? Int) ?: 0
    return deviceState.toInt() and (1 shl bit) != 0
  }
}