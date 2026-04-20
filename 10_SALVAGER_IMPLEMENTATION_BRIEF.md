# Salvager Implementation Brief

This document is the current implementation brief for Salvager card work.

It is the authority for the current starter/common direction and the baseline to continue implementing against when the repository is still catching up in later passes.

It supersedes the older starter-heavy scaffold language in the other design notes where they conflict, while preserving the same class identity:

- hand-paid Energy
- `Consume` as common native grammar
- `Rot` as tension
- `Hoard` as a cross-lane moonshot vein
- two main lanes:
  - reactive block
  - exhaust/value

Public naming note:

- the current public character name is `Salvager`
- this brief should now use `Salvager` consistently

## Document Role

Use documents in this order:

1. [10_SALVAGER_IMPLEMENTATION_BRIEF.md](c:\Users\Corey\Documents\Projects\sls_handfuel_mod\10_SALVAGER_IMPLEMENTATION_BRIEF.md)
   This is the authoritative starter/common implementation brief.
2. [20_SALVAGER_MECHANICS.md](c:\Users\Corey\Documents\Projects\sls_handfuel_mod\20_SALVAGER_MECHANICS.md)
   This is the mechanic-state document.
3. [30_SALVAGER_SET_DESIGN.md](c:\Users\Corey\Documents\Projects\sls_handfuel_mod\30_SALVAGER_SET_DESIGN.md)
   This is the broader set-design and role-template document.
4. [40_SALVAGER_IMPLEMENTATION_PLAN.md](c:\Users\Corey\Documents\Projects\sls_handfuel_mod\40_SALVAGER_IMPLEMENTATION_PLAN.md)
   This is the staged roadmap.
5. [50_SALVAGER_RESOURCE_CHECKLIST.md](c:\Users\Corey\Documents\Projects\sls_handfuel_mod\50_SALVAGER_RESOURCE_CHECKLIST.md)
   This is the planning and tracking checklist.

Where those documents conflict on immediate starter/common implementation priorities, this brief wins.

## Skeleton Mapping Note

Use [design/red_set_mapping.csv](c:\Users\Corey\Documents\Projects\sls_handfuel_mod\design\red_set_mapping.csv) as the current scaffold map for replacing the temporary red baseline.

Interpret that CSV as:

- a loose structural template
- a coverage sanity check
- a record of which red slot we are anchoring against

Do not interpret it as a requirement that every Salvager card preserve the exact original Ironclad role.

The important fields are:

- `red_slot`: the reference slot in the scaffold
- `red_role_tags`: the apparent structural job of that reference slot
- `derived_from_red`: the red implementation or concept we are borrowing from, if any
- `replacement_relation`: whether the replacement is `direct`, `analogous`, or a deliberate `strategic_shift`

The implementation target is still the Salvager set's own strategic identity. The red skeleton is there to keep pacing and role coverage legible while the set is being rewritten.

Some next-pass designs also borrow obvious Silent precedent. In those cases, keep using the red slot as the scaffold anchor, and capture the non-red lineage in notes or the design documents rather than pretending the card is a literal red import.

## High-Level Intent

Keep the starter specials as `BASIC`.

Do not promote the old starter specials directly into the common pool unchanged.

The structure is:

- Basics teach the class grammar cleanly
- Commons scaffold the two main lanes
- `Hoard` is a vein, not an isolated lane
- `Rot` is a tension package, not a standalone lane
- `Consume` remains the class's most common native keyword language

## Status

Read the sections below as:

- the current starter/common direction
- the priority content brief for continued implementation passes
- the replacement plan for the older starter scaffold where any legacy code still remains

Use [20_SALVAGER_MECHANICS.md](c:\Users\Corey\Documents\Projects\sls_handfuel_mod\20_SALVAGER_MECHANICS.md) for what is actually true in the current codebase right now.

## Resource / Cost Rules

Keep the current non-X rule:

- non-X cards pay printed cost + 1 Energy

Keep the current character-specific X rule:

- for X-cost cards, `X = ceil(Energy paid / 2)`

Player-facing rule:

- available Energy is the number of cards of the character's color in hand
- this character pays Energy by discarding those cards

No native Salvager X cards are planned right now.

## Final Starter Deck

Starter deck:

