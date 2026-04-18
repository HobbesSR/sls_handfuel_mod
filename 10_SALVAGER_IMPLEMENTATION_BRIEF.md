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
- Effect: Deal 7 damage to ALL enemies
- No keyword

Role:

- reward-pool AoE common
- descendant of the starter signature AoE without duplicating `Consume`

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
- Deal 7 damage to ALL enemies
- No keyword

`Rotting Slash`

- Cost: 1
- Deal 8 damage
- `Rot`

`Salvage Swing`

- Cost: 1
- Deal 7 damage. If a card was Exhausted this turn, gain 3 Block.

`Pile Driver`

- Cost: 2
- Deal 12 damage

### Defense commons

`Shoulder the Load`

- Cost: 1
- Gain 8 Block. Discard 1 card.

`Rotting Shelter`

- Cost: 2
- Gain 12 Block. Draw 1.
- `Rot`

`Brace for Impact`

- Cost: 1
- Gain 9 Block

`Guard the Heap`

- Cost: 1
- Gain 7 Block. If you were attacked last turn, gain 3 additional Block.

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
- Draw 2 cards. Discard 1 card.

### Exhaust/value commons

`Recovery`

- Cost: 0
- Gain 3 Block. Return up to 2 exhausted cards to discard.

`Ashen Count`

- Cost: 1
- Deal 8 damage. If a card was Exhausted this turn, deal 4 additional damage.

`Patchwork`

- Cost: 1
- Exhaust 1 card from your hand. Draw 2 cards.

### Reactive commons

`Barbed Guard`

- Cost: 1
- Gain 7 Block. If an enemy attacks you this turn, deal 4 damage to it.

`Pack Away`

- Cost: 0
- Draw 1 card. Put 1 card from your hand on top of your draw pile.

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

- Cost: 1
- Exhaust 1 card in your hand. Draw 2 cards.

`Packed Swing`

- Cost: 1
- Deal 6 damage. Gain 4 Block.

Current design note:

- `Dug In`, `Loose Parts`, and `Turn Aside` were useful first-pass cards, but the newer handoff package points toward cleaner common infrastructure in `Shoulder the Load`, `Sort the Haul`, and `Pack Away`
- `Sort the Haul` should function as the low-cost filter outlet for dumping off-color clutter, not as a throwaway exhaust cantrip
- `Hidden Cache` should be the harsher Hoard support card: under-rate at first glance, but a real answer to retained junk, statuses, and curses once hand pressure matters
- `Precious Bauble` should replace a dead mirrored rare with a real retained-hand moonshot rather than another generic red payoff the class barely exploits
- `Brace for Impact` still has a place as neutral defensive glue, but the common pool should lean more heavily on simple conversion cards and cleaner exhaust enablers than on bespoke placeholder effects

## Recommended Next-Pass Uncommons

These are the current recommended additions from the handoff package. They are target-state designs, not all immediate implementation requirements.

Status markers below reflect current repository state. `implemented` means the card ships as a real Salvager uncommon and its mirrored Ironclad counterpart has been removed from the reward pool. `deferred` means the design is still the target, but implementation is gated on an open decision captured inline.

`Barbed Harness` — implemented

- Cost: 1
- Type: Power
- Whenever an enemy attacks you, deal 3 damage back.
- Runtime note: grants vanilla `ThornsPower` rather than a custom power so no new power-art pipeline is required yet.

`Upend the Pack` — implemented

- Cost: 0
- Type: Skill
- Discard your hand, then draw that many cards. Exhaust.
- Upgrade removes Exhaust.

`Strip the Wreck` — implemented

- Cost: 0
- Type: Skill
- Exhaust up to 2 cards from your hand. Gain 2 Thorns for each card Exhausted this way. Exhaust.
- Upgrade: +1 Thorns per card Exhausted (3 per).
- Runtime note: the `Gain [E]` translation was dropped in favor of Thorns generation. This bridges the exhaust-value lane into reactive defense, stacks with Barbed Harness, and avoids introducing Strength generation (which the class is deliberately kept away from). Uses vanilla `ThornsPower`; no new power infrastructure required.

`Empty the Pack` — implemented

- Cost: 1
- Type: Attack
- Deal 14 damage. Discard your hand.
- The discard routes through the unified manual-discard hook, so Consume and Rot cards in hand fire normally.

`Scrapstorm` — implemented

- Cost: 1
- Type: Attack
- Deal 9 damage to ALL enemies. Discard 1 card at random.

`Breakdown Rush` — implemented

- Cost: 2
- Type: Attack
- Deal 14 damage. Deals 6 additional damage for each card Exhausted this turn.
- Upgrade: +2 additional damage per card Exhausted this turn (8 per).
- Runtime note: reads `SalvagerCombatState.getExhaustedThisTurn(player)` during `applyPowers` and `calculateCardDamage`, rewriting `baseDamage` to `printed + (per-exhaust × count)` and flagging `isDamageModified` when the bonus is active. No new cost-modifier infrastructure required — this is a damage scaler, not a cost scaler.

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
- At the start of your turn, if this is in your hand, gain 1 Intangible.
- At the end of your turn, if this is in your hand, lose 1 Max HP.

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

