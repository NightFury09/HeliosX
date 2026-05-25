---
name: Luminous Industrial HMI
colors:
  surface: '#111415'
  surface-dim: '#111415'
  surface-bright: '#373a3b'
  surface-container-lowest: '#0c0f10'
  surface-container-low: '#191c1d'
  surface-container: '#1d2021'
  surface-container-high: '#282a2b'
  surface-container-highest: '#323536'
  on-surface: '#e1e3e4'
  on-surface-variant: '#bbcabf'
  inverse-surface: '#e1e3e4'
  inverse-on-surface: '#2e3132'
  outline: '#86948a'
  outline-variant: '#3c4a42'
  surface-tint: '#4edea3'
  primary: '#4edea3'
  on-primary: '#003824'
  primary-container: '#10b981'
  on-primary-container: '#00422b'
  inverse-primary: '#006c49'
  secondary: '#adc6ff'
  on-secondary: '#002e6a'
  secondary-container: '#0566d9'
  on-secondary-container: '#e6ecff'
  tertiary: '#ffb95f'
  on-tertiary: '#472a00'
  tertiary-container: '#e29100'
  on-tertiary-container: '#523200'
  error: '#ffb4ab'
  on-error: '#690005'
  error-container: '#93000a'
  on-error-container: '#ffdad6'
  primary-fixed: '#6ffbbe'
  primary-fixed-dim: '#4edea3'
  on-primary-fixed: '#002113'
  on-primary-fixed-variant: '#005236'
  secondary-fixed: '#d8e2ff'
  secondary-fixed-dim: '#adc6ff'
  on-secondary-fixed: '#001a42'
  on-secondary-fixed-variant: '#004395'
  tertiary-fixed: '#ffddb8'
  tertiary-fixed-dim: '#ffb95f'
  on-tertiary-fixed: '#2a1700'
  on-tertiary-fixed-variant: '#653e00'
  background: '#111415'
  on-background: '#e1e3e4'
  surface-variant: '#323536'
typography:
  display-lg:
    fontFamily: Space Grotesk
    fontSize: 48px
    fontWeight: '700'
    lineHeight: 56px
    letterSpacing: -0.02em
  display-md:
    fontFamily: Space Grotesk
    fontSize: 36px
    fontWeight: '600'
    lineHeight: 44px
    letterSpacing: -0.01em
  headline-sm:
    fontFamily: Space Grotesk
    fontSize: 24px
    fontWeight: '600'
    lineHeight: 32px
  body-lg:
    fontFamily: Space Grotesk
    fontSize: 18px
    fontWeight: '400'
    lineHeight: 28px
  body-md:
    fontFamily: Space Grotesk
    fontSize: 16px
    fontWeight: '400'
    lineHeight: 24px
  label-caps:
    fontFamily: Space Grotesk
    fontSize: 12px
    fontWeight: '700'
    lineHeight: 16px
    letterSpacing: 0.1em
  data-mono:
    fontFamily: Space Grotesk
    fontSize: 20px
    fontWeight: '500'
    lineHeight: 24px
    letterSpacing: -0.02em
rounded:
  sm: 0.25rem
  DEFAULT: 0.5rem
  md: 0.75rem
  lg: 1rem
  xl: 1.5rem
  full: 9999px
spacing:
  base: 8px
  xs: 4px
  sm: 12px
  md: 24px
  lg: 40px
  xl: 64px
  gutter: 16px
  margin: 24px
---

## Brand & Style
The design system is engineered for high-performance solar energy management, blending **industrial precision** with a **premium consumer electronics** aesthetic. It targets energy professionals and tech-forward homeowners who require immediate clarity and a sense of "mission control" sophistication.

The visual direction is **Minimalist Glassmorphism**. It utilizes deep, monochromatic foundations to allow vibrant, functional color accents to communicate system status instantly. The interface should feel like a high-end physical glass console integrated into a luxury hardware unit—technical, geometric, and exceptionally clean.

