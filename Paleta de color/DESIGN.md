---
name: Nordic Precision
colors:
  surface: '#fbf8ff'
  surface-dim: '#dad9e3'
  surface-bright: '#fbf8ff'
  surface-container-lowest: '#ffffff'
  surface-container-low: '#f4f2fd'
  surface-container: '#eeedf7'
  surface-container-high: '#e8e7f1'
  surface-container-highest: '#e3e1ec'
  on-surface: '#1a1b22'
  on-surface-variant: '#47464b'
  inverse-surface: '#2f3038'
  inverse-on-surface: '#f1effa'
  outline: '#77767b'
  outline-variant: '#c8c5cb'
  surface-tint: '#5f5e61'
  primary: '#000000'
  on-primary: '#ffffff'
  primary-container: '#1b1b1e'
  on-primary-container: '#858387'
  inverse-primary: '#c8c5ca'
  secondary: '#0058be'
  on-secondary: '#ffffff'
  secondary-container: '#2170e4'
  on-secondary-container: '#fefcff'
  tertiary: '#000000'
  on-tertiary: '#ffffff'
  tertiary-container: '#1d1b16'
  on-tertiary-container: '#88837c'
  error: '#ba1a1a'
  on-error: '#ffffff'
  error-container: '#ffdad6'
  on-error-container: '#93000a'
  primary-fixed: '#e4e1e6'
  primary-fixed-dim: '#c8c5ca'
  on-primary-fixed: '#1b1b1e'
  on-primary-fixed-variant: '#47464a'
  secondary-fixed: '#d8e2ff'
  secondary-fixed-dim: '#adc6ff'
  on-secondary-fixed: '#001a42'
  on-secondary-fixed-variant: '#004395'
  tertiary-fixed: '#e8e2d9'
  tertiary-fixed-dim: '#cbc6bd'
  on-tertiary-fixed: '#1d1b16'
  on-tertiary-fixed-variant: '#494640'
  background: '#fbf8ff'
  on-background: '#1a1b22'
  surface-variant: '#e3e1ec'
typography:
  headline-xl:
    fontFamily: Plus Jakarta Sans
    fontSize: 40px
    fontWeight: '600'
    lineHeight: 48px
    letterSpacing: -0.03em
  headline-xl-mobile:
    fontFamily: Plus Jakarta Sans
    fontSize: 30px
    fontWeight: '600'
    lineHeight: 38px
    letterSpacing: -0.025em
  headline-lg:
    fontFamily: Plus Jakarta Sans
    fontSize: 28px
    fontWeight: '600'
    lineHeight: 36px
    letterSpacing: -0.02em
  headline-md:
    fontFamily: Plus Jakarta Sans
    fontSize: 22px
    fontWeight: '500'
    lineHeight: 30px
    letterSpacing: -0.015em
  headline-sm:
    fontFamily: Plus Jakarta Sans
    fontSize: 18px
    fontWeight: '500'
    lineHeight: 26px
    letterSpacing: -0.01em
  body-lg:
    fontFamily: Plus Jakarta Sans
    fontSize: 16px
    fontWeight: '400'
    lineHeight: 26px
    letterSpacing: -0.005em
  body-md:
    fontFamily: Plus Jakarta Sans
    fontSize: 14px
    fontWeight: '400'
    lineHeight: 22px
    letterSpacing: 0em
  body-sm:
    fontFamily: Plus Jakarta Sans
    fontSize: 13px
    fontWeight: '400'
    lineHeight: 18px
    letterSpacing: 0em
  label-md:
    fontFamily: Plus Jakarta Sans
    fontSize: 12px
    fontWeight: '500'
    lineHeight: 16px
    letterSpacing: 0.02em
  label-sm:
    fontFamily: Plus Jakarta Sans
    fontSize: 11px
    fontWeight: '600'
    lineHeight: 14px
    letterSpacing: 0.04em
