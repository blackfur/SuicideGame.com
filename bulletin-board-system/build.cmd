@echo off

@rem @set USERPROFILE=d:
@set USERPROFILE=f:\Users\iwillbe5
@set Program=g:
@call %USERPROFILE%\env.bat
@rem @call d:\env.bat

@set port=9080
@set public=%USERPROFILE%\workspace\SuicideGame.com\bulletin-board-system\src\main\webapp
@set dist=%public%
@set DATFILE=/opt/hsql/bookshelf
if not exist \opt\hsql mkdir \opt\hsql

@call %USERPROFILE%\default.cmd %*
