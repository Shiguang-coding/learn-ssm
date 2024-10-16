> 在线视频：[尚硅谷新版SSM框架全套视频教程，Spring6+SpringBoot3最新SSM企业级开发](https://www.bilibili.com/video/BV1AP411s7D7?p=170)
>
> 在线文档：[终极实战：SpringBoot版微头条实战](https://www.wolai.com/ie9813gmgSnVnEbFCdWfM2)
>
> 课程资料：https://pan.baidu.com/s/1R8Q-u4xV5rKOSTL8cRpwfQ?pwd=jf3x
>
> 后端代码
>
> Gitee: https://gitee.com/an_shiguang/springboot-headline-part
>
> GitHub: https://github.com/Shiguang-coding/springboot-headline-part
>
> 前端代码
>
> Gitee: https://gitee.com/an_shiguang/my-vue3-vite-project
>
> GitHub: https://github.com/Shiguang-coding/my-vue3-vite-project


# 微头条案例介绍

## 微头条业务简介

用户功能
-   注册功能
-   登录功能
-   jwt实现

头条新闻
-   新闻的分页浏览
-   通过标题关键字搜索新闻
-   查看新闻详情
-   新闻的修改和删除

## 技术栈介绍

> 前端技术栈

-   ES6作为基础JS语法
-   nodejs用于运行环境
-   npm用于项目依赖管理工具
-   vite用于项目的构建架工具
-   Vue3用于项目数据的渲染框架
-   Axios用于前后端数据的交互
-   Router用于页面的跳转
-   Pinia用于存储用户的数据
-   LocalStorage作为用户校验token的存储手段
-   Element-Plus提供组件

> 后端技术栈

-   JAVA作为开发语言,版本为JDK17
-   Tomcat作为服务容器,版本为10.1.7
-   Mysql8用于项目存储数据
-   SpringMVC用于控制层实现前后端数据交互
-   MyBatis-Plus用于实现数据的CURD
-   Druid用于提供数据源的连接池
-   SpringBoot作为项目基础架构
-   MD5用于用户密码的加密
-   Jwt用于token的生成和校验
-   Jackson用于转换JSON

## 功能展示

> 头条首页信息搜索

![image-20240910090001894](images/66df9a121e529.png)

> 登录功能

![image-20240910090012007](images/66df9a1b1bd78.png)

> 注册功能

![image-20240910090026798](images/66df9a29eedba.png)

> 展示功能

![image-20240910090036411](images/66df9a33aa6c1.png)

> 发布头条功能

![image-20240910090043746](images/66df9a3aea6da.png)

> 修改头条功能

![image-20240910090058195](images/66df9a4985d8f.png)

> 删除头条功能

![image-20240910090106496](images/66df9a5187ab9.png)

# 微头条前端搭建

> 确保本地node，npm，vscode安装完毕！ 如果没有安装可以回看ssm整合实战！

解压前端项目代码并存放到磁盘的合适位置

[my-vue3-vite-project](https://gitee.com/an_shiguang/my-vue3-vite-project)

![image-20240910090124689](images/66df9a63c9450.png)

使用vscode打开工程

![image-20240910090137474](images/66df9a708fd8e.png)

进入项目后打开集成终端或者在src上右击选择在集成终端中打开

![image-20240910090146095](images/66df9a79208d9.png)

通过 npm run dev启动前端项目
```bash
npm install 
npm run dev
```

若出现以下错误

```
Failed to resolve import "@element-plus/icons-vue" from "src\components\Header.vue". Does the file exist?
```

可参考文章

> [解决Failed to resolve import “@element-plus/icons-vue“ from “src\views\Home.vue“. Does the file exist?](https://blog.csdn.net/hu_666666/article/details/129783526)

前端项目部署成功后如下所示

![image-20240910090506784](images/66df9b41df9b0.png)



# 基于SpringBoot搭建项目基础架构

## 数据库脚本执行

执行数据库脚本: `top_news.sql`

```sql
CREATE DATABASE sm_db;

USE sm_db;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for news_headline
-- ----------------------------
DROP TABLE IF EXISTS `news_headline`;
CREATE TABLE `news_headline`  (
  `hid` INT NOT NULL AUTO_INCREMENT COMMENT '头条id',
  `title` VARCHAR(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '头条标题',
  `article` VARCHAR(5000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '头条新闻内容',
  `type` INT NOT NULL COMMENT '头条类型id',
  `publisher` INT NOT NULL COMMENT '头条发布用户id',
  `page_views` INT NOT NULL COMMENT '头条浏览量',
  `create_time` DATETIME(0) NULL DEFAULT NULL COMMENT '头条发布时间',
  `update_time` DATETIME(0) NULL DEFAULT NULL COMMENT '头条最后的修改时间',
  `version` INT DEFAULT 1 COMMENT '乐观锁',
  `is_deleted` INT DEFAULT 0 COMMENT '头条是否被删除 1 删除  0 未删除',
  PRIMARY KEY (`hid`) USING BTREE
) ENGINE = INNODB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of news_headline
-- ----------------------------
INSERT INTO `news_headline` (hid,title,article,TYPE,publisher,page_views,create_time,update_time,is_deleted) VALUES (1, '特色产业激发乡村振兴新活力', '推进中国式现代化，必须全面推进乡村振兴。习近平总书记指出，产业振兴是乡村振兴的重中之重，也是实际工作的切入点。近日，记者走进乡村一线，看到各地以特色产业为抓手，拓展产业链发展产业集群，一二三产业融合发展，培育乡村振兴新动能。\n\n　　这个端午，广东茂名高州市根子镇柏桥村的荔枝迎来了丰收。今年4月，习近平总书记来到柏桥村考察调研。总书记走进荔枝种植园，了解当地发展特色种植产业和文旅产业等情况，并同现场技术人员亲切交流。', 1, 1, 0, '2023-06-25 09:26:20', '2023-06-25 09:26:20', 0);
INSERT INTO `news_headline` (hid,title,article,TYPE,publisher,page_views,create_time,update_time,is_deleted) VALUES (2, '北京连续三天最高温超40℃，6月“炎值”因何爆表？', '　中新社北京6月24日电 (记者 陈杭 徐婧)京城连续三日“热晴不减”，且高温红色预警持续生效。截至24日13时51分，作为北京地区气象观测代表站的南郊观象台气温突破40℃，这是该站观测史上首次连续三天气温超40℃。22日以来，北京高温“烤验”突出。22日，北京南郊观象台最高气温达41.1℃，这是有观测纪录以来历史第二高(并列)。北京市气象局表示，观象台1951年建站以来极端最高气温为41.9℃，出现在1999年7月24日。\n\n　　23日，北京南郊观象台最高气温为40.3℃，这是该观象台建站以来首次出现连续两天最高气温超40℃。当天，北京时隔9年再次发布最高级别的高温红色预警信号。', 1, 1, 0, '2023-06-25 09:28:06', '2023-06-25 09:28:06', 0);
INSERT INTO `news_headline` (hid,title,article,TYPE,publisher,page_views,create_time,update_time,is_deleted) VALUES (3, '今年夏天，极端高温是否会成为常态？', '针对京津冀地区持续高温天气，23日下午，中国气象局召开高温天气新闻通气会。\n\n　　刚过6月就出现极端高温天，今年夏天还会有多少高温天呢？对此，国家气候中心首席预报员高辉表示，根据国家气候中心预计，今年夏天全国大部分地区气温都比常年同期要偏高，这也对应着高温日数也要高于常年同期。但不同的地区，高温集中时段不一样，比如南方地区是在盛夏时间段进入高温季，而北方地区往往是在初夏时间段，所以从今年夏季来说，要区分不同的地区来考虑高温的影响。\n\n　　我国各地高温集中时段有明显的地域差异。对华北地区来说，通常雨季前的6月至7月初更容易出现高温天气，连续数天的高温在6月也比较常见。高辉说，这段时间主要是干热型高温为主，表现为气温高湿度小。进入7月后期，随着副高北跳和夏季风往北推进，水汽输送和大气湿度增加，云量也会增多，会出现闷热天气，也就是湿热型高温。就最高气温而言，前一时段气温最高值通常高于后一时段。但也需要说明的是，人体体感温度不仅和气温有关，还受到湿度影响，往往这种湿热型高温会加重人体体感温度。', 1, 1, 0, '2023-06-25 09:31:00', '2023-06-25 09:31:00', 0);
INSERT INTO `news_headline` (hid,title,article,TYPE,publisher,page_views,create_time,update_time,is_deleted) VALUES (4, '中央气象台发布今年首个高温橙色预警', '新华社北京6月22日电（记者黄垚）22日18时，中央气象台升级发布今年首个高温橙色预警。预计23日白天，华北、黄淮等地将继续出现35℃以上的高温天气，北京、天津、河北中南部、山东中北部等地部分地区最高气温可达40℃左右。\n\n　　气象监测显示，22日8时至16时，北京、天津、河北中部、山东北部等地气温上升迅猛，最高气温升至40℃以上。上述4省份共有17个国家气象观测站最高气温突破历史极值。', 1, 1, 0, '2023-06-25 09:31:36', '2023-06-25 09:31:36', 0);
INSERT INTO `news_headline` (hid,title,article,TYPE,publisher,page_views,create_time,update_time,is_deleted) VALUES (5, '江南水乡 龙舟竞渡', '江南水乡 龙舟竞渡---6月18日，浙江省湖州市“我们的节日·端午”暨第七届江南·民当端午民俗文化旅游节在南浔区和孚镇民当村开幕，来自南浔区各个乡镇的农民选手在河道中赛龙舟、划菱桶，体验传统端午民俗。', 1, 1, 0, '2023-06-25 09:32:13', '2023-06-25 09:32:13', 0);
INSERT INTO `news_headline` (hid,title,article,TYPE,publisher,page_views,create_time,update_time,is_deleted) VALUES (6, '螃蟹粽、印花蛋、艾草凉粉……你知道端午有哪些创意美食吗？', '端午有旅行路上的见闻，有诗画里的艺术，也少不了舌尖上的风韵。听风入夏粽香佐茶，您还知道端午有哪些创意美食吗？端午至味，总少不了粽子这一味。甜的、咸的，肉馅的、蛋黄的、红枣的、豆沙的……一起来寻味端午！\n\n　　古人其实早就喜欢把各种果干放进粽子里，美食家苏轼还发明了杨梅粽。《玉台新咏》中说，“酒中喜桃子，粽里觅杨梅。”后来苏轼曾借用过这个典故，在元祐三年所写的端午帖子中说，“不独盘中见卢橘，时于粽里得杨梅”。', 1, 1, 0, '2023-06-25 09:32:40', '2023-06-25 09:32:40', 0);
INSERT INTO `news_headline` (hid,title,article,TYPE,publisher,page_views,create_time,update_time,is_deleted) VALUES (7, '尼克斯拒绝执行罗斯球队选项 罗斯成自由球员', '北京时间6月25日，据多方消息源报道，尼克斯拒绝执行德里克-罗斯下赛季的球队选项，罗斯成为完全自由球员。\n\n　　34岁的罗斯在刚刚结束的赛季队内角色严重下滑，他仅出战27场比赛，场均登场12.5分钟，得到5.6分1.5篮板1.7助攻。\n\n　　2021年，罗斯与尼克斯签下3年4300万美元的续约合同，其中最后一年为1560万美元球队选项。', 2, 2, 0, '2023-06-25 09:34:26', '2023-06-25 09:34:26', 0);
INSERT INTO `news_headline` (hid,title,article,TYPE,publisher,page_views,create_time,update_time,is_deleted) VALUES (8, '班凯罗承诺代表美国男篮打世界杯 名单仅差1人', '北京时间6月25日，据著名NBA记者沙姆斯-查拉尼亚报道，魔术前锋保罗-班凯罗承诺将代表美国男篮参加2023年男篮世界杯。\n\n　　班凯罗在刚刚结束的赛季场均能够砍下20.0分6.9篮板3.7助攻，获得了NBA2022-23赛季年度最佳新秀。', 2, 2, 0, '2023-06-25 09:34:59', '2023-06-25 09:34:59', 0);
INSERT INTO `news_headline` (hid,title,article,TYPE,publisher,page_views,create_time,update_time,is_deleted) VALUES (9, 'F1加拿大大奖赛正赛：维斯塔潘冠军 阿隆索亚军', '2023年F1加拿大大奖赛正式比赛结束。红牛车队维斯塔潘杆位发车一路轻松领跑，再次完成了Pole-to-Win！这是红牛车队历史上的第100座分站冠军！同时也是维斯塔潘F1生涯的第41座分站冠军，追平了“车神”埃尔顿·塞纳的冠军数！阿斯顿马丁车队阿隆索亚军，梅赛德斯车队汉密尔顿季军。', 2, 2, 0, '2023-06-25 09:35:43', '2023-06-25 09:35:43', 0);
INSERT INTO `news_headline` (hid,title,article,TYPE,publisher,page_views,create_time,update_time,is_deleted) VALUES (10, 'CTCC绍兴柯桥站圆满落幕 张志强曹宏炜各取一冠', '6月24日，2023赛季CTCC中国汽车场地职业联赛绍兴柯桥站在雨中的浙江国际赛车场上演了两回合决赛的巅峰角逐。在线上线下观众的共同见证下，超级杯-TCR中国系列赛、运动杯-长三角赛车节联袂献上高水平对决，以精彩的比赛献礼这个端午节假期！TCR 中国系列赛第三回合于今天上午率先开战。来自壳牌捷凯领克车队的张志强穿云破雾夺得冠军；夺得该回合亚军的是驾驶新赛车出战的东风本田车手高度，季军则由Z.SPEED N车队的张臻东斩获。这也是超级杯四冠王本赛季首次登台。', 2, 2, 0, '2023-06-25 09:36:18', '2023-06-25 09:36:18', 0);
INSERT INTO `news_headline` (hid,title,article,TYPE,publisher,page_views,create_time,update_time,is_deleted) VALUES (11, '国象联赛常规赛收兵：杭州银行第一 山东成功上岸', '6月17日，“武陵山大裂谷杯”中国国际象棋甲级联赛常规赛在武陵云海国际酒店进行了最后一轮的争夺，杭州银行弈和山东队，抢到常规赛的冠军；山东队也是凭借这场平局，成功脱离保级区。本轮最大的悬念是第八名的争夺——在年底进行的甲级联赛总决赛中，前八名为上半区争冠组，保级无忧；而第九至十二名为保级区，不仅夺冠无望，还要为保级而苦战。', 2, 2, 0, '2023-06-25 09:36:51', '2023-06-25 09:36:51', 0);
INSERT INTO `news_headline` (hid,title,article,TYPE,publisher,page_views,create_time,update_time,is_deleted) VALUES (12, '围棋名宿解读高考作文:人生如棋 要先学会下“本手”', '今年高考开考了，在语文考试后，体育借势冲上了社交媒体的热搜榜。奥运相关话题进入高考，是意料之中。不过当记者看到关于围棋术语“本手、妙手和俗手”的作文命题时，着实觉得有些难。在被迅速刷屏的朋友圈里，记者感受到了很多从业者的激动、兴奋乃至油然而生的自豪感。但也有人则为那些没学过棋的孩子感到担心，这么难的题目，究竟该如何解题？\n\n　　“围棋正在深入人心。题目有些难，‘俗手’如何定义？但确实应该先下好‘本手’。”翻到中国围棋协会副主席、国家围棋队领队华学明的这条朋友圈动态时，记者瞬间觉得这道公认的难题有了解题的思路。正如高考作文材料中所说，本手是基础。只有持之以恒地打好基础，补强短板，守住不发生系统性风险的底线，才有可能在本手的基础上，下出妙手，避免俗手。而如果脱离了基础，所谓的妙手很可能就是花拳绣腿，经不起推敲，更经不起对手的冲击。世界冠军柯洁表示：“很多人在对局中经常会拘泥于局部，下出假妙手。想下出真正的妙手，必须在平日里有一定的经验积累和训练，才可能完成真正卓越的妙手。”人生如棋，棋如人生。“其实人生中大部分时间都是在下本手”，围棋名宿曹大元九段说。', 2, 2, 0, '2023-06-25 09:37:43', '2023-06-25 09:37:43', 0);
INSERT INTO `news_headline` (hid,title,article,TYPE,publisher,page_views,create_time,update_time,is_deleted) VALUES (13, '不甘人后：被生成式AI弥漫的亚马逊', '今年早些时候，随着ChatGPT席卷全球，亚马逊的经理们要求员工开动脑筋，想想如何使用人工智能（AI）聊天机器人技术来改进自家产品和工作流程。\n\n　　其中一些想法被分享在一份名为《生成式AI——ChatGPT的影响和机会分析》的内部文件中。这份文件共列了ChatGPT和类似应用程序在亚马逊多个团队中的67个潜在应用案例。\n\n　　早在20世纪90年代，亚马逊就靠在网上卖书创造了互联网界首个真正的商业奇迹。\n\n　　随后，Kindle阅读器带来革命性体验，Alexa和Echo智能音箱又带来了语音计算，而AWS则创造了云计算行业，ChatGPT就运行在这个行业之上。\n\n　　但这次热潮中拿到先发优势的是同为科技大厂的微软。微软现在是OpenAI背后的金主，且还在忙着把ChatGPT的底层技术融进微软产品和服务中。', 4, 5, 0, '2023-06-25 09:40:20', '2023-06-25 09:40:20', 0);
INSERT INTO `news_headline` (hid,title,article,TYPE,publisher,page_views,create_time,update_time,is_deleted) VALUES (14, '微创新超实用：米家旅行箱居然想到了这一点', '旅行说走就走，除非老板没安排。名义上是旅游，实则执行任务，对内讲“为公司负重前行”，对外称“带薪游山玩水”，一介打工人，两副扑克脸，个中苦乐谁人知！\n\n“差旅人”精明如我，随身携带更偏向实用。\n\n必备日用之外，能路上买的尽量不带，华而不实的东西，往包里多塞一个都算我输。行李箱尺寸自然也要浓缩到小巧但够装的20英寸，拉着轻松又顺手，常用小物件转移到背包，“轻装上战场”。', 4, 5, 0, '2023-06-25 09:41:04', '2023-06-25 09:41:04', 0);
INSERT INTO `news_headline` (hid,title,article,TYPE,publisher,page_views,create_time,update_time,is_deleted) VALUES (15, '小鹏G6动态试驾：辅助驾驶很惊喜', '这次我们开着小鹏G6上了赛道，又体验了最新版本的高速NGP和城市NGP，小鹏，还顺便测了下充电速度，那么小鹏G6驾驶感受如何？辅助驾驶表现怎么样？', 4, 5, 0, '2023-06-25 09:42:07', '2023-06-25 09:42:07', 0);
INSERT INTO `news_headline` (hid,title,article,TYPE,publisher,page_views,create_time,update_time,is_deleted) VALUES (16, '养车市场陷入低价内卷，“虎猫狗”还没等到春天', '今年“618”期间，汽车后市场的玩家们都打出了“低价牌”。比如途虎养车宣布推出“6.18全民养车季”活动，在此期间北京车主可享受“轮胎买一送一”以及多品牌轮胎降价促销的活动。\n\n　　与此同时，京东养车和天猫养车两大大厂玩家，在本次618期间也喊出了各自的营销口号。\n\n　　前者不仅喊出了“养车爱车立省不止30%”的口号，还推出了轮胎、保养买贵赔两倍、“轮胎免费装、三年无忧质保”、5公里无服务门店赔双倍安装费等举措；天猫养车的618活动，则覆盖了更大的零部件范围，比如推出了空调清洗、机油和轮胎更换等低价服务。\n\n　　这样看，在本次618期间，途虎养车、京东养车和天猫养车均贯彻着“以价换量”的战略，以至于让行业价格战一触即发。这些玩家会这样做，主要是为了与传统4S店、以及与彼此竞争，以便保证自身获得更多的市场份额。', 4, 5, 0, '2023-06-25 09:42:51', '2023-06-25 09:42:51', 0);
INSERT INTO `news_headline` (hid,title,article,TYPE,publisher,page_views,create_time,update_time,is_deleted) VALUES (17, '微软股价历史新高 聊天机器人技术潜力显现', '周四，微软股价创下历史新高，成为今年继英伟达和苹果之后，又一家市值达到新高点的大型科技公司。这家软件巨头正致力于在其产品和服务中添加生成式人工智能功能，旨在全面改造其Office产品阵容，其中包括Excel、PowerPoint、Outlook和Word等。股价上涨3.2%，收于每股348.1美元，为2021年11月19日以来的最高收盘价。自今年初起，微软股价累计上涨了45%，市值增加约8006亿美元。微软持有OpenAI的大部分股份，这家初创公司凭借聊天机器人ChatGPT引发生成式人工智能的热潮。近几个月来，该工具广受欢迎，展示了聊天机器人技术所具有的巨大潜力。微软于今年1月宣布将再向OpenAI投资100亿美元。然而有报道称，微软与OpenAI之间既有合作，亦存竞争，这种特殊的双重关系导致了双方关系的紧张和潜在冲突。科技股如英伟达等同样受益于生成式人工智能技术的应用，各公司将此技术融入各自产品，进而推动相关芯片需求。英伟达股价今年已飙升192%，被视为最大赢家。', 4, 5, 0, '2023-06-25 09:43:48', '2023-06-25 09:43:48', 0);
INSERT INTO `news_headline` (hid,title,article,TYPE,publisher,page_views,create_time,update_time,is_deleted) VALUES (18, '再获11亿美元投资：蔚来“长期主义”的底气', '如果说全系降价3万是李斌的“阳谋”，那么蔚来ET5T的发布，则是李斌的又一次诚意之作。\n\n　　ET5T是蔚来首款售价下探到30万元以下的新车，作为ET5的姐妹车型，ET5T和ET5的双车合璧，得以在30万以下快速开疆辟土。\n\n　　这样的做法有迹可循：特斯拉曾经在Model Y上实践过，并大获成功。\n\n　　Model Y和Model 3共用平台，零部件复用率高达75%，研发成本骤降。尽管Model Y最初被用户吐槽是Model 3的放大版，但不置可否的是Model Y确实解决了用户对Model 3空间不足的槽点。\n\n　　不过，最为关键的还是Model Y的价格足够低，直接降低了特斯拉的购买门槛，给那些对价格敏感，本犹豫要不要多花四五万的消费者一个充足的理由。\n\n　　蔚来ET5T正在用一种经受了市场验证过的方式，直面与特斯拉的竞争。但同时，蔚来ET5T在智能化、空间表现、设计以及产品力上，都正在接近、超越特斯拉Model Y。\n\n　　蔚来ET5T，平替特斯拉Model Y？\n\n　　小家庭，预算30万左右，消费者到底会选哪款纯电动车？\n\n　　全球市场的反馈是，特斯拉Model Y ——一款紧凑型SUV。2022年，Model Y的全球销量为74.7万辆，其在中国的销量为31.5万台，约占其全球份额的42.2%。\n\n　　按照车型大小，SUV可以分为大型、中型、小型、紧凑型四大类。按照价位，SUV又可以分为实用型、经济型、中高档型、豪华型、超豪华型等。\n\n　　Model Y 在中高端SUV的细分市场中一骑绝尘，可以说是没有对手。因为无论是奔驰EQC、宝马iX3，还是国产的比亚迪唐EV等，和Model Y相比，都不能对其构成威胁。奔驰EQC、宝马iX3这两款车型都是“油改电”，算不上真正的电动车。而比亚迪的智能化能力，远及不特斯拉，座舱、智驾上的核心模块还来自于供应商方案，并非自研。\n\n　　雷峰网认为，此前，国内的自主品牌中只有蔚来的ES6能和Model Y一较高下。不过ES6的均价比Model Y高出一大截，二者入门版之间的价差大约在10万左右。但在蔚来推出ET5T后，局势必然会发生逆转。', 4, 5, 0, '2023-06-25 09:44:20', '2023-06-25 09:44:20', 0);

-- ----------------------------
-- Table structure for news_type
-- ----------------------------
DROP TABLE IF EXISTS `news_type`;
CREATE TABLE `news_type`  (
  `tid` INT NOT NULL AUTO_INCREMENT COMMENT '新闻类型id',
  `tname` VARCHAR(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '新闻类型描述',
  `version` INT DEFAULT 1 COMMENT '乐观锁',
  `is_deleted` INT DEFAULT 0 COMMENT '头条是否被删除 1 删除  0 未删除',
  PRIMARY KEY (`tid`) USING BTREE
) ENGINE = INNODB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of news_type
-- ----------------------------
INSERT INTO `news_type` (tid,tname) VALUES (1, '新闻');
INSERT INTO `news_type` (tid,tname) VALUES (2, '体育');
INSERT INTO `news_type` (tid,tname) VALUES (3, '娱乐');
INSERT INTO `news_type` (tid,tname) VALUES (4, '科技');
INSERT INTO `news_type` (tid,tname) VALUES (5, '其他');

-- ----------------------------
-- Table structure for news_user
-- ----------------------------
DROP TABLE IF EXISTS `news_user`;
CREATE TABLE `news_user`  (
  `uid` INT NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` VARCHAR(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户登录名',
  `user_pwd` VARCHAR(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户登录密码密文',
  `nick_name` VARCHAR(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户昵称',
  `version` INT DEFAULT 1 COMMENT '乐观锁',
  `is_deleted` INT DEFAULT 0 COMMENT '头条是否被删除 1 删除  0 未删除',
  PRIMARY KEY (`uid`) USING BTREE,
  UNIQUE INDEX `username_unique`(`username`) USING BTREE
) ENGINE = INNODB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of news_user
-- ----------------------------
INSERT INTO `news_user` (uid,username,user_pwd,nick_name) VALUES (1, 'zhangsan', 'e10adc3949ba59abbe56e057f20f883e', '张三');
INSERT INTO `news_user` (uid,username,user_pwd,nick_name) VALUES (2, 'lisi', 'e10adc3949ba59abbe56e057f20f883e', '李四');
INSERT INTO `news_user` (uid,username,user_pwd,nick_name) VALUES (5, 'zhangxiaoming', 'e10adc3949ba59abbe56e057f20f883e', '张小明');
INSERT INTO `news_user` (uid,username,user_pwd,nick_name)VALUES (6, 'xiaohei', 'e10adc3949ba59abbe56e057f20f883e', '李小黑');

SET FOREIGN_KEY_CHECKS = 1;

```

## 搭建SprintBoot工程

### 创建boot工程

`springboot-headline-part`

### 导入依赖

```xml
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>3.0.5</version>
</parent>

<groupId>com.atguigu</groupId>
<artifactId>springboot-headline</artifactId>
<version>1.0-SNAPSHOT</version>

<properties>
    <maven.compiler.source>17</maven.compiler.source>
    <maven.compiler.target>17</maven.compiler.target>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
</properties>

<dependencies>

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <!-- mybatis-plus  -->
    <dependency>
        <groupId>com.baomidou</groupId>
        <artifactId>mybatis-plus-boot-starter</artifactId>
        <version>3.5.3.1</version>
    </dependency>

    <!-- 数据库相关配置启动器 -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-jdbc</artifactId>
    </dependency>

    <!-- druid启动器的依赖  -->
    <dependency>
        <groupId>com.alibaba</groupId>
        <artifactId>druid-spring-boot-3-starter</artifactId>
        <!--<version>1.2.18</version>-->
        <!-- 1.2.20版本后已兼容springboot3无需再写druid兼容springboot3文件-->
        <version>1.2.23</version>
    </dependency>

    <!-- 驱动类-->
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>8.0.28</version>
    </dependency>

    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <version>1.18.28</version>
    </dependency>

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-aop</artifactId>
    </dependency>
    
    <dependency>
       <groupId>org.springframework.boot</groupId>
       <artifactId>spring-boot-starter-test</artifactId>
       <scope>test</scope>
    </dependency>

</dependencies>
<!--    SpringBoot应用打包插件-->
<build>
    <plugins>
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
        </plugin>
    </plugins>
</build>
```

### 编写配置

`application.yaml`

```yaml
# server配置
server:
  port: 8080
  servlet:
    context-path: /

# 连接池配置
spring:
  datasource:
    type: com.alibaba.druid.pool.DruidDataSource
    druid:
      url: jdbc:mysql:///sm_db
      username: root
      password: shiguang
      driver-class-name: com.mysql.cj.jdbc.Driver

# mybatis-plus的配置
mybatis-plus:
  type-aliases-package: com.atguigu.pojo
  global-config:
    db-config:
      logic-delete-field: isDeleted  #全局逻辑删除
      id-type: auto #主键策略自增长
      table-prefix: news_ # 设置表的前缀
```

### druid兼容springboot3文件

> druid 1.2.20版本后已兼容，无需此配置

![image-20240910092434608](images/66df9fd19dd86.png)

文件名和内容
```bash
#文件名:
org.springframework.boot.autoconfigure.AutoConfiguration.imports
#内容:
com.alibaba.druid.spring.boot3.autoconfigure.DruidDataSourceAutoConfigure
```

### 启动类和mybatis-plus配置

包: `com.atguigu`
```java
@SpringBootApplication
@MapperScan("com.atguigu.mapper")
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class,args);
    }

    //配置mybatis-plus插件
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL)); //分页
        interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());  //乐观锁
        interceptor.addInnerInterceptor(new BlockAttackInnerInterceptor());  //防全局修改和删除
        return interceptor;
    }

}

