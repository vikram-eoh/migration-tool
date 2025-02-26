This is tool application that can be used to make bulk changes in Deployment files. 

How use it:
1. Add your file path to resource/data file. - You can add any file path or folder path. You can add any number of paths. Each path should be an absolute path and in one line there should be only path.
2. Define the value of currentEnv variable in AKSMigration class. Put then env value for which you are making changes - **private final static String currentEnv = uat;** 

Suggestion : Please don't add too many files to change, if something fails then it will be difficult to trace. Good Luck


Changes that this tool do :
1. It changes the secret file to new way to defining the secrets.
2. It replaces the docker image base path in deployment to the new docker base path of azure acr
3. It replaces the hostname in ingress file to new host names

Planned more changes:
1. Add the all new database secrets to deployment file(In Progress)