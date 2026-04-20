# Salvager Working Card Set Design Document

## Current Implementation Brief

Use [10_SALVAGER_IMPLEMENTATION_BRIEF.md](c:\Users\Corey\Documents\Projects\sls_handfuel_mod\10_SALVAGER_IMPLEMENTATION_BRIEF.md) as the current implementation brief.

Where this document conflicts with that brief on starter composition, common-pool targets, or immediate card implementation priorities, the brief wins.

## Document Role

This document should answer:

- what the class is trying to be
- what structural jobs the early pool must cover
- how `Consume`, `Rot`, `Hoard`, and `Junk` are meant to function in the set

It should not be the authoritative source for the exact current starter list or the exact current starter/common implementation queue. That belongs in the implementation brief.

When this document and the mechanics doc differ, assume:

- this file is expressing durable design intent
- the mechanics doc is reporting the current code state

## Current Implementation Note

The current codebase still uses a broad mirrored Ironclad pool as Terracotta's temporary baseline set, but the first pass of Salvager-specific starter and common cards, plus a partial first pass of Salvager-specific uncommons, now replaces part of that mirror scaffold.

That is not the final content direction. It is the implementation scaffold.

Current practical rule:

- mirrored red cards still provide most of the Terracotta reward pool
- custom Terracotta starter cards, first-pass custom commons, and the first landed uncommons now exercise the class grammar directly
- final Terracotta cards should be created by editing those mirrored red-derived cards into their new names, text, and mechanics over time

So the working plan is:

- do not preserve old one-off prototype cards as long-term content
- do not leave the pool undersized while commons are still being designed
- replace mirrored red cards gradually with real Terracotta cards as they are ready

This note is descriptive of the current repository scaffold, not a recommendation to keep that scaffold forever.

## 1. Core Identity

The class is now a Salvager: a resourceful drifter, junk sage, or alley alchemist carrying a basket, sack, or tagged bundle of found items. The visual specifics are not locked. The important part is the mechanical fantasy:

- cards in hand are stuff you are carrying
- you consume cards as fuel to act
- some cards get better if you hoard them
- some cards rot if you keep or discard them carelessly
- some cards are just junk, and some decks can exploit Junk density

Desired tone:

- resourceful rather than filthy
- practiced rather than feral
- resonant with thrift, restraint, patience, and conversion
- centered on making leverage and value from scraps rather than wallowing in grime

The strongest thematic bridge is:

- turning scraps into leverage
- making something useful from what would otherwise be discarded
- carrying, sorting, tagging, repairing, and converting limited resources

This is a better thematic fit than the earlier electrical or singularity framing and avoids overlapping too much with existing electric characters.

## 2. Core Mechanical Grammar

### Resource model

The class still uses the hand as the real resource:

- cards are paid for by discarding eligible cards from hand
- non-X plays pay an extra 1 Energy
- large draw and retention-like smoothing are part of the class identity
- the deck is meant to feel like it is carrying and consuming inventory

Working rule:

- available Energy equals the number of discardable character-color cards currently in hand
- paying Energy means discarding that many such cards
- non-X cards cost 1 additional Energy
- for X-cost cards, `X = ceil(Energy paid / 2)`

**Important design principle: there is no "fuel" as a detectable resource or trigger.**

The word "fuel" appears in docs and code as shorthand for "the cards discarded to satisfy a card's cost." Mechanically, the game does not distinguish a fuel-payment discard from any other discard. A card that says "Discard a card: do X" and a card that is paid for by discarding a card both resolve as an ordinary discard. This is deliberate:

- Cards and relics **must not** trigger off "when you pay fuel", "when a card is spent as fuel", or "when you lose Energy via fuel." Those aren't observable events.
- Cards and relics **may** trigger off the same unified events a vanilla character could: *when a card is discarded*, *when a card leaves your hand*, *when a card is played*, *when your hand is empty*, *when your hand size changes*. These cover the fuel-discard case automatically, without singling it out.
- Gaining Energy in card text resolves as draw. Losing Energy resolves as discard of a character-color card if any exist.
- The player experiences fuel as a consequence of playing cards, not as a tracked counter. Design cards and relics to match that experience.

Anywhere in this document that says "fuel" in a trigger-shaped way, read it as shorthand - the actual implementation is always the vanilla discard/draw/hand-size event, not a fuel-specific hook.