```

### 工具类准备

所属包：`com.atguigu.utils`

#### 结果封装类

```java
package com.atguigu.utils;

/**
 * 全局统一返回结果类
 */
public class Result<T> {
    // 返回码
    private Integer code;
    // 返回消息
    private String message;
    // 返回数据
    private T data;
    public Result(){}
    // 返回数据
    protected static <T> Result<T> build(T data) {
        Result<T> result = new Result<T>();
        if (data != null)
            result.setData(data);
        return result;
    }
    public static <T> Result<T> build(T body, Integer code, String message) {
        Result<T> result = build(body);
        result.setCode(code);
        result.setMessage(message);
        return result;
    }
    public static <T> Result<T> build(T body, ResultCodeEnum resultCodeEnum) {
        Result<T> result = build(body);
        result.setCode(resultCodeEnum.getCode());
        result.setMessage(resultCodeEnum.getMessage());
        return result;
    }

    public static<T> Result<T> ok(T data){
        Result<T> result = build(data);
        return build(data, ResultCodeEnum.SUCCESS);
    }
    
    /**
     * 操作成功
     *
     * @param <T>
     * @param data          baseCategory1List
     * @param usernameError
     * @return
     */
    
    public static<T> Result<T> ok(T data, ResultCodeEnum usernameError){
        Result<T> result = build(data);
        return build(data, ResultCodeEnum.SUCCESS);
    }
    public Result<T> message(String msg){
        this.setMessage(msg);
        return this;
    }
    public Result<T> code(Integer code){
        this.setCode(code);
        return this;
    }
    public Integer getCode() {
        return code;
    }
    public void setCode(Integer code) {
        this.code = code;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }
}
```
#### 解决枚举类

```java
/**
 * 统一返回结果状态信息类
 *
 */
