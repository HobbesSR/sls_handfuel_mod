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

The current codebase still uses a broad mirrored Ironclad pool as Terracotta's temporary baseline set, but the first pass of Salvager-specific starter and common cards now replaces part of that mirror scaffold.

That is not the final content direction. It is the implementation scaffold.

Current practical rule:

- mirrored red cards still provide most of the Terracotta reward pool
- custom Terracotta starter cards and first-pass custom commons now exercise the class grammar directly
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

## 3. Keywords and Family Language

### Consume

Working rules text:

`Consume` - When this card would be discarded from your hand, play it automatically and Exhaust it instead.

Purpose:

- creates the play-versus-fuel tension
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

- a reactive defense / thorns / punishment lane
- a consume / exhaust-matters lane
- a Hoard vein that runs through both and becomes a higher-ceiling package
- Junk as the family crossing all of the above

This means the class should not feel like three separate archetypes stapled together. Instead:

- reactive cards can be Junk
- consume / exhaust cards can be Junk
- Hoard especially rewards Junk density
- the best Hoard decks are moonshot decks enabled by the right uncommons and rares

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
- starter card that teaches fuel spending
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
- `Scrap Spray`: starter mechanic tutorial for `Consume` / fuel discard upside
- `Recovery`: smoother that turns prior exhaust into future consistency
- `Rotting Blow` / `Rotting Shelter`: early discard/exhaust-payoff tension cards
- `Stockpile`: early hold-value card showing the Hoard vein

The useful retained lesson is not the exact old starter composition. The useful lesson is that future commons must cleanly extend these jobs:

- simple fuel-spend cards
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

