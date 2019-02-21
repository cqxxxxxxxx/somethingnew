### notice.

annotation processor 只能创建类，不能修改类

that means 我不能再一个已存在的类里加字段 加方法。

不过有个hack方法。 就是

https://stackoverflow.com/questions/13690272/code-replacement-with-an-annotation-processor

但是感觉不好啊



Lombok他是用了ASM来修改字节码。。。。。 不对好像也不是。。。。看下面的链接
https://stackoverflow.com/questions/16511100/java-annotation-processing-with-asm