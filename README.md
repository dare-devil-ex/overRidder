
# OverRidder

overRidder lets users repurpose unused remote-control keys on Android TV devices.


## Screenshot

![](https://github.com/user-attachments/assets/3125543b-23e4-435c-a2ae-945203b8ac98)

## Changes you need to do

| Location             | Changes                                  |
| ----------------- | ------------------------------------------------------------------ |
| MainActivity.kt | keyCodes & scanCodes |
| AndroidManifest.xml | queries |



## Build

This apk build works TCL C06 (mt5867)

To build this project by your own

- Use Android Studio or Any other IDEs



## Noobie guide

Connect your Android Tv via USB debugging
```bash
adb connect [ipaddress of the Tv]
```
Enter to the ADB shell
```bash
adb shell
```
Fetch the all KeyEvents
```bash
logcat | grep KeyEvent
```
Example log
> KeyEvent { action=ACTION_DOWN, keyCode=4065, scanCode=245, metaState=0, flags=0x8, repeatCount=0, eventTime=3033575, downTime=3033575, deviceId=-1, source=0x301, displayId=-1 }

Required part
> keyCode=4065, scanCode=245

Code replace
- Inside the [MainActivity.kt](https://github.com/dare-devil-ex/overRidder/blob/main/app/src/main/java/com/lonemods/overridder/MainActivity.kt) file, replace with those fetched codes
- replace these array vales accordingly
```kt
private val youTube = arrayOf(4065, 245)

private val netflix = arrayOf(4062, 242)

private val spotify = arrayOf(4099, 752)
```

Inside the [AndroidManifest.xml](https://github.com/dare-devil-ex/overRidder/blob/main/app/src/main/AndroidManifest.xml) change these packages to the desired package you wanted to load.

```xml
<queries>
    <package android:name="com.youtubetv.lonemods" />
    <package android:name="app.netmirror.newtv" />
    <package android:name="com.spotify.tv.android" />

    <intent>
        <action android:name="android.intent.action.MAIN" />
        <category android:name="android.intent.category.LAUNCHER" />
    </intent>
</queries>
```

## FAQ

#### Does it work on all androidTv?

Yes, with own customization

#### Does root necessary?

No

## Modded Apps for Tv

- [YouTube Tv Mod](https://www.mediafire.com/file/rb1vphgcp3zkwvo/YouTubeMod_by_%2528%2540lonemods%2529.apk/file)
- [Spotify Tv Mod](https://t.me/lonemods/376)
- [Netmirror](https://www.mediafire.com/file/z8hbja0zjlvkpcn/NetMirrorTV.apk/file)

## Support

For support, [Telegram](https://t.me/dare_devil_ex), [Instagram](https://instagram.com/dare_devil_ex) or join our [LoneMods](https://t.me/lonemods) channel.


## Authors

- [@dare-devil-ex](https://www.github.com/dare-devil-ex)

