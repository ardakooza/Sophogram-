# Sophon — Telegram for Android (monochrome fork)

Sophon is a **rebranded, monochrome (black & white) fork of Telegram for Android**.
It keeps Telegram's core messaging, security, and MTProto protocol exactly as upstream,
while the visual identity — app name, icon, and color scheme — is Sophon's own.

- **Upstream source:** <https://github.com/DrKLO/Telegram>
- **This fork:** <https://github.com/ardakooza/Sophogram->
- **App name:** `Sophon` (package name unchanged: `org.telegram.messenger`)
- **Launcher icon:** Sophon's own mark (a glossy four-pointed star), black background
- **Color scheme:** monochrome — light `#FAFAFA` and dark `#0A0A0A`, zero color residue

## License

Telegram for Android is licensed under the **GNU General Public License v2.0 or later**
(GPL-2.0-or-later), with the [OpenSSL exception](https://www.openssl.org/opensource/license-openssl.html).
This fork is derived work and is distributed under the same terms.

- Source of this fork: <https://github.com/ardakooza/Sophogram->
- Upstream source: <https://github.com/DrKLO/Telegram>

## Building

This repo ships a **GitHub Actions workflow** (`.github/workflows/build-apk.yml`) that
produces the release APK. It builds the `:TMessagesProj_App:assembleAfatRelease` task
for `arm64-v8a`, signed with the release keystore, R8-minified.

Run it manually from the **Actions** tab → **Sophon Build** → **Run workflow**.

Required inputs:

| Input | Default | Notes |
|---|---|---|
| `variant` | `:TMessagesProj_App:assembleAfatRelease` | release buildType = no `.beta` package suffix, label `@string/AppName` |
| `abi` | `arm64-v8a` | use `all` for all 4 ABIs (much slower) |

### Why the release buildType

The `debug` buildType adds `applicationIdSuffix ".beta"` and uses the
`@string/AppNameBeta` label, which shows **"Telegram Beta"**. The `release` buildType
keeps the plain `org.telegram.messenger` package and the `@string/AppName` label,
so the app shows as **Sophon** and can replace an installed Telegram.

### API credentials

`BuildVars.java` keeps the **official public** values (`APP_ID = 4`).
They are left in place deliberately: Telegram's auth keys are bound to the
`api_id` on the server, so substituting your own `api_id` invalidates an existing
session and logs the user out. If you later want your own app credentials,
replace them in `BuildVars.java` and start with a fresh install.

### What you still need for your own distribution

The repo intentionally keeps upstream's **dummy** `release.keystore`, `google-services.json`
and `BuildVars.java` values (see the upstream reproducible-builds note).
Before publishing your own APKs, replace them with your own:

1. Copy your `release.keystore` into `TMessagesProj/config`
2. Fill `RELEASE_KEY_PASSWORD`, `RELEASE_KEY_ALIAS`, `RELEASE_STORE_PASSWORD` in `gradle.properties`
3. Create Firebase apps at <https://console.firebase.google.com/> for the package names
   you build (`org.telegram.messenger`, `org.telegram.messenger.beta`, `org.telegram.messenger.web`),
   enable Firebase Cloud Messaging, and download `google-services.json` to `TMessagesProj/`
4. Open the project in Android Studio (opened, **not** imported)
5. Fill your own values in `TMessagesProj/src/main/java/org/telegram/messenger/BuildVars.java`

## Localization

All translations live at <https://translations.telegram.org/en/android/>