public enum ResultCodeEnum {

    SUCCESS(200,"success"),
    USERNAME_ERROR(501,"usernameError"),
    PASSWORD_ERROR(503,"passwordError"),
    NOTLOGIN(504,"notLogin"),
    USERNAME_USED(505,"userNameUsed");

    private Integer code;
    private String message;
    private ResultCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
    public Integer getCode() {
        return code;
    }
    public String getMessage() {
        return message;
    }
}

```
#### MD5加密工具类

```java
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class MD5Util {
    public static String encrypt(String strSrc) {
        try {
            char hexChars[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8',
                    '9', 'a', 'b', 'c', 'd', 'e', 'f' };
            byte[] bytes = strSrc.getBytes();
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(bytes);
            bytes = md.digest();
            int j = bytes.length;
            char[] chars = new char[j * 2];
            int k = 0;
            for (int i = 0; i < bytes.length; i++) {
                byte b = bytes[i];
                chars[k++] = hexChars[b >>> 4 & 0xf];
                chars[k++] = hexChars[b & 0xf];
            }
            return new String(chars);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new RuntimeException("MD5加密出错！！+" + e);
        }
    }
}
```

## MybatisX逆向工程

### 逆向工程

连接数据库

![image-20240910093258853](images/66dfa1c9e6883.png)

选择要连接的数据库

![image-20240910093358308](images/66dfa20551ac4.png)

选择要生成的表

![image-20240910093456543](images/66dfa23f7ca33.png)

选择项目目录，基础包路径，实体类包路径，忽略表前缀等

![image-20240910093856991](images/66dfa3300cc61.png)

选择注解及模板选择Mybatis-plus3插件，选项选择采用Lombok，Model

![image-20240910094014406](images/66dfa37d6b8a6.png)

最终生成的文件如下所示

![image-20240910094246484](images/66dfa4157891f.png)

### 完善实体类注解

删除`@TableName`注解,表名前缀，逻辑删除全局统一设置

```yaml
# mybatis-plus的配置
mybatis-plus:
  type-aliases-package: com.atguigu.pojo
  global-config:
    db-config:
      logic-delete-field: isDeleted  #全局逻辑删除
      id-type: auto #主键策略自增长
      table-prefix: news_ # 设置表的前缀
