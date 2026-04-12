package cardenergy.cards;

import basemod.BaseMod;
import cardenergy.CardEnergyMod;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.red.*;

public final class RedMirrorCards {
    private RedMirrorCards() {
    }

    public static void registerAll() {
        BaseMod.addCard(new BarricadeCopy());
        BaseMod.addCard(new BashCopy());
        BaseMod.addCard(new BattleTranceCopy());
        BaseMod.addCard(new BerserkCopy());
        BaseMod.addCard(new BloodForBloodCopy());
        BaseMod.addCard(new BloodlettingCopy());
        BaseMod.addCard(new BludgeonCopy());
        BaseMod.addCard(new BrutalityCopy());
        BaseMod.addCard(new BurningPactCopy());
        BaseMod.addCard(new CarnageCopy());
        BaseMod.addCard(new CombustCopy());
        BaseMod.addCard(new CorruptionCopy());
        BaseMod.addCard(new DarkEmbraceCopy());
        BaseMod.addCard(new DemonFormCopy());
        BaseMod.addCard(new DisarmCopy());
        BaseMod.addCard(new DoubleTapCopy());
        BaseMod.addCard(new DropkickCopy());
        BaseMod.addCard(new DualWieldCopy());
        BaseMod.addCard(new EntrenchCopy());
        BaseMod.addCard(new EvolveCopy());
        BaseMod.addCard(new ExhumeCopy());
        BaseMod.addCard(new FeedCopy());
        BaseMod.addCard(new FeelNoPainCopy());
        BaseMod.addCard(new FiendFireCopy());
        BaseMod.addCard(new FireBreathingCopy());
        BaseMod.addCard(new FlameBarrierCopy());
        BaseMod.addCard(new HemokinesisCopy());
        BaseMod.addCard(new ImmolateCopy());
        BaseMod.addCard(new ImperviousCopy());
        BaseMod.addCard(new InfernalBladeCopy());
        BaseMod.addCard(new InflameCopy());
        BaseMod.addCard(new IntimidateCopy());
        BaseMod.addCard(new JuggernautCopy());
        BaseMod.addCard(new LimitBreakCopy());
        BaseMod.addCard(new MetallicizeCopy());
        BaseMod.addCard(new OfferingCopy());
        BaseMod.addCard(new PowerThroughCopy());
        BaseMod.addCard(new PummelCopy());
        BaseMod.addCard(new RampageCopy());
        BaseMod.addCard(new ReaperCopy());
        BaseMod.addCard(new RecklessChargeCopy());
        BaseMod.addCard(new RuptureCopy());
        BaseMod.addCard(new SearingBlowCopy());
        BaseMod.addCard(new SecondWindCopy());
        BaseMod.addCard(new SeeingRedCopy());
        BaseMod.addCard(new SentinelCopy());
        BaseMod.addCard(new SeverSoulCopy());
        BaseMod.addCard(new ShockwaveCopy());
        BaseMod.addCard(new SpotWeaknessCopy());
        BaseMod.addCard(new UppercutCopy());
        BaseMod.addCard(new WhirlwindCopy());
    }

    public static final class AngerCopy extends Anger {
        public static final String ID = CardEnergyMod.makeID("Anger");
        public AngerCopy() { super(); TerracottaCardHelper.applyIdentityKeepBaseStrings(this, ID); }
        @Override public AbstractCard makeCopy() { return new AngerCopy(); }
    }

    public static final class ArmamentsCopy extends Armaments {
        public static final String ID = CardEnergyMod.makeID("Armaments");
        public ArmamentsCopy() { super(); TerracottaCardHelper.applyIdentityKeepBaseStrings(this, ID); }
        @Override public AbstractCard makeCopy() { return new ArmamentsCopy(); }
    }

    public static final class BarricadeCopy extends Barricade {
        public static final String ID = CardEnergyMod.makeID("Barricade");
        public BarricadeCopy() { super(); TerracottaCardHelper.applyIdentityKeepBaseStrings(this, ID); }
        @Override public AbstractCard makeCopy() { return new BarricadeCopy(); }
    }

