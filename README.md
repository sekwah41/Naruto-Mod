# Naruto-Mod ![GitHub Workflow Status](https://img.shields.io/github/workflow/status/sekwah41/Naruto-Mod/Build%20Project/forge-1-16)

[![Discord](https://img.shields.io/discord/168282484037910528.svg?style=for-the-badge&logo=discord&logoColor=white)](https://discord.gg/fAJ3xJg)
[![](https://img.shields.io/github/contributors/sekwah41/Naruto-Mod.svg?style=for-the-badge&logo=github)](https://github.com/sekwah41/Naruto-Mod/graphs/contributors)
[![](https://img.shields.io/github/issues/sekwah41/Naruto-Mod.svg?style=for-the-badge&logo=github)](https://github.com/sekwah41/Naruto-Mod/issues)
[![](https://img.shields.io/github/issues-pr/sekwah41/Naruto-Mod.svg?style=for-the-badge&logo=github)](https://github.com/sekwah41/Naruto-Mod/pulls)
[![](https://img.shields.io/github/forks/sekwah41/Naruto-Mod.svg?style=for-the-badge&logo=github)](https://github.com/sekwah41/Naruto-Mod/com.sekwah.narutomod.network/members)
[![](https://img.shields.io/github/stars/sekwah41/Naruto-Mod.svg?style=for-the-badge&logo=github)](https://github.com/sekwah41/Naruto-Mod/stargazers)

If you would like to contribute to the language files by creating another language or editing one of the current files fork the repo and make any changes or additions to the language files you feel are neccasary. The language files can be found [here](https://github.com/sekwah41/Naruto-Mod/tree/master/src/main/resources/assets/narutomod/lang).

Where to get the mod http://www.planetminecraft.com/mod/naruto-mod-1750133/

# SekCLib
For now until some of the main dev tools are ready and SekCLib has more features/is needed for other mods
it will be included inside the packages of this mod. However, soon it will be split into a second mod for public use.

# Contributing
## Translations
Translations can be contributed over at Crowdin: https://crowdin.com/project/naruto-mod
This will automatically create pr's and should be merged when the next update or patch comes out.

## Code
Please ensure that your commits are in the following style for PR's

https://www.conventionalcommits.org/en/v1.0.0/

Accepted tags mostly follow the Angular style and are meant to only loosely be followed.
When commits close an issue refer in the commit description in the following style (Refs #1, #2, #3)

## Types available
See the [Release please config](./release-please-config.json) for the types available.

## Using release-please-actions
If you are wanting to use the same release configuration check out the [release-please-action](https://github.com/google-github-actions/release-please-action).
You should be able to leave out the token part though if you also want testing snapshots to automatically post you will need to create a PAT and use that otherwise the actions will not trigger.

## If you are using the jetbrains runtime
For DCEVM -XX:+AllowEnhancedClassRedefinition