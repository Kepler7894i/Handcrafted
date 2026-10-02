#!/usr/bin/env bash
# Installs Handcrafted (and its ResourcefulLib dependency) into a Minecraft mods folder. macOS version;
# see install-windows.ps1 (Windows) and install-linux.sh (Linux).
#
# Usage: ./install-macos.sh [--mods-dir DIR] [--loader fabric|neoforge] [--skip-build] [--mc-jar PATH]
#
# Where the jar comes from:
#   - run from a repository checkout (gradlew next to this script): the mod is compiled (code only);
#   - run from a release download (handcrafted-<loader>-*.jar next to this script): that jar is used.
#
# The mod's artwork (textures, models, sounds, language files) is All Rights Reserved by its original authors and is
# not part of this repository or its jars. After the jar is ready this script runs AssetInstaller.java, which fetches
# the artwork from the original mod's official Modrinth download onto this machine and adds it to the installed jar.
# See the README ("Licensing"). Older copies of Handcrafted and ResourcefulLib in the mods folder are replaced.
# Fabric Loader / Fabric API / NeoForge themselves are assumed to be installed already and are never touched.
# Needs: bash, curl, Java 25+ on PATH.
set -euo pipefail

root="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
mods_dir="$HOME/Library/Application Support/minecraft/mods"
loader=fabric
skip_build=0
mc_jar=""

while [ $# -gt 0 ]; do
  case "$1" in
    --mods-dir)   mods_dir="$2"; shift 2 ;;
    --loader)     loader="$2"; shift 2 ;;
    --skip-build) skip_build=1; shift ;;
    --mc-jar)     mc_jar="$2"; shift 2 ;;
    -h|--help)    sed -n '2,15p' "${BASH_SOURCE[0]}" | sed 's/^# \{0,1\}//'; exit 0 ;;
    *) echo "Unknown option: $1" >&2; exit 2 ;;
  esac
done
case "$loader" in fabric|neoforge) ;; *) echo "--loader must be fabric or neoforge" >&2; exit 2 ;; esac

prop() { [ -f "$root/gradle.properties" ] && grep -E "^$1=" "$root/gradle.properties" | head -n1 | cut -d= -f2- | tr -d '\r ' || true; }

mkdir -p "$mods_dir"

# 1. Get a code-only jar
if [ -x "$root/gradlew" ] || [ -f "$root/gradlew" ]; then
  if [ "$skip_build" -eq 0 ]; then
    echo "Building Handcrafted ($loader, code only)..."
    (cd "$root" && sh ./gradlew ":$loader:build" -PcodeOnly --console=plain)
  fi
fi
mod_jar=""
for dir in "$root/$loader/build/libs" "$root"; do
  [ -d "$dir" ] || continue
  # newest matching jar that is not a sources jar
  found="$(ls -t "$dir"/handcrafted-"$loader"-*.jar 2>/dev/null | grep -v -- '-sources\.jar$' | head -n1 || true)"
  if [ -n "$found" ]; then mod_jar="$found"; break; fi
done
[ -n "$mod_jar" ] || { echo "No handcrafted-$loader-*.jar found. Run this from a repository checkout, or put the release jar next to this script." >&2; exit 1; }

mc="$(prop minecraftVersion)"
if [ -z "$mc" ]; then mc="$(basename "$mod_jar" | sed -E "s/^handcrafted-$loader-([^-]+)-.*/\1/")"; fi
rlib="$(prop resourcefulLibVersion)"
repo="https://maven.teamresourceful.com/repository/maven-public/com/teamresourceful/resourcefullib/resourcefullib-$loader-$mc"
if [ -z "$rlib" ]; then
  rlib="$(curl -fsSL "$repo/maven-metadata.xml" | sed -n 's:.*<release>\(.*\)</release>.*:\1:p' | head -n1)"
fi

# 2. Work in a temp folder so a failure never leaves the mods folder half-updated.
tmp="$(mktemp -d)"
trap 'rm -rf "${tmp:?}"' EXIT

rlib_name="resourcefullib-$loader-$mc-$rlib.jar"
echo "Downloading $rlib_name..."
curl -fsSL -o "$tmp/$rlib_name" "$repo/$rlib/$rlib_name"

# 3. Add the artwork (from the original mod, via the licensing workaround)
mod_name="$(basename "$mod_jar")"
cp "$mod_jar" "$tmp/$mod_name"
installer=""
for f in "$root/tools/AssetInstaller.java" "$root/AssetInstaller.java"; do [ -f "$f" ] && { installer="$f"; break; }; done
[ -n "$installer" ] || { echo "AssetInstaller.java not found next to this script (or in tools/)." >&2; exit 1; }
echo "Adding artwork with AssetInstaller..."
if [ -n "$mc_jar" ]; then
  java "$installer" "$tmp/$mod_name" --mc-jar "$mc_jar"
else
  java "$installer" "$tmp/$mod_name"
fi

# 4. Remove old versions, then copy the new ones in.
for pattern in "handcrafted-$loader-" "resourcefullib-$loader-"; do
  for old in "$mods_dir"/"$pattern"*.jar; do
    [ -e "$old" ] || continue
    echo "Removing $(basename "$old")"
    rm -f "$old" || { echo "Cannot replace $(basename "$old"): is Minecraft (or a server) running with it loaded, or is the folder read-only? Close it and re-run." >&2; exit 1; }
  done
done
cp "$tmp/$mod_name" "$mods_dir/$mod_name" || { echo "Cannot write to $mods_dir" >&2; exit 1; }; echo "Installed $mod_name"
cp "$tmp/$rlib_name" "$mods_dir/$rlib_name" || { echo "Cannot write to $mods_dir" >&2; exit 1; }; echo "Installed $rlib_name"

echo
echo "Done. Mods folder: $mods_dir"