    public static final class BashCopy extends Bash {
        public static final String ID = CardEnergyMod.makeID("Bash");
        public BashCopy() { super(); TerracottaCardHelper.applyIdentityKeepBaseStrings(this, ID); }
        @Override public AbstractCard makeCopy() { return new BashCopy(); }
    }

    public static final class BattleTranceCopy extends BattleTrance {
        public static final String ID = CardEnergyMod.makeID("BattleTrance");
        public BattleTranceCopy() { super(); TerracottaCardHelper.applyIdentityKeepBaseStrings(this, ID); }
        @Override public AbstractCard makeCopy() { return new BattleTranceCopy(); }
    }

    public static final class BerserkCopy extends Berserk {
        public static final String ID = CardEnergyMod.makeID("Berserk");
        public BerserkCopy() { super(); TerracottaCardHelper.applyIdentityKeepBaseStrings(this, ID); }
        @Override public AbstractCard makeCopy() { return new BerserkCopy(); }
    }

    public static final class BloodForBloodCopy extends BloodForBlood {
        public static final String ID = CardEnergyMod.makeID("BloodForBlood");
        public BloodForBloodCopy() { super(); TerracottaCardHelper.applyIdentityKeepBaseStrings(this, ID); }
        @Override public AbstractCard makeCopy() { return new BloodForBloodCopy(); }
    }

    public static final class BloodlettingCopy extends Bloodletting {
        public static final String ID = CardEnergyMod.makeID("Bloodletting");
        public BloodlettingCopy() { super(); TerracottaCardHelper.applyIdentityKeepBaseStrings(this, ID); }
        @Override public AbstractCard makeCopy() { return new BloodlettingCopy(); }
    }

    public static final class BludgeonCopy extends Bludgeon {
        public static final String ID = CardEnergyMod.makeID("Bludgeon");
        public BludgeonCopy() { super(); TerracottaCardHelper.applyIdentityKeepBaseStrings(this, ID); }
        @Override public AbstractCard makeCopy() { return new BludgeonCopy(); }
    }

    public static final class BodySlamCopy extends BodySlam {
        public static final String ID = CardEnergyMod.makeID("BodySlam");
        public BodySlamCopy() { super(); TerracottaCardHelper.applyIdentityKeepBaseStrings(this, ID); }
        @Override public AbstractCard makeCopy() { return new BodySlamCopy(); }
    }

    public static final class BrutalityCopy extends Brutality {
        public static final String ID = CardEnergyMod.makeID("Brutality");
        public BrutalityCopy() { super(); TerracottaCardHelper.applyIdentityKeepBaseStrings(this, ID); }
        @Override public AbstractCard makeCopy() { return new BrutalityCopy(); }
    }

    public static final class BurningPactCopy extends BurningPact {
        public static final String ID = CardEnergyMod.makeID("BurningPact");
        public BurningPactCopy() { super(); TerracottaCardHelper.applyIdentityKeepBaseStrings(this, ID); }
        @Override public AbstractCard makeCopy() { return new BurningPactCopy(); }
    }

    public static final class CarnageCopy extends Carnage {
        public static final String ID = CardEnergyMod.makeID("Carnage");
        public CarnageCopy() { super(); TerracottaCardHelper.applyIdentityKeepBaseStrings(this, ID); }
        @Override public AbstractCard makeCopy() { return new CarnageCopy(); }
    }

    public static final class ClashCopy extends Clash {
        public static final String ID = CardEnergyMod.makeID("Clash");
        public ClashCopy() { super(); TerracottaCardHelper.applyIdentityKeepBaseStrings(this, ID); }
        @Override public AbstractCard makeCopy() { return new ClashCopy(); }
    }

    public static final class CleaveCopy extends Cleave {
        public static final String ID = CardEnergyMod.makeID("Cleave");
        public CleaveCopy() { super(); TerracottaCardHelper.applyIdentityKeepBaseStrings(this, ID); }
        @Override public AbstractCard makeCopy() { return new CleaveCopy(); }
    }

