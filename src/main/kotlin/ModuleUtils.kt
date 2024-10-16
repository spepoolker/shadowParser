package org.example

import org.yaml.snakeyaml.Yaml
import java.io.File

object ModuleUtils {



}

fun main() {
  val yamlFile = File("data/all-modules.yml")
  val yaml = Yaml()
  val data: List<Map<String, Any>> = yaml.load(yamlFile.reader())
  val result = data.map { item ->
    val name = (item["name"] as String).replace("-", "_")
    item["properties"]?.let {
      val properties = (it as Map<*, *>).keys
      return@map "val ${camelToSnake(name).uppercase()}_SERVICES = listOf(${properties.joinToString(", ") { "\"$it\"" }})"
    }
  }

  // Print the result
  result.forEach { println(it) }
}