# Scavenger Working Card Set Design Document

## Current Implementation Note

The current codebase is using a full mirrored Ironclad pool as Indigo's temporary baseline set.

That is not the final content direction. It is the implementation scaffold.

Current practical rule:

- mirrored red cards provide the complete Indigo reward pool
- custom Indigo starter/test cards exist to exercise the hand-fuel mechanics immediately
- final Indigo cards should be created by editing those mirrored red-derived cards into their new names, text, and mechanics over time

So the working plan is:

- do not preserve old one-off prototype cards as long-term content
- do not leave the pool undersized while commons are still being designed
- replace mirrored red cards gradually with real Indigo cards as they are ready

## 1. Core Identity

The class is now a Scavenger: a cloaked, grimy figure or golem-like scavenger carrying a basket, sack, or similar bundle of found items. The visual specifics are not locked. The important part is the mechanical fantasy:

- cards in hand are stuff you are carrying
- you consume cards as fuel to act
- some cards get better if you hoard them
- some cards rot if you keep or discard them carelessly
- some cards are just junk, and some decks can exploit Junk density

This is a better thematic fit than the earlier electrical or singularity framing and avoids overlapping too much with existing electric characters.

## 2. Core Mechanical Grammar

### Resource model

The class still uses the hand as the real resource:

- cards are paid for by discarding eligible cards from hand
- there is a surcharge on plays
- large draw and retention-like smoothing are part of the class identity
- the deck is meant to feel like it is carrying and consuming inventory

The exact backend rules can remain as already implemented for now.

## 3. Keywords and Family Language

### Consume

Working rules text:

`Consume` — When this card is discarded from your hand, play it automatically after the current card resolves. Exhaust it.

Purpose:

- creates the play-versus-fuel tension
- is the main behavior keyword on the current starter package
- naturally feeds exhaust-matters cards

Notes:

- this is a behavior keyword, not a tracked synergy axis
- other cards should generally not look for "Consume triggered"
- the public bridge state is still cards exhausted this turn
- for now, `Consume` should stay on self-target, no-target, or all-enemy cards unless a proper delayed targeting interface is added

### Hoard

Working rules text:

`Hoard X` — At the end of your turn, if this card is in your hand, it gains +X damage and Block.

Purpose:

- delayed growth
- support vein rather than a full standalone lane
- especially strong on Junk-heavy decks
- the "shoot the moon" package

Notes:

- Hoard should be present at common, but concentrated more heavily at uncommon and rare
- most Hoard cards should be intrinsically useful even without full build-around support

### Rot

Working rules text:

`Rot` — If this card is discarded or remains in your hand at end of turn, Exhaust it.

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

## 5. Starter Deck Scaffold

Starter size is currently justified at 14 cards, because the class effectively draws 7 per turn at baseline and would otherwise become too compressed too quickly.

### Starter basics: 8 cards

- 4 basic attacks
- 4 basic block skills

Current implementation direction:

- the 4 basic attacks are plain single-target attacks
- the 4 basic block skills have `Consume`

Reason:

- `Consume` currently resolves after the main card
- single-target consumed cards would need a post-resolution targeting interface
- until that exists, `Consume` should stay on self-target, no-target, or all-enemy cards

Working baseline numbers:

- basic attack: 5 damage
- basic block: 6 block

### Starter non-basic scaffold

There are currently 6 non-basic starter cards:

- `Recovery`
- `Rotting Blow`
- `Rotting Shelter`
- `Stockpile`
- `2x Scrap Spray`

Current roles:

- `Recovery`: recover up to 2 exhausted basics to discard
- `Rotting Blow`: simple above-rate rot attack
- `Rotting Shelter`: expensive temporary defense with draw
- `Stockpile`: simple starter Hoard card
- `Scrap Spray`: all-enemy `Consume` attack that safely demonstrates delayed consume resolution

## 6. Rarity and Pool Philosophy

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
- custom Indigo cards should gradually replace those mirrored cards as real design work is completed

## 7. Recommended Build Order

Build the set by rarity across both lanes, not one lane at a time.

Order:

1. commons across reactive, consume/exhaust, Junk, glue, and a little Hoard
2. uncommons across both lanes plus bridge cards
3. rares as engines, moonshots, and spectacle

That will keep the class coherent and prevent one lane from becoming overbuilt while another is still theoretical.

## 8. Short Glossary

Consume:
Discarded from hand, then auto-play after the current card resolves, then exhaust.

Hoard:
Improves while held in hand.

Rot:
Exhausts if discarded or left in hand at end of turn.

Junk:
Shared family or tag that matters together, especially in Hoard shells.
