1.输入文件路径
2.输入文件名
3.输入需要分割的列号（0开始计数）
4.输入该列名称

用于分割有多值属性的字段，此字段的单值需与表中第一列personId(nconst)/titleId(tconst)有多对多关系，输出两文件
如
table1：id，attr1，attr2（attr2为目标分割字段）
输出文件为
table2：id，attr1
table3：id，attr2（多值变为单值，多对多关系，且列命名为4中输入的名称）