package cardenergy.character;

import basemod.abstracts.CustomPlayer;
import basemod.animations.SpineAnimation;
import cardenergy.CardEnergyMod;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.relics.RunicPyramid;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import com.megacrit.cardcrawl.unlock.UnlockTracker;

import java.util.ArrayList;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class CardEnergyCharacter extends CustomPlayer {
    private static Texture customModeButtonTexture;

    public CardEnergyCharacter(String name) {
        super(
                name,
                CardEnergyCharacterEnum.HAND_FUEL_INDIGO,
                null,
                null,
                new SpineAnimation(
                        "images/characters/ironclad/idle/skeleton.atlas",
                        "images/characters/ironclad/idle/skeleton.json",
                        1.0F
                )
        );

        this.dialogX = this.drawX;
        this.dialogY = this.drawY + 220.0F * Settings.scale;

        initializeClass(
                null,
                "images/characters/ironclad/shoulder2.png",
                "images/characters/ironclad/shoulder.png",
                "images/characters/ironclad/corpse.png",
                getLoadout(),
                20.0F,
                -10.0F,
                220.0F,
                290.0F,
                new EnergyManager(2)
        );

        AnimationState.TrackEntry entry = this.state.setAnimation(0, "Idle", true);
        stateData.setMix("Hit", "Idle", 0.1f);
        entry.setTimeScale(0.6f);
    }

    @Override
    public ArrayList<String> getStartingDeck() {
        ArrayList<String> deck = new ArrayList<>();
        deck.add(CardEnergyMod.makeID("ScroungeStrike"));
        deck.add(CardEnergyMod.makeID("ScroungeStrike"));
        deck.add(CardEnergyMod.makeID("ScroungeStrike"));
        deck.add(CardEnergyMod.makeID("ScroungeStrike"));
        deck.add(CardEnergyMod.makeID("ScroungeDefend"));
        deck.add(CardEnergyMod.makeID("ScroungeDefend"));
        deck.add(CardEnergyMod.makeID("ScroungeDefend"));
        deck.add(CardEnergyMod.makeID("ScroungeDefend"));
        deck.add(CardEnergyMod.makeID("Recovery"));
        deck.add(CardEnergyMod.makeID("RottingBlow"));
        deck.add(CardEnergyMod.makeID("RottingShelter"));
        deck.add(CardEnergyMod.makeID("Stockpile"));
        deck.add(CardEnergyMod.makeID("ScrapSpray"));
        deck.add(CardEnergyMod.makeID("ScrapSpray"));
        return deck;
    }

    @Override
    public AbstractCard getStartCardForEvent() {
        return new cardenergy.cards.starter.ScroungeStrike();
    }

    @Override
    public String getPortraitImageName() {
        return CardEnergyMod.PORTRAIT_IMAGE;
    }

    @Override
    public Color getCardTrailColor() {
        return IndigoColorScheme.INDIGO.cpy();
    }

    @Override
    public int getAscensionMaxHPLoss() {
        return 5;
    }

    @Override
    public BitmapFont getEnergyNumFont() {
        return FontHelper.energyNumFontRed;
    }

    @Override
    public void doCharSelectScreenSelectEffect() {
        CardCrawlGame.sound.playA("ATTACK_HEAVY", MathUtils.random(-0.2f, 0.2f));
        CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.MED, ScreenShake.ShakeDur.SHORT, true);
    }

    @Override
    public String getCustomModeCharacterButtonSoundKey() {
        return "ATTACK_HEAVY";
    }

    @Override
    public Texture getCustomModeCharacterButtonImage() {
        if (customModeButtonTexture == null) {
            customModeButtonTexture = loadTextureFromJar(CardEnergyMod.BUTTON_IMAGE);
        }
        return customModeButtonTexture;
    }

    @Override
    public String getLocalizedCharacterName() {
        return "The Scrounger";
    }

    @Override
    public AbstractPlayer newInstance() {
        return new CardEnergyCharacter(name);
    }

    @Override
    public TextureAtlas.AtlasRegion getOrb() {
        return AbstractCard.orb_red;
    }

    @Override
    public String getSpireHeartText() {
        return "NL You tighten your grip on a fistful of scavenged tools.";
    }

    @Override
    public Color getSlashAttackColor() {
        return IndigoColorScheme.INDIGO.cpy();
    }

    @Override
    public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect() {
        return new AbstractGameAction.AttackEffect[] {
                AbstractGameAction.AttackEffect.SLASH_HEAVY,
                AbstractGameAction.AttackEffect.SLASH_DIAGONAL,
                AbstractGameAction.AttackEffect.SLASH_HORIZONTAL
        };
    }

    @Override
    public String getVampireText() {
        return "NL You offer up scraps instead of blood.";
    }

    @Override
    public ArrayList<String> getStartingRelics() {
        ArrayList<String> relics = new ArrayList<>();
        relics.add(RunicPyramid.ID);
        UnlockTracker.markRelicAsSeen(RunicPyramid.ID);
        return relics;
    }

    @Override
    public CharSelectInfo getLoadout() {
        return new CharSelectInfo(
                "The Scrounger",
                "An indigo scavenger who spends cards in hand as fuel.",
                80,
                80,
                0,
                99,
                5,
                this,
                getStartingRelics(),
                getStartingDeck(),
                false
        );
    }

    @Override
    public String getTitle(PlayerClass playerClass) {
        return "the Scrounger";
    }

    @Override
    public AbstractCard.CardColor getCardColor() {
        return CardEnergyCharacterEnum.INDIGO;
    }

    @Override
    public Color getCardRenderColor() {
        return IndigoColorScheme.INDIGO.cpy();
    }

    private static Texture loadTextureFromJar(String path) {
        if (CardEnergyCharacter.class.getClassLoader().getResourceAsStream(path) == null) {
            throw new RuntimeException("Missing resource in mod jar: " + path);
        }

        try (InputStream stream = CardEnergyCharacter.class.getClassLoader().getResourceAsStream(path)) {
            byte[] data = readAllBytes(stream);
            Pixmap pixmap = new Pixmap(data, 0, data.length);
            Texture texture = new Texture(pixmap);
            pixmap.dispose();
            return texture;
        } catch (IOException e) {
            throw new RuntimeException("Failed reading resource from mod jar: " + path, e);
        }
    }

    private static byte[] readAllBytes(InputStream stream) throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int read;
        while ((read = stream.read(buffer)) != -1) {
            output.write(buffer, 0, read);
        }
        return output.toByteArray();
    }
}