## 3. Keywords and Family Language

### Consume

Working rules text:

`Consume` - When this card would be discarded from your hand, play it automatically and Exhaust it instead.

Purpose:

- creates the play-versus-hold tension: if you discard a Consume card for any reason, it plays itself first, so keeping it unplayed in hand is a real decision
- is the main behavior keyword on the current starter package
- naturally feeds exhaust-matters cards

Notes:

- this is a behavior keyword, not a tracked synergy axis
- other cards should generally not look for "Consume triggered"
- the public bridge state is still cards exhausted this turn
- targeted `Consume` cards are allowed; when autoplayed they follow the game's normal autoplay targeting behavior

### Hoard

Working rules text:

`Hoard X` - At the end of your turn, if this card is in your hand, it gains +X damage and Block.

Purpose:

- delayed growth
- support vein rather than a full standalone lane
- especially strong on Junk-heavy decks
- the "shoot the moon" package

Notes:

- Hoard should be present at common, but concentrated more heavily at uncommon and rare
- most Hoard cards should be intrinsically useful even without full build-around support
- at least one Hoard card should turn retained-hand pressure into action by discarding multiple cards for payoff, so Hoard decks can cash in clogged hands instead of merely suffering them
- current direction: Hoard charge should reset when the card is played, not merely when it is discarded
- current direction: `Hoard` should imply staying in hand so it remains portable outside the base class context
- current direction: Hoard's stored bonus should stay mechanically separate from the card's authored base stats even though it feeds the displayed and played values

### Rot

Working rules text:

`Rot` - If this card would be discarded, or remains in your hand at end of turn, Exhaust it instead.

Purpose:

- creates anti-hoarding pressure
- is harsher and more class-native than Ethereal
- makes the carry, use, and burn tension much sharper

Notes:

- use sparingly
- best on above-rate and situational cards
- should create urgency without becoming omnipresent punishment

### Junk

Junk is the shared family or tag.

Purpose:

- the named family that matters together
- spans both major lanes
- especially loved by Hoard
- explicit Junk-matters cards should remain sparse

Current explicit density target:

- 1 Junk-matters common
- 2 Junk-matters uncommons
- 1 Junk-matters rare

Junk should mostly matter through:

- cards in hand
- family density
- support pieces
- synergy with Hoard

Not through constant tribal text on everything.

## 4. Structural Deck Identity

The class has:

- a reactive defense / punishment package
- an exhaust-value package
- `Consume` as infrastructure supporting both
- `Rot` as tension living mostly inside exhaust-value rather than as a third full lane
- a Hoard vein that runs through the class as sparse optionality support
- Junk as the family crossing all of the above

This means the class should not feel like three separate archetypes stapled together. Instead:

- reactive cards can be Junk
- consume / exhaust cards can be Junk
- Hoard especially rewards Junk density
- the best Hoard decks are moonshot decks enabled by the right uncommons and rares
- most decks should touch `Consume`, but `Consume` itself should not be the main class endgame

## 5. Structural Template

The useful comparison to the vanilla characters is not "which exact cards match" but "which early-game structural jobs must exist."

Across Ironclad, Silent, Defect, and Watcher, the early pool consistently covers:

- a filler attack
- a filler block
- a signature tutorial card that teaches the class loop
- a signature payoff card that shows why that loop matters
- a smoother that fixes sequencing or consistency
- a hybrid card that partially covers two jobs at once

That is the template worth reusing.

The current repository tracks that template in [design/red_set_mapping.csv](c:\Users\Corey\Documents\Projects\sls_handfuel_mod\design\red_set_mapping.csv).

That CSV should be read as:

- a loose reference skeleton
- a map of which red slot we are replacing
- a way to compare the old slot's apparent job against the new Salvager card's intended job

It should not be read as a one-to-one obligation to preserve every original Ironclad role exactly.

Some replacements should be:

- `direct`, where the Salvager card is intentionally covering nearly the same structural job
- `analogous`, where the job is similar but translated into Salvager's own grammar
- `strategic_shift`, where the slot is being reused for a different but still structurally useful Salvager purpose

That is intentional. The skeleton is there to keep the set coherent while it evolves, not to prevent the set from becoming its own thing.