```

添加主键`@TableId`,乐观锁`@Version`注解!!!
```java
@Data
public class Type implements Serializable {
    @TableId
    private Integer tid;

    private String tname;

    @Version
    private Integer version;

    private Integer isDeleted;

    private static final long serialVersionUID = 1L;
}
```

# 后台功能开发

## 用户模块开发

### jwt和token介绍

#### token介绍

令牌（Token）：在计算机领域，令牌是一种代表某种访问权限或身份认证信息的令牌。它可以是一串随机生成的字符或数字，用于验证用户的身份或授权用户对特定资源的访问。普通的令牌可能以各种形式出现，如访问令牌、身份令牌、刷新令牌等。

![image-20240910095526061](images/66dfa70d3ec17.png)

简单理解 : 每个用户生成的唯一字符串标识,可以进行用户识别和校验

类似技术:  天王盖地虎 ,小鸡炖蘑菇

优势: token验证标识无法直接识别用户的信息,盗取token后也无法\`登录\`程序! 相对安全!

#### jwt介绍

Token是一项规范和标准(接口)

JWT(**JSON Web Token**)是具体可以生成,校验,解析等动作Token的技术(实现类)

![image-20240910095604855](images/66dfa734380fd.png)

#### jwt工作流程

-   用户提供其凭据（通常是用户名和密码）进行身份验证。
-   服务器对这些凭据进行验证，并在验证成功后创建一个JWT。
-   服务器将JWT发送给客户端，并客户端在后续的请求中将JWT附加在请求头或参数中。
-   服务器接收到请求后，验证JWT的签名和有效性，并根据JWT中的声明进行身份验证和授权操作

#### jwt数据组成和包含信息

JWT由三部分组成: `header`(头部).`payload`(载荷).`signature`(签名)

![image-20240910095729459](images/66dfa788988e9.png)

我们需要理解的是, jwt可以携带很多信息! 一般情况,需要加入:**有效时间**,**签名秘钥**,**用户标识信息**!

有效时间为了保证token的时效性,过期可以重新登录获取!

签名秘钥为了防止其他人随意解析和校验token数据!

用户信息为了我们自己解析的时候,知道Token对应的具体用户!

#### jwt使用和测试

##### 导入依赖

```xml
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt</artifactId>
    <version>0.9.1</version>
