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

`Rotting Shelter`

- Cost: 2
- Gain 12 Block. Draw 1.
- `Rot`

`Brace for Impact`

- Cost: 1
- Gain 8 Block

`Guard the Heap`

- Cost: 1
- Gain 7 Block. If you were attacked last turn, gain 3 additional Block.

`Hunker`

- Cost: 2
- Gain 13 Block

`Dug In`

- Cost: 1
- Gain 6 Block. Apply 1 Weak.

### Consume / expendable-material commons

`Scrap Knife`

- Cost: 1
- Deal 6 damage
- `Consume`

`Burn Through`

- Cost: 1
- Gain 6 Block
- `Consume`

`Loose Parts`

- Cost: 0
- Draw 1 card
- `Consume`

### Exhaust/value commons

`Recovery`

- Cost: 0
- Gain 3 Block. Return up to 2 exhausted cards to discard.

`Ashen Count`

- Cost: 1
- Deal 5 damage. If a card was Exhausted this turn, deal 5 additional damage.

`Patchwork`

- Cost: 1
- Gain 5 Block. Return 1 exhausted card to your discard pile. Draw 1 card.

### Reactive commons

`Barbed Guard`

- Cost: 1
- Gain 7 Block. If an enemy attacks you this turn, deal 4 damage to it.

`Turn Aside`

- Cost: 1
- Gain 6 Block. If all damage is blocked this turn, draw 1 card next turn.

### Hoard commons

`Stockpile`

- Cost: 2
- Deal 4 damage. Gain 5 Block.
- `Hoard 3`

`Hidden Cache`

- Cost: 1
- Gain 6 Block.
- `Hoard 2`

### Extra glue / overlap commons

`Scavenge the Wreck`

- Cost: 1
- Exhaust 1 card in your hand. Draw 2 cards.

`Packed Swing`

- Cost: 1
- Deal 6 damage. Gain 4 Block.

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
3. Replace the current starter deck with the new 14-card starter above.
4. Mark the three starter specials as `BASIC`.
5. Remove the old starter-only versions of `Recovery`, `Rotting Shelter`, `Stockpile`, and `Scrap Spray` from starter usage.
6. Recreate those cards as commons using the designs above.
7. Implement the rest of the listed common cards by replacing mirrored Ironclad-shell commons for now.
8. Preserve existing Terracotta / mirrored-Ironclad scaffold behavior where possible, but overwrite names, cost, text, and mechanics to match this brief.
9. Test:
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