For the Salvager, the matching early roles should be:

- basic attack
- basic defense
- starter card that teaches the cost-by-discard resource model
- starter card that teaches discard as upside rather than pure tax
- common attack that rewards holding cards
- common defense or utility card that rewards discarding, consuming, or exhausting
- common smoother that fixes awkward hands
- hybrid card that solves the turn while also advancing the engine

The class thesis should be:

- your hand is both your wallet and your junk pile

That means the early set should include some deliberately plain cards. The trap is making every common shout the mechanic. Slay the Spire starter and common pools usually leave enough oatmeal in the bowl for the spicy cards to matter.

## 6. Starter Deck Scaffold

This section is design context for the old scaffold and the role map it exposed.

For the current starter implementation target, use [10_SALVAGER_IMPLEMENTATION_BRIEF.md](c:\Users\Corey\Documents\Projects\sls_handfuel_mod\10_SALVAGER_IMPLEMENTATION_BRIEF.md).

Starter size is currently justified at 14 cards, because the class effectively draws 7 per turn at baseline and would otherwise become too compressed too quickly.

### Starter basics: 8 cards

- 4 basic attacks
- 4 basic block skills

Current implementation direction:

- the 4 basic attacks are plain single-target attacks
- the 4 basic block skills are plain block cards

Reason:

- starter defends having `Consume` made the opening deck too forgiving
- targeted `Consume` cards are still viable because autoplayed cards already fall back to normal random targeting behavior when player choice is unavailable

Working baseline numbers:

- basic attack: 4 damage
- basic block: 5 block

### Starter non-basic scaffold

The older scaffold used 6 non-basic starter cards:

- `Recovery`
- `Rotting Blow`
- `Rotting Shelter`
- `Stockpile`
- `2x Scrap Spray`

Those cards demonstrated these roles:

- `Recovery`: recover up to 2 exhausted cards to discard
- `Rotting Blow`: simple above-rate rot attack
- `Rotting Shelter`: temporary defense with draw
- `Stockpile`: simple starter Hoard card
- `Scrap Spray`: all-enemy `Consume` attack that safely demonstrates delayed consume resolution

Mapped against the structural template, that scaffold covered:

- `Scrounge Strike`: filler attack
- `Scrounge Defend`: filler block
- `Scrap Spray`: starter mechanic tutorial for `Consume` / discard-as-upside
- `Recovery`: smoother that turns prior exhaust into future consistency
- `Rotting Blow` / `Rotting Shelter`: early discard/exhaust-payoff tension cards
- `Stockpile`: early hold-value card showing the Hoard vein

The useful retained lesson is not the exact old starter composition. The useful lesson is that future commons must cleanly extend these jobs:

- simple vanilla cards that just get played by discarding
- simple discard-as-upside cards
- simple Hoard cards
- a few hybrids that advance two of those jobs at once

## 7. Rarity and Pool Philosophy

Recommended long-term pool shape:

- 21 commons
- 32 to 34 uncommons
- 16 rares

Practical design rule:

- commons teach
- uncommons connect and signpost
- rares define

Current implementation scaffold:

- the mirrored Ironclad set temporarily supplies these rarity bands
- custom Terracotta cards should gradually replace those mirrored cards as real design work is completed

Recommended next-pass package from the handoff document:

- commons should get cleaner conversion glue and simpler infrastructure:
  - `Shoulder the Load`
  - `Sort the Haul`
  - `Scrap Toss`
  - `Shore Up`
  - `Pack Away`
  - cleaner `Patchwork`
- uncommons should carry the more volatile hand-reset and burst-conversion cards:
  - `Upend the Pack`
  - `Strip the Wreck`
  - `Empty the Pack`
  - `Scrapstorm`
  - `Breakdown Rush`
  - `Barbed Harness`
  - `Hurl the Heap` - promoted from common onto the `Bludgeon` mirrored slot. Occupies the section 10.2 "attack rider" vein as an exhaust-pile scaler. Rarity slip rare->uncommon is deliberate per the loose-guideline anchoring policy.
  - (parked) `Hidden Compartments` - draw-plus-discard power concept; pulled from the uncommon pool, may migrate to a relic slot (section 12), and the name is likely to change. See "Parked designs" below.