rounded:
  sm: 0.125rem
  DEFAULT: 0.25rem
  md: 0.375rem
  lg: 0.5rem
  xl: 0.75rem
  full: 9999px
spacing:
  gutter: 1.5rem
  gutter-mobile: 0.75rem
  margin: 3rem
  margin-mobile: 1rem
  space-xs: 0.25rem
  space-sm: 0.5rem
  space-md: 1rem
  space-lg: 1.5rem
  space-xl: 2.5rem
---

## Brand & Style

This design system delivers a calm, frictionless utility experience inspired by minimalist Nordic industrial design and high-end consumer hardware interfaces. 

### Brand Personality
- **Quiet Authority:** Confident without shouting. Eliminates decorative noise in favor of functional clarity.
- **Architectural Calm:** Structured layouts with intentional breathing room create space for cognitive focus.
- **Frictionless Precision:** Micro-interactions and typography communicate responsiveness, reliability, and tactile precision.

### Target Audience
Discerning professionals, knowledge workers, and design-conscious operators who demand high-efficiency tooling with an unobtrusive, elegant footprint.

### Emotional Response
Users should feel unhurried, organized, and focused—operating inside a pristine, serene workspace where data and content command primary focus.

## Colors

The palette operates primarily on a strictly calibrated monochromatic scale, utilizing an ink-black primary tone and neutral zinc scales to preserve visual clarity. 

### Palette Architecture
- **Primary Canvas (`#ffffff`):** Pure, uncompromised white canvas serving as the baseline ground.
- **Subtle Surface (`#fafafa` to `#f4f4f5`):** Soft, non-glare surface fills for containers, nested viewports, and table row groupings.
- **Border Baseline (`#e4e4e7`):** Hairline separation defining elements without drawing visual focus away from content.
- **Foreground / Primary (`#18181b` / `#09090b`):** Deep carbon black used for high-emphasis typography, primary state actions, and deliberate structural anchors.
- **Muted Foreground (`#71717a`):** Neutral zinc for secondary typography, icons, and non-active controls.
- **Accent Indigo/Slate (`#3b82f6`):** Applied with extreme restraint; reserved for focused inputs, live status indicators, and selected states.

### Contrast & State Rules
Interactive states rely on micro-shifts in surface luminosity rather than color shifts. Hover states shift background surfaces from pure white to `#f4f4f5`, while active states deepen borders to `#d4d4d8`.

## Typography

The typographic system relies on **Plus Jakarta Sans** across all levels, calibrated with tight negative letter spacing on larger scales and subtle positive tracking on technical labels.

### Hierarchy & Legibility
- **Headlines:** Clean, modern geometric forms with restrained weights (500–600). Never heavy or aggressive.
- **Body Text:** Balanced proportioning with generous line-height ratios (1.5–1.6) to preserve scanning comfort during long sessions.
- **Labels & Metadata:** Compact sizes (`11px` to `12px`) with deliberate tracking (`+0.02em` to `+0.04em`) to ensure legibility in tight interfaces, tables, and sidebars without requiring heavy pill enclosures.

## Layout & Spacing

The layout is governed by an asymmetric fluid-column system with structured content wells and generous spatial margins.

### Grid Architecture
- **Desktop (>= 1024px):** 12-column layout with `margin: 3rem` and `gutter: 1.5rem`. Max content container width clamped at `1280px` to maintain optimal line lengths.
- **Tablet (768px – 1023px):** 8-column layout with `margin: 2rem` and `gutter: 1.25rem`.
- **Mobile (< 768px):** 4-column layout with `margin: 1rem` and `gutter: 0.75rem`.

### Spatial Rhythm
Density is controlled via distinct whitespace steps:
- Internal component spacing uses `space-xs` (4px) and `space-sm` (8px) for related inline items.
- Section boundaries and card interiors use `space-md` (16px) and `space-lg` (24px).
- Generous outer breathing room relies on `space-xl` (40px) to maintain a light, gallery-like layout balance.