## Colors
The palette is rooted in a **Deep Charcoal (#121212)** background to minimize light pollution in industrial environments and maximize the "pop" of data visualizations.

- **Solar (PV):** Emerald Green (#10B981) represents generation and environmental vitality.
- **Grid:** Electric Blue (#3B82F6) denotes the stable, high-voltage utility connection.
- **Battery:** Amber (#F59E0B) indicates stored energy and chemical potential.
- **Load:** Soft White (#F9FAFB) is used for consumption data and primary text, ensuring maximum legibility.
- **Fault:** Pulsing Red (#EF4444) is reserved strictly for critical alerts and system failures.

Surface containers use a semi-transparent white (5-8% opacity) with a heavy background blur (20px-40px) to create the glass effect.

## Typography
**Space Grotesk** is the sole typeface, chosen for its geometric purity and technical "monospaced-adjacent" feel that suits numerical data. 

- **Data Visuals:** Large "Display" sizes should be used for real-time wattage or percentage readings to ensure visibility from a distance.
- **Hierarchy:** Use "Label-Caps" for categories (e.g., PV INPUT, BATTERY TEMP) to distinguish metadata from active readings.
- **Legibility:** Maintain a minimum weight of 400 for body text to ensure clarity against blurred glass backgrounds.

## Layout & Spacing
The layout follows a strict **8dp grid system**, ensuring all elements align with mathematical precision. 

- **Dashboard Layout:** Use a 12-column fluid grid for desktop/tablet HMI screens. 
- **Modules:** Content is organized into discrete "Glass Cards." Standardize internal padding at `24px (md)` to create a spacious, premium feel.
- **Power Flow:** Centralize the most critical flow diagram, using `64px (xl)` spacing to isolate the primary system status from peripheral settings.
- **Mobile/HMI Small:** Collapse to a single column with `16px` margins, prioritizing the "Current Yield" and "Battery State" at the top.

## Elevation & Depth
Depth is communicated through **Optical Stacked Layers** rather than heavy shadows:

1.  **Level 0 (Background):** Deep Charcoal (#121212) canvas.
2.  **Level 1 (Main Cards):** Glassmorphism with 20px background blur, 1px stroke (white at 10% opacity), and a very subtle ambient shadow (0px 4px 20px rgba(0,0,0,0.4)).
3.  **Level 2 (Active States/Modals):** Increased glass opacity (12%) and a brighter 1px border. 
4.  **Z-Axis:** Interactive elements (buttons, sliders) should appear slightly "floated" above the glass surface using a tighter, darker shadow to indicate tactility.

## Shapes
The shape language is **Technical-Geometric**. 

- **Containers:** Apply a `1rem` (rounded-lg) radius to all main glass cards to soften the industrial edge and align with "Apple-like" hardware design.
- **Small Elements:** Buttons and input fields use a `0.5rem` radius for a tighter, more precise appearance.
- **Graphs:** Line charts should use a slight smoothing (bezier) but maintain crisp points at data intervals to emphasize accuracy.

## Components
- **Buttons:** Primary buttons use a solid Emerald Green or Electric Blue. Secondary buttons are "Ghost" style with a 1px border and high-blur background.
- **Status Chips:** Small, pill-shaped indicators with a subtle glow (outer shadow) in the color of the respective power source (e.g., a green glowing dot for "PV Active").
- **Gauges:** Circular "Donut" charts with high-contrast strokes. Use the primary accent color for the progress arc and a muted 10% opacity version for the track.
- **Power Flow Lines:** Animated paths between icons. Use "marching ants" or gradient pulses to indicate the direction and speed of energy flow.
- **Input Fields:** Darker than the card background (2% opacity) with a clear 1px "Focus" border using the Grid (Blue) or Solar (Green) accent.
- **Data Cards:** Title at the top-left in Label-Caps, large metric in the center, and a secondary trend sparkline at the bottom.