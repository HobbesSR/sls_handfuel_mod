# Salvager Set Implementation Plan

## Purpose

This document turns the current Salvager hand-fuel prototype and the broader set design ideas into a staged implementation plan.

It is intentionally focused on sequencing, supporting documents, and practical content scaffolding. It is not a final set bible and it is not an implementation spec for every card.

It is also not the authoritative source for the exact current starter/common target. That role now belongs to [10_SALVAGER_IMPLEMENTATION_BRIEF.md](c:\Users\Corey\Documents\Projects\sls_handfuel_mod\10_SALVAGER_IMPLEMENTATION_BRIEF.md).

## Design Inputs

Current inputs:

- [10_SALVAGER_IMPLEMENTATION_BRIEF.md](c:\Users\Corey\Documents\Projects\sls_handfuel_mod\10_SALVAGER_IMPLEMENTATION_BRIEF.md)
- the existing hand-fuel prototype and adapter behavior
- [20_SALVAGER_MECHANICS.md](c:\Users\Corey\Documents\Projects\sls_handfuel_mod\20_SALVAGER_MECHANICS.md)
- [30_SALVAGER_SET_DESIGN.md](c:\Users\Corey\Documents\Projects\sls_handfuel_mod\30_SALVAGER_SET_DESIGN.md)

Important note:

- `10_SALVAGER_IMPLEMENTATION_BRIEF.md` is now the authoritative starter/common implementation brief
- this roadmap should follow that brief for immediate content priorities
- `30_SALVAGER_SET_DESIGN.md` remains source material for broader class structure and long-term set reasoning

## Project Direction

Current working direction:

1. Create a new player color for this character: `TERRACOTTA`
2. Make a full Terracotta copy of the red card set as the initial content baseline
3. Add the starter deck, starter relics, keywords, tooltips, and support plumbing required for the character to function as a real content-bearing class
4. Focus first on the opening game experience and starter hand patterns
5. Build and iterate the common set before moving on to uncommon and rare content

This is intentionally pragmatic.

The first goal is not originality across the full card pool. The first goal is getting a playable Terracotta class shell that uses the hand-fuel mechanic and can be evaluated in early acts.

## Guiding Principles

- Reuse the red card set first to guarantee a complete and playable baseline
- Keep the hand-fuel mechanic stable while content is expanding
- Prefer documents and content scaffolding before large implementation bursts
- Evaluate the starter deck and early common experience before spending time on higher-rarity identity cards
- Use abstractions only where there is a clear engine-level fit

## Phase 0: Design Hygiene

Goal:

- normalize the design inputs so content work is not pulling from contradictory sources

Deliverables:

- this roadmap
- a resource and content checklist
- a cleaned set-bible draft later, after Terracotta structure is in place

Open questions to settle early:

- does the class keep the working fantasy and vocabulary from `30_SALVAGER_SET_DESIGN.md` such as `Consume`, `Hoard`, `Rot`, and `Junk`
- is `Terracotta` only the gameplay color, or also the public visual/faction identity
- how much of the copied red set is temporary scaffolding versus intended long-term base content

Current public-facing answer:

- the gameplay color remains `Terracotta`
- the public character name is now `Salvager`
- the thematic framing should emphasize thrift, patience, repair, and turning scraps into leverage

## Phase 1: Content Scaffolding Baseline

Goal:

- create the minimum infrastructure required for the character to own a real color and a real card pool

Implementation targets:

- create `TERRACOTTA` card color and any matching library enums
- create card backgrounds, frames, orb usage rules, and library registration paths for Terracotta cards
- establish package and naming conventions for Terracotta cards copied from red
- define starter card file layout and copied-card file layout
- define localization file layout for cards, keywords, and character strings

Deliverables:

- Terracotta color scaffolding
- content folder structure for cards and localization
- naming convention doc or section in the resource checklist

Success criteria:

- the game can recognize Terracotta cards as the class's own color
- copied red cards can exist as Terracotta-owned content without ambiguity

## Phase 2: Full Red Set Copy Baseline

Goal:

- produce a complete Terracotta starter pool quickly by copying the red set

Why this comes early:

- it gives a full playable content baseline immediately
- it removes pressure to invent dozens of cards before testing the class shell
- it allows the hand-fuel mechanic to be tested against familiar card effects

Implementation targets:

- duplicate the red card set into Terracotta equivalents
- retain card behavior initially unless a card is directly incompatible with the hand-fuel model
- track which copied cards are:
  - acceptable as-is
  - acceptable for now but likely to be replaced
  - incompatible and in need of immediate redesign

Recommended support document:

- `TERRACOTTA_RED_SET_AUDIT.md`

That audit should classify each copied red card by:

- early-game fit
- fuel-economy fit
- obvious mechanical tension with the class
- keep / adjust / replace recommendation

Success criteria:

- Terracotta has a full baseline set that can be loaded and played
- obvious anti-synergy outliers are known, even if not yet fixed

## Phase 3: Starter Package

Goal:

- implement the deliberate starter package defined in [10_SALVAGER_IMPLEMENTATION_BRIEF.md](c:\Users\Corey\Documents\Projects\sls_handfuel_mod\10_SALVAGER_IMPLEMENTATION_BRIEF.md)

This is the first design-critical phase.

The first actual gameplay focus is:

- what does the opening hand feel like
- what does the first hallway fight feel like
- what does the first few floors teach the player

Implementation targets:

- starter strikes
- starter defends
- starter non-basic cards
- starter relic setup
- starter deck composition

