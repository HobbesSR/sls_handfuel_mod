# Hand-Fuel Character

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

- the full non-basic Ironclad set is mirrored into Indigo as a temporary baseline pool
- the custom Indigo starter/test cards exist separately to exercise the hand-fuel mechanics early

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

Non-fuel cards include:

- the card being played
- colorless cards
- off-color cards
- cards outside the hand

## Current Cost Rules

All non-free hand-played cards pay an extra 1 fuel surcharge.

Current payment behavior:

- `freeToPlayOnce` cards are free
- `0`-cost cards are not free; they cost 1 fuel
- normal cards cost `costForTurn + 1` fuel
- X-cost cards require at least 1 fuel to play
- for X-cost cards, 1 selected fuel is treated as tax and the remaining selected fuel becomes `energyOnUse`

Examples:

- a `0`-cost card costs 1 fuel
- a `1`-cost card costs 2 fuel
- a `2`-cost card costs 3 fuel
- selecting 4 fuel cards for an X-cost card produces `X = 3`

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

## Character Setup

Current character traits:

- player color: Indigo
- starter relic: `Runic Pyramid`
- visible energy orb: hidden
- recharge baseline: 2

Current starter deck is intentionally test-heavy and stronger than a normal starter deck so the mechanic is easy to exercise.

It currently includes:

- `4x Scrounge Strike`
- `4x Scrounge Defend`
- `Recovery`
- `Rotting Blow`
- `Rotting Shelter`
- `Stockpile`
- `2x Scrap Spray`

Current starter rules intent:

- `Scrounge Strike` is a normal single-target basic attack and does not have `Consume`
- `Scrounge Defend` keeps `Consume`
- `Consume` is currently intended only for cards that do not require target selection UI after the main card resolves
- the starter offensive `Consume` demonstration card is `Scrap Spray`, an all-enemy attack

Current tuned starter values:

- `Scrounge Strike`: 0 cost, 4 damage
- `Scrounge Defend`: 0 cost, 5 block, `Consume`
- `Recovery`: 1 cost, 3 block, return up to 2 exhausted cards to discard
- `Rotting Blow`: 1 cost, 7 damage, `Rot`
- `Rotting Shelter`: 2 cost, 12 block, draw 1, `Rot`
- `Stockpile`: 2 cost, 3 damage, 4 block, `Hoard 4`
- `Scrap Spray`: 2 cost, 3 damage to ALL enemies, `Consume`

Current reward-pool intent outside the starter deck:

- normal rewards should primarily come from mirrored Ironclad cards in Indigo color
- those mirrored cards are the cards meant to be edited into final Indigo cards over time
- the custom starter-only mechanic cards are currently marked `BASIC` so they stay in the opening deck without defining the normal reward pool

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

## Known Intent

This mod is currently a demonstration scaffold for the hand-fuel mechanic.

It is not balanced for production. The goals right now are:

- prove the hand-fuel payment rule
- prove the surcharge rule
- prove X-cost behavior
- prove discard-replacement `Consume` behavior on non-targeted cards
- prove energy-to-draw replacement
- keep the Indigo reward pool complete while final cards are built by editing mirrored red cards
- keep iteration fast on Windows with local Maven packaging