</dependency>

<dependency>
    <groupId>javax.xml.bind</groupId>
    <artifactId>jaxb-api</artifactId>
    <version>2.3.0</version>
</dependency>
```

##### 编写配置

`application.yaml`

```yaml
#jwt配置
jwt:
  token:
    tokenExpiration: 120 #有效时间,单位分钟
    tokenSignKey: headline123456  #当前程序签名秘钥 自定义
```

##### 导入工具类

封装jwt技术工具类
```java
package com.atguigu.utils;

import com.alibaba.druid.util.StringUtils;
import io.jsonwebtoken.*;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@Component
@ConfigurationProperties(prefix = "jwt.token")
public class JwtHelper {

    private  long tokenExpiration; //有效时间,单位毫秒 1000毫秒 == 1秒
    private  String tokenSignKey;  //当前程序签名秘钥

    //生成token字符串
    public  String createToken(Long userId) {
        System.out.println("tokenExpiration = " + tokenExpiration);
        System.out.println("tokenSignKey = " + tokenSignKey);
        String token = Jwts.builder()

            .setSubject("YYGH-USER")
            .setExpiration(new Date(System.currentTimeMillis() + tokenExpiration*1000*60)) //单位分钟
            .claim("userId", userId)
            .signWith(SignatureAlgorithm.HS512, tokenSignKey)
            .compressWith(CompressionCodecs.GZIP)
            .compact();
        return token;
    }

    //从token字符串获取userid
    public  Long getUserId(String token) {
        if(StringUtils.isEmpty(token)) return null;
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token);
        Claims claims = claimsJws.getBody();
        Integer userId = (Integer)claims.get("userId");
        return userId.longValue();
    }
    //判断token是否有效
    public  boolean isExpiration(String token){
        try {
            boolean isExpire = Jwts.parser()
                .setSigningKey(tokenSignKey)
                .parseClaimsJws(token)
                .getBody()
                .getExpiration().before(new Date());
            //没有过期，有效，返回false
            return isExpire;
        }catch(Exception e) {
            //过期出现异常，返回true
            return true;
        }
    }
}
```

##### 使用和测试

```java
@SpringBootTest
public class JwtTest {

    @Autowired
    private JwtHelper jwtHelper;