- `5x Scrounge Strike`
- `6x Scrounge Defend`
- `1x Rotting Blow`
- `1x Brace`
- `1x Scrap Burst`

Total: 14 cards

### Starter basics

`Scrounge Strike`

- Rarity: `BASIC`
- Type: Attack
- Cost: `0`
- Effect: Deal 4 damage
- No keyword

`Scrounge Defend`

- Rarity: `BASIC`
- Type: Skill
- Cost: `0`
- Effect: Gain 5 Block
- No keyword

### Starter special attack

`Rotting Blow`

- Rarity: `BASIC`
- Type: Attack
- Cost: `1`
- Effect: Deal 10 damage
- Keyword: `Rot`

Role:

- Bash-equivalent slot
- urgent attack
- teaches Rot through rate plus inflexibility

### Starter special block

`Brace`

- Rarity: `BASIC`
- Type: Skill
- Cost: `2`
- Effect: Gain 11 Block. Apply 1 Weak.
- No keyword

Role:

- Survivor-analogue slot
- premium stabilization
- clean block-heavy class posture

### Starter signature simple card

`Scrap Burst`

- Rarity: `BASIC`
- Type: Attack
- Cost: `1`
- Effect: Deal 5 damage to ALL enemies
- Keyword: `Consume`

Role:

- simple class-signature card
- teaches discard-as-upside
- safe autoplay targeting because AoE

## Old Starter Cards Moved Out

These are no longer starter `BASIC` cards. They should be implemented as commons or common descendants.

`Recovery`

- Rarity: `COMMON`
- Type: Skill
- Cost: `0`
- Effect: Gain 3 Block. Return up to 2 exhausted cards to your discard pile.

Role:

- common exhaust/value glue

`Rotting Shelter`

- Rarity: `COMMON`
- Type: Skill
- Cost: `2`
- Effect: Gain 12 Block. Draw 1 card.
- Keyword: `Rot`

Role:

- common defensive Rot signpost

`Stockpile`

- Rarity: `COMMON`
- Cost: `2`
- Effect: Deal 4 damage. Gain 5 Block.
- Keyword: `Hoard 3`

Role:

- common Hoard signpost hybrid
- current implementation direction: `Hoard` implies staying in hand and resets on play

`Scrap Spray`

- Rarity: `COMMON`
- Type: Attack
- Cost: `1`
- Effect: Deal 10 damage to ALL enemies. Add 1 Junk to your discard pile.

Role:

- reward-pool AoE pressure common
- explicit Junk producer with no upside rider beyond above-rate base damage

## Common Pool Scaffold

Target roughly `~21 commons`, organized by play role:

- 4 attack commons
- 5 defense commons
- 3 Consume / expendable-material commons
- 3 exhaust/value commons
- 2 reactive commons
- 2 Rot commons
- 2 Hoard commons

Overlap is expected.

## Explicit Common Targets

### Attack commons

`Scrap Toss`

- Cost: 1
- Deal 8 damage. Draw 1 card, then discard 1 card.

`Scrap Spray`

- Cost: 1
- Deal 10 damage to ALL enemies.
- Add 1 Junk to your discard pile.

`Rotting Slash`

- Cost: 1
- Deal 10 damage
- `Rot`

`Salvage Swing`

- Cost: 0
- Deal 7 damage. If a card was Exhausted this turn, gain 3 Block.

`Pile Driver`

- Cost: 2
- Deal 12 damage

### Defense commons

`Shoulder the Load`

- Cost: 1
- Gain 10 Block.
- Add 1 Junk to your discard pile.

`Rotting Shelter`

- Cost: 2
- Gain 12 Block. Draw 1.
- `Rot`

`Brace for Impact`

- Cost: 1
- Gain 9 Block

`Set Shoulder`

- Cost: 1
- Gain 7 Block. If you were attacked last turn, gain 3 additional Block.
- Renamed from `Guard the Heap` so the `Heap` theme can stay reserved for exhaust-pile interactions.

`Hunker`

- Cost: 2
- Gain 13 Block

`Shore Up`

- Cost: 1
- Gain 7 Block. Exhaust 1 card from your hand.

### Consume / expendable-material commons

`Scrap Knife`

