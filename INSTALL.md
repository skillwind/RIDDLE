![Alt text](https://github.com/skillwind/RIDDLE/blob/master/images/1.png)
[![DOI](https://zenodo.org/badge/145226316.svg)](https://zenodo.org/badge/latestdoi/145226316)
# How to use Riddle
Riddle takes a Maven based project (it should contain the complete Maven built project directory and file pom.xml) as input for analysis. The expected running environment is 64-bit Window operating system with JDK 1.8.**As Maven built projects need to download dependencies from Maven Central Repository, Riddle cannot work offline.**
You can run Riddle on subjects based on the following steps:
**Step 1:** Unzip the **workspace_riddle.zip** to local directory. Recommended directory structure is:
> D:\workspace_riddle  
&#8195;&#8195;├─apache-maven-3.2.5&#8195;&#8195;&#8195;&#8195;Maven source code  
&#8195;&#8195;├─jar&#8195;&#8195;&#8195;&#8195;&#8195;&#8195;&#8195;&#8195;&#8195;&#8195;&#8195;&#8195;Riddle source code  
&#8195;&#8195;├─reportProject&#8195;&#8195;&#8195;&#8195;&#8195;&#8195;&#8195;  Java project under test  
&#8195;&#8195;├─installscript.jar&#8195;&#8195;&#8195;&#8195;&#8195;&#8195; &#8195; Install script file  
&#8195;&#8195;├─batchScript.jar&#8195;&#8195;&#8195;&#8195;&#8195;&#8195;&#8195;Script file used to reproduce experimental result  
 
 **Step 2:** Install Riddle:
 Execute the following Windows CMD command to install Riddle :java -jar
 > D:\workspace_riddle\installScript.jar

**Step 3:** Generate test cases to examine the dependency conflict issues:
Execute the following Windows CMD command to run Riddle on project under test:
>D:\workspace_riddle\apache-maven-3.2.5\bin\mvn.bat                                                        -  f=D:\workspace_riddle\reportProject\storm-1.1.2\external\storm-kafka-monitor\pom.xml -DcallConflict="org.apache.zookeeper+zookeeper" -Dmaven.test.skip=true package neu.lab:riddle:1.0:gene –e

![Alt text](https://github.com/skillwind/RIDDLE/blob/master/images/2.png)

Then you can get the results in the specified directory(e.g., **D:\workspace_riddle\tempWs**).
The result directory is shown as follows:  
![Alt text](https://github.com/skillwind/RIDDLE/blob/master/images/3.png)

where 
- **modifyCp** is the directory containing the mutated source code
- **finalTest** is the generated test cases
- **d_xxxxx** is the document recording the distances from any entry method to any risky method
- **p_ xxxxx** is the document recording the paths from any entry method to any risky method
- **TraceLog** is the log file recording the stack traces obtained by triggering the risky method
-  **GarbageLog.txt** is the Riddle execution log file </font>


