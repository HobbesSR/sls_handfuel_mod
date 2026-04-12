# Workshop Upload Prep

This folder contains the files needed to stage and upload the current mod build to Steam Workshop.

Files:

- `content/`
  Steam Workshop content folder. After preparation, it contains `CardEnergy.jar`.
- `preview/preview.jpg`
  Workshop preview image copied from the mod portrait art.
- `item.vdf`
  Steam Workshop descriptor configured for an initial friends-only upload.
- `prepare_workshop.ps1`
  Syncs the current built jar and preview image into the upload folder.

## Readiness

Before upload:

1. Build the mod jar.
2. Run `workshop\\prepare_workshop.ps1`.
3. Inspect `workshop\\item.vdf` and update:
   - `title`
   - `description`
   - `changenote`
   - `publishedfileid` after the first upload creates an item

## Visibility

`item.vdf` is currently set to:

- `visibility = 1`

That is the friends-only setting for the initial upload.

## SteamCMD upload

If `steamcmd.exe` is installed, the usual upload flow is:

```powershell
steamcmd.exe +login <your_steam_username> +workshop_build_item "<absolute-path-to-item.vdf>" +quit
```

After the first upload succeeds, Steam returns a `publishedfileid`. Add that value into `item.vdf` for updates to the same Workshop item.

## Interactive upload helper

This repo also includes:

- `upload_workshop.ps1`

Usage:

```powershell
powershell -ExecutionPolicy Bypass -File .\workshop\upload_workshop.ps1 -SteamUser <your_steam_login_name>
```

That script launches SteamCMD and leaves password / Steam Guard entry to the local interactive prompt.
