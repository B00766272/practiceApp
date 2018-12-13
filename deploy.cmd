[config]

@echo off

echo ---Deploying site 

copy D:\home\site\repository\exampleApp\target\*.war %DEPLOYMENT_TARGET%\webapps\*.war

rename  D:\home\site\wwwroot\webapps\*.war ROOT.WAR





