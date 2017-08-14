package twopiradians.minewatch.client.particle;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleSimpleAnimated;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import twopiradians.minewatch.common.Minewatch;

@SideOnly(Side.CLIENT)
public class ParticleTrail extends ParticleSimpleAnimated {

	public static final ResourceLocation TEXTURE = new ResourceLocation(Minewatch.MODID, "entity/particle/trail");
	private float fadeTargetRed;
	private float fadeTargetGreen;
	private float fadeTargetBlue;
	
	public ParticleTrail(World world, double x, double y, double z, int color, int colorFade, float scale, int maxAge) {
		super(world, x, y, z, 0, 0, 0);
		this.particleGravity = 0.01f;
		this.particleMaxAge = maxAge;
		this.particleScale = scale;
		this.setColor(color);
		this.setColorFade(colorFade);
		TextureAtlasSprite sprite = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(TEXTURE.toString());
		this.setParticleTexture(sprite); 	
	}
	
	@Override
    public void onUpdate() {
		super.onUpdate();
		
		// color fade (faster than vanilla)
        this.particleRed += (this.fadeTargetRed - this.particleRed) * 0.3F;
        this.particleGreen += (this.fadeTargetGreen - this.particleGreen) * 0.3F;
        this.particleBlue += (this.fadeTargetBlue - this.particleBlue) * 0.3F;
		
		this.particleAlpha = Math.max((float)(this.particleMaxAge - this.particleAge) / this.particleMaxAge, 0);
    }
	
	@Override
    public void setColorFade(int rgb) {
        this.fadeTargetRed = (float)((rgb & 16711680) >> 16) / 255.0F;
        this.fadeTargetGreen = (float)((rgb & 65280) >> 8) / 255.0F;
        this.fadeTargetBlue = (float)((rgb & 255) >> 0) / 255.0F;
    }

	@Override
	public int getFXLayer() {
		return 1;
	}

	@Override
	public void setParticleTextureIndex(int particleTextureIndex) {}
	
}
