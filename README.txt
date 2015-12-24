#HostFileManager
####Definition
Hosts file manager enables user to create profiles to change and create hosts files easily. 
This application is useful for people who frequently change their hosts file for development or testing purposes. 
People with lack of development skills might get more benefit from this application.

###UI
Application consists of 3 panels which are left, main and right panel.
1. Previously created profiles are listed on left panel.
2. In main panel hosts file can be seen and edited.
3. User can see whether an dns is redirected to development or production environment. In addition s/he can define/remove ip and dns addresses via ui instead of using editor directly.

###Short Explanation
When application runs, it read hosts file and parse its data into sub data which is called expressions. Expressions consists of dns and relative ip addresses. If previously no profile is
created and when user save hosts file, user is asked to select a location to save profile file. If user save file to location which is shown by default later on s/he can see those profiles
on the left panel when s/he relaunches program. Otherwise s/he can open them manually.

######Example-1:
If a hosts file consists of below line, then program creates 4 expressions with 127.0.0.1 as ip address. One thing to notice program ignores '#' character.

```
127.0.0.1 a.com.tr b.com.tr c.com.tr #d.com.tr
```

######Example-2:
If a hosts file consists of below line, then program creates an expression which has 3 ip addresses.

```
127.0.0.1 a.com.tr
1.1.1.1 a.com.tr
2.2.2.2 a.com.tr
```