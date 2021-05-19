let versionRegex = /(\nversion=)([0-9.-]+)/;

const tracker = {
  filename: 'gradle.properties',
  updater: {
    'readVersion': (contents) => {
      return versionRegex.exec(contents)[2];
    },
    'writeVersion': (contents, version) => {
      return contents.replace(versionRegex, `$1${version}`);
    }
  }
}

module.exports = {
  bumpFiles: [tracker],
  packageFiles: [tracker],
  // In case you need to force a version change (mostly due to change of scope of the update e.g. major now instead of patch)
  //releaseAs: '0.16.0',
  header:"# Changelog\n" +
      "\n" +
      "All notable changes to this project will be documented in this file. See [standard-version](https://github.com/conventional-changelog/standard-version) for commit guidelines.\n" +
      "\n" +
      "For the release changelogs see [CHANGELOG.md](CHANGELOG.md)  \n" +
      "For the snapshot changelogs see [SNAPSHOT_CHANGELOG.md](SNAPSHOT_CHANGELOG.md)\n",
}
