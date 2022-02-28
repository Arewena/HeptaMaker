package io.github.arewena



import io.github.monun.kommand.kommand
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.World
import org.bukkit.enchantments.Enchantment
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.plugin.java.JavaPlugin
typealias triple = Triple<Double, Double, Double>


class Main : JavaPlugin(), Listener {

    val blockList = mutableMapOf<triple, Material>(
        triple(-1.0, 1.0, 0.0) to Material.YELLOW_CONCRETE,
        triple(-2.0, 1.0, 0.0) to Material.YELLOW_CONCRETE,
        triple(-3.0, 1.0, 0.0) to Material.YELLOW_CONCRETE,
        triple(2.0, 1.0, 0.0) to Material.YELLOW_CONCRETE,
        triple(3.0, 1.0, 0.0) to Material.YELLOW_CONCRETE,
        triple(4.0, 1.0, 0.0) to Material.YELLOW_CONCRETE,

        triple(-1.0, 2.0, 0.0) to Material.YELLOW_CONCRETE,
        triple(-2.0, 2.0, 0.0) to Material.YELLOW_CONCRETE,
        triple(0.0, 2.0, 0.0) to Material.YELLOW_CONCRETE,
        triple(1.0, 2.0, 0.0) to Material.YELLOW_CONCRETE,
        triple(2.0, 2.0, 0.0) to Material.YELLOW_CONCRETE,
        triple(3.0, 2.0, 0.0) to Material.YELLOW_CONCRETE,

        triple(0.0, 3.0, 0.0) to Material.YELLOW_CONCRETE,
        triple(1.0, 3.0, 0.0) to Material.YELLOW_CONCRETE,
        triple( 2.0, 3.0, 0.0) to Material.YELLOW_CONCRETE,
        triple(-1.0, 3.0, 0.0) to Material.YELLOW_CONCRETE,

        triple(-2.0, 4.0, 0.0) to Material.YELLOW_CONCRETE,
        triple(-1.0, 4.0, 0.0) to Material.BLACK_CONCRETE,
        triple(0.0, 4.0, 0.0) to Material.YELLOW_CONCRETE,
        triple(1.0, 4.0, 0.0) to Material.YELLOW_CONCRETE,
        triple(2.0, 4.0, 0.0) to Material.BLACK_CONCRETE,
        triple(3.0, 4.0, 0.0) to Material.YELLOW_CONCRETE,

        triple(1.0, 5.0, 0.0) to Material.YELLOW_CONCRETE,
        triple(0.0, 5.0, 0.0) to Material.YELLOW_CONCRETE,
        triple(-1.0, 5.0, 0.0) to Material.YELLOW_CONCRETE,
        triple(-2.0, 5.0, 0.0) to Material.YELLOW_CONCRETE,
        triple(-3.0, 5.0, 0.0) to Material.YELLOW_CONCRETE,
        triple(2.0, 5.0, 0.0) to Material.YELLOW_CONCRETE,
        triple(3.0, 5.0, 0.0) to Material.YELLOW_CONCRETE,
        triple(4.0, 5.0, 0.0) to Material.YELLOW_CONCRETE,

        triple(3.0, 6.0, 0.0) to Material.BLACK_CONCRETE,
        triple(2.0, 6.0, 0.0) to Material.BLACK_CONCRETE,
        triple(1.0, 6.0, 0.0) to Material.YELLOW_CONCRETE,
        triple(0.0, 6.0, 0.0) to Material.BLACK_CONCRETE,
        triple(-1.0, 6.0, 0.0) to Material.BLACK_CONCRETE,

        triple(1.0, 7.0, 0.0) to Material.YELLOW_CONCRETE,
        triple(0.0, 7.0, 0.0) to Material.YELLOW_CONCRETE,

        triple(1.0, 8.0, 0.0) to Material.YELLOW_CONCRETE,







    )

    override fun onEnable() {
        this.server.pluginManager.registerEvents(this, this)
        kommand {
            register("wand") {
                executes {
                    player.inventory.addItem(ItemStack(Material.STICK).apply {
                        addUnsafeEnchantment(Enchantment.LOYALTY, 3)
                    })
                }
            }
        }
    }


    @EventHandler
    fun useWand(e: PlayerInteractEvent) {
        if (e.action == Action.RIGHT_CLICK_BLOCK && e.item?.type == Material.STICK && e.item?.getEnchantmentLevel(Enchantment.LOYALTY) == 3) {
            val x = e.clickedBlock!!.x
            val y = e.clickedBlock!!.y
            val z = e.clickedBlock!!.z

            blockList.forEach { (location, type) ->
                Location(e.player.world, location.first + e.clickedBlock!!.x, location.second + e.clickedBlock!!.y, location.third + e.clickedBlock!!.z).block.type = type
            }

        }
    }


}