- Cost: 1
- Deal 6 damage
- `Consume`

`Burn Through`

- Cost: 1
- Gain 6 Block
- `Consume`

`Sort the Haul`

- Cost: 0
- Draw 3 cards.
- Add 1 Junk to your discard pile.

### Exhaust/value commons

`Recovery`

- Cost: 0
- Gain 3 Block. Return up to 2 exhausted cards to discard.

`Ashen Count`

- Cost: 1
- Deal 8 damage. If a card was Exhausted this turn, deal 4 additional damage.

`Patchwork`

- Cost: 1
- Exhaust 1 card from your hand. Draw 3 cards.
- Add 1 Junk to your discard pile.

### Reactive commons

`Barbed Guard`

- Cost: 1
- Gain 7 Block. If an enemy attacks you this turn, deal 4 damage to it.

`Pack Away`

- Cost: 0
- Draw 1 card. Put 1 card from your hand on top of your draw pile.

`Counterthrow`

- Cost: 2 (upgrades to 1)
- Type: Skill
- Apply 1 Counterthrow.
- Keyword `Counterthrow` (custom power): when attacked, lose 1 stack and deal damage to the attacker equal to your remaining Block. Resets at the start of your turn.
- Runtime note: first native Salvager power. Reads `owner.currentBlock` post-block-reduction inside `onAttacked`, so the retaliation is the Block the attack failed to chew through. Charge is consumed unconditionally on a qualifying hit (open decision: preserve charge on 0-block retaliation). Placeholder icon reuses vanilla `loadRegion("thorns")` until custom Counterthrow art lands.

### Hoard commons

`Stockpile`

- Cost: 2
- Deal 4 damage. Gain 5 Block.
- `Hoard 3`

`Hidden Cache`

- Cost: 1
- Gain 8 Block. Discard 2 cards.
- `Hoard 3`

### Extra glue / overlap commons

`Scavenge the Wreck`

- Cost: 0
- Exhaust 1 card from your discard pile. Draw 1 card.

`Packed Swing`

- Cost: 1
- Deal 6 damage. Gain 4 Block.

Current design note:

- `Dug In`, `Loose Parts`, and `Turn Aside` were useful first-pass cards, but the newer pass now repurposes the first two into explicit Junk-production commons while preserving `Pack Away` as hand-shaping glue
- common Junk producers should stay slightly above rate with no upside rider text beyond their base effect; the built-in downside is adding Junk to your discard pile
- `Hidden Cache` should be the harsher Hoard support card: under-rate at first glance, but a real answer to retained junk, statuses, and curses once hand pressure matters
- `Precious Bauble` should replace a dead mirrored rare with a real retained-hand moonshot rather than another generic red payoff the class barely exploits
- `Brace for Impact` still has a place as neutral defensive glue, but the common pool should lean more heavily on simple conversion cards and cleaner exhaust enablers than on bespoke placeholder effects

## Recommended Next-Pass Uncommons

These are the current recommended additions from the handoff package. They are target-state designs, not all immediate implementation requirements.

Status markers below reflect current repository state. `implemented` means the card ships as a real Salvager uncommon and its mirrored Ironclad counterpart has been removed from the reward pool. `deferred` means the design is still the target, but implementation is gated on an open decision captured inline.

`Barbed Harness` - implemented

- Cost: 1
- Type: Power
- Whenever an enemy attacks you, deal 3 damage back.
- Runtime note: grants vanilla `ThornsPower` rather than a custom power so no new power-art pipeline is required yet.

`Upend the Pack` - implemented

- Cost: 0
- Type: Skill
- Discard your hand, then draw that many cards. Exhaust.
- Upgrade removes Exhaust.

`Strip the Wreck` - implemented

- Cost: 0
- Type: Skill
- Exhaust up to 2 cards from your hand. Gain 2 Thorns for each card Exhausted this way. Exhaust.
- Upgrade: +1 Thorns per card Exhausted (3 per).
- Runtime note: the `Gain [E]` translation was dropped in favor of Thorns generation. This bridges the exhaust-value lane into reactive defense, stacks with Barbed Harness, and avoids introducing Strength generation (which the class is deliberately kept away from). Uses vanilla `ThornsPower`; no new power infrastructure required.

`Empty the Pack` - implemented