    @Test
    public void test(){
        //生成 传入用户标识
        String token = jwtHelper.createToken(1L);
        System.out.println("token = " + token);

        //解析用户标识
        int userId = jwtHelper.getUserId(token).intValue();
        System.out.println("userId = " + userId);

        //校验是否到期! false 未到期 true到期
        boolean expiration = jwtHelper.isExpiration(token);
        System.out.println("expiration = " + expiration);
    }

}
```
执行结果：

```bash
tokenExpiration = 120
tokenSignKey = headline123456
token = eyJhbGciOiJIUzUxMiIsInppcCI6IkdaSVAifQ.H4sIAAAAAAAA_6tWKi5NUrJSiox099ANDXYNUtJRSq0oULIyNDcytTQxMrYw1FEqLU4t8kwBitUCAEbYN_ovAAAA.ZpTMiWS2G_WOohEtj-I2gwaDGJiq9vtUsJ-z4zOyqxhNHrMSP15sEdsLlC4S0TfaMtdHWQsp4L7p_KEWKIJghg
userId = 1
expiration = false
```

### 登录功能实现

#### 需求描述

![image-20240910114119001](images/66dfbfde8fdf7.png)

用户在客户端输入用户名密码并向后端提交,后端根据用户名和密码判断登录是否成功,用户有误或者密码有误响应不同的提示信息！

#### 接口描述

url地址： user/login

请求方式：POST

请求参数：
```java
{
    "username":"zhangsan", //用户名
    "userPwd":"123456"     //明文密码
}
```
**响应数据**：

成功
```java
{
   "code":"200",         // 成功状态码 
   "message":"success"   // 成功状态描述
   "data":{
    "token":"... ..." // 用户id的token
  }
}
```
失败
```java
{
   "code":"501",
   "message":"用户名有误"
   "data":{}
}
```
```java
{
   "code":"503",
   "message":"密码有误"
   "data":{}
}
```

#### 实现代码

##### controller

```java
@RestController
@RequestMapping("user")
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 登录需求
     * 地址: /user/login
     * 方式: post
     * 参数:
     *    {
     *     "username":"zhangsan", //用户名
     *     "userPwd":"123456"     //明文密码
     *    }
     * 返回:
     *   {
     *    "code":"200",         // 成功状态码
     *    "message":"success"   // 成功状态描述
     *    "data":{
     *         "token":"... ..." // 用户id的token
     *     }
     *  }
     *
     * 大概流程:
     *    1. 账号进行数据库查询 返回用户对象
     *    2. 对比用户密码(md5加密)
     *    3. 成功,根据userId生成token -> map key=token value=token值 - result封装
     *    4. 失败,判断账号还是密码错误,封装对应的枚举错误即可
     */
    @PostMapping("login")
    public Result login(@RequestBody User user){
        Result result = userService.login(user);
        System.out.println("result = " + result);
        return result;
    }

}
```
##### service

```java
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{
    @Autowired
    private JwtHelper jwtHelper;
    @Autowired
    private  UserMapper userMapper;

    /**
     * 登录业务实现
     * @param user
     * @return result封装
     */
    @Override
    public Result login(User user) {

        //根据账号查询
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername,user.getUsername());
        User loginUser = userMapper.selectOne(queryWrapper);

        //账号判断
        if (loginUser == null) {
            //账号错误
            return Result.build(null, ResultCodeEnum.USERNAME_ERROR);
        }

        //判断密码
        if (!StringUtils.isEmpty(user.getUserPwd())
            && loginUser.getUserPwd().equals(MD5Util.encrypt(user.getUserPwd())))
        {
            //账号密码正确
            //根据用户唯一标识生成token
            String token = jwtHelper.createToken(Long.valueOf(loginUser.getUid()));

            Map data = new HashMap();
            data.put("token",token);

            return Result.ok(data);
        }

        //密码错误
        return Result.build(null,ResultCodeEnum.PASSWORD_ERROR);
    }
}
```
### 根据token获取用户数据

#### 需求描述

客户端发送请求,提交token请求头,后端根据token请求头获取登录用户的详细信息并响应给客户端进行存储

#### 接口描述

url地址：user/getUserInfo

请求方式：GET

请求头：
```java
token: token内容
```
响应数据：

成功
```java
{
    "code": 200,
    "message": "success",
    "data": {
        "loginUser": {
            "uid": 1,
            "username": "zhangsan",
            "userPwd": "",
            "nickName": "张三"
        }
    }
}
```
失败
```java
{
    "code": 504,
    "message": "notLogin",
    "data": null
}
```

#### 代码实现

##### controller

```java
/**
 * 地址: user/getUserInfo
 * 方式: get
 * 请求头: token = token内容
 * 返回:
 *    {
 *     "code": 200,
 *     "message": "success",
 *     "data": {
 *         "loginUser": {
 *             "uid": 1,
 *             "username": "zhangsan",
 *             "userPwd": "",
 *             "nickName": "张三"
 *         }
 *      }
 *   }
 *
 * 大概流程:
 *    1.获取token,解析token对应的userId
 *    2.根据userId,查询用户数据
 *    3.将用户数据的密码置空,并且把用户数据封装到结果中key = loginUser
 *    4.失败返回504 (本次先写到当前业务,后期提取到拦截器和全局异常处理器)
 */
@GetMapping("getUserInfo")
public Result userInfo(@RequestHeader String token){
    Result result = userService.getUserInfo(token);
    return result;
}
```

##### service

```java
/**
 * 查询用户数据
 * @param token
 * @return result封装
 */
@Override
public Result getUserInfo(String token) {

    //1.判定是否有效期
    if (jwtHelper.isExpiration(token)) {
        //true过期,直接返回未登录
        return Result.build(null,ResultCodeEnum.NOTLOGIN);
    }

    //2.获取token对应的用户
    int userId = jwtHelper.getUserId(token).intValue();

    //3.查询数据
    User user = userMapper.selectById(userId);

    if (user != null) {
        user.setUserPwd(null);
        Map data = new HashMap();
        data.put("loginUser",user);
        return Result.ok(data);
    }

    return Result.build(null,ResultCodeEnum.NOTLOGIN);
}
```

### 注册用户名检查

#### 需求描述

![checkuserName_RkV-nZcKMO](images/66dfc8bb68228.gif)

用户在注册时输入用户名时,立刻将用户名发送给后端,后端根据用户名查询用户名是否可用并做出响应

#### 接口描述

url地址：user/checkUserName

请求方式：POST

请求参数：param形式
```java
username=zhangsan
```
响应数据：

成功
```java
{
   "code":"200",
   "message":"success"
   "data":{}
}
```
失败
```java
{
    "code":"505",
   "message":"用户名占用"
   "data":{}
}
```

#### 代码实现

##### controller

```java
/**
 * url地址：user/checkUserName
 * 请求方式：POST
 * 请求参数：param形式
 * username=zhangsan
 * 响应数据:
 * {
 *    "code":"200",
 *    "message":"success"
 *    "data":{}
 * }
 *
 * 实现步骤:
 *   1. 获取账号数据
 *   2. 根据账号进行数据库查询
 *   3. 结果封装
 */
@PostMapping("checkUserName")
public Result checkUserName(String username){
    Result result = userService.checkUserName(username);
    return result;
}
```

##### service&#x20;

```java
/**
 * 检查账号是否可以注册
 *
 * @param username 账号信息
 * @return
 */
@Override
public Result checkUserName(String username) {

    LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
    queryWrapper.eq(User::getUsername,username);
    User user = userMapper.selectOne(queryWrapper);

    if (user != null){
        return Result.build(null,ResultCodeEnum.USERNAME_USED);
    }

    return Result.ok(null);
}
```

### 用户注册功能

#### 需求描述

![regist_HHgh8aMYtY](images/66dfce4719938.gif)

客户端将新用户信息发送给服务端,服务端将新用户存入数据库,存入之前做用户名是否被占用校验,校验通过响应成功提示,否则响应失败提示

#### 接口描述

url地址：user/regist

请求方式：POST

请求参数：
```java
{
    "username":"zhangsan",
    "userPwd":"123456", 
    "nickName":"张三"
}
```
响应数据：

成功
```java
{
   "code":"200",
   "message":"success"
   "data":{}
}
```
失败
```java
{
   "code":"505",
   "message":"用户名占用"
   "data":{}
}
```

#### 代码实现

##### controller

```java
/**
* url地址：user/regist
* 请求方式：POST
* 请求参数：
* {
*     "username":"zhangsan",
*     "userPwd":"123456",
*     "nickName":"张三"
* }
* 响应数据：
* {
*    "code":"200",
*    "message":"success"
*    "data":{}
* }
*
* 实现步骤:
*   1. 将密码加密
*   2. 将数据插入
*   3. 判断结果,成 返回200 失败 505
*/

@PostMapping("regist")
public Result regist(@RequestBody User user){
  Result result = userService.regist(user);
  return result;
}
```

##### service&#x20;

```java
@Override
public Result regist(User user) {
    LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
    queryWrapper.eq(User::getUsername,user.getUsername());
    Long count = userMapper.selectCount(queryWrapper);

    if (count > 0){
        return Result.build(null,ResultCodeEnum.USERNAME_USED);
    }

    user.setUserPwd(MD5Util.encrypt(user.getUserPwd()));
    int rows = userMapper.insert(user);
    System.out.println("rows = " + rows);
    return Result.ok(null);
}
```

## 首页模块开发

### 查询首页分类

#### 需求描述

![image-20240910124432406](images/66dfceaf49ace.png)

进入新闻首页,查询所有分类并动态展示新闻类别栏位

#### 接口描述

url地址：portal/findAllTypes

请求方式：get

请求参数：无

响应数据：

成功
```java
{
   "code":"200",
   "message":"OK"
   "data":{
            [
                {
                    "tid":"1",
                    "tname":"新闻"
                },
                {
                    "tid":"2",
                    "tname":"体育"
                },
                {
                    "tid":"3",
                    "tname":"娱乐"
                },
                {
                    "tid":"4",
                    "tname":"科技"
                },
                {
                    "tid":"5",
                    "tname":"其他"
                }
            ]
    }
}
```

#### 代码实现

##### controller

```java
@RestController
@RequestMapping("portal")
@CrossOrigin
public class PortalController {