- rares should continue to define late-game inevitability through exhaust engines, bridge powers, and a few compact finishers:
  - `Close Appraisal`
  - `Shelter from Scraps`
  - `Jury-Rig Barrage`
  - `Emergency Refit`
  - `Precious Bauble`

Package guardrails:

- do not treat this as a sealed discard lane
- keep `Consume` at full value and balance it through density, awkwardness, and deck texture
- keep at least one low-cost common filter card whose discard effect can target off-color cards, so off-color clutter and temporary junk can still be converted into useful selection without spending the same color you are trying to clear
- keep Hoard sparse
- if two cards occupy the same structural job, prefer the cleaner Salvager-native version and absorb the other role into notes or upgrades
- late-game inevitability should still come mainly from exhaust payoffs, stable defense, and a few finishers rather than from `Consume` alone

## 8. Recommended Build Order

Build the set by rarity across both lanes, not one lane at a time.

Order:

1. commons across reactive, consume/exhaust, Junk, glue, and a little Hoard
2. uncommons across both lanes plus bridge cards
3. rares as engines, moonshots, and spectacle

That will keep the class coherent and prevent one lane from becoming overbuilt while another is still theoretical.

## 9. Short Glossary

Consume:
If it would be discarded from hand, it plays instead and is then exhausted.

Hoard:
Improves while held in hand.

Rot:
If it would be discarded, or remains in hand at end of turn, it exhausts instead.

Junk:
Shared family or tag that matters together, especially in Hoard shells.

## 10. Exhaust-Matters Power Design Space

The class already has trigger-on-exhaust engines designed at rare (`Close Appraisal` at the Dark Embrace slot, `Shelter from Scraps` at the Feel No Pain slot). Those cover the *"exhausting a card pays off"* question.

Three adjacent design veins are still open and distinctive to the class:

### 10.1 Recurring exhume (recover from exhaust without spending a card)

Vanilla has one-shot recovery (Exhume card, Medical Kit relic). No vanilla **power** recurs cards out of the exhaust pile. Treat this as a fresh design space the Salvager can own.

Open variables to resolve before locking a card:

- **How many per trigger** - one card, N cards, or variable?
- **Trigger shape** - start of turn, end of turn, on empty hand, on N-cards-exhausted, or random chance per exhaust?
- **Player control** - full choice, random pick, or filtered random (e.g., non-Attack only)?
- **Destination** - hand, discard pile, or draw pile? Draw pile is softest, hand is strongest, discard is middle ground (re-enters rotation but costs a draw to see again).

Starter sketches to stress-test:

- **Passive drip**: *At the start of your turn, return a random card from your exhaust pile to your discard pile.* (Power, likely rare.)
- **Reactive drip**: *Whenever your hand is empty, return a card from your exhaust pile to your hand. It costs 0 this combat.* (Power. Gates itself naturally in a hand-fuel deck.)
- **Conditional choice**: *At the end of each turn, if 3 or more cards were exhausted this turn, choose a card from your exhaust pile to put into your discard pile.* (Skill, uncommon - gives the effect on demand with a real activation cost.)

Likely rarity: one at rare (marquee), optionally a weaker version at uncommon.

### 10.2 Exhaust pile size matters (scaling payoff)

Vanilla has no player-side scaling power that reads *"cards in your exhaust pile"* as a stat. This is a naturally late-game axis and pairs well with the class's preference for exhaust density.

Starter sketches:

- **Offensive scaler**: *Gain 1 Strength for every 5 cards in your exhaust pile at the start of your turn.* (Power, rare.)
- **Defensive scaler**: *At the end of your turn, gain Block equal to the number of cards in your exhaust pile, up to X.* (Power, uncommon with a cap; rare uncapped.)
- **Attack rider**: *Attack. Deal X damage, plus 1 for each card in your exhaust pile.* (Attack card, uncommon or rare finisher - not a power. Worth noting because it occupies the same design space without needing a power slot.)

Watch-out: uncapped scaling off exhaust pile is uniquely dangerous in Salvager because the class can exhaust very fast. Cap numbers tighter than you would for Ironclad.

### 10.3 Trigger-on-exhaust alternatives (beyond draw and block)

Dark Embrace = draw on exhaust. Feel No Pain = block on exhaust. The empty design corners are *strength*, *hand smoothing*, *card generation*, and *debuff spreading*:

