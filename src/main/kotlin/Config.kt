package org.example

import java.util.Properties

object Config {

  // load the properties from the resources/config.properties file
  private val properties: Properties = Properties().apply {
    load(Config::class.java.getResourceAsStream("/config.properties"))
  }

  private fun getProperty(key: String): String = properties.getProperty(key)

  val userDir by lazy { getProperty("user_dir") }
  val awsPath by lazy { getProperty("aws_path") }
  val useWsl by lazy { getProperty("use_wsl").toBoolean() }
  val awsRegion by lazy { getProperty("aws_path") }
  val shadowDir by lazy { getProperty("shadow_dir") }

  fun getAwsCommand(vararg cmd: String): List<String> {
    return if (useWsl) {
      listOf("wsl", awsPath) + cmd
    } else {
      listOf(awsPath) + cmd
    }.also {
      println(it.joinToString(" "))
    }
  }

  val outputDirPath = "data/output"

}