    @Autowired
    private TypeService typeService;

    /**
     * 查询全部类别信息
     * @return
     */
    @GetMapping("findAllTypes")
    public Result findAllTypes(){
        //直接调用业务层,查询全部数据
        List<Type> list = typeService.list();
        return  Result.ok(list);
    }
}
```



### 分页查询首页头条信息

#### 需求描述

![image-20240910125800064](images/66dfd1d722074.png)
-   客户端向服务端发送查询关键字,新闻类别,页码数,页大小
-   服务端根据条件搜索分页信息,返回含页码数,页大小,总页数,总记录数,当前页数据等信息,并根据时间降序,浏览量降序排序

#### 接口描述

url地址：portal/findNewsPage

请求方式：post

请求参数:
```json
{
    "keyWords":"马斯克", // 搜索标题关键字
    "type":0,           // 新闻类型
    "pageNum":1,        // 页码数
    "pageSize":10     // 页大小
}
```
响应数据：

成功
```java
{
   "code":"200",
   "message":"success"
   "data":{
      "pageInfo":{
        "pageData":[
          {
            "hid":"1",                     // 新闻id 
            "title":"尚硅谷宣布 ... ...",   // 新闻标题
            "type":"1",                    // 新闻所属类别编号
            "pageViews":"40",              // 新闻浏览量
            "pastHours":"3" ,              // 发布时间已过小时数
            "publisher":"1"                // 发布用户ID
        },
        {
            "hid":"1",                     // 新闻id 
            "title":"尚硅谷宣布 ... ...",   // 新闻标题
            "type":"1",                    // 新闻所属类别编号
            "pageViews":"40",              // 新闻浏览量
            "pastHours":"3",              // 发布时间已过小时数
            "publisher":"1"                // 发布用户ID
        },
        {
            "hid":"1",                     // 新闻id 
            "title":"尚硅谷宣布 ... ...",   // 新闻标题
            "type":"1",                    // 新闻所属类别编号
            "pageViews":"40",              // 新闻浏览量
            "pastHours":"3",               // 发布时间已过小时数
            "publisher":"1"                // 发布用户ID
        }
        ],
      "pageNum":1,    //页码数
      "pageSize":10,  // 页大小
      "totalPage":20, // 总页数
      "totalSize":200 // 总记录数
    }
  }
}
```

#### 代码实现

##### 准备条件实体类

```java
package com.atguigu.vo;

import lombok.Data;

@Data
public class PortalVo {
    
    private String keyWords;
    private Integer type;
    private Integer pageNum = 1;
    private Integer pageSize =10;
}
```

##### controller

```java
/**
 * 首页分页查询
 * @return
 */
@PostMapping("findNewsPage")
public Result findNewPage(@RequestBody PortalVo portalVo){
    Result result = headlineService.findNewsPage(portalVo);
    return result;
}
```

##### service

```java
@Service
public class HeadlineServiceImpl extends ServiceImpl<HeadlineMapper, Headline>
    implements HeadlineService{

    @Autowired
    private HeadlineMapper headlineMapper;

    /**
     * 首页数据查询
     * @param portalVo
     * @return
     */
    @Override
    public Result findNewsPage(PortalVo portalVo) {

        //1.条件拼接 需要非空判断
        LambdaQueryWrapper<Headline> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(!StringUtils.isEmpty(portalVo.getKeyWords()),Headline::getTitle,portalVo.getKeyWords())
                .eq(portalVo.getType()!= null,Headline::getType,portalVo.getType());

        //2.分页参数
        IPage<Headline> page = new Page<>(portalVo.getPageNum(),portalVo.getPageSize());

        //3.分页查询
        //查询的结果 "pastHours":"3"   // 发布时间已过小时数 我们查询返回一个map
        //自定义方法
        headlineMapper.selectPageMap(page, portalVo);

        //4.结果封装
        //分页数据封装
        Map<String,Object> pageInfo =new HashMap<>();
        pageInfo.put("pageData",page.getRecords());
        pageInfo.put("pageNum",page.getCurrent());
        pageInfo.put("pageSize",page.getSize());
        pageInfo.put("totalPage",page.getPages());
        pageInfo.put("totalSize",page.getTotal());

        Map<String,Object> pageInfoMap=new HashMap<>();
        pageInfoMap.put("pageInfo",pageInfo);
        // 响应JSON
        return Result.ok(pageInfoMap);
    }
}
```

##### mapper

接口
```java
public interface HeadlineMapper extends BaseMapper<Headline> {

    //自定义分页查询方法
    IPage<Map> selectPageMap(IPage<Headline> page, 
                @Param("portalVo") PortalVo portalVo);
}
```
##### mapper xml

```xml
<select id="selectPageMap" resultType="map">
    select hid,title,type,page_views pageViews,TIMESTAMPDIFF(HOUR,create_time,NOW()) pastHours,
            publisher from news_headline where is_deleted=0
            <if test="portalVo.keyWords !=null and portalVo.keyWords.length()>0 ">
                and title like concat('%',#{portalVo.keyWords},'%')
            </if>
            <if test="portalVo.type != null and portalVo.type != 0">
                and type = #{portalVo.type}
            </if>
</select>
```

### 查询头条详情

## 需求描述

![image-20240910133528596](images/66dfda9fa4c42.png)
-   用户点击"查看全文"时,向服务端发送新闻id
-   后端根据新闻id查询完整新闻文章信息并返回
-   后端要同时让新闻的浏览量+1

#### 接口描述

url地址：portal/showHeadlineDetail

请求方式：post

请求参数:
```json
hid=1 param形成参数
```
响应数据：

成功
```java
{
    "code":"200",
    "message":"success",
    "data":{
        "headline":{
            "hid":"1",                     // 新闻id 
            "title":"马斯克宣布 ... ...",   // 新闻标题
            "article":"... ..."            // 新闻正文
            "type":"1",                    // 新闻所属类别编号
            "typeName":"科技",             // 新闻所属类别
            "pageViews":"40",              // 新闻浏览量
            "pastHours":"3" ,              // 发布时间已过小时数
            "publisher":"1" ,              // 发布用户ID
            "author":"张三"                 // 新闻作者
        }
    }
}
```

#### 代码实现

##### controller

```java
 /**
 * 首页详情接口
 * @param hid
 * @return
 */
@PostMapping("showHeadlineDetail")
public Result showHeadlineDetail(Integer hid){
    Result result = headlineService.showHeadlineDetail(hid);
    return result;
}
```

##### service

```java
/**
 * 详情数据查询
 * "headline":{
 * "hid":"1",                     // 新闻id
 * "title":"马斯克宣布 ... ...",   // 新闻标题
 * "article":"... ..."            // 新闻正文
 * "type":"1",                    // 新闻所属类别编号
 * "typeName":"科技",             // 新闻所属类别
 * "pageViews":"40",              // 新闻浏览量
 * "pastHours":"3" ,              // 发布时间已过小时数
 * "publisher":"1" ,              // 发布用户ID
 * "author":"张三"                 // 新闻作者
 * }
 * 注意: 是多表查询 , 需要更新浏览量+1
 *
 * @param hid
 * @return
 */
@Override
public Result showHeadlineDetail(Integer hid) {

    //1.实现根据id的查询(多表
    Map headLineDetail = headlineMapper.selectDetailMap(hid);
    //2.拼接头条对象(阅读量和version)进行数据更新
    Headline headline = new Headline();
    headline.setHid(hid);
    headline.setPageViews((Integer) headLineDetail.get("pageViews")+1); //阅读量+1
    headline.setVersion((Integer) headLineDetail.get("version")); //设置版本
    headlineMapper.updateById(headline);

    Map<String,Object> pageInfoMap=new HashMap<>();
    pageInfoMap.put("headline",headLineDetail);
    return Result.ok(pageInfoMap);
}
```

##### mapper

接口:
```java
/**
 * 分页查询头条详情
 * @param hid
 * @return
 */
Map selectDetailMap(Integer hid);
```
##### mapper xml

```xml
<!--    Map selectDetailMap(Integer hid);-->
<select id="selectDetailMap" resultType="map">
    select hid,
    title,
    article,
    type,
    h.version,
    tname                                  typeName,
    page_views                             pageViews,
    TIMESTAMPDIFF(HOUR,create_time, NOW()) pastHours,
    publisher,
    nick_name                              author
    from news_headline h
    left join news_type t on h.type = t.tid
    left join news_user u on h.publisher = u.uid
    where hid = #{hid}
</select>
```

## 头条模块开发

### 登陆验证和保护

#### 需求描述

![image-20240910134930141](images/66dfdde92e7cf.png)

客户端在进入发布页前、发布新闻前、进入修改页前、修改前、删除新闻前先向服务端发送请求携带token请求头

后端接收token请求头后,校验用户登录是否过期并做响应

前端根据响应信息提示用户进入登录页还是进入正常业务页面

#### 接口描述

url地址：user/checkLogin

请求方式：get

请求参数:  无

请求头： token: 用户token

响应数据：

未过期：
```java
{
"code":"200",
"message":"success",
"data":{}
}
```
过期：
```java
{
"code":"504",
"message":"loginExpired",
"data":{}
}
```
#### 代码实现

##### controller 【登录检查】

```java
@GetMapping("checkLogin")
public Result checkLogin(@RequestHeader String token){
    if (StringUtils.isEmpty(token) || jwtHelper.isExpiration(token)){
        //没有传或者过期 未登录
        return Result.build(null, ResultCodeEnum.NOTLOGIN);
    }

    return Result.ok(null);
}
```
##### 拦截器 【所有/headline开头都需要检查登陆】

```java
package com.atguigu.interceptors;

import com.alibaba.druid.util.StringUtils;
import com.atguigu.utils.JwtHelper;
import com.atguigu.utils.Result;
import com.atguigu.utils.ResultCodeEnum;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LoginProtectInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtHelper jwtHelper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String token = request.getHeader("token");
        if (StringUtils.isEmpty(token) || jwtHelper.isExpiration(token)) {
            Result result = Result.build(null, ResultCodeEnum.NOTLOGIN);
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(result);
            response.getWriter().print(json);
            //拦截
            return false;
        }
        //放行
        return true;
    }
}
```
##### 拦截器配置

```java
package com.atguigu.config;

