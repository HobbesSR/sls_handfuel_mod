# Hand-Fuel Character

## Current Implementation Brief

Use [10_SCAVENGER_IMPLEMENTATION_BRIEF.md](c:\Users\Corey\Documents\Projects\sls_handfuel_mod\10_SCAVENGER_IMPLEMENTATION_BRIEF.md) as the current implementation brief for starter/common card work.

This document remains the mechanic-state document.

## Document Role

This document should answer:

- how the hand-fuel system currently works in code
- what the current cost and energy model is
- what helper classes and patches currently own the mechanic
- what the current starter/reward-pool scaffold is from a runtime perspective

It should not be the source of truth for the evolving common-set card list. That belongs in the implementation brief and broader set-design docs.

When this document conflicts with the implementation brief, interpret it as:

- this file describes the current repository state
- the brief describes the intended next content state

## Current Scope

This repository is a standalone Slay the Spire mod that adds an Indigo test character whose real resource is cards in hand rather than stored energy.

The mod depends on:

- Slay the Spire
- ModTheSpire
- BaseMod
- StSLib at runtime

The implementation is character-gated. Vanilla characters still use normal energy behavior.

## Current Card Pool Strategy

The Indigo set currently uses a two-layer content strategy:

- most of the non-basic Ironclad set is still mirrored into Indigo as a temporary scaffold
- a first Scavenger-specific starter deck and first pass of Scavenger-specific commons now replace part of that mirror set

This is intentional. The mirrored Ironclad cards are the scaffold the final Indigo set will be built from. They provide:

- a complete reward pool
- stable rarity distribution
- normal upgrade and targeting behavior
- known-good red implementations to edit into final Indigo cards over time

The current starter-specific custom cards are not the long-term reward pool backbone.

## Resource Model

The Hand-Fuel character does not spend stored energy to play cards.

Instead, card costs are paid by discarding eligible fuel cards from hand.

Fuel cards are:

- currently in hand
- the same color as the character
- not the card currently being played

For the current character, that means only Indigo cards in hand count as fuel.

In practice, this is the character's available Energy:

- available Energy equals the number of discardable same-color cards currently in hand
- Energy is paid by discarding those cards

Non-fuel cards include:

- the card being played
- colorless cards
- off-color cards
- cards outside the hand

## Current Cost Rules

Current payment behavior:

- `freeToPlayOnce` cards are free
- `0`-cost cards are not free; they cost 1 fuel
- normal cards cost `costForTurn + 1` fuel
- X-cost cards do not pay the extra +1 surcharge
- for X-cost cards, `X = ceil(fuel paid / 2)`

Examples:

- a `0`-cost card costs 1 fuel
- a `1`-cost card costs 2 fuel
- a `2`-cost card costs 3 fuel
- paying 1 fuel for an X-cost card produces `X = 1`
- paying 2 fuel for an X-cost card produces `X = 1`
- paying 3 fuel for an X-cost card produces `X = 2`
- paying 4 fuel for an X-cost card produces `X = 2`

## Current UI / UX

The character hides the normal energy HUD.

When a payable hand card is played:

- the play is intercepted
- only valid fuel cards are shown in a hand-selection screen
- the chosen fuel cards are discarded as payment
- discard replacements such as `Consume` and `Rot` may replace that discard instead of letting the card reach the discard pile
- the original card is replayed with vanilla energy spending suppressed

This is still a functional test implementation, not finished UX.

## Energy Replacement Behavior

Stored energy is still present under the hood only as a compatibility layer.

For this character:

- start-of-turn energy recharge is replaced with draw
- explicit `gainEnergy(int)` effects are replaced with draw
- the visible energy panel is hidden

The character currently has `EnergyManager(2)`, so start-of-turn recharge becomes drawing 2 cards.

Current energy-demand resolution shape:

- fixed-cost cards build a payment plan from chosen Indigo fuel cards
- X-cost cards build a payment plan automatically from the full eligible Indigo fuel set in hand
- prepaid replay state is stored on the card itself
- pending discard replacement state for `Consume` / `Rot` is also stored on the card itself

## Character Setup

Current character traits:

- player color: Indigo
- starter relic: `Runic Pyramid`
- visible energy orb: hidden
- recharge baseline: 2

## Structural Template

This section is kept only as a compressed design-reference bridge so the runtime doc remains navigable. The authoritative structural card-design writeup lives in [30_SCAVENGER_SET_DESIGN.md](c:\Users\Corey\Documents\Projects\sls_handfuel_mod\30_SCAVENGER_SET_DESIGN.md).

The useful design lens is not "which vanilla cards are we copying," but "which structural jobs does the early Scavenger pool need covered."

Every Slay the Spire character needs early coverage for:

- basic frontload damage
- basic block
- a starter card that teaches the class mechanic
- a few simple extensions of that mechanic at low rarity
- at least one consistency smoother through draw, hand fixing, recursion, or flexible utility

Across the vanilla cast, the recurring role template is:

