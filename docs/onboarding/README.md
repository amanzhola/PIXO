# 🎬 PIXO — Detailed Onboarding & Premium Flow

<p align="center">
  <img src="screenshots/Splash.png" width="180">
</p>

---

# 🚀 Flow Overview

| 🚀 Step               | 📖 Description                                | ⚡ Result                      | 🔒 Premium Logic     |
| --------------------- | --------------------------------------------- | ----------------------------- | -------------------- |
| Splash                | First screen opens automatically              | Transition to onboarding      | Initial app launch   |
| 11 onboarding screens | Continue button + interactive sliders         | Sequential onboarding flow    | Feature education    |
| Template onboarding   | Opens after tools onboarding                  | Transition to Prompt          | Premium introduction |
| Prompt onboarding     | Opens after Templates                         | Transition to Rate flow       | AI prompt education  |
| Rate flow             | Rate0 → Rate1 → Rate2 → Rate3 → Rate4 → Rate5 | Interactive rating experience | User engagement      |
| Paywall               | Paywall1 → Paywall2                           | Subscription selection        | Monetization         |

---

# 🧩 Splash Screen

| 📱 Splash                                      | ⚡ Behavior          | 🔄 Transition | ✅ Result         |
| ---------------------------------------------- | ------------------- | ------------- | ---------------- |
| <img src="screenshots/Splash.png" width="170"> | Opens automatically | Auto hides    | Opens onboarding |

---

# 🎨 Tools Onboarding

| AI Enhancer                                         | Glam Makeup                                         | Remove Objects                                         | Remove Background                                         |
| --------------------------------------------------- | --------------------------------------------------- | ------------------------------------------------------ | --------------------------------------------------------- |
| <img src="screenshots/AI_ENHANCER.png" width="150"> | <img src="screenshots/GLAM_MAKEUP.png" width="150"> | <img src="screenshots/REMOVE_OBJECTS.png" width="150"> | <img src="screenshots/REMOVE_BACKGROUND.png" width="150"> |

| Skin Improve                                         | Change Scene                                         | Hair Studio                                         | Smile Edit                                         |
| ---------------------------------------------------- | ---------------------------------------------------- | --------------------------------------------------- | -------------------------------------------------- |
| <img src="screenshots/SKIN_IMPROVE.png" width="150"> | <img src="screenshots/CHANGE_SCENE.png" width="150"> | <img src="screenshots/HAIR_STUDIO.png" width="150"> | <img src="screenshots/SMILE_EDIT.png" width="150"> |

| Ghostface                                         | Ghibli                                         | Prompt                                         | Template                                         |
| ------------------------------------------------- | ---------------------------------------------- | ---------------------------------------------- | ------------------------------------------------ |
| <img src="screenshots/GHOSTFACE.png" width="150"> | <img src="screenshots/GHIBLI.png" width="150"> | <img src="screenshots/PROMPT.png" width="150"> | <img src="screenshots/TEMPLATE.png" width="150"> |

---

# 🖼 Interactive Slider System

| 📱 Tool Cards                    | 📱 Open Screens            | ⚡ Interaction        | 🧠 Purpose               |
| -------------------------------- | -------------------------- | -------------------- | ------------------------ |
| Interactive before/after sliders | Full interactive compare   | User can drag slider | Demonstrate AI result    |
| 2-photo comparison               | Dynamic overlay comparison | Swipe interaction    | Premium feature showcase |

---

# 🧰 Tools Grid

| Tools1                                         | Tools2                                         | Tools3                                         | Tools4                                         |
| ---------------------------------------------- | ---------------------------------------------- | ---------------------------------------------- | ---------------------------------------------- |
| <img src="screenshots/Tools1.png" width="170"> | <img src="screenshots/Tools2.png" width="170"> | <img src="screenshots/Tools3.png" width="170"> | <img src="screenshots/Tools4.png" width="170"> |

---

# ✍️ Prompt Flow

| Prompt Screen                                  | Premium Logic                                         | Result                   |
| ---------------------------------------------- | ----------------------------------------------------- | ------------------------ |
| <img src="screenshots/PROMPT.png" width="180"> | Bottom navigation Prompt opens Paywall for free users | Premium gated generation |

---

# ⭐ Rate Flow

