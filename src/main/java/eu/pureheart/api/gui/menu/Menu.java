package eu.pureheart.api.gui.menu;

import eu.pureheart.api.PureAPI;
import eu.pureheart.api.gui.CloseListener;
import eu.pureheart.api.gui.button.Button;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public abstract class Menu {

    protected final Inventory inventory;
    protected final HashMap<Integer, Button> slots;
    protected final Material fillMaterial;
    private boolean interactDisabled = true;
    protected int currentPage = 0;
    protected final int itemsPerPage;
    protected final int rowsPerPage;
    protected final ItemStack prevPageItem;
    protected final ItemStack nextPageItem;
    protected @NotNull BukkitTask autoUpdateTask;
    protected final boolean updateEnabled;
    protected CloseListener closeListener;

    public Menu(String title, int rows, Material fillMaterial, boolean updateEnabled) {
        this.inventory = Bukkit.createInventory(null, rows * 9, title);
        this.slots = new HashMap<>();
        this.fillMaterial = fillMaterial;
        this.rowsPerPage = rows;
        this.itemsPerPage = rows * 9;
        this.prevPageItem = new ItemStack(Material.ARROW);
        this.nextPageItem = new ItemStack(Material.ARROW);
        this.updateEnabled = updateEnabled;

        updatePage();

        if (updateEnabled) {
            startAutoUpdate();
        }
    }

    public void open(Player player) {
        Bukkit.getScheduler().runTask(PureAPI.getAPI(), () -> {
            inventory.clear();
            player.openInventory(inventory);
            disableInteract(true);

            fillInventory(null);
            disableInteract(false);
        });
    }

    private void fillInventory(List<Integer> customSlots) {
        List<Integer> slotsToFill = customSlots != null ? customSlots : getDefaultSlots();

        for (int slot : slotsToFill) {
            if (slots.containsKey(slot)) {
                inventory.setItem(slot, slots.get(slot).getItem());
            } else {
                inventory.setItem(slot, new ItemStack(fillMaterial));
            }
        }
    }

    private List<Integer> getDefaultSlots() {
        return IntStream.range(0, inventory.getSize())
                .boxed()
                .collect(Collectors.toList());
    }

    public Menu refreshItems() {
        Bukkit.getScheduler().runTask(PureAPI.getAPI(), () -> {
            inventory.clear();
            for (Map.Entry<Integer, Button> entry : slots.entrySet()) {
                inventory.setItem(entry.getKey(), entry.getValue().getItem());
            }
            for (HumanEntity player : inventory.getViewers()) {
                ((Player) player).updateInventory();
            }
        });
        return this;
    }

    public Menu refreshSlot(int slot) {
        Bukkit.getScheduler().runTask(PureAPI.getAPI(), () -> {
            if (!slots.containsKey(slot)) {
                inventory.clear(slot);
            } else {
                inventory.setItem(slot, slots.get(slot).getItem());
            }
            for (HumanEntity player : inventory.getViewers()) {
                ((Player) player).updateInventory();
            }
        });

        return this;
    }

    public int getSlotPosition(Button slot) {
        for (Map.Entry<Integer, Button> entry : slots.entrySet()) {
            if (entry.getValue().equals(slot)) {
                return entry.getKey();
            }
        }
        return -1;
    }

    public Menu setSlot(int position, Button slot) {
        slot.setPosition(position);
        slots.put(position, slot);
        inventory.setItem(position, slot.getItem());
        return this;
    }

    public Menu disableInteract(boolean disable) {
        interactDisabled = disable;
        return this;
    }

    public boolean isInteractDisabled() {
        return interactDisabled;
    }

    public Menu removeSlot(int position) {
        slots.remove(position);
        inventory.clear(position);
        return this;
    }

    private void updatePage() {
        Bukkit.getScheduler().runTask(PureAPI.getAPI(), () -> {
            inventory.clear();
            int start = currentPage * itemsPerPage;
            int end = Math.min(start + itemsPerPage, slots.size());

            for (int i = start; i < end; i++) {
                Button button = slots.get(i);
                if (button != null) {
                    inventory.setItem(i % itemsPerPage, button.getItem());
                }
            }

            if (currentPage > 0) {
                inventory.setItem(itemsPerPage - 9, prevPageItem);
            }
            if (end < slots.size()) {
                inventory.setItem(itemsPerPage - 1, nextPageItem);
            }

            for (HumanEntity player : inventory.getViewers()) {
                ((Player) player).updateInventory();
            }
        });
    }

    public boolean hasCloseListener() {
        return closeListener != null;
    }

    public CloseListener getCloseListener() {
        return closeListener;
    }

    public void setCloseListener(CloseListener closeListener) {
        this.closeListener = closeListener;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public HashMap<Integer, Button> getSlots() {
        return slots;
    }

    public Material getFillMaterial() {
        return fillMaterial;
    }

    public void setPage(int page) {
        this.currentPage = page;
        updatePage();
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public int getItemsPerPage() {
        return itemsPerPage;
    }

    public int getRowsPerPage() {
        return rowsPerPage;
    }

    public ItemStack getPrevPageItem() {
        return prevPageItem;
    }

    public ItemStack getNextPageItem() {
        return nextPageItem;
    }

    public @NotNull BukkitTask getAutoUpdateTask() {
        return autoUpdateTask;
    }

    public boolean isUpdateEnabled() {
        return updateEnabled;
    }

    public Button getSlot(int n) {
        return slots.get(n);
    }

    private void startAutoUpdate() {
        autoUpdateTask = new BukkitRunnable() {
            @Override
            public void run() {
                Bukkit.getScheduler().runTask(PureAPI.getAPI(), Menu.this::refreshItems);
            }
        }.runTaskTimer(PureAPI.getAPI(), 0L, 60L);
    }

    public void stopAutoUpdate() {
        if (autoUpdateTask == null) return;
        this.autoUpdateTask.cancel();
    }
}
