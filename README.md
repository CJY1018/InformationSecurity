# 合肥工业大学 信息安全 任务二
用 Java 语言设计并实现 DES 和 RSA 加密解密程序，均没有调用库函数等现成程序。

运行结果截图：

![image](https://github.com/CJY1018/InformationSecurity/assets/90013748/d0e465f6-d020-47e0-89b2-45929dde044c)

实现了 DES 加密解密程序分别对字符串、文本、图像等数据进行加密和解密；

实现了 RSA 加密解密程序对文本加密和解密，并与DES算法进行效率对比，发现 RSA 加密耗时较长。

运行结果：

C:\Users\13108\.jdks\corretto-17.0.3\bin\java.exe "-javaagent:C:\Program Files\JetBrains\IntelliJ IDEA 2021.3.3\lib\idea_rt.jar=6884:C:\Program Files\JetBrains\IntelliJ IDEA 2021.3.3\bin" -Dfile.encoding=UTF-8 -classpath D:\WorkSpace\java\InformationSecurity\out\production\InformationSecurity;D:\WorkSpace\java\InformationSecurity\lib\javax.ejb.jar;D:\WorkSpace\java\InformationSecurity\lib\javax.servlet.jsp.jar;D:\WorkSpace\java\InformationSecurity\lib\javax.resource.jar;D:\WorkSpace\java\InformationSecurity\lib\javax.jms.jar;D:\WorkSpace\java\InformationSecurity\lib\javax.annotation.jar;D:\WorkSpace\java\InformationSecurity\lib\javax.servlet.jsp.jstl.jar;D:\WorkSpace\java\InformationSecurity\lib\javax.servlet.jar;D:\WorkSpace\java\InformationSecurity\lib\javax.persistence.jar;D:\WorkSpace\java\InformationSecurity\lib\javax.transaction.jar Main



---------------------------DES---------------------------

原文：abc
原文不满足 8 的倍数，末尾补充5个 0，补充后的原文为：abc00000

DES 加密结果:  ?ÙèS¥4Ã
DES 加密耗时:  15ms

DES 解密结果:  abc

DES 解密耗时:  1ms



---------------------------RSA---------------------------

RSA 公钥：107723579762507827755541337836859175491872917522623349679553397059978415546529
RSA 私钥：23965066752520124810412610243034762420800474763939080512950843028047593379313163535926021275929622680081892196761705400951197769696718005394538248261800118431949368351501332208380337691404899523469266930370022541507997951588891311823320822249530107939544721021136406302494064418840670111212623320547855409937
RSA 密钥生成耗时:  236ms



原文：abc

RSA 加密结果:  4615191109244634114830960978067296361266905893765897744356344492030424197998337604527659832676778851260055078554544350912829179515375940007735627959194903464821258679194881192583350815324032413417507462008458502396260399825495580011320908680242017152018636064440952774653318780789768508337875904888130263302
RSA 加密耗时:  1ms

RSA 解密结果:  abc

RSA 解密耗时:  1ms



------------------------DES File-------------------------

src.txt 文件大小: 49B
原文不满足 8 的倍数，末尾补充7个 0，补充后的原文为：Name: ChenJunyang
ID: 2021217859
Class:IOT 21-10000000

DES 文件加密耗时:  3ms

DES 文件解密耗时:  1ms

src.txt 和 dec.txt 文件内容一致，文件加密解密成功



------------------------DES Image-------------------------

src.jpg 文件大小: 3.63MB
DES 图片加密耗时:  3550ms

DES 图片解密耗时:  2618ms

src.jpg 和 dec.jpg 文件内容一致，图片加密解密成功



进程已结束,退出代码0