    public static final class ClotheslineCopy extends Clothesline {
        public static final String ID = CardEnergyMod.makeID("Clothesline");
        public ClotheslineCopy() { super(); TerracottaCardHelper.applyIdentityKeepBaseStrings(this, ID); }
        @Override public AbstractCard makeCopy() { return new ClotheslineCopy(); }
    }

    public static final class CombustCopy extends Combust {
        public static final String ID = CardEnergyMod.makeID("Combust");
        public CombustCopy() { super(); TerracottaCardHelper.applyIdentityKeepBaseStrings(this, ID); }
        @Override public AbstractCard makeCopy() { return new CombustCopy(); }
    }

    public static final class CorruptionCopy extends Corruption {
        public static final String ID = CardEnergyMod.makeID("Corruption");
        public CorruptionCopy() { super(); TerracottaCardHelper.applyIdentityKeepBaseStrings(this, ID); }
        @Override public AbstractCard makeCopy() { return new CorruptionCopy(); }
    }

    public static final class DarkEmbraceCopy extends DarkEmbrace {
        public static final String ID = CardEnergyMod.makeID("DarkEmbrace");
        public DarkEmbraceCopy() { super(); TerracottaCardHelper.applyIdentityKeepBaseStrings(this, ID); }
        @Override public AbstractCard makeCopy() { return new DarkEmbraceCopy(); }
    }

    public static final class DemonFormCopy extends DemonForm {
        public static final String ID = CardEnergyMod.makeID("DemonForm");
        public DemonFormCopy() { super(); TerracottaCardHelper.applyIdentityKeepBaseStrings(this, ID); }
        @Override public AbstractCard makeCopy() { return new DemonFormCopy(); }
    }

    public static final class DisarmCopy extends Disarm {
        public static final String ID = CardEnergyMod.makeID("Disarm");
        public DisarmCopy() { super(); TerracottaCardHelper.applyIdentityKeepBaseStrings(this, ID); }
        @Override public AbstractCard makeCopy() { return new DisarmCopy(); }
    }

    public static final class DoubleTapCopy extends DoubleTap {
        public static final String ID = CardEnergyMod.makeID("DoubleTap");
        public DoubleTapCopy() { super(); TerracottaCardHelper.applyIdentityKeepBaseStrings(this, ID); }
        @Override public AbstractCard makeCopy() { return new DoubleTapCopy(); }
    }

    public static final class DropkickCopy extends Dropkick {
        public static final String ID = CardEnergyMod.makeID("Dropkick");
        public DropkickCopy() { super(); TerracottaCardHelper.applyIdentityKeepBaseStrings(this, ID); }
        @Override public AbstractCard makeCopy() { return new DropkickCopy(); }
    }

    public static final class DualWieldCopy extends DualWield {
        public static final String ID = CardEnergyMod.makeID("DualWield");
        public DualWieldCopy() { super(); TerracottaCardHelper.applyIdentityKeepBaseStrings(this, ID); }
        @Override public AbstractCard makeCopy() { return new DualWieldCopy(); }
    }

    public static final class EntrenchCopy extends Entrench {
        public static final String ID = CardEnergyMod.makeID("Entrench");
        public EntrenchCopy() { super(); TerracottaCardHelper.applyIdentityKeepBaseStrings(this, ID); }
        @Override public AbstractCard makeCopy() { return new EntrenchCopy(); }
    }

    public static final class EvolveCopy extends Evolve {
        public static final String ID = CardEnergyMod.makeID("Evolve");
        public EvolveCopy() { super(); TerracottaCardHelper.applyIdentityKeepBaseStrings(this, ID); }
        @Override public AbstractCard makeCopy() { return new EvolveCopy(); }
    }

    public static final class ExhumeCopy extends Exhume {
        public static final String ID = CardEnergyMod.makeID("Exhume");
        public ExhumeCopy() { super(); TerracottaCardHelper.applyIdentityKeepBaseStrings(this, ID); }
        @Override public AbstractCard makeCopy() { return new ExhumeCopy(); }
    }