import com.atguigu.interceptors.LoginProtectInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private LoginProtectInterceptor loginProtectInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginProtectInterceptor).addPathPatterns("/headline/**");
    }
}
```

### 头条发布实现

#### 需求描述

![image-20240910141908633](images/66dfe4db8034b.png)
-   用户在客户端输入发布的新闻信息完毕后
-   发布前先请求后端的登录校验接口验证登录
-   登录通过则提交新闻信息
-   后端将新闻信息存入数据库

#### 接口描述

url地址：headline/publish

请求方式：post

请求头:&#x20;
```java
token: ... ...
```
请求参数:
```json
{
"title":"尚硅谷宣布 ... ...",   // 文章标题
"article":"... ...",          // 文章内容
"type":"1"                    // 文章类别
}
```
响应数据：

未登录
```java
{
"code":"504",
"message":"loginExpired",
"data":{}
}
```
成功
```java
{
"code":"200",
"message":"success",
"data":{}
}

```
#### 代码实现

##### controller

```java
package com.atguigu.controller;

import com.atguigu.pojo.Headline;
import com.atguigu.service.HeadlineService;
import com.atguigu.utils.JwtHelper;
import com.atguigu.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created By Shiguang On 2024/9/10 14:20
 */

@RestController
@RequestMapping("headline")
@CrossOrigin
public class HeadLineController {


    @Autowired
    private JwtHelper jwtHelper;
    @Autowired
    private HeadlineService headlineService;

    /**
     * 实现步骤:
     * 1. token获取userId [无需校验,拦截器会校验]
     * 2. 封装headline数据
     * 3. 插入数据即可
     */
    @PostMapping("publish")
    public Result publish(@RequestBody Headline headline, @RequestHeader String token) {

        int userId = jwtHelper.getUserId(token).intValue();
        headline.setPublisher(userId);
        Result result = headlineService.publish(headline);
        return result;
    }
}

```
##### service

```java
/**
* 发布数据
*
* @param headline
* @return
*/
@Override
public Result publish(Headline headline) {
    headline.setCreateTime(new Date());
    headline.setUpdateTime(new Date());
    headline.setPageViews(0);
    headlineMapper.insert(headline);
    return Result.ok(null);
}
```

### 修改头条回显

#### 需求描述

![image-20240910142943855](images/66dfe7571d5a7.png)

前端先调用登录校验接口,校验登录是否过期

登录校验通过后 ,则根据新闻id查询新闻的完整信息并响应给前端

#### 接口描述

url地址：headline/findHeadlineByHid

请求方式：post

请求头： token: 用户token

请求参数:
```json
hid=1 param形成参数
```
响应数据：

成功
```java
{
    "code":"200",
    "message":"success",
    "data":{
        "headline":{
            "hid":"1",
            "title":"马斯克宣布",
            "article":"... ... ",
            "type":"2"
        }
    }
}
```

#### 代码实现

##### controller

```java
@PostMapping("findHeadlineByHid")
public Result findHeadlineByHid(Integer hid) {
    Result result = headlineService.findHeadlineByHid(hid);
    return result;
}
```
##### service

```java
/**
* 根据id查询详情
* @param hid
* @return
*/
@Override
public Result findHeadlineByHid(Integer hid) {
    Headline headline = headlineMapper.selectById(hid);
    Map<String, Object> pageInfoMap = new HashMap<>();
    pageInfoMap.put("headline", headline);
    return Result.ok(pageInfoMap);
}
```

### 头条修改实现

#### 需求描述

客户端将新闻信息修改后,提交前先请求登录校验接口校验登录状态

登录校验通过则提交修改后的新闻信息,后端接收并更新进入数据库

接口描述

url地址：headline/update

请求头： token: 用户token

请求方式：post

请求参数:
```json
{
"hid":"1",
"title":"尚硅谷宣布 ... ...",
"article":"... ...",
"type":"2"
}
```
响应数据：

成功
```java
{
"code":"200",
"message":"success",
"data":{}
}
```
#### 代码实现

##### controller

```java
@PostMapping("update")
public Result update(@RequestBody Headline headline){
    Result result = headlineService.updateHeadLine(headline);
    return result;
}
```
##### service

```java
/**
* 修改业务
* 1.查询version版本
* 2.补全属性,修改时间 , 版本!
*
* @param headline
* @return
*/
@Override
public Result updateHeadLine(Headline headline) {

    //读取版本
    Integer version = headlineMapper.selectById(headline.getHid()).getVersion();

    headline.setVersion(version);
    headline.setUpdateTime(new Date());

    headlineMapper.updateById(headline);

    return Result.ok(null);
}
```

### 删除头条功能

#### 需求描述

![image-20240910144223863](images/66dfea4ec295b.png)

将要删除的新闻id发送给服务端

服务端校验登录是否过期,未过期则直接删除,过期则响应登录过期信息

#### 接口描述

url地址：headline/removeByHid

请求方式：post

请求头： token: 用户token

请求参数:
```json
hid=1 param形成参数
```
响应数据：

成功
```java
{
    "code":"200",
    "message":"success",
    "data":{}
}
```

#### 代码实现

##### controller

```java
@PostMapping("removeByHid")
public Result removeById(Integer hid){
    headlineService.removeById(hid);
    return Result.ok(null);
}
```

# 前后端联调