- Cost: 1
- Type: Attack
- Deal 14 damage. Discard your hand.
- The discard routes through the unified manual-discard hook, so Consume and Rot cards in hand fire normally.

`Scrapstorm` - implemented

- Cost: 1
- Type: Attack
- Deal 12 damage to ALL enemies.
- Add 1 Scrap to your discard pile.

`Breakdown Rush` - implemented

- Cost: 2
- Type: Attack
- Deal 14 damage. Deals 6 additional damage for each card Exhausted this turn.
- Upgrade: +2 additional damage per card Exhausted this turn (8 per).
- Runtime note: reads `SalvagerCombatState.getExhaustedThisTurn(player)` during `applyPowers` and `calculateCardDamage`, rewriting `baseDamage` to `printed + (per-exhaust x count)` and using the standard dynamic-damage idiom (`isDamageModified = damage != baseDamage`) so Strength/Weak modifiers remain visible. No new cost-modifier infrastructure is required - this is a damage scaler, not a cost scaler.

`Hurl the Heap` - implemented (promoted from common)

- Cost: 2 (upgrades to 1)
- Type: Attack
- Deal M damage for each card in your exhaust pile (M = 3, upgrades to 2).
- Anchored on the `Bludgeon` mirrored slot. Rarity slip rare->uncommon is deliberate per the loose-guideline anchoring policy. `BludgeonCopy` removed from the mirror pool.
- Upgrade trades raw damage for energy efficiency rather than cost-to-zero. Open decision: confirm this shape, or swap to "flat floor + pile_size" if that was the intent.
- Runtime note: `baseDamage = magicNumber x exhaustPile.size()` is rewritten in `applyPowers` and `calculateCardDamage` with null-safe player guards for non-combat rendering. Uses the standard dynamic-damage idiom (`isDamageModified = damage != baseDamage`) so normal damage modifiers remain visible. `DamageAction` is added unconditionally so `onAttacked`-style hooks (Thorns, Flame Barrier) fire even at empty pile; `damageAmount`-gated effects do not.

## Recommended Next-Pass Rares

`Close Appraisal`

- Cost: 2
- Type: Power
- Whenever a card is exhausted, draw 1 card.

`Shelter from Scraps`

- Cost: 1
- Type: Power
- Whenever a card is exhausted, gain 3 Block.

`Jury-Rig Barrage`

- Cost: 1
- Type: Attack
- Deal 2 damage 5 times. If a card was exhausted this turn, hit 2 additional times.

`Emergency Refit`

- Cost: 0
- Type: Skill
- Discard your hand. Draw that many cards. Gain [E] if you discarded 3 or more cards this way. Exhaust.

`Precious Bauble`

- Cost: Unplayable
- Type: Skill
- Innate
- After discard (before enemies act), if this is in your hand, gain 1 Intangible.
- At the start of your turn, if this is in your hand, lose 1 Max HP.

## Rot Philosophy

Rot cards should usually follow one of these patterns:

1. Great rate
2. Wide value at standard-ish rate, but some value is often partially moot
3. Situational silver-bullet value that self-cleans if not needed

Do not make Rot mostly mediocre narrow filler.

## Hoard Philosophy

Hoard is a vein, not a silo.

It should:

- cross-support both reactive block and exhaust/value
- reward retained-hand greed
- sometimes become a shoot-the-moon package if enough support is assembled
- not require a fully isolated Hoard deck to justify any single Hoard common

Current implementation direction:

- `Hoard` is a generic keyword rather than a Terracotta-only helper
- Hoard cards should be authored through a standard keyword path
- `Hoard` implies intrinsic stay-in-hand behavior so the keyword remains functional off-class
- the accumulated Hoard bonus should remain separable from authored base stats and reset cleanly on play

Common Hoard cards should be:

- playable at floor value
- clearly better if held
- not all-in build-arounds

## Implementation Instructions