    public static final class FeedCopy extends Feed {
        public static final String ID = CardEnergyMod.makeID("Feed");
        public FeedCopy() { super(); TerracottaCardHelper.applyIdentityKeepBaseStrings(this, ID); }
        @Override public AbstractCard makeCopy() { return new FeedCopy(); }
    }

    public static final class FeelNoPainCopy extends FeelNoPain {
        public static final String ID = CardEnergyMod.makeID("FeelNoPain");
        public FeelNoPainCopy() { super(); TerracottaCardHelper.applyIdentityKeepBaseStrings(this, ID); }
        @Override public AbstractCard makeCopy() { return new FeelNoPainCopy(); }
    }

    public static final class FiendFireCopy extends FiendFire {
        public static final String ID = CardEnergyMod.makeID("FiendFire");
        public FiendFireCopy() { super(); TerracottaCardHelper.applyIdentityKeepBaseStrings(this, ID); }
        @Override public AbstractCard makeCopy() { return new FiendFireCopy(); }
    }

    public static final class FireBreathingCopy extends FireBreathing {
        public static final String ID = CardEnergyMod.makeID("FireBreathing");
        public FireBreathingCopy() { super(); TerracottaCardHelper.applyIdentityKeepBaseStrings(this, ID); }
        @Override public AbstractCard makeCopy() { return new FireBreathingCopy(); }
    }

    public static final class FlameBarrierCopy extends FlameBarrier {
        public static final String ID = CardEnergyMod.makeID("FlameBarrier");
        public FlameBarrierCopy() { super(); TerracottaCardHelper.applyIdentityKeepBaseStrings(this, ID); }
        @Override public AbstractCard makeCopy() { return new FlameBarrierCopy(); }
    }

    public static final class FlexCopy extends Flex {
        public static final String ID = CardEnergyMod.makeID("Flex");
        public FlexCopy() { super(); TerracottaCardHelper.applyIdentityKeepBaseStrings(this, ID); }
        @Override public AbstractCard makeCopy() { return new FlexCopy(); }
    }

    public static final class GhostlyArmorCopy extends GhostlyArmor {
        public static final String ID = CardEnergyMod.makeID("GhostlyArmor");
        public GhostlyArmorCopy() { super(); TerracottaCardHelper.applyIdentityKeepBaseStrings(this, ID); }
        @Override public AbstractCard makeCopy() { return new GhostlyArmorCopy(); }
    }

    public static final class HavocCopy extends Havoc {
        public static final String ID = CardEnergyMod.makeID("Havoc");
        public HavocCopy() { super(); TerracottaCardHelper.applyIdentityKeepBaseStrings(this, ID); }
        @Override public AbstractCard makeCopy() { return new HavocCopy(); }
    }

    public static final class HeadbuttCopy extends Headbutt {
        public static final String ID = CardEnergyMod.makeID("Headbutt");
        public HeadbuttCopy() { super(); TerracottaCardHelper.applyIdentityKeepBaseStrings(this, ID); }
        @Override public AbstractCard makeCopy() { return new HeadbuttCopy(); }
    }

    public static final class HeavyBladeCopy extends HeavyBlade {
        public static final String ID = CardEnergyMod.makeID("HeavyBlade");
        public HeavyBladeCopy() { super(); TerracottaCardHelper.applyIdentityKeepBaseStrings(this, ID); }
        @Override public AbstractCard makeCopy() { return new HeavyBladeCopy(); }
    }

    public static final class HemokinesisCopy extends Hemokinesis {
        public static final String ID = CardEnergyMod.makeID("Hemokinesis");
        public HemokinesisCopy() { super(); TerracottaCardHelper.applyIdentityKeepBaseStrings(this, ID); }
        @Override public AbstractCard makeCopy() { return new HemokinesisCopy(); }
    }

