/*
 * Copyright (c) bdew, 2013 - 2014
 * https://github.com/bdew/gendustry
 *
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license located in
 * https://raw.github.com/bdew/gendustry/master/MMPL-1.0.txt
 */

package net.bdew.gendustry.config

import java.io.File

import net.bdew.lib.gui.GuiHandler
import net.minecraftforge.common.config.Configuration

object Config {
  val guiHandler = new GuiHandler

  var neiAddSamples = false
  var powerShowUnits = "MJ"
  var powerShowMultiplier = 1F

  def load(cfg: File) {
    val c = new Configuration(cfg)
    c.load()

    try {
      neiAddSamples = c.get("NEI", "Add samples", true).getBoolean(false)
      powerShowUnits = c.get("Display", "PowerShowUnits", "MJ", "Units to use when displaying power. Valid values: MJ, EU, RF").getString
      if (powerShowUnits != "MJ") powerShowMultiplier = Tuning.getSection("Power").getFloat(powerShowUnits + "_MJ_Ratio")
      Fluids.load()
      Blocks.load()
      Items.load()
      Machines.load()
    } finally {
      c.save()
    }

  }
}