@echo off
setlocal

REM Define paths and application properties
set JPACKAGE_HOME="C:\Program Files\Java\jdk-17.0.4\bin"
set APP_VERSION=1.0
set MAIN_CLASS=com.bigezo.bigezojfx.SplashScreen
set APP_NAME=BigezoJfx
set ICON_PATH="src\main\resources\images\app-icon.png"

REM Run jpackage to create the executable
%JPACKAGE_HOME%\jpackage ^
  --type exe ^
  --input target ^
  --name %APP_NAME% ^
  --main-jar %APP_NAME%-1.0-SNAPSHOT.jar ^
  --main-class %MAIN_CLASS% ^
  --app-version %APP_VERSION% ^
  --icon %ICON_PATH% ^
  --win-menu ^
  --win-shortcut ^
  --win-dir-chooser ^
  --win-console ^
  --runtime-image target\jlink-image

endlocal