    public static final class ImmolateCopy extends Immolate {
        public static final String ID = CardEnergyMod.makeID("Immolate");
        public ImmolateCopy() { super(); TerracottaCardHelper.applyIdentityKeepBaseStrings(this, ID); }
        @Override public AbstractCard makeCopy() { return new ImmolateCopy(); }
    }

    public static final class ImperviousCopy extends Impervious {
        public static final String ID = CardEnergyMod.makeID("Impervious");
        public ImperviousCopy() { super(); TerracottaCardHelper.applyIdentityKeepBaseStrings(this, ID); }
        @Override public AbstractCard makeCopy() { return new ImperviousCopy(); }
    }

    public static final class InfernalBladeCopy extends InfernalBlade {
        public static final String ID = CardEnergyMod.makeID("InfernalBlade");
        public InfernalBladeCopy() { super(); TerracottaCardHelper.applyIdentityKeepBaseStrings(this, ID); }
        @Override public AbstractCard makeCopy() { return new InfernalBladeCopy(); }
    }

    public static final class InflameCopy extends Inflame {
        public static final String ID = CardEnergyMod.makeID("Inflame");
        public InflameCopy() { super(); TerracottaCardHelper.applyIdentityKeepBaseStrings(this, ID); }
        @Override public AbstractCard makeCopy() { return new InflameCopy(); }
    }

    public static final class IntimidateCopy extends Intimidate {
        public static final String ID = CardEnergyMod.makeID("Intimidate");
        public IntimidateCopy() { super(); TerracottaCardHelper.applyIdentityKeepBaseStrings(this, ID); }
        @Override public AbstractCard makeCopy() { return new IntimidateCopy(); }
    }

    public static final class IronWaveCopy extends IronWave {
        public static final String ID = CardEnergyMod.makeID("IronWave");
        public IronWaveCopy() { super(); TerracottaCardHelper.applyIdentityKeepBaseStrings(this, ID); }
        @Override public AbstractCard makeCopy() { return new IronWaveCopy(); }
    }

    public static final class JuggernautCopy extends Juggernaut {
        public static final String ID = CardEnergyMod.makeID("Juggernaut");
        public JuggernautCopy() { super(); TerracottaCardHelper.applyIdentityKeepBaseStrings(this, ID); }
        @Override public AbstractCard makeCopy() { return new JuggernautCopy(); }
    }

    public static final class LimitBreakCopy extends LimitBreak {
        public static final String ID = CardEnergyMod.makeID("LimitBreak");
        public LimitBreakCopy() { super(); TerracottaCardHelper.applyIdentityKeepBaseStrings(this, ID); }
        @Override public AbstractCard makeCopy() { return new LimitBreakCopy(); }
    }

    public static final class MetallicizeCopy extends Metallicize {
        public static final String ID = CardEnergyMod.makeID("Metallicize");
        public MetallicizeCopy() { super(); TerracottaCardHelper.applyIdentityKeepBaseStrings(this, ID); }
        @Override public AbstractCard makeCopy() { return new MetallicizeCopy(); }
    }

    public static final class OfferingCopy extends Offering {
        public static final String ID = CardEnergyMod.makeID("Offering");
        public OfferingCopy() { super(); TerracottaCardHelper.applyIdentityKeepBaseStrings(this, ID); }
        @Override public AbstractCard makeCopy() { return new OfferingCopy(); }
    }

    public static final class PerfectedStrikeCopy extends PerfectedStrike {
        public static final String ID = CardEnergyMod.makeID("PerfectedStrike");
        public PerfectedStrikeCopy() { super(); TerracottaCardHelper.applyIdentityKeepBaseStrings(this, ID); }
        @Override public AbstractCard makeCopy() { return new PerfectedStrikeCopy(); }
    }

    public static final class PommelStrikeCopy extends PommelStrike {
        public static final String ID = CardEnergyMod.makeID("PommelStrike");
        public PommelStrikeCopy() { super(); TerracottaCardHelper.applyIdentityKeepBaseStrings(this, ID); }
        @Override public AbstractCard makeCopy() { return new PommelStrikeCopy(); }
    }

