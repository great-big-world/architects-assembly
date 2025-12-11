package dev.creoii.greatbigworld.architectsassembly.client.screen;

import dev.creoii.greatbigworld.GreatBigWorld;
import dev.creoii.greatbigworld.architectsassembly.recipe.SawmillingRecipe;
import dev.creoii.greatbigworld.architectsassembly.registry.ArchitectsAssemblySoundEvents;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;
import net.minecraft.util.context.ContextMap;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.crafting.SelectableRecipe;
import net.minecraft.world.item.crafting.display.SlotDisplay;
import net.minecraft.world.item.crafting.display.SlotDisplayContext;

@Environment(EnvType.CLIENT)
public class SawmillScreen extends AbstractContainerScreen<SawmillScreenHandler> {
    private static final Identifier TEXTURE = Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "textures/gui/container/sawmill.png");
    private float scrollAmount;
    private boolean mouseClicked;
    private int scrollOffset;
    private boolean canCraft;

    public SawmillScreen(SawmillScreenHandler handler, Inventory inventory, Component title) {
        super(handler, inventory, title);
        handler.setContentsChangedListener(this::onInventoryChange);
        --titleLabelY;
    }

    @Override
    public void render(GuiGraphics context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        this.renderTooltip(context, mouseX, mouseY);
    }

    @Override
    protected void renderBg(GuiGraphics context, float delta, int mouseX, int mouseY) {
        int i = this.leftPos;
        int j = this.topPos;
        context.blit(RenderPipelines.GUI_TEXTURED, TEXTURE, i, j, 0, 0, this.imageWidth, this.imageHeight, 256, 256);
        int k = (int)(41.0f * this.scrollAmount);
        context.blit(RenderPipelines.GUI_TEXTURED, TEXTURE, i + 119, j + 15 + k, 176 + (this.shouldScroll() ? 0 : 12), 0, 12, 15, 256, 256);
        int l = this.leftPos + 52;
        int m = this.topPos + 14;
        int n = this.scrollOffset + 12;
        renderRecipeBackground(context, mouseX, mouseY, l, m, n);
        renderRecipeIcons(context, l, m, n);
    }

    @Override
    protected void renderTooltip(GuiGraphics context, int x, int y) {
        super.renderTooltip(context, x, y);
        if (this.canCraft) {
            int i = this.leftPos + 52;
            int j = this.topPos + 14;
            int k = this.scrollOffset + 12;
            SelectableRecipe.SingleInputSet<SawmillingRecipe> grouping = this.menu.getAvailableRecipes();

            for(int l = this.scrollOffset; l < k && l < grouping.size(); ++l) {
                int m = l - this.scrollOffset;
                int n = i + m % 4 * 16;
                int o = j + m / 4 * 18 + 2;
                if (x >= n && x < n + 16 && y >= o && y < o + 18) {
                    ContextMap contextParameterMap = SlotDisplayContext.fromLevel(this.minecraft.level);
                    SlotDisplay slotDisplay = ((SelectableRecipe.SingleInputEntry)grouping.entries().get(l)).recipe().optionDisplay();
                    context.setTooltipForNextFrame(this.font, slotDisplay.resolveForFirstStack(contextParameterMap), x, y);
                }
            }
        }
    }

    private void renderRecipeBackground(GuiGraphics context, int mouseX, int mouseY, int x, int y, int scrollOffset) {
        for (int i = this.scrollOffset; i < scrollOffset && i < menu.getAvailableRecipeCount(); ++i) {
            int j = i - this.scrollOffset;
            int k = x + j % 4 * 16;
            int l = j / 4;
            int m = y + l * 18 + 2;
            int n = this.imageHeight;
            if (i == menu.getSelectedRecipe()) {
                n += 18;
            } else if (mouseX >= k && mouseY >= m && mouseX < k + 16 && mouseY < m + 18) {
                n += 36;
            }
            context.blit(RenderPipelines.GUI_TEXTURED, TEXTURE, k, m - 1, 0, n, 16, 18, 256, 256);
        }
    }

    private void renderRecipeIcons(GuiGraphics context, int x, int y, int scrollOffset) {
        SelectableRecipe.SingleInputSet<SawmillingRecipe> grouping = this.menu.getAvailableRecipes();
        ContextMap contextParameterMap = SlotDisplayContext.fromLevel(this.minecraft.level);

        for(int i = this.scrollOffset; i < scrollOffset && i < grouping.size(); ++i) {
            int j = i - this.scrollOffset;
            int k = x + j % 4 * 16;
            int l = j / 4;
            int m = y + l * 18 + 2;
            SlotDisplay slotDisplay = grouping.entries().get(i).recipe().optionDisplay();
            context.renderItem(slotDisplay.resolveForFirstStack(contextParameterMap), k, m);
        }
    }

    @Override
    public boolean mouseClicked(MouseButtonEvent click, boolean doubled) {
        this.mouseClicked = false;
        if (this.canCraft) {
            int i = this.leftPos + 52;
            int j = this.topPos + 14;
            int k = this.scrollOffset + 12;

            for(int l = this.scrollOffset; l < k; ++l) {
                int m = l - this.scrollOffset;
                double d = click.x() - (double)(i + m % 4 * 16);
                double e = click.y() - (double)(j + m / 4 * 18);
                if (d >= (double)0.0F && e >= (double)0.0F && d < (double)16.0F && e < (double)18.0F && menu.clickMenuButton(minecraft.player, l)) {
                    Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(ArchitectsAssemblySoundEvents.UI_SAWMILL_SELECT_RECIPE, 1.0F));
                    this.minecraft.gameMode.handleInventoryButtonClick(menu.containerId, l);
                    return true;
                }
            }

            i = this.leftPos + 119;
            j = this.topPos + 9;
            if (click.x() >= (double)i && click.x() < (double)(i + 12) && click.y() >= (double)j && click.y() < (double)(j + 54)) {
                this.mouseClicked = true;
            }
        }

        return super.mouseClicked(click, doubled);
    }

    public boolean mouseDragged(MouseButtonEvent click, double offsetX, double offsetY) {
        if (this.mouseClicked && this.shouldScroll()) {
            int i = this.topPos + 14;
            int j = i + 54;
            this.scrollAmount = ((float)click.y() - (float)i - 7.5F) / ((float)(j - i) - 15.0F);
            this.scrollAmount = Mth.clamp(this.scrollAmount, 0.0F, 1.0F);
            this.scrollOffset = (int)((double)(this.scrollAmount * (float)this.getMaxScroll()) + (double)0.5F) * 4;
            return true;
        } else {
            return super.mouseDragged(click, offsetX, offsetY);
        }
    }

    public boolean mouseScrolled(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
        if (super.mouseScrolled(mouseX, mouseY, horizontalAmount, verticalAmount)) {
            return true;
        } else {
            if (this.shouldScroll()) {
                int i = this.getMaxScroll();
                float f = (float)verticalAmount / (float)i;
                this.scrollAmount = Mth.clamp(this.scrollAmount - f, 0.0F, 1.0F);
                this.scrollOffset = (int)((double)(this.scrollAmount * (float)i) + (double)0.5F) * 4;
            }

            return true;
        }
    }

    private boolean shouldScroll() {
        return this.canCraft && menu.getAvailableRecipeCount() > 12;
    }

    protected int getMaxScroll() {
        return (menu.getAvailableRecipeCount() + 4 - 1) / 4 - 3;
    }

    private void onInventoryChange() {
        this.canCraft = menu.canCraft();
        if (!this.canCraft) {
            this.scrollAmount = 0.0f;
            this.scrollOffset = 0;
        }
    }
}