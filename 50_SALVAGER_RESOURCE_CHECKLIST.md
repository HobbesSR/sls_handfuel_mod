# Salvager Resource Checklist

## Purpose

This document lists the documents, assets, code scaffolding, and content resources needed to move from the current hand-fuel prototype to a real Terracotta Salvager card set.

It is a planning and tracking document, not an implementation log.

It should not overrule the implementation brief or mechanics doc. It exists to track work and missing support material.

## Source Documents

Use documents in this order:

- [10_SALVAGER_IMPLEMENTATION_BRIEF.md](c:\Users\Corey\Documents\Projects\sls_handfuel_mod\10_SALVAGER_IMPLEMENTATION_BRIEF.md)
- [20_SALVAGER_MECHANICS.md](c:\Users\Corey\Documents\Projects\sls_handfuel_mod\20_SALVAGER_MECHANICS.md)
- [30_SALVAGER_SET_DESIGN.md](c:\Users\Corey\Documents\Projects\sls_handfuel_mod\30_SALVAGER_SET_DESIGN.md)
- [40_SALVAGER_IMPLEMENTATION_PLAN.md](c:\Users\Corey\Documents\Projects\sls_handfuel_mod\40_SALVAGER_IMPLEMENTATION_PLAN.md)

Notes:

- `10_SALVAGER_IMPLEMENTATION_BRIEF.md` is the current starter/common implementation brief
- `20_SALVAGER_MECHANICS.md` is the mechanic-state doc
- `30_SALVAGER_SET_DESIGN.md` is the broader set-design doc
- `40_SALVAGER_IMPLEMENTATION_PLAN.md` is the staged roadmap

## Planning Documents To Create

These should exist before or during early content implementation:

- `TERRACOTTA_RED_SET_AUDIT.md`
- `TERRACOTTA_STARTER_PACKAGE.md`
- `TERRACOTTA_KEYWORD_RULES.md`
- `TERRACOTTA_COMMON_SET_BOARD.md`
- `TERRACOTTA_PLAYTEST_LOG.md`

## Card Content Inventory

### Card Definitions

Need:

- Terracotta card base class or conventions
- copied Terracotta versions of the red card set
- Terracotta starter basics
- Terracotta starter non-basics
- common-set tracking list

Checklist:

- define card package layout
- define naming convention for copied red cards
- define naming convention for Terracotta-native cards
- mark placeholder copies versus intended final cards

### Card Color Infrastructure

Need:

- Terracotta card color enum entries
- Terracotta library color handling
- Terracotta card backgrounds / frames / orb compatibility
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
- any Terracotta-specific card assets

Current state:

- prototype uses existing images and test scaffolding

Current thematic direction:

- avoid filthy caricature framing
- more resourceful drifter / salvage sage / alley alchemist
- repaired gear, tagged bundles, baskets, trinkets, containers, and practiced reuse
- visual language should support thrift, patience, and conversion

Checklist:

- decide whether existing prototype visuals are temporary only
- define minimum art needed before Terracotta cards are comfortable to test
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
- separate prototype strings from real Terracotta content strings
- decide whether copied red cards reuse original names temporarily or get Terracotta-specific names immediately

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
- define whether starter cards are copied placeholders or Terracotta-native from day one

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
- identify the first Terracotta-native commons required for identity

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
- prefer generic card-behavior support for `Consume`, `Rot`, and `Hoard` over character-specific helper ownership

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

- create `TERRACOTTA_PLAYTEST_LOG.md`
- define one run-entry template
- record problem cards and category of failure

## Recommended Immediate Document Order

Create next:

1. `TERRACOTTA_RED_SET_AUDIT.md`
2. `TERRACOTTA_STARTER_PACKAGE.md`
3. `TERRACOTTA_KEYWORD_RULES.md`

Create after starter work begins:

4. `TERRACOTTA_COMMON_SET_BOARD.md`
5. `TERRACOTTA_PLAYTEST_LOG.md`

## Immediate Resource Risks

Known planning risks:

- copied red cards may distort the class identity if left in place too long
- Terracotta color/UI work may expand faster than starter gameplay work
- keyword implementation may get ahead of actual card usage
- the source design document currently mixes durable ideas with temporary naming/frame choices

## Current Recommendation

Use the current prototype as the mechanic bed.

Use the Terracotta plan to add content in this order:

1. color and content scaffolding
2. red set audit
3. starter package
4. keyword/tooltips support
5. common set
6. early playtesting

That keeps implementation grounded in real playable checkpoints instead of abstract card-pool ambition.