    public static final class PowerThroughCopy extends PowerThrough {
        public static final String ID = CardEnergyMod.makeID("PowerThrough");
        public PowerThroughCopy() { super(); TerracottaCardHelper.applyIdentityKeepBaseStrings(this, ID); }
        @Override public AbstractCard makeCopy() { return new PowerThroughCopy(); }
    }

    public static final class PummelCopy extends Pummel {
        public static final String ID = CardEnergyMod.makeID("Pummel");
        public PummelCopy() { super(); TerracottaCardHelper.applyIdentityKeepBaseStrings(this, ID); }
        @Override public AbstractCard makeCopy() { return new PummelCopy(); }
    }

    public static final class RageCopy extends Rage {
        public static final String ID = CardEnergyMod.makeID("Rage");
        public RageCopy() { super(); TerracottaCardHelper.applyIdentityKeepBaseStrings(this, ID); }
        @Override public AbstractCard makeCopy() { return new RageCopy(); }
    }

    public static final class RampageCopy extends Rampage {
        public static final String ID = CardEnergyMod.makeID("Rampage");
        public RampageCopy() { super(); TerracottaCardHelper.applyIdentityKeepBaseStrings(this, ID); }
        @Override public AbstractCard makeCopy() { return new RampageCopy(); }
    }

    public static final class ReaperCopy extends Reaper {
        public static final String ID = CardEnergyMod.makeID("Reaper");
        public ReaperCopy() { super(); TerracottaCardHelper.applyIdentityKeepBaseStrings(this, ID); }
        @Override public AbstractCard makeCopy() { return new ReaperCopy(); }
    }

    public static final class RecklessChargeCopy extends RecklessCharge {
        public static final String ID = CardEnergyMod.makeID("RecklessCharge");
        public RecklessChargeCopy() { super(); TerracottaCardHelper.applyIdentityKeepBaseStrings(this, ID); }
        @Override public AbstractCard makeCopy() { return new RecklessChargeCopy(); }
    }

    public static final class RuptureCopy extends Rupture {
        public static final String ID = CardEnergyMod.makeID("Rupture");
        public RuptureCopy() { super(); TerracottaCardHelper.applyIdentityKeepBaseStrings(this, ID); }
        @Override public AbstractCard makeCopy() { return new RuptureCopy(); }
    }

    public static final class SearingBlowCopy extends SearingBlow {
        public static final String ID = CardEnergyMod.makeID("SearingBlow");
        public SearingBlowCopy() { super(); TerracottaCardHelper.applyIdentityKeepBaseStrings(this, ID); }
        @Override public AbstractCard makeCopy() { return new SearingBlowCopy(); }
    }

    public static final class SecondWindCopy extends SecondWind {
        public static final String ID = CardEnergyMod.makeID("SecondWind");
        public SecondWindCopy() { super(); TerracottaCardHelper.applyIdentityKeepBaseStrings(this, ID); }
        @Override public AbstractCard makeCopy() { return new SecondWindCopy(); }
    }

    public static final class SeeingRedCopy extends SeeingRed {
        public static final String ID = CardEnergyMod.makeID("SeeingRed");
        public SeeingRedCopy() { super(); TerracottaCardHelper.applyIdentityKeepBaseStrings(this, ID); }
        @Override public AbstractCard makeCopy() { return new SeeingRedCopy(); }
    }

    public static final class SentinelCopy extends Sentinel {
        public static final String ID = CardEnergyMod.makeID("Sentinel");
        public SentinelCopy() { super(); TerracottaCardHelper.applyIdentityKeepBaseStrings(this, ID); }
        @Override public AbstractCard makeCopy() { return new SentinelCopy(); }
    }

    public static final class SeverSoulCopy extends SeverSoul {
        public static final String ID = CardEnergyMod.makeID("SeverSoul");
        public SeverSoulCopy() { super(); TerracottaCardHelper.applyIdentityKeepBaseStrings(this, ID); }
        @Override public AbstractCard makeCopy() { return new SeverSoulCopy(); }
    }