- **Strength on exhaust** - overlaps with Anger-style "strength when struck" if both exist. Pick one.
- **Discard-to-hand on exhaust** - *Whenever a card is exhausted, you may move one card from your discard pile to your hand.* Because hand size *is* energy for this class (section 2), adding cards to hand without a draw is a naturally strong effect - it inflates next turn's hand without cycling the deck. Very strong; probably rare. Note this triggers off the exhaust event, not off any discard or payment, so it stays inside the unified-event principle.
- **Card generation on exhaust** - *Whenever a card is exhausted, add a copy of it to your discard pile.* A soft auto-Exhume, bounded by the existing exhaust rate. Potentially rare.
- **Debuff spreader on exhaust** - *Whenever a card is exhausted, apply 1 Weak to a random enemy.* Cheap uncommon; distributes pressure without direct damage.

Prefer one of **Discard-to-hand on exhaust** or **Card generation on exhaust** as the marquee uncommon power in this vein - both feed the hand in ways that interact with the resource model without needing a fuel-specific trigger.


## 11. Junk and Scrap Status Cards

This section extends section 3's Junk family with a concrete **status-card** implementation. The tag from section 3 still applies - a card or relic can be tagged Junk without being the Junk status card - but the status cards below give the family a grounded, iconic representative.

### 11.1 Cards

**Junk** (Status, **colorless - not fuel-valid**)

- Cost: 0
- Type: Status
- Text: *Playable only if you have at least 2 other Junk or Scrap cards in your hand. Exhaust all Junk and Scrap in your hand. Add a random Uncommon Terracotta card to your hand. Exhaust.*
- **Not fuel**: because Junk is colorless (status), it cannot be spent to pay fuel for Salvager cards. Holding it in hand genuinely costs a hand slot.

**Scrap** (Status, **colorless - not fuel-valid**)

- Cost: 0
- Type: Status
- Text: *Playable only if you have at least 1 other Scrap card in your hand. Exhaust all Scrap in your hand. Add a random Rare Terracotta card to your hand. Exhaust.*
- The stricter trigger condition (**Scrap only**, not Junk+Scrap) is what justifies the rarer reward.

Because both are colorless status cards, they cannot pay fuel, cannot be targeted by Salvager-only affect-hand effects that require your color, and clog hand until either (a) the conversion threshold is met or (b) they are cleared by effects that target status cards.

### 11.2 Design intent

- Junk and Scrap are a **build-around tension**, not free value. They convert into powerful cards, but they also actively clog the hand while you wait for the threshold. A deck that takes Junk/Scrap sources is committing to draw volume, hand cycling, or status removal.
- The deck-builder's question becomes: *"do I see Junk often enough, and do I cycle my hand hard enough, to convert reliably before combat ends?"* That gates the build-around behind deck shape, not card density alone.
- Because conversion **exhausts the converted cards**, the clog is self-cleaning *if* you hit the threshold. Missed thresholds mean the Junk sits in hand or gets discarded at end of turn like any non-Retain status.
- The granted card enters **hand**, not draw pile, so a successful conversion is felt immediately on the turn it triggers - that is the payoff for eating the clog tax.
- This inverts the usual Salvager promise ("nothing in your hand is dead") deliberately: Junk/Scrap are the one deliberate exception, and that exception is what makes the build-around real.

### 11.3 Sources

Because Junk/Scrap cost you hand slots, *generation* is the build-around lever. Decks that skip generators should effectively never see these cards. Recommended density:

- **Self-inflicted generators** are the primary lever. 2 to 3 cards across uncommon/rare that produce Junk as part of their cost.
  - Sketch (uncommon): *"Draw 3 cards. Add a Junk to your discard pile."* - draw-plus-generator, pays for itself over 2 to 3 cycles.
  - Sketch (uncommon): *"Deal X damage. Add 2 Junks to your discard pile."* - aggressive cost, aggressive payoff.
  - Sketch (rare): *"Exhaust your hand. Add a Scrap to your discard pile for each card exhausted this way."* - high-commit moonshot.
- **Relic-based generators** - section 12 covers a Junk-per-combat starter relic and a Scrap-on-first-exhaust uncommon relic.
- **Enemy-applied Junk** is discouraged as the primary source. If enemies add Junk broadly, every deck becomes a Junk deck whether it wanted to be or not, collapsing the build-around. Keep enemy Junk rare, flavor-driven, and narrow in density (one or two monster variants at most).