- filler attack
- filler block
- signature tutorial card
- signature payoff card
- smoother
- hybrid card that covers two jobs at once

For Scavenger, the intended early analogs are:

- a basic attack
- a basic defense
- a starter card that teaches fuel spending
- a starter card that teaches discard as upside rather than pure cost
- a low-rarity card that rewards holding cards
- a low-rarity card that rewards discarding, consuming, or exhausting
- a consistency card that fixes awkward hands
- a hybrid card that both solves the current turn and advances the engine

The class thesis should read as:

- your hand is both your wallet and your junk pile

That means not every common should scream the mechanic. Some commons should stay plain on purpose so the more volatile mechanic cards have contrast and the class remains readable.

Current runtime starter deck includes:

- `5x Scrounge Strike`
- `6x Scrounge Defend`
- `Rotting Blow`
- `Brace`
- `Scrap Burst`

Current runtime starter rules intent:

- `Scrounge Strike` is a normal single-target basic attack and does not have `Consume`
- `Scrounge Defend` is a plain basic block card and does not have `Consume`
- `Consume` can appear on targeted cards; autoplayed consumed cards use the game's normal autoplay targeting behavior
- the starter offensive `Consume` demonstration card is `Scrap Burst`, an all-enemy attack

Current runtime starter values:

- `Scrounge Strike`: 0 cost, 4 damage
- `Scrounge Defend`: 0 cost, 5 block
- `Rotting Blow`: 1 cost, 10 damage, `Rot`
- `Brace`: 2 cost, 11 block, apply 1 Weak
- `Scrap Burst`: 1 cost, 5 damage to ALL enemies, `Consume`

Current reward-pool/runtime intent outside the starter deck:

- normal rewards still come mostly from mirrored Ironclad cards in Indigo color
- the first Scavenger-specific common pass now replaces a chunk of the mirrored Ironclad commons
- those mirrored cards are still meant to be edited into final Indigo cards over time

## Current Art Pipeline

Custom Scavenger card art now uses a two-step pipeline:

- raw cropped portrait art lives in:
  - [raw](c:\Users\Corey\Documents\Projects\sls_handfuel_mod\src\main\resources\img\cardenergy\cards\raw)
- card-type masks live in:
  - [masks](c:\Users\Corey\Documents\Projects\sls_handfuel_mod\src\main\resources\img\cardenergy\cards\masks)

Current runtime behavior:

- `ATTACK` cards use `attack_mask_rgba.png`
- `SKILL` cards use `skill_mask_rgba.png`
- `POWER` cards use `power_mask_rgba.png`
- the helper applies the mask at runtime and derives both:
  - the portrait-size art
  - the small in-card art

Current support files:

- [IndigoCardHelper.java](c:\Users\Corey\Documents\Projects\sls_handfuel_mod\src\main\java\cardenergy\cards\IndigoCardHelper.java)
- [prepare_card_art.ps1](c:\Users\Corey\Documents\Projects\sls_handfuel_mod\tools\prepare_card_art.ps1)

This means art assignment is no longer baked permanently by card type at export time. Art can be moved between attacks, skills, and powers while leaving crop/scale as preprocessing and mask application as presentation logic.

## Important Patches

Current behavior is mainly implemented through:

- `AbstractCard.hasEnoughEnergy()`
- `AbstractPlayer.useCard(...)`
- `AbstractPlayer.gainEnergy(int)`
- `EnergyManager.recharge()`
- `EnergyPanel.render(...)`
- `NeowEvent(boolean)`
- `NeowReward.activate()`
- `CardGroup.moveToDiscardPile(...)`

Core helper:

- `cardenergy.util.HandFuelResourceAdapter`

Card helpers:

- `cardenergy.cards.IndigoCardHelper`
- `cardenergy.cards.RedMirrorCards`

Selection action:

- `cardenergy.actions.SelectFuelPaymentAction`

Selection / payment helpers:

- `cardenergy.util.HandFuelSelectionHelper`
- `cardenergy.util.HandFuelPaymentHelper`
- `cardenergy.util.HandFuelPaymentPlan`

Asset / presentation helpers:

- `cardenergy.cards.IndigoCardHelper`
- `tools/prepare_card_art.ps1`

## Workshop Packaging

The repository now also contains a minimal Workshop upload scaffold:

- [workshop](c:\Users\Corey\Documents\Projects\sls_handfuel_mod\workshop)

Current purpose:

- stage the built jar and preview image
- store the Workshop descriptor for the current item
- support updates through SteamCMD

Current item metadata:

- Workshop item id: `3704910650`
- visibility: friends-only

## Known Intent

This mod is currently a demonstration scaffold for the hand-fuel mechanic.

It is not balanced for production. The goals right now are:

- prove the hand-fuel payment rule
- prove the surcharge rule
- prove X-cost behavior
- prove discard-replacement `Consume` behavior
- prove energy-to-draw replacement
- keep the Indigo reward pool complete while final cards are built by editing mirrored red cards
- keep iteration fast on Windows with local Maven packaging