## Elevation & Depth

This system avoids heavy drop shadows and dramatic skeuomorphic layering. Depth is established through structural perimeter hairlines and subtle tonal layering.

### Surface Hierarchy
1. **Canvas Level (Ground):** Pure white (`#ffffff`) background.
2. **Container Level (Resting):** Pure white container bounded by a 1px border (`#e4e4e7`). No drop shadow.
3. **Elevated Overlays (Flyouts, Menus, Modals):** White surface with a 1px border (`#e4e4e7`) and an ambient, ultra-diffused shadow:
   `box-shadow: 0 4px 20px -2px rgba(9, 9, 11, 0.05), 0 2px 6px -1px rgba(9, 9, 11, 0.02)`.
4. **Recessed Wells:** `#f4f4f5` background fills without borders, used for search bars, inline code segments, or inactive state plates.

## Shapes

The geometric signature is subtle and disciplined (`roundedness: 1`). Corner radii are crisp and modern, avoiding both harsh right angles and playful rounded pill geometries.

### Corner Radii Guidelines
- **Interactive Controls (Inputs, Buttons, Segmented Controls):** `0.25rem` (4px). Matches the low-radius utility aesthetic of precision software.
- **Containers & Cards:** `0.5rem` (8px) via `rounded-lg`.
- **Modals & Overlays:** `0.75rem` (12px) via `rounded-xl`.
- **Badges & Tags:** Standardized to `0.25rem` (4px) rectangles. Rounded pills (`9999px`) are intentionally avoided to preserve a crisp, technical look.

## Components

### Buttons
- **Primary:** Background `#18181b`, text `#ffffff`, border none, radius `4px`, padding `8px 16px`. Active state transitions subtly to `#27272a`.
- **Secondary:** Background `#ffffff`, text `#18181b`, border `1px solid #e4e4e7`, radius `4px`, padding `8px 16px`. Hover state changes background to `#f4f4f5`.
- **Ghost:** Background transparent, text `#71717a`, border none, padding `8px 12px`. Hover state shifts text to `#18181b`.

### Input Fields
- **Resting:** Background `#ffffff`, border `1px solid #e4e4e7`, radius `4px`, padding `8px 12px`, typography `body-md`. Placeholder color `#a1a1aa`.
- **Focus:** Border `1px solid #18181b` (or subtle `#3b82f6` accent ring: `box-shadow: 0 0 0 1px #3b82f6`). Background remains pure white.

### Chips & Tags
- **Style:** Understated rectangular badges with `4px` corner radius. 
- **Palette:** Background `#f4f4f5`, text `#71717a`, border `1px solid #e4e4e7`. Never saturated or pill-shaped.

### Cards & Panels
- **Style:** Background `#ffffff`, border `1px solid #e4e4e7`, radius `8px`, padding `24px`. Zero baseline shadow.
- **Section Dividers:** 1px hairline rule using `#f4f4f5`.

### Checkboxes & Radios
- **Unchecked:** `16px x 16px`, border `1px solid #d4d4d8`, background `#ffffff`, radius `3px` for checkboxes, circular for radios.
- **Checked:** Background `#18181b`, border `#18181b`, checkmark/indicator `#ffffff`.

### Lists & Data Rows
- **Structure:** Clean hairline rows separated by `1px solid #f4f4f5`.
- **Hover State:** Entire row gently shifts background to `#fafafa` with a smooth 150ms transition. No harsh bounding boxes.

### Segmented Controls
- **Track:** Background `#f4f4f5`, padding `2px`, radius `6px`.
- **Selected Item:** Background `#ffffff`, text `#18181b`, border `1px solid #e4e4e7`, radius `4px`, subtle micro-shadow (`0 1px 2px rgba(0, 0, 0, 0.04)`).
- **Unselected Item:** Text `#71717a`, border transparent.