    public static final class ShockwaveCopy extends Shockwave {
        public static final String ID = CardEnergyMod.makeID("Shockwave");
        public ShockwaveCopy() { super(); TerracottaCardHelper.applyIdentityKeepBaseStrings(this, ID); }
        @Override public AbstractCard makeCopy() { return new ShockwaveCopy(); }
    }

    public static final class ShrugItOffCopy extends ShrugItOff {
        public static final String ID = CardEnergyMod.makeID("ShrugItOff");
        public ShrugItOffCopy() { super(); TerracottaCardHelper.applyIdentityKeepBaseStrings(this, ID); }
        @Override public AbstractCard makeCopy() { return new ShrugItOffCopy(); }
    }

    public static final class SpotWeaknessCopy extends SpotWeakness {
        public static final String ID = CardEnergyMod.makeID("SpotWeakness");
        public SpotWeaknessCopy() { super(); TerracottaCardHelper.applyIdentityKeepBaseStrings(this, ID); }
        @Override public AbstractCard makeCopy() { return new SpotWeaknessCopy(); }
    }

    public static final class SwordBoomerangCopy extends SwordBoomerang {
        public static final String ID = CardEnergyMod.makeID("SwordBoomerang");
        public SwordBoomerangCopy() { super(); TerracottaCardHelper.applyIdentityKeepBaseStrings(this, ID); }
        @Override public AbstractCard makeCopy() { return new SwordBoomerangCopy(); }
    }

    public static final class ThunderClapCopy extends ThunderClap {
        public static final String ID = CardEnergyMod.makeID("ThunderClap");
        public ThunderClapCopy() { super(); TerracottaCardHelper.applyIdentityKeepBaseStrings(this, ID); }
        @Override public AbstractCard makeCopy() { return new ThunderClapCopy(); }
    }

    public static final class TrueGritCopy extends TrueGrit {
        public static final String ID = CardEnergyMod.makeID("TrueGrit");
        public TrueGritCopy() { super(); TerracottaCardHelper.applyIdentityKeepBaseStrings(this, ID); }
        @Override public AbstractCard makeCopy() { return new TrueGritCopy(); }
    }

    public static final class TwinStrikeCopy extends TwinStrike {
        public static final String ID = CardEnergyMod.makeID("TwinStrike");
        public TwinStrikeCopy() { super(); TerracottaCardHelper.applyIdentityKeepBaseStrings(this, ID); }
        @Override public AbstractCard makeCopy() { return new TwinStrikeCopy(); }
    }

    public static final class UppercutCopy extends Uppercut {
        public static final String ID = CardEnergyMod.makeID("Uppercut");
        public UppercutCopy() { super(); TerracottaCardHelper.applyIdentityKeepBaseStrings(this, ID); }
        @Override public AbstractCard makeCopy() { return new UppercutCopy(); }
    }

    public static final class WarcryCopy extends Warcry {
        public static final String ID = CardEnergyMod.makeID("Warcry");
        public WarcryCopy() { super(); TerracottaCardHelper.applyIdentityKeepBaseStrings(this, ID); }
        @Override public AbstractCard makeCopy() { return new WarcryCopy(); }
    }

    public static final class WhirlwindCopy extends Whirlwind {
        public static final String ID = CardEnergyMod.makeID("Whirlwind");
        public WhirlwindCopy() { super(); TerracottaCardHelper.applyIdentityKeepBaseStrings(this, ID); }
        @Override public AbstractCard makeCopy() { return new WhirlwindCopy(); }
    }

    public static final class WildStrikeCopy extends WildStrike {
        public static final String ID = CardEnergyMod.makeID("WildStrike");
        public WildStrikeCopy() { super(); TerracottaCardHelper.applyIdentityKeepBaseStrings(this, ID); }
        @Override public AbstractCard makeCopy() { return new WildStrikeCopy(); }
    }
}

