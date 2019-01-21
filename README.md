![Alt text](./1.png)
# Project description
<font size="4">Riddle is a test generation tool to trigger the crashes caused by dependency conflict issues and collect the crashing stack traces to help developers examine the problems.</font>
# Background
Intensive use of libraries in Java projects brings potential risk of dependency conflicts, which occur when a project directly or indirectly depends on multiple versions of the same library or class. When this happens, JVM loads one version and shadows the others. Runtime exceptions can occur when methods in the shadowed versions are referenced. Although project management tools such as Maven are able to give warnings of potential dependency conflicts when a project is built, developers often ask for crashing stack traces before examining these warnings. It motivates us to develop RIDDLE, an automated approach that generates tests and collects crashing stack traces for projects subject to risk of dependency conflicts. RIDDLE, built on top of ASM and EVOSUITE, combines condition mutation, search strategies and condition restoration to trigger the crashes caused by dependency conflict issues.
# License
Riddle is released under the MIT License.
# Environment
Window operating system & JDK 1.7 or 1.8
# Reference
This project references the open source projects **Soot, ASM, EVOSUITE.**
# Contact author
[wangying8052@163.com]( wangying8052@163.com)