1. Keep the current hand-paid Energy model and current non-X cost handling.
2. Keep the current X rule: `X = ceil(Energy paid / 2)`.
3. Only explicit `freeToPlayOnce` should bypass the Salvager surcharge. Temporary `costForTurn = 0` effects should still pay the normal class tax unless the game also marks the card free.
4. Replace the current starter deck with the new 14-card starter above.
5. Mark the three starter specials as `BASIC`.
6. Remove the old starter-only versions of `Recovery`, `Rotting Shelter`, `Stockpile`, and `Scrap Spray` from starter usage.
7. Recreate those cards as commons using the designs above.
8. Implement the rest of the listed common cards by replacing mirrored Ironclad-shell commons for now.
9. Preserve existing Terracotta / mirrored-Ironclad scaffold behavior where possible, but overwrite names, cost, text, and mechanics to match this brief.
10. Keep single-fuel payment selection on the exact-one-card path so vanilla fast confirm works without auto-picking fuel for the player.
11. Test:
   - starter deck play feel
   - `Consume`
   - `Rot` auto-exhaust
   - `Recovery` exhaust recursion
   - `Hoard` scaling persistence and reset
   - reactive cards triggering
   - common pool generation

## Short Rationale

The main mistake to avoid is overloading the starter with too many mechanics at once.

The starter should now teach:

- basic attack
- basic block
- urgent Rot attack
- premium stabilizing defense
- simple Consume identity

The commons should then carry the broader scaffolding:

- reactive block lane
- exhaust/value lane
- Hoard vein
- Rot tension
- Consume grammar

## Custom Powers

The mod now carries its first native Salvager power, `CounterthrowPower`:

- Source: `src/main/java/cardenergy/powers/CounterthrowPower.java`
- Localization: `src/main/resources/localization/eng/PowerStrings.json` (loaded in `CardEnergyMod.receiveEditStrings`)
- Keyword: `Counterthrow` registered in `CardEnergyMod.receiveEditKeywords` alongside `Consume`, `Hoard`, `Rot`
- Icon: placeholder reuses the vanilla Thorns region via `loadRegion("thorns")` until bespoke art is authored
- Behavior: `onAttacked` consumes one stack per qualifying incoming hit and retaliates with `DamageType.THORNS` equal to `owner.currentBlock` (read post-block-reduction). Cleared at `atStartOfTurn`.

Use this class as the reference template for future native powers. The minimum surface a new power needs in this codebase is: concrete `AbstractPower` subclass, `PowerStrings.json` entry, `BaseMod.addKeyword` call, and (eventually) a custom icon region.

## Handoff: Open Decisions

The following decisions were surfaced during the uncommon pass 2 work and are deliberately unresolved so the next pass can choose. None of these block further card work; they are small-radius tuning choices on cards that already ship cleanly.

- **Counterthrow whiff semantics.** `CounterthrowPower.onAttacked` currently consumes a charge on every qualifying incoming hit, including hits where `owner.currentBlock == 0` (retaliation is skipped but the charge is still spent). Confirm or flip to preserve the charge when the retaliation would be 0.
- **Hurl the Heap upgrade shape.** Shipped as cost 2 / magic 3 base -> cost 1 / magic 2 upgraded (per-exhaust-pile-card multiplier), trading raw damage for energy efficiency on upgrade. Confirm or swap to "flat floor + pile_size" if the intent was a flat-damage floor rather than a per-card multiplier.
- **Brutality mirrored slot.** `BrutalityCopy` remains in the mirror pool because `Hidden Compartments` was parked (see section 13.1 in `30_SALVAGER_SET_DESIGN.md`). That slot is open for a fresh Salvager-native power design.

## Handoff: Next-Pass Direction

Next-pass uncommons are explicitly the "boring" utility uncommons - cards that raise the general quality of any Salvager deck rather than anchor a new axis. The user has asked to reserve pool space for two specialized families during this pass:

- **Exhaust-return cards.** Cards that move cards from the exhaust pile back into play, hand, or discard. `Recovery` already anchors this at common; a stronger uncommon version is wanted. See section 10.1 in `30_SALVAGER_SET_DESIGN.md` for the recurring-exhume design space.
- **Junk/Scrap producers and consumers.** Cards that generate Junk/Scrap status cards, and cards that care about having them in hand for scaling or consumption (e.g., exhaust a Scrap to upgrade a random card in hand). See section 11 in `30_SALVAGER_SET_DESIGN.md` for the status-card design.

Do not implement these as the whole uncommon pass - they are earmarks inside a broader utility-uncommon batch.

