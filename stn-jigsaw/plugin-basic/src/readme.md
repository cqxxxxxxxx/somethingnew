参考 http://openjdk.java.net/projects/jigsaw/quick-start
1. Greetings
```$xslt
# 编译生成class文件
javac -d mods \                                                  
src/com/cqx/acc/Accessible.java \
src/com/cqx/acc/Plugin.java \
src/com/cqx/unacc/UnAccessible.java \
src/com/cqx/acc/Main.java \
src/module-info.java 

javac -d mods $(find ./ -name "*.java")

# main类启动
java --module-path mods -m com.cqx.plugin.help/Main (-m  module的名字  / module中主类的路径)

# 吧class文件打成jar包
jar --create --file=./com.cqx.plugin.help@1.0.jar --module-version=1.0 -C mods/ .


# 吧class文件打成jar包 并指定main class
jar --create --file=./com.cqx.plugin.help@2.0.jar --main-class=Main --module-version=1.0 -C mods/ .

# 
java -p mods com.cqx.plugin.help@2.0.jar
```