Design targets:

- opening turns must demonstrate hand-as-resource cleanly
- fuel choice must feel understandable
- the deck must not implode immediately
- starter cards must expose the intended class rhythm

Deliverables:

- `TERRACOTTA_STARTER_PACKAGE.md`

That document should lock:

- card names
- card roles
- rough numbers
- intended lesson each starter card teaches
- test notes from actual early-floor runs

Success criteria:

- beginning-of-run gameplay is coherent
- the starter deck is no longer just a test pile
- the class teaches its core rule set in the first few combats

## Phase 4: Keywords, Tooltips, and Support Rules

Goal:

- formalize the vocabulary needed by the class before expanding the common set

Initial likely keyword/support candidates from the set design source:

- `Consume`
- `Hoard`
- `Rot`
- `Junk`

Implementation targets:

- keyword registration
- tooltip text
- localization entries
- any required card tags or tracking helpers
- any end-of-turn / discard / exhaust hooks needed by those mechanics

Important constraint:

- do not build every synergy engine immediately
- only implement the support needed by the starter package and first-pass commons

Current implementation lesson:

- if `Consume`, `Rot`, and `Hoard` are intended to be portable mechanics, their runtime support should live as generic card-behavior infrastructure rather than Terracotta-only helper code

Deliverables:

- `TERRACOTTA_KEYWORD_RULES.md`

That document should define:

- public-facing rules text
- implementation notes
- what game state each keyword depends on
- what should remain lightweight versus engine-heavy

Success criteria:

- keyword text is stable enough to put on cards
- the rules are understandable in play
- support code is not overbuilt ahead of the card set

## Phase 5: Common Set First Pass

Goal:

- build the common set as the main coherence pass for the class

This is the central content iteration phase.

Why commons first:

- commons define the class's real floor
- commons determine whether runs feel coherent
- if commons do not work, uncommon and rare identity cards cannot fix the class

Implementation targets:

- first-pass common slate
- copied red commons retained where appropriate
- Terracotta-native commons added where necessary
- replacement of copied commons that clearly fail the hand-fuel model

Recommended structure:

- define the full common slot list first
- then mark each slot as:
  - copied red placeholder
  - adjusted red derivative
  - new Terracotta-native design

Deliverables:

- `TERRACOTTA_COMMON_SET_BOARD.md`

That document should include:

- card slot list
- intended role
- lane / family tags
- implementation status
- playtest observations
- keep / revise / replace decision

Success criteria:

- common pools produce coherent drafts
- early acts feel structurally sound
- the class's core identity emerges without relying on rares

## Phase 6: Early Playtest Loop

Goal:

- repeatedly test only the part of the class that exists so far

Initial playtest loop focus:

- starter hand
- first three combats
- first elite approach
- common-card pick texture

Questions to answer:

- does paying with hand cards feel like tension or just tax
- are starter cards individually understandable
- do copied red cards create obviously wrong incentives
- does Terracotta feel coherent before uncommon support exists
- which common roles are still missing

Recommended test document:

- `TERRACOTTA_PLAYTEST_LOG.md`

Use flat entries per run:

- run date
- notable cards seen
- opening hand feel
- floor 1-5 notes
- fuel pain points
- standout cards
- obvious cuts or redesign candidates

Success criteria:

- repeated early runs point to the same missing pieces
- the class starts to develop a stable common identity

## Phase 7: Common Set Revision

Goal:

- revise commons until the set feels coherent before moving up-rarity

This is the explicit gate before uncommons and rares.

Do not move on simply because enough cards exist.

Move on only when:

- the starter package feels intentional
- copied red placeholders have been triaged
- common drafts support the class properly
- the mechanic is carrying gameplay instead of merely being novel

## Phase 8: Uncommons and Rares Later

Goal:

- only after common coherence is proven, expand upward

Uncommons should:

- connect lanes
- add real support packages
- deepen build paths

Rares should:

- define engines
- create moonshots
- provide spectacle

This phase is intentionally deferred.

## Recommended Working Documents

Core planning docs for the next stretch:

- `40_SALVAGER_IMPLEMENTATION_PLAN.md`
- `50_SALVAGER_RESOURCE_CHECKLIST.md`
- `TERRACOTTA_RED_SET_AUDIT.md`
- `TERRACOTTA_STARTER_PACKAGE.md`
- `TERRACOTTA_KEYWORD_RULES.md`
- `TERRACOTTA_COMMON_SET_BOARD.md`
- `TERRACOTTA_PLAYTEST_LOG.md`

Suggested order to author them:

1. `50_SALVAGER_RESOURCE_CHECKLIST.md`
2. `TERRACOTTA_RED_SET_AUDIT.md`
3. `TERRACOTTA_STARTER_PACKAGE.md`
4. `TERRACOTTA_KEYWORD_RULES.md`
5. `TERRACOTTA_COMMON_SET_BOARD.md`
6. `TERRACOTTA_PLAYTEST_LOG.md`

## Immediate Next Steps

The next actual work should be:

1. Lock Terracotta as the content color direction
2. Create the resource checklist and content inventory
3. Audit the red set copy plan before implementation
4. Define the starter package in a dedicated doc
5. Implement only enough content infrastructure to support starter-hand testing

## Immediate Non-Goals

Do not prioritize yet:

- full uncommon design
- full rare design
- final art direction
- exhaustive balance
- broad refactors unrelated to content scaffolding

The right near-term question is:

- does the Terracotta starter package and early common environment make the hand-fuel class feel coherent