| Rate0                                         | Rate1                                         | Rate2                                                      | Rate3                                                      |
| --------------------------------------------- | --------------------------------------------- | ---------------------------------------------------------- | ---------------------------------------------------------- |
| <img src="screenshots/Rate0.png" width="160"> | <img src="screenshots/Rate1.png" width="160"> | <img src="screenshots/Rate2(interactive).png" width="160"> | <img src="screenshots/Rate3(interactive).png" width="160"> |

| Rate4                                                      | Rate5                                                      | Interactive Flow      | Result        |
| ---------------------------------------------------------- | ---------------------------------------------------------- | --------------------- | ------------- |
| <img src="screenshots/Rate4(interactive).png" width="160"> | <img src="screenshots/Rate5(interactive).png" width="160"> | Automatic transitions | Opens Paywall |

---

# 💳 Paywall Flow

| Paywall1                                         | Paywall2                                         | Weekly Plan | Yearly Plan            |
| ------------------------------------------------ | ------------------------------------------------ | ----------- | ---------------------- |
| <img src="screenshots/Paywall1.png" width="180"> | <img src="screenshots/Paywall2.png" width="180"> | Selectable  | Highlighted by default |

| Restore   | Continue      | Subscription State | Result         |
| --------- | ------------- | ------------------ | -------------- |
| Supported | Purchase flow | Weekly / Yearly    | Premium access |

---

# ⚙️ Settings

| Settings Screen                                  | Upgrade Banner | Contact Us     | Share With Friends |
| ------------------------------------------------ | -------------- | -------------- | ------------------ |
| <img src="screenshots/Settings.png" width="180"> | Opens Paywall  | Working action | Working action     |

| Privacy Policy | Terms Of Use   | Premium Route | Result                   |
| -------------- | -------------- | ------------- | ------------------------ |
| Browser action | Browser action | Opens Paywall | Functional settings flow |

---

# 🕘 History Flow

| History1                                                      | History2                                                      | History3                                                      | Result                   |
| ------------------------------------------------------------- | ------------------------------------------------------------- | ------------------------------------------------------------- | ------------------------ |
| <img src="screenshots/History1(interactive).png" width="160"> | <img src="screenshots/History2(interactive).png" width="160"> | <img src="screenshots/History3(interactive).png" width="160"> | Interactive loading flow |

| Loader             | Example Images        | Empty Cards        | Text State                                     |
| ------------------ | --------------------- | ------------------ | ---------------------------------------------- |
| Interactive loader | 4 example generations | Empty placeholders | “Пока ничего нет. Ваши работы появятся здесь.” |

---

# 🔐 Free User Restrictions

| Trigger                     | Result        |
| --------------------------- | ------------- |
| Prompt tab click            | Opens Paywall |
| Get PRO click               | Opens Paywall |
| Settings banner click       | Opens Paywall |
| Premium onboarding complete | Opens Paywall |

---

# 🚀 Navigation Logic

| Source                   | Destination         |
| ------------------------ | ------------------- |
| Splash                   | Tools onboarding    |
| Tools onboarding         | Template onboarding |
| Template onboarding      | Prompt onboarding   |
| Prompt onboarding        | Rate flow           |
| Rate flow                | Interactive flow    |
| Interactive flow         | Paywall             |
| Free user premium action | Paywall             |

---

# 📁 Folder Structure

```text
docs/
 └── onboarding/
      ├── README.md
      └── screenshots/
           ├── Splash.png
           ├── AI_ENHANCER.png
           ├── GLAM_MAKEUP.png
           ├── REMOVE_OBJECTS.png
           ├── REMOVE_BACKGROUND.png
           ├── SKIN_IMPROVE.png
           ├── CHANGE_SCENE.png
           ├── HAIR_STUDIO.png
           ├── SMILE_EDIT.png
           ├── GHOSTFACE.png
           ├── GHIBLI.png
           ├── TEMPLATE.png
           ├── PROMPT.png
           ├── Rate0.png
           ├── Rate1.png
           ├── Rate2(interactive).png
           ├── Rate3(interactive).png
           ├── Rate4(interactive).png
           ├── Rate5(interactive).png
           ├── Paywall1.png
           ├── Paywall2.png
           ├── Tools1.png
           ├── Tools2.png
           ├── Tools3.png
           ├── Tools4.png
           ├── Settings.png
           ├── History1(interactive).png
           ├── History2(interactive).png
           └── History3(interactive).png
```
