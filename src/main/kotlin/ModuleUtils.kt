package org.example

import org.yaml.snakeyaml.Yaml
import java.io.File

object ModuleUtils {

  fun generatePropertiesLists() {
    val yamlFile = File("data/all-modules.yml")
    val data: List<Map<String, Any>> = Yaml().load(yamlFile.reader())
    val result = data.map { item ->
      val name = (item["name"] as String).replace("-", "_")
      item["properties"]?.let { properties ->
        val keys = (properties as Map<*, *>).keys
        return@map "val ${camelToSnake(name).uppercase()}_SERVICES = listOf(${keys.joinToString(", ") { "\"$it\"" }})"
      }
    }

    result.forEach { println(it) }
  }

}

fun main() {
  ModuleUtils.generatePropertiesLists()
}