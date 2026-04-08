# Hand-Fuel Character

## Current Scope

This repository is a standalone Slay the Spire mod that adds a red test character whose real resource is cards in hand rather than stored energy.

The mod depends on:

- Slay the Spire
- ModTheSpire
- BaseMod
- StSLib at runtime

The implementation is character-gated. Vanilla characters still use normal energy behavior.

## Resource Model

The Hand-Fuel character does not spend stored energy to play cards.

Instead, card costs are paid by discarding eligible fuel cards from hand.

Fuel cards are:

- currently in hand
- the same color as the character
- not the card currently being played

For the current test character, that means only red cards in hand count as fuel.

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
- the chosen fuel cards are moved to the discard pile
- the original card is replayed with vanilla energy spending suppressed

This is currently intended as a functional test implementation, not finished UX.

## Energy Replacement Behavior

Stored energy is still present under the hood only as a compatibility layer.

For this character:

- start-of-turn energy recharge is replaced with draw
- explicit `gainEnergy(int)` effects are replaced with draw
- the visible energy panel is hidden

The character currently has `EnergyManager(2)`, so start-of-turn recharge becomes drawing 2 cards.

## Character Setup

Current character traits:

- player color: red
- starter relic: `Runic Pyramid`
- visible energy orb: hidden
- recharge baseline: 2

Current starter deck is intentionally test-heavy and stronger than a normal starter deck so the mechanic is easy to exercise.

It currently includes:

- `Strike`
- `Defend`
- `Bash`
- `Uppercut`
- `Carnage`
- `Seeing Red`

`Seeing Red` is included specifically to verify that energy gain is converted into draw.

## Important Patches

Current behavior is mainly implemented through:

- `AbstractCard.hasEnoughEnergy()`
- `AbstractPlayer.useCard(...)`
- `AbstractPlayer.gainEnergy(int)`
- `EnergyManager.recharge()`
- `EnergyPanel.render(...)`

Core helper:

- `cardenergy.util.HandFuelResourceAdapter`

Selection action:

- `cardenergy.actions.SelectFuelPaymentAction`

## Known Intent

This mod is currently a demonstration scaffold for the hand-fuel mechanic.

It is not balanced for production. The goals right now are:

- prove the hand-fuel payment rule
- prove the surcharge rule
- prove X-cost behavior
- prove energy-to-draw replacement
- keep iteration fast on Windows with local Maven packaging
