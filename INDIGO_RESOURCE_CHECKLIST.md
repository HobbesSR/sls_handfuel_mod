# Indigo Resource Checklist

## Purpose

This document lists the documents, assets, code scaffolding, and content resources needed to move from the current hand-fuel prototype to a real Indigo card set.

It is a planning and tracking document, not an implementation log.

## Source Documents

Keep these as the main planning inputs:

- [HAND_FUEL_CHARACTER_DESIGN.md](c:\Users\Corey\Documents\Projects\sls_handfuel_mod\HAND_FUEL_CHARACTER_DESIGN.md)
- [Fuel_cards_set_design.md](c:\Users\Corey\Documents\Projects\sls_handfuel_mod\Fuel_cards_set_design.md)
- [INDIGO_SET_IMPLEMENTATION_PLAN.md](c:\Users\Corey\Documents\Projects\sls_handfuel_mod\INDIGO_SET_IMPLEMENTATION_PLAN.md)

Notes:

- `Fuel_cards_set_design.md` contains strong design material, but it should be normalized later because it currently has encoding artifacts
- `HAND_FUEL_CHARACTER_DESIGN.md` is the mechanic-state doc
- `INDIGO_SET_IMPLEMENTATION_PLAN.md` is the staged roadmap

## Planning Documents To Create

These should exist before or during early content implementation:

- `INDIGO_RED_SET_AUDIT.md`
- `INDIGO_STARTER_PACKAGE.md`
- `INDIGO_KEYWORD_RULES.md`
- `INDIGO_COMMON_SET_BOARD.md`
- `INDIGO_PLAYTEST_LOG.md`

## Card Content Inventory

### Card Definitions

Need:

- Indigo card base class or conventions
- copied Indigo versions of the red card set
- Indigo starter basics
- Indigo starter non-basics
- common-set tracking list

Checklist:

- define card package layout
- define naming convention for copied red cards
- define naming convention for Indigo-native cards
- mark placeholder copies versus intended final cards

### Card Color Infrastructure

Need:

- Indigo card color enum entries
- Indigo library color handling
- Indigo card backgrounds / frames / orb compatibility
- card registration pathways

Checklist:

- player color enum
- card color enum
- library color enum if needed by BaseMod usage
- color registration
- card UI frame/background resources

### Card Tags / Family Markers

Likely needed:

- `Junk` family tag
- any helper grouping tags used only for internal organization

Checklist:

- decide whether `Junk` is a public-facing tag, an implementation tag, or both
- decide whether starter basics are `Junk`
- decide whether copied red cards receive no family tag by default or temporary tags for testing

## Keyword / Tooltip Resources

Likely keyword set from design source:

- `Consume`
- `Hoard`
- `Rot`
- `Junk` as family vocabulary or tooltip entry if needed

Need:

- keyword localization entries
- tooltip registration
- implementation notes for when each keyword triggers
- wording lock before large card-writing starts

Checklist:

- keyword names
- exact reminder text
- whether keyword appears inline or as named tooltip only
- whether family terms like `Junk` are represented by tag icons, plain text, or both

## Character Resources

Need review or replacement for:

- character select button
- portrait
- shoulders
- corpse image
- card color VFX identity
- any Indigo-specific card assets

Current state:

- prototype uses existing images and test scaffolding

Checklist:

- decide whether existing prototype visuals are temporary only
- define minimum art needed before Indigo cards are comfortable to test
- list which visuals can remain borrowed during early content iteration

## Localization Resources

Need:

- character strings
- card strings
- keyword strings
- relic strings if relics become custom later
- power strings if keyword support creates powers later

Checklist:

- create localization folder structure
- separate prototype strings from real Indigo content strings
- decide whether copied red cards reuse original names temporarily or get Indigo-specific names immediately

## Red Set Copy Audit Inputs

Before implementing copied red cards, gather:

- full red card list by rarity
- red starter deck contents
- red commons most likely to fail under hand-fuel economics
- red cards that create energy, draw, exhaust, or retention interactions worth special attention

Audit outputs needed:

- keep as-is
- keep for now
- modify soon
- replace immediately

## Starter Package Design Inputs

Need a dedicated starter package design sheet covering:

- starter strikes
- starter defends
- starter non-basic cards
- starter relic
- intended first three turns of gameplay
- what the deck is teaching

Checklist:

- define starter card names
- define provisional numbers
- define role of each starter card
- define whether starter cards are copied placeholders or Indigo-native from day one

## Common Set Planning Inputs

Need:

- common role grid
- lane / family mapping
- placeholder versus native design marker
- playtest notes attached to each common slot

Checklist:

- define common count target
- define first-pass common slot roles
- decide which copied red commons stay as scaffolding
- identify the first Indigo-native commons required for identity

## Implementation Support Files

Likely support code and data areas:

- card registration scaffolding
- color registration
- keyword registration
- tags
- tooltips
- any discard / exhaust / end-of-turn helper logic needed by `Consume`, `Hoard`, or `Rot`

Checklist:

- avoid implementing support code before its card usage is defined
- prefer one source-of-truth helper per keyword or tracked state
- keep starter support minimal until the first real content pass is stable

## Testing Resources

Need:

- repeatable early-run test process
- log template
- benchmark test questions

Minimum test scenarios:

- opening hand with starter deck only
- first hallway fights
- first elite approach
- card reward choices among common candidates
- behavior of copied red cards under fuel surcharge

Checklist:

- create `INDIGO_PLAYTEST_LOG.md`
- define one run-entry template
- record problem cards and category of failure

## Recommended Immediate Document Order

Create next:

1. `INDIGO_RED_SET_AUDIT.md`
2. `INDIGO_STARTER_PACKAGE.md`
3. `INDIGO_KEYWORD_RULES.md`

Create after starter work begins:

4. `INDIGO_COMMON_SET_BOARD.md`
5. `INDIGO_PLAYTEST_LOG.md`

## Immediate Resource Risks

Known planning risks:

- copied red cards may distort the class identity if left in place too long
- Indigo color/UI work may expand faster than starter gameplay work
- keyword implementation may get ahead of actual card usage
- the source design document currently mixes durable ideas with temporary naming/frame choices

## Current Recommendation

Use the current prototype as the mechanic bed.

Use the Indigo plan to add content in this order:

1. color and content scaffolding
2. red set audit
3. starter package
4. keyword/tooltips support
5. common set
6. early playtesting

That keeps implementation grounded in real playable checkpoints instead of abstract card-pool ambition.