### 11.4 Open design questions

- Should Junk convert **all Junk and Scrap**, or only **all Junk**? Current proposal is Junk+Scrap so Scrap can ride Junk's lower threshold as a cheaper out. Alternative: Junk only eats Junk, so players must make a real decision between cashing early for Uncommons vs. holding for Scrap's Rare.
- Should the granted card be **free this turn** or **cost normally**? Cost-normally is the lean - the clog was the cost, the random-rarity grant is the reward, and compounding a free-play on top over-centralizes conversion as the only thing worth doing on the trigger turn.
- Should Junk/Scrap be **Ethereal**? No. Ethereal would solve the clog for free (they'd just exhaust at end of turn) and kill the build-around tension. They must occupy hand slots until played or the hand is cycled through them.
- Should Junk/Scrap be **Retain-hostile** (e.g., auto-discard at end of turn)? Default end-of-turn behavior already discards them; no special handling needed. The "hold across turns" play pattern is: keep them in hand via low-draw turns, not via Retain.
- Does Junk **count as Junk family** for tag-matters cards (section 3)? Yes, explicitly. Scrap also counts. Keep the tag consistent so Hoard and Junk-matters cards key off both.
- Can the player **remove Junk/Scrap from the deck at rest sites**? They aren't in the deck - they're generated into discard/hand during combat - so rest-site removal doesn't apply. Good.

### 11.5 Anti-abuse and implementation guardrails

- Junk and Scrap are **not added to the master deck**. They are inserted into discard pile (or directly into hand) by their generator cards and clear on combat end like any status. This also means they bypass card-rewards/shops entirely.
- The **color** of the generated grant comes from the Salvager color pool at the appropriate rarity, *not* from `AbstractDungeon.srcUncommonCardPool` (which is shuffled per-floor and includes only cards already seen). Use a filtered snapshot of the Salvager pool at combat time.
- Exclude curses, statuses, and any card with a `Soul` or `reward`-style flag from the random-grant pool.
- Grants should not trigger card-played or on-draw effects that depend on the card entering hand from a draw. Inserting via `makeStatEquivalentCopy()` and `AbstractDungeon.player.hand.addToHand(...)` is the intended path; keep it plain.
- Because Junk and Scrap are **not Salvager-colored**, the fuel-payment code already excludes them: `HandFuelResourceAdapter.isFuelCard(...)` requires `card.color == player.getCardColor()`, and colorless status cards fail that check. No additional filter code is needed for the status cards themselves.

## 12. Character-Specific Relic Design Space

Relics are a **secondary pool** that must not duplicate card effects. Where there is precedent for overlap (e.g., Busted Crown vs. energy cards, Velvet Choker vs. card-draw limits), the overlap should be *framing-level*, not *effect-level*.

### 12.1 Starter relic

Every STS character has one starter relic. Some (Burning Blood, Cracked Core) are flavor/tempo pieces; some (Ring of the Snake, Pure Water) hard-couple with the class's mechanic. Either model is valid.

**Design constraint** (from section 2): the starter relic cannot trigger off "paying fuel" - that isn't a detectable event. It can trigger off draws, discards, plays, hand-empty, or start/end of turn. Anything that sounds like "when you pay fuel, do X" must be rewritten as one of those unified events, or dropped.

Additional constraint: because Junk and Scrap are **colorless clog** (section 11), forcing Junk into hand every combat via the starter would punish every deck whether the player opted into the build-around or not. Junk-forcing belongs on an opt-in relic (see section 12.4 boss swap), not the starter.

Candidates:

- **Draw boost (Ring of the Snake analog)**: *At the start of each combat, draw 2 additional cards.* Since hand = energy in this class, +2 draws directly translates to +2 starting energy. Teaches "hand size is what matters" by brute force. Safe, clean, no trigger gymnastics. **Recommended primary candidate.**
- **Teach Consume**: *At the start of each combat, a random card in your starting hand gains Consume for this combat.* Consume is a real distinct keyword (section 3) with observable behavior - it triggers on discard, not on fuel. The player sees Consume go off naturally as they play through their turn. Teaches one of the class's two identity mechanics. **Strong backup.**
- **End-of-turn retain analog**: *At the end of your turn, if you have 2 or more cards in your hand, draw 1 card.* Rewards *not* emptying your hand to pay costs - shows the player that holding cards has value. Fires off a hand-size check, not a fuel-payment event. Decent.
- ~~*The first time you would discard a card to pay fuel each turn, draw 1 card.*~~ **Rejected.** "Discard to pay fuel" is indistinguishable from any other discard, so this either fires on all discards (too generic, too strong) or requires a fuel-specific hook we explicitly don't want.
- ~~*At the start of each combat, add 1 Junk to your hand.*~~ **Rejected.** Forces the Junk build-around every combat. Belongs on an opt-in relic, not the starter.

Lean: **Draw boost** as the starter relic. It matches vanilla precedent (Ring of the Snake), teaches the class's core pressure (hand-as-energy) without a trigger, and leaves Consume/Junk/exhaust as discoverable through cards and later relics.

### 12.2 Common relic slots

Common relics should be low-power, high-clarity. Candidates (pick 3 to 4):

- **Empty-hand smoother**: *Whenever you would be left with an empty hand during your turn, draw 1 card.* Protects against hand-dry-out regardless of cause (paying for a big card, playing out, discard effects). Triggers off hand-empty, not off any fuel hook.
- **Exhaust-count relic**: *At the end of combat, if you exhausted 5 or more cards, heal 2 HP.* Rewards the exhaust lane without touching combat math.
- **Block-leftover relic**: *If you end your turn with 15 or more Block, gain 1 Artifact next turn.* Reactive-defense-lane reward; light overlap with Brace for Impact's Artifact axis but triggered passively.
- **Shop relic**: *Card removal cost is reduced by 25 gold.* Generic; safe common slot.

### 12.3 Uncommon relic slots

Uncommons deepen an axis without being build-defining.

- **Scrap generator**: *The first time you exhaust a card in each combat, add a Scrap to your discard pile.* Creates a Scrap source without a card slot; pairs naturally with Rare-card chasing. Because this is one Scrap per combat (not per exhaust), it does not flood the hand with clog even in heavy exhaust decks.
- **Junk-trigger smoother**: *Junk and Scrap trigger their conversion with one fewer card in hand (minimum 2 Junk or 1 Scrap).* Turns a found relic into a real commitment to the build-around - the player didn't plan on Junk, but now they can actually run it.
- **Hoard amplifier**: *At end of turn, Hoard triggers twice on each card in your hand.* Narrow but build-defining when it lands.
- **Cost-reduction relic**: *At the start of each combat, the first card you play has its cost reduced by 1 (minimum 0) for that play only.* A one-shot cost reduction on the first card played each combat. In this class, a cost reduction means one fewer color-card discarded to play the card - no fuel-specific hook, just ordinary cost modification that already exists in the engine.

### 12.4 Rare / boss relic slots

Rares define. Boss relics swap starter. Design space sketches:

- **Rare "exhume" relic**: *At the start of each combat, return a random card from last combat's exhaust pile to your hand.* (Requires persisted last-combat exhaust - implementable but adds state.) Covers the section 10.1 design vein without burning a card slot.
- **Boss swap**: *Remove your starter relic. At the start of each combat, add 2 Junks and 1 Scrap to your hand.* Swings the class hard into status-card conversion.
- **Rare Consume amplifier**: *Consume triggers gain +1 of their effect.* Narrow; only interesting if Consume has more numeric payloads by the time we get here.

### 12.5 Overlap policy with the card pool

Precedent exists for relic/card effect overlap (e.g., Runic Dome + Intangible cards, Pocketwatch + draw cards). The rule to apply:

- **Do not duplicate** a *specific* card's function in a relic (e.g., a relic that exhumes one specific card is a card, not a relic).
- **Do duplicate** *axes* - a relic that gives passive exhaust-payoff does not conflict with a card that does it actively, because the relic is always on and the card is a choice with a cost.
- **When in doubt, shift the relic to a conditional or per-combat trigger** so its interaction with the card is additive rather than redundant.

### 12.6 Relic slots to reserve vs. release

Reserve these design slots for relics (do not burn them on cards):

- a passive exhaust-pile scaler
- a Scrap generator
- a Junk generator that scales over the run
- an empty-hand / low-hand draw relic (dead-hand protection via hand-size trigger, not a fuel trigger)

Release these back to the card pool (do not design a relic here):

- Consume triggering - belongs on cards
- Hoard buildup - belongs on cards
- Reactive defense triggers - belongs on cards, except the end-of-turn-Artifact relic above which is a passive *reward*, not a *trigger*


## 13. Parked Designs

Designs that were on the implementation short-list but have been pulled back for redesign. Kept here so the raw concept isn't lost and so future passes can revisit them with fresh framing.

### 13.1 Hidden Compartments (parked)

Original proposal:

- Cost: 1
- Type: Power
- *At the start of your turn, draw 1 additional card, then discard 1 card.*
- Mapped red slot: `Brutality` (power | draw-engine | self-damage).

Why it's parked:

- The name doesn't carry the class's salvage/breakdown flavor cleanly - it reads more Silent-coded than Salvager-coded.
- The draw-then-discard loop may be a better fit as a relic passive (section 12) than as a card, since the effect is cheaply-granted upside without a clear cost inside the card.
- Implementing it as a power would require standing up the mod's first custom power (power class, `PowerStrings.json` loader entry, power icon asset). That infrastructure is worth building eventually, but not for a card whose design is itself in question.

Revisit when:

- a better-themed name lands, or
- the effect is repitched as a relic candidate in section 12, at which point the Brutality slot gets a different Salvager-native replacement.

Until then, the Brutality red slot stays mirrored and is eligible for a new Salvager-native power design.

## 14. Reactive Defense Axis and Native Powers

The reactive-defense lane now has a concrete custom-power anchor beyond Thorns: `CounterthrowPower`.

### 14.1 Counterthrow axis

Working rules text:

`Counterthrow X` - when attacked, lose 1 stack and deal damage to the attacker equal to your remaining Block. Resets at the start of your turn.

Design role:

- translates "Block matters" into an offensive payoff without depending on Strength
- pairs naturally with the class's preference for reading block *after* the incoming attack (section 10.2 uncapped scaling caveat does not apply here - the payoff is capped at remaining Block per hit)
- bridges the reactive-defense lane into attack-shaped turns without shifting the class toward Strength-scaling

Design notes:

- Counterthrow reads `owner.currentBlock` **after** the incoming attack has chewed through the pre-reduction block, so a well-timed Set Shoulder or Hunker directly becomes the retaliation value
- stacks are charges, not permanent - each qualifying incoming attack consumes one stack
- multi-hit attacks only trigger the retaliation on the first hit that finds a stack; subsequent hits in the same sequence do not refund the charge
- the power resets at `atStartOfTurn` so Counterthrow cannot accumulate across turns
- multi-target applications are deliberately avoided at uncommon/rare - Counterthrow is a *per-card* grant, not a mass-apply

Design adjacency:

- **Barbed Harness** (uncommon Thorns power) stacks with Counterthrow without overlapping: Thorns fires as a flat retaliation, Counterthrow fires as a block-scaled retaliation. A deck that assembles both layers both the floor and the ceiling of reactive damage.
- **Strip the Wreck** bridges Thorns into the reactive package from the exhaust-value lane. Counterthrow does the same bridge from the block side.
- **Set Shoulder** is the cleanest common block-payoff target for Counterthrow: extra block after being attacked last turn translates directly into larger retaliation.

### 14.2 Custom power infrastructure

`CounterthrowPower` is the mod's first native power. The working pattern future powers should follow:

- subclass `AbstractPower`, implement the relevant hook (`onAttacked`, `atStartOfTurn`, `atEndOfTurn`, etc.)
- add a `POWER_ID` constant using `CardEnergyMod.makeID("PowerName")`
- register localization in `src/main/resources/localization/eng/PowerStrings.json` (loaded via `BaseMod.loadCustomStringsFile(PowerStrings.class, ...)` in `CardEnergyMod.receiveEditStrings`)
- register the keyword via `BaseMod.addKeyword(...)` in `CardEnergyMod.receiveEditKeywords`
- placeholder icons can be borrowed from vanilla regions via `loadRegion(...)`; custom power art is a later pass

The Brutality red slot remains available for the next native-power candidate (section 13